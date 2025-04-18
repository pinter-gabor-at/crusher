package eu.pintergabor.crusher.screen.base;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.FurnaceFuelSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;


/**
 * Similar to {@link FurnaceFuelSlot},
 * but without special handling for buckets.
 */
public class ProcessingFuelSlot extends Slot {
	private final AbstractProcessingMenu menu;

	public ProcessingFuelSlot(
		AbstractProcessingMenu menu, Container container,
		int slot, int x, int y
	) {
		super(container, slot, x, y);
		this.menu = menu;
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return menu.isFuel(stack);
	}
}
