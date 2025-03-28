package eu.pintergabor.crusher.screen;

import eu.pintergabor.crusher.screen.base.AbstractProcessingScreenHandler;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PropertyDelegate;


public class CrusherScreenHandler extends AbstractProcessingScreenHandler {

	public CrusherScreenHandler(
		int syncId, PlayerInventory playerInventory) {
		super(
			ModScreenHandlers.CRUSHER_SCREEN_HANDLER,
			syncId,
			playerInventory);
	}

	public CrusherScreenHandler(
		int syncId, PlayerInventory playerInventory,
		Inventory inventory, PropertyDelegate propertyDelegate) {
		super(
			ModScreenHandlers.CRUSHER_SCREEN_HANDLER,
			syncId,
			playerInventory,
			inventory,
			propertyDelegate);
	}
}
