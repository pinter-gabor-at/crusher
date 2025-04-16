package eu.pintergabor.crusher.screen;

import eu.pintergabor.crusher.screen.base.AbstractProcessingMenu;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;


public class CrusherMenu extends AbstractProcessingMenu {

	public CrusherMenu(
		int containerId, Inventory playerInventory,
		Container container, ContainerData data
	) {
		super(
			ModScreenHandlers.CRUSHER_SCREEN_HANDLER.get(),
			containerId,
			playerInventory,
			container,
			data);
	}

	public CrusherMenu(
		int containerId, Inventory playerInventory
	) {
		super(
			ModScreenHandlers.CRUSHER_SCREEN_HANDLER.get(),
			containerId,
			playerInventory);
	}
}
