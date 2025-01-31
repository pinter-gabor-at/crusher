package eu.pintergabor.crusher.screen.base;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.FurnaceFuelSlot;
import net.minecraft.screen.slot.Slot;

/**
 * Similar to {@link FurnaceFuelSlot},
 * but without special handling for buckets.
 */
public class ProcessingFuelSlot extends Slot {
    private final AbstractProcessingScreenHandler handler;

    public ProcessingFuelSlot(AbstractProcessingScreenHandler handler, Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.handler = handler;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return handler.isFuel(stack);
    }
}
