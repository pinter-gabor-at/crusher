package eu.pintergabor.crusher.screen.base;

import static eu.pintergabor.crusher.blocks.base.AbstractProcessingBlockEntity.*;

import java.util.List;

import eu.pintergabor.crusher.recipe.base.AbstractProcessingRecipe;
import eu.pintergabor.crusher.recipe.base.OneStackRecipeInput;
import org.jspecify.annotations.NonNull;

import net.minecraft.recipebook.ServerPlaceRecipe;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;


/**
 * Similar to {@link AbstractFurnaceMenu}.
 */
public class AbstractProcessingMenu extends RecipeBookMenu {
	final Container container;
	private final ContainerData data;
	protected final Level level;

	protected AbstractProcessingMenu(
		final @NonNull MenuType<?> menuType,
		final int containerId,
		final @NonNull Inventory playerInventory,
		final @NonNull Container container,
		final @NonNull ContainerData data
	) {
		super(menuType, containerId);
		checkContainerSize(container, 3);
		checkContainerDataCount(data, PROPERTY_COUNT);
		this.container = container;
		this.data = data;
		this.level = playerInventory.player.level();
		this.addSlot(new Slot(container,
			INPUT_SLOT_INDEX, 56, 17));
		this.addSlot(new ProcessingFuelSlot(this, container,
			FUEL_SLOT_INDEX, 56, 53));
		this.addSlot(new ProcessingResultSlot(playerInventory.player, container,
			OUTPUT_SLOT_INDEX, 116, 35));
		this.addStandardInventorySlots(playerInventory, 8, 84);
		this.addDataSlots(data);
	}

	protected AbstractProcessingMenu(
		final @NonNull MenuType<?> menuType,
		final int syncId,
		final @NonNull Inventory playerInventory
	) {
		this(
			menuType,
			syncId,
			playerInventory,
			new SimpleContainer(3),
			new SimpleContainerData(PROPERTY_COUNT)
		);
	}

	public void fillCraftSlotsStackedContents(final @NonNull StackedItemContents contents) {
		if (container instanceof StackedContentsCompatible inputProvider) {
			inputProvider.fillStackedContents(contents);
		}
	}

	public @NonNull Slot getResultSlot() {
		return slots.get(OUTPUT_SLOT_INDEX);
	}

	@Override
	public boolean stillValid(final @NonNull Player player) {
		return container.stillValid(player);
	}

	public @NonNull ItemStack quickMoveStack(final @NonNull Player player, final int slot) {
		final Slot clickSlot = slots.get(slot);
		if (clickSlot.hasItem()) {
			final ItemStack clickItemStack = clickSlot.getItem();
			final ItemStack returnItemStack = clickItemStack.copy();
			if (slot == OUTPUT_SLOT_INDEX) {
				// From output slot to inventory.
				if (!moveItemStackTo(clickItemStack,
					3, 39, true)) {
					return ItemStack.EMPTY;
				}
				clickSlot.onQuickCraft(clickItemStack, returnItemStack);
			} else if (slot == FUEL_SLOT_INDEX || slot == INPUT_SLOT_INDEX) {
				// From fuel, or input slot to inventory.
				if (!moveItemStackTo(clickItemStack,
					3, 39, false)) {
					return ItemStack.EMPTY;
				}
			} else {
				// From elsewhere, if it is fuel, to the fuel slot.
				if (isFuel(clickItemStack)) {
					if (!moveItemStackTo(clickItemStack,
						FUEL_SLOT_INDEX, FUEL_SLOT_INDEX + 1, false)) {
						return ItemStack.EMPTY;
					}
				} else {
					// From elsewhere to the input slot.
					if (!moveItemStackTo(clickItemStack,
						INPUT_SLOT_INDEX, INPUT_SLOT_INDEX + 1, false)) {
						return ItemStack.EMPTY;
					}
				}
			}
			if (clickItemStack.isEmpty()) {
				clickSlot.setByPlayer(ItemStack.EMPTY);
			} else {
				clickSlot.setChanged();
			}
			if (clickItemStack.getCount() == returnItemStack.getCount()) {
				return ItemStack.EMPTY;
			}
			clickSlot.onTake(player, clickItemStack);
			return returnItemStack;
		}
		return ItemStack.EMPTY;
	}

	protected boolean isFuel(final @NonNull ItemStack item) {
		return level.fuelValues().isFuel(item);
	}

	/**
	 * Calculate cooking progress
	 *
	 * @return Progress (0.0 ... 1.0)
	 */
	public float getBurnProgress() {
		final int progress = data.get(PROCESS_TIME_PROPERTY_INDEX);
		final int total = data.get(PROCESS_TIME_TOTAL_PROPERTY_INDEX);
		return total != 0 ?
			Mth.clamp((float) progress / (float) total, 0F, 1F) :
			0.0f;
	}

	/**
	 * Calculate fuel consumption progress
	 *
	 * @return Progress (0.0 ... 1.0)
	 */
	public float getLitProgress() {
		int total = data.get(FUEL_TIME_PROPERTY_INDEX);
		if (total == 0) {
			total = DEFAULT_PROCESS_TIME;
		}
		final int progress = data.get(BURN_TIME_PROPERTY_INDEX);
		return Mth.clamp((float) progress / (float) total, 0F, 1F);
	}

	public boolean isLit() {
		return 0 < data.get(BURN_TIME_PROPERTY_INDEX);
	}

	@Override
	public @NonNull RecipeBookType getRecipeBookType() {
		return RecipeBookType.FURNACE;
	}

	@SuppressWarnings("unchecked")
	public @NonNull PostPlaceAction handlePlacement(
		final boolean craftAll,
		final boolean creative,
		final @NonNull RecipeHolder<?> recipe,
		final @NonNull ServerLevel level,
		final @NonNull Inventory inventory
	) {
		final List<Slot> list = List.of(getSlot(INPUT_SLOT_INDEX), getSlot(OUTPUT_SLOT_INDEX));
		AbstractProcessingMenu parent = this;
		return ServerPlaceRecipe.placeRecipe(
			new ServerPlaceRecipe.CraftingMenuAccess<>() {
				public void fillCraftSlotsStackedContents(@NonNull StackedItemContents contents) {
					parent.fillCraftSlotsStackedContents(contents);
				}

				@Override
				public void clearCraftingContent() {
					list.forEach(slot -> slot.set(ItemStack.EMPTY));
				}

				@Override
				public boolean recipeMatches(@NonNull RecipeHolder<AbstractProcessingRecipe> recipe) {
					if (container instanceof Inventory parentInventory) {
						return recipe.value().matches(
							new OneStackRecipeInput(parentInventory.getItem(INPUT_SLOT_INDEX)), level);
					}
					return false;
				}
			},
			1,
			1,
			List.of(getSlot(INPUT_SLOT_INDEX)),
			list,
			inventory,
			(RecipeHolder<AbstractProcessingRecipe>) recipe,
			craftAll,
			creative
		);
	}
}
