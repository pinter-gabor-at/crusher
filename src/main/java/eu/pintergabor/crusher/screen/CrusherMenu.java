package eu.pintergabor.crusher.screen;

import eu.pintergabor.crusher.screen.base.AbstractProcessingMenu;
import org.jspecify.annotations.NonNull;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;


public class CrusherMenu extends AbstractProcessingMenu {

	public CrusherMenu(
		final int containerId,
		final @NonNull Inventory playerInventory,
		final @NonNull Container container,
		final @NonNull ContainerData data
	) {
		super(
			ModScreenHandlers.CRUSHER_SCREEN_HANDLER,
			containerId,
			playerInventory,
			container,
			data
		);
	}

	public CrusherMenu(
		final int containerId,
		final @NonNull Inventory playerInventory
	) {
		super(
			ModScreenHandlers.CRUSHER_SCREEN_HANDLER,
			containerId,
			playerInventory
		);
	}
}
