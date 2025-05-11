package eu.pintergabor.crusher.blocks.base;

import eu.pintergabor.crusher.recipe.base.AbstractProcessingRecipe;
import eu.pintergabor.crusher.recipe.base.OneStackRecipeInput;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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

	protected StaticProcessingBlockEntity(
		BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState
	) {
		super(blockEntityType, blockPos, blockState);
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
	static boolean canCraft(
		ItemStack inputStack,
		ItemStack outputStack,
		int inputCount,
		ItemStack resultStack,
		int maxCount
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
	 * @param registryAccess Lookup table
	 * @param recipe         Using this recipe
	 * @param input          To craft from this item
	 * @param inventory      Inventory of the entity
	 * @param maxCount       To further limit the craftable items in the output slot
	 * @return true if the recipe is craftable,
	 * and there are enough items in the input slot,
	 * and there is enough space in the output slot for the new items.
	 */
	public static boolean canAcceptRecipeOutput(
		RegistryAccess registryAccess,
		@Nullable RecipeHolder<? extends AbstractProcessingRecipe> recipe,
		OneStackRecipeInput input,
		NonNullList<ItemStack> inventory,
		int maxCount
	) {
		if (recipe != null) {
			final ItemStack inputStack = inventory.get(AbstractProcessingBlockEntity.INPUT_SLOT_INDEX);
			final ItemStack outputStack = inventory.get(AbstractProcessingBlockEntity.OUTPUT_SLOT_INDEX);
			final int inputCount = recipe.value().inputCount();
			final ItemStack resultStack = recipe.value().assemble(input, registryAccess);
			return canCraft(inputStack, outputStack, inputCount, resultStack, maxCount);
		}
		return false;
	}

	/**
	 * Craft new items if the recipe can be used to craft them.
	 * <p>
	 * Similar to {@link AbstractFurnaceBlockEntity}, but allows multiple input and output counts.
	 *
	 * @param dynamicRegistryManager Lookup table
	 * @param recipe                 Using this recipe
	 * @param input                  To craft from this item
	 * @param inventory              Inventory of the entity
	 * @param maxCount               To further limit the craftable items in the output slot
	 * @return true if the new items are crafted.
	 */
	private static boolean craftRecipe(
		RegistryAccess dynamicRegistryManager,
		@Nullable RecipeHolder<? extends AbstractProcessingRecipe> recipe,
		OneStackRecipeInput input,
		NonNullList<ItemStack> inventory,
		int maxCount
	) {
		if (recipe != null) {
			final ItemStack inputStack = inventory.get(AbstractProcessingBlockEntity.INPUT_SLOT_INDEX);
			final ItemStack outputStack = inventory.get(AbstractProcessingBlockEntity.OUTPUT_SLOT_INDEX);
			final int inputCount = recipe.value().inputCount();
			final ItemStack resultStack = recipe.value().assemble(input, dynamicRegistryManager);
			if (canCraft(inputStack, outputStack, inputCount, resultStack, maxCount)) {
				// Craft.
				final int resultCount = resultStack.getCount();
				if (outputStack.isEmpty()) {
					// If the output slot is empty then craft it.
					inventory.set(AbstractProcessingBlockEntity.OUTPUT_SLOT_INDEX, resultStack.copy());
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
	static int getCookTime(
		ServerLevel level, AbstractProcessingBlockEntity processor
	) {
		final OneStackRecipeInput oneStackRecipeInput =
			new OneStackRecipeInput(processor.getItem(AbstractProcessingBlockEntity.INPUT_SLOT_INDEX));
		return processor.matchGetter
			.getRecipeFor(oneStackRecipeInput, level)
			.map(recipe ->
				recipe.value().cookingTime())
			.orElse(AbstractProcessingBlockEntity.DEFAULT_COOK_TIME);
	}

	/**
	 * Similar to {@link AbstractFurnaceBlockEntity}, but allows multiple input and output counts.
	 */
	public static void serverTick(
		ServerLevel level, BlockPos pos, BlockState state, AbstractProcessingBlockEntity processor
	) {
		final boolean burning = processor.isBurning();
		boolean changed = false;
		// Count down burning time.
		if (burning) {
			processor.litTimeRemaining--;
		}
		// Check if starting / continuing processing is possible.
		final ItemStack inputStack = processor.inventory.get(AbstractProcessingBlockEntity.INPUT_SLOT_INDEX);
		final ItemStack fuelStack = processor.inventory.get(AbstractProcessingBlockEntity.FUEL_SLOT_INDEX);
		final boolean hasInput = !inputStack.isEmpty();
		final boolean hasFuel = !fuelStack.isEmpty();
		if (processor.isBurning() || (hasFuel && hasInput)) {
			// Get the recipe.
			final OneStackRecipeInput oneStackRecipeInput = new OneStackRecipeInput(inputStack);
			RecipeHolder<? extends AbstractProcessingRecipe> recipeEntry;
			if (hasInput) {
				recipeEntry = processor.matchGetter.getRecipeFor(oneStackRecipeInput, level).orElse(null);
			} else {
				recipeEntry = null;
			}
			// Start processing a new input item.
			final int maxCount = processor.getMaxStackSize();
			if (!processor.isBurning() &&
				canAcceptRecipeOutput(
					level.registryAccess(),
					recipeEntry,
					oneStackRecipeInput,
					processor.inventory,
					maxCount)) {
				processor.litTimeRemaining = processor.getFuelTime(level.fuelValues(), fuelStack);
				processor.litTotalTime = processor.litTimeRemaining;
				// Need more fuel to continue.
				if (processor.isBurning()) {
					changed = true;
					if (hasFuel) {
						final Item item = fuelStack.getItem();
						fuelStack.shrink(1);
						if (fuelStack.isEmpty()) {
							processor.inventory.set(AbstractProcessingBlockEntity.FUEL_SLOT_INDEX, item.getCraftingRemainder());
						}
					}
				}
			}
			// End processing one input item and generate output.
			if (processor.isBurning() &&
				canAcceptRecipeOutput(
					level.registryAccess(),
					recipeEntry,
					oneStackRecipeInput,
					processor.inventory,
					maxCount)) {
				processor.cookingTimer++;
				if (processor.cookingTimer == processor.cookingTotalTime) {
					processor.cookingTimer = 0;
					processor.cookingTotalTime = getCookTime(level, processor);
					if (craftRecipe(
						level.registryAccess(),
						recipeEntry,
						oneStackRecipeInput,
						processor.inventory,
						maxCount)) {
						processor.setRecipeUsed(recipeEntry);
						// Special action?
						processor.crafted();
					}
					changed = true;
				}
			} else {
				processor.cookingTimer = 0;
			}
		} else {
			// Continue processing the input item.
			if (!processor.isBurning() && processor.cookingTimer > 0) {
				processor.cookingTimer = Mth.clamp(
					processor.cookingTimer - 2, 0, processor.cookingTotalTime);
			}
		}
		// Burning state changed.
		if (burning != processor.isBurning()) {
			changed = true;
			state = state.setValue(AbstractFurnaceBlock.LIT, processor.isBurning());
			level.setBlock(pos, state, Block.UPDATE_ALL);
		}
		// Something changed -> redraw.
		if (changed) {
			setChanged(level, pos, state);
		}
	}
}
