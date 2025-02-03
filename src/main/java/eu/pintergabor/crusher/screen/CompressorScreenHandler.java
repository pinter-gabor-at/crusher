package eu.pintergabor.crusher.screen;

import eu.pintergabor.crusher.screen.base.AbstractProcessingScreenHandler;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PropertyDelegate;

public class CompressorScreenHandler extends AbstractProcessingScreenHandler {
    public CompressorScreenHandler(
            int syncId, PlayerInventory playerInventory) {
        super(
                ModScreenHandlers.COMPRESSOR_SCREEN_HANDLER,
                syncId,
                playerInventory);
    }

    public CompressorScreenHandler(
            int syncId, PlayerInventory playerInventory,
            Inventory inventory, PropertyDelegate propertyDelegate) {
        super(
                ModScreenHandlers.COMPRESSOR_SCREEN_HANDLER,
                syncId,
                playerInventory,
                inventory,
                propertyDelegate);
    }
}