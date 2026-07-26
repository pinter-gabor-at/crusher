package eu.pintergabor.crusher.blocks.base;

import eu.pintergabor.crusher.recipe.base.AbstractProcessingRecipe;
import eu.pintergabor.crusher.recipe.base.OneStackRecipeInput;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;


/**
 * Static methods of {@link AbstractProcessingBlockEntity}.
 */
public abstract sealed class StaticProcessingBlockEntity
	extends BaseContainerBlockEntity
	permits AbstractProcessingBlockEntity {

	public static final int SLOT_INPUT = 0;
	public static final int SLOT_FUEL = 1;
	public static final int SLOT_RESULT = 2;
	public static final int DATA_LIT_TIME = 0;
	public static final int DATA_LIT_DURATION = 1;
	public static final int DATA_PROGRESS = 2;
	public static final int DATA_TOTAL_TIME = 3;
	public static final int NUM_DATA_VALUES = 4;
	public static final int DEFAULT_PROCESS_TIME = 200;

	protected StaticProcessingBlockEntity(
		final @NonNull BlockEntityType<?> blockEntityType,
		final @NonNull BlockPos blockPos,
		final @NonNull BlockState blockState
	) {
		super(blockEntityType, blockPos, blockState);
	}

	public static int getTotalProcessingTime(
		final @NonNull ServerLevel level,
		final @NonNull AbstractProcessingBlockEntity processor
	) {
		OneStackRecipeInput input = new OneStackRecipeInput(processor.getItem(SLOT_INPUT));
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
			// If the output slot is empty then anything is craftable.
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
	 * @param recipe    Using this recipe
	 * @param input     To craft from this item
	 * @param inventory Inventory of the entity
	 * @param maxCount  To further limit the craftable items in the output slot
	 * @return true if the recipe is craftable,
	 * and there are enough items in the input slot,
	 * and there is enough space in the output slot for the new items.
	 */
	private static boolean canAcceptRecipeOutput(
		final @Nullable RecipeHolder<? extends AbstractProcessingRecipe> recipe,
		final @NonNull OneStackRecipeInput input,
		final @NonNull NonNullList<ItemStack> inventory,
		final int maxCount
	) {
		if (recipe != null) {
			final ItemStack inputStack = inventory.get(AbstractProcessingBlockEntity.SLOT_INPUT);
			final ItemStack outputStack = inventory.get(AbstractProcessingBlockEntity.SLOT_RESULT);
			final int inputCount = recipe.value().inputCount();
			final ItemStack resultStack = recipe.value().assemble(input);
			return canCraft(inputStack, outputStack, inputCount, resultStack, maxCount);
		}
		return false;
	}

	/**
	 * Craft new items if the recipe can be used to craft them.
	 * <p>
	 * Similar to {@link AbstractFurnaceBlockEntity}, but allows multiple input and output counts.
	 *
	 * @param recipe    Using this recipe
	 * @param input     To craft from this item
	 * @param inventory Inventory of the entity
	 * @param maxCount  To further limit the craftable items in the output slot
	 * @return true if the new items are crafted.
	 */
	private static boolean craftRecipe(
		final @Nullable RecipeHolder<? extends AbstractProcessingRecipe> recipe,
		final @NonNull OneStackRecipeInput input,
		final @NonNull NonNullList<ItemStack> inventory,
		final int maxCount
	) {
		if (recipe != null) {
			final ItemStack inputStack = inventory.get(AbstractProcessingBlockEntity.SLOT_INPUT);
			final ItemStack outputStack = inventory.get(AbstractProcessingBlockEntity.SLOT_RESULT);
			final int inputCount = recipe.value().inputCount();
			final ItemStack resultStack = recipe.value().assemble(input);
			if (canCraft(inputStack, outputStack, inputCount, resultStack, maxCount)) {
				// Craft.
				final int resultCount = resultStack.getCount();
				if (outputStack.isEmpty()) {
					// If the output slot is empty then craft it.
					inventory.set(AbstractProcessingBlockEntity.SLOT_RESULT, resultStack.copy());
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

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	public static int getProcessingTime(
		final @NonNull ServerLevel level,
		final @NonNull AbstractProcessingBlockEntity processor
	) {
		final OneStackRecipeInput oneStackRecipeInput =
			new OneStackRecipeInput(processor.getItem(AbstractProcessingBlockEntity.SLOT_INPUT));
		return processor.quickCheck
			.getRecipeFor(oneStackRecipeInput, level)
			.map(recipe ->
				recipe.value().processingTime())
			.orElse(AbstractProcessingBlockEntity.DEFAULT_PROCESS_TIME);
	}

	private static @Nullable RecipeHolder<? extends AbstractProcessingRecipe> getRecipeEntry(
		final @NonNull ServerLevel level,
		final @NonNull AbstractProcessingBlockEntity processor,
		final @NonNull OneStackRecipeInput oneStackRecipeInput
	) {
		return processor.quickCheck.getRecipeFor(oneStackRecipeInput, level).orElse(null);
	}

	/**
	 * Consume fuel and generate the remainder if needed.
	 */
	private static void consumeFuel(
		final @NonNull AbstractProcessingBlockEntity processor,
		@NonNull ItemStack fuelStack
	) {
		final ItemStackTemplate remainderStackTemplate = fuelStack.getCraftingRemainder();
		final ItemStack remainderStack = remainderStackTemplate == null ?
			ItemStack.EMPTY :
			remainderStackTemplate.create();
		fuelStack.shrink(1);
		if (fuelStack.isEmpty()) {
			processor.items.set(AbstractProcessingBlockEntity.SLOT_FUEL, remainderStack);
		}
	}

	/**
	 * Can the processor start processing?
	 *
	 * @return true if the state or appearance of the processor must be updated.
	 */
	private static boolean canStart(
		final @NonNull ServerLevel level,
		final @NonNull AbstractProcessingBlockEntity processor,
		final @NonNull ItemStack fuelStack
	) {
		processor.litTimeRemaining = processor.getBurnDuration(level.fuelValues(), fuelStack);
		processor.litTotalTime = processor.litTimeRemaining;
		// Need more fuel to continue.
		if (processor.isLit()) {
			if (!fuelStack.isEmpty()) {
				consumeFuel(processor, fuelStack);
			}
			return true;
		}
		return false;
	}

	/**
	 * Can the processor end processing and generate an output?
	 *
	 * @return true if the state or appearance of the processor must be updated.
	 */
	private static boolean canEnd(
		final @NonNull ServerLevel level,
		final @NonNull AbstractProcessingBlockEntity processor,
		final RecipeHolder<? extends AbstractProcessingRecipe> recipeEntry,
		final @NonNull OneStackRecipeInput oneStackRecipeInput
	) {
		processor.processingTimer++;
		if (processor.processingTimer == processor.processingTotalTime) {
			processor.processingTimer = 0;
			processor.processingTotalTime = getProcessingTime(level, processor);
			if (craftRecipe(
				recipeEntry,
				oneStackRecipeInput,
				processor.items,
				processor.getMaxStackSize())
			) {
				processor.setRecipeUsed(recipeEntry);
				// Special action?
				processor.crafted();
			}
			return true;
		}
		return false;
	}

	/**
	 * Start or end processing.
	 *
	 * @return true if the state or appearance of the processor must be updated.
	 */
	private static boolean canWork(
		final @NonNull ServerLevel level,
		final @NonNull AbstractProcessingBlockEntity processor,
		final ItemStack inputStack,
		final ItemStack fuelStack
	) {
		// Set to true and returned, if anything changes here.
		boolean changed = false;
		// Get the recipe.
		final OneStackRecipeInput oneStackRecipeInput = new OneStackRecipeInput(inputStack);
		final RecipeHolder<? extends AbstractProcessingRecipe> recipeEntry =
			getRecipeEntry(level, processor, oneStackRecipeInput);
		// Can the processor create an output?
		final boolean canMakeOutput = canAcceptRecipeOutput(
			recipeEntry,
			oneStackRecipeInput,
			processor.items,
			processor.getMaxStackSize());
		if (!processor.isLit() && canMakeOutput) {
			// Start processing a new input item.
			changed = canStart(level, processor, fuelStack);
		}
		if (processor.isLit() && canMakeOutput) {
			// End processing one input item and generate output.
			changed = changed || canEnd(level, processor, recipeEntry, oneStackRecipeInput);
		} else {
			processor.processingTimer = 0;
		}
		return changed;
	}

	/**
	 * Continue processing the input item.
	 */
	private static void continueWork(final @NonNull AbstractProcessingBlockEntity processor) {
		if (0 < processor.processingTimer) {
			processor.processingTimer = Mth.clamp(
				processor.processingTimer - 2, 0, processor.processingTotalTime);
		}
	}

	/**
	 * Similar to {@link AbstractFurnaceBlockEntity}, but allows multiple input and output counts.
	 */
	public static void serverTick(
		final @NonNull ServerLevel level,
		final @NonNull BlockPos pos,
		@NonNull BlockState state,
		final @NonNull AbstractProcessingBlockEntity processor
	) {
		// It is true, if the processor was working before this tick.
		final boolean wasLit = processor.isLit();
		// Set to true, if anything changes during this tick.
		boolean changed = false;
		// Count down burning time.
		if (wasLit) {
			processor.litTimeRemaining--;
		}
		// Check if starting / continuing processing is possible.
		final ItemStack inputStack = processor.items.get(AbstractProcessingBlockEntity.SLOT_INPUT);
		final ItemStack fuelStack = processor.items.get(AbstractProcessingBlockEntity.SLOT_FUEL);
		final boolean hasInput = !inputStack.isEmpty();
		final boolean hasFuel = !fuelStack.isEmpty();
		if (processor.isLit() || (hasFuel && hasInput)) {
			// Can the processor start or end processing?
			changed = canWork(level, processor, inputStack, fuelStack);
		} else {
			// Can the processor continue processing?
			continueWork(processor);
		}
		// Burning state changed.
		if (wasLit != processor.isLit()) {
			changed = true;
			state = state.setValue(AbstractFurnaceBlock.LIT, processor.isLit());
			level.setBlock(pos, state, Block.UPDATE_ALL);
		}
		// Something changed -> redraw.
		if (changed) {
			setChanged(level, pos, state);
		}
	}
}
