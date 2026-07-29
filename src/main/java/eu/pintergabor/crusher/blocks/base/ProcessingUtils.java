package eu.pintergabor.crusher.blocks.base;

import static eu.pintergabor.crusher.blocks.base.LitUtils.isLit;
import static eu.pintergabor.crusher.blocks.base.LitUtils.refuel;
import static eu.pintergabor.crusher.blocks.base.StaticProcessingBlockEntity.*;

import eu.pintergabor.crusher.recipe.base.AbstractProcessingRecipe;
import eu.pintergabor.crusher.recipe.base.OneStackRecipeInput;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;


/**
 * Processing related static methods of {@link AbstractProcessingBlockEntity}.
 */
public final class ProcessingUtils {

	private ProcessingUtils() {
		// Static class.
	}

	public static int getProcessingTotalTime(
		final @NonNull ServerLevel level,
		final @NonNull AbstractProcessingBlockEntity processor
	) {
		final OneStackRecipeInput input = new OneStackRecipeInput(processor.getItem(SLOT_INPUT));
		return processor.quickCheck.getRecipeFor(input, level)
			.map((recipeHolder) ->
				(recipeHolder.value()).processingTime()).orElse(DEFAULT_PROCESS_TIME);
	}

	/**
	 * Check if there are enough items in the input slot,
	 * and enough space in the output slot to craft new items.
	 *
	 * @param inputStack  {@link ItemStack} in the input slot.
	 * @param outputStack {@link ItemStack} in the output slot.
	 * @param inputCount  Number of items needed to craft {@code resultStack}.
	 * @param resultStack {@link ItemStack} that will be crafted.
	 * @param maxCount    Optional to further limit the max size of the new {@code outputStack}.
	 * @return true if nothing prevents crafting.
	 */
	private static boolean canCraft(
		final @NonNull ItemStack inputStack,
		final @NonNull ItemStack outputStack,
		final int inputCount,
		final @NonNull ItemStack resultStack,
		final int maxCount
	) {
		if (inputStack.isEmpty() || inputStack.getCount() < inputCount) {
			// If the input slot does not contain enough items for crafting.
			return false;
		}
		// If the input slot contains enough items for crafting.
		if (resultStack.isEmpty()) {
			// If there is no valid recipe, then there is no valid result.
			return false;
		}
		// The number of crafting result items.
		final int resultCount = resultStack.getCount();
		if (outputStack.isEmpty()) {
			// If the output slot is empty, then anything is craftable.
			return true;
		}
		if (!ItemStack.isSameItemSameComponents(outputStack, resultStack)) {
			// If the output slot contains incompatible items then the new item is not craftable.
			return false;
		}
		// If there is enough space for the new items, then they are craftable.
		final int outputCount = outputStack.getCount() + resultCount;
		return ((outputCount <= maxCount) && (outputCount <= outputStack.getMaxStackSize())) ||
			(outputCount <= resultStack.getMaxStackSize());
	}

	/**
	 * Check if a recipe can be used to craft new items.
	 * <p>
	 * Similar to {@link AbstractFurnaceBlockEntity}, but allows multiple input counts.
	 *
	 * @param recipe    Using this recipe.
	 * @param input     To craft from this item.
	 * @param inventory Inventory of the processing entity.
	 * @param maxCount  To further limit the craftable items in the output slot
	 * @return true if the recipe is craftable,
	 * and there are enough items in the input slot,
	 * and there is enough space in the output slot for the new items.
	 */
	private static boolean canCraftRecipe(
		final @Nullable RecipeHolder<? extends AbstractProcessingRecipe> recipe,
		final @NonNull OneStackRecipeInput input,
		final @NonNull NonNullList<ItemStack> inventory,
		final int maxCount
	) {
		if (recipe != null) {
			// Get the inputs and outputs of the recipe.
			final ItemStack inputStack = inventory.get(SLOT_INPUT);
			final ItemStack outputStack = inventory.get(SLOT_RESULT);
			final int inputCount = recipe.value().inputCount();
			final ItemStack resultStack = recipe.value().assemble(input);
			// Check if there are enough items in the input slot,
			// and enough space in the output slot to craft new items using this recipe.
			return canCraft(inputStack, outputStack, inputCount, resultStack, maxCount);
		}
		return false;
	}

	/**
	 * Craft new items if the recipe can be used to craft them.
	 * <p>
	 * Similar to {@link AbstractFurnaceBlockEntity}, but allows multiple input and output counts.
	 *
	 * @param recipe    Using this recipe.
	 * @param input     To craft from this item.
	 * @param inventory Inventory of the entity.
	 * @param maxCount  To further limit the craftable items in the output slot.
	 * @return true if the new items are crafted.
	 */
	private static boolean craftRecipe(
		final @Nullable RecipeHolder<? extends AbstractProcessingRecipe> recipe,
		final @NonNull OneStackRecipeInput input,
		final @NonNull NonNullList<ItemStack> inventory,
		final int maxCount
	) {
		if (recipe != null) {
			// Get the inputs and outputs of the recipe.
			final ItemStack inputStack = inventory.get(SLOT_INPUT);
			final ItemStack outputStack = inventory.get(SLOT_RESULT);
			final int inputCount = recipe.value().inputCount();
			final ItemStack resultStack = recipe.value().assemble(input);
			// Check if there are enough items in the input slot,
			// and enough space in the output slot to craft new items using this recipe.
			if (canCraft(inputStack, outputStack, inputCount, resultStack, maxCount)) {
				// Craft.
				final int resultCount = resultStack.getCount();
				if (outputStack.isEmpty()) {
					// If the output slot is empty, then craft it.
					inventory.set(SLOT_RESULT, resultStack.copy());
				} else {
					// Else increment the item count in the output slot.
					outputStack.grow(resultCount);
				}
				// Use up the needed amount of input items.
				inputStack.shrink(inputCount);
				return true;
			}
		}
		return false;
	}

	private static @Nullable RecipeHolder<? extends AbstractProcessingRecipe> getRecipe(
		final @NonNull ServerLevel level,
		final @NonNull AbstractProcessingBlockEntity processor,
		final @NonNull OneStackRecipeInput input
	) {
		return processor.quickCheck.getRecipeFor(input, level).orElse(null);
	}

	/**
	 * Can the processor end processing and generate an output?
	 *
	 * @return true if the state or appearance of the processor must be updated.
	 */
	private static boolean tickForwardProcessing(
		final @NonNull ServerLevel level,
		final @NonNull AbstractProcessingBlockEntity processor,
		final RecipeHolder<? extends AbstractProcessingRecipe> recipe,
		final @NonNull OneStackRecipeInput input
	) {
		// Count the time.
		processor.processingTimer++;
		if (processor.processingTimer == processor.processingTotalTime) {
			// At the end of the processing.
			// Reload the processing timer.
			processor.processingTimer = 0;
			processor.processingTotalTime = getProcessingTotalTime(level, processor);
			// Craft the output item.
			// (At this point it is known to be craftable.)
			if (craftRecipe(
				recipe,
				input,
				processor.items,
				processor.getMaxStackSize())
			) {
				// Record that the recipe was successfully used.
				processor.setRecipeUsed(recipe);
				// Special action?
				processor.crafted();
			}
			return true;
		}
		return false;
	}

	/**
	 * Roll back the processing timer slowly.
	 *
	 * @return true if the state or appearance of the processor must be updated.
	 */
	private static boolean tickBackwardProcessing(final @NonNull AbstractProcessingBlockEntity processor) {
		if (0 < processor.processingTimer) {
			processor.processingTimer--;
			return true;
		}
		return false;
	}

	/**
	 * Start or end processing.
	 *
	 * @return true if the state or appearance of the processor must be updated.
	 */
	public static boolean tickProcessing(
		final @NonNull ServerLevel level,
		final @NonNull AbstractProcessingBlockEntity processor
	) {
		// Set to true and returned, if anything changes here.
		boolean changed = false;
		// Get the recipe.
		final OneStackRecipeInput input = new OneStackRecipeInput(processor.getItem(SLOT_INPUT));
		final RecipeHolder<? extends AbstractProcessingRecipe> recipe =
			getRecipe(level, processor, input);
		// Can the processor use this recipe?
		final boolean canCraftRecipe = canCraftRecipe(
			recipe,
			input,
			processor.items,
			processor.getMaxStackSize());
		if (canCraftRecipe) {
			// Start burning one more fuel item to continue processing if needed.
			changed = refuel(level, processor);
		}
		if (canCraftRecipe && isLit(processor)) {
			// The recipe is usable and the processor is working.
			// Check if the processing has ended and generate the output.
			changed = tickForwardProcessing(level, processor, recipe, input) || changed;
		} else {
			// Roll back the processing timer slowly, because
			// not all conditions are met to continue processing.
			changed = tickBackwardProcessing(processor) || changed;
		}
		return changed;
	}


}
