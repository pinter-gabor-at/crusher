package eu.pintergabor.crusher.screen;

import eu.pintergabor.crusher.screen.base.AbstractProcessingMenu;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;


public class CompressorMenu extends AbstractProcessingMenu {

	public CompressorMenu(
		int containerId, Inventory playerInventory,
		Container container, ContainerData data
	) {
		super(
			ModScreenHandlers.COMPRESSOR_SCREEN_HANDLER,
			containerId,
			playerInventory,
			container,
			data);
	}

	public CompressorMenu(
		int conteinerId, Inventory playerInventory
	) {
		super(
			ModScreenHandlers.COMPRESSOR_SCREEN_HANDLER,
			conteinerId,
			playerInventory);
	}
}
