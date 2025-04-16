package eu.pintergabor.crusher.screen;

import java.util.function.Supplier;

import eu.pintergabor.crusher.main.Main;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;


/**
 * Screen handlers are registered similar to vanilla screen handlers in {@link MenuType}.
 */
public final class ModScreenHandlers {
	public static final Supplier<MenuType<CrusherMenu>> CRUSHER_SCREEN_HANDLER =
		Main.MENU_TYPES.register("crusher", () ->
			new MenuType<>(CrusherMenu::new, FeatureFlags.VANILLA_SET));
	public static final Supplier<MenuType<CompressorMenu>> COMPRESSOR_SCREEN_HANDLER =
		Main.MENU_TYPES.register("compressor", () ->
			new MenuType<>(CompressorMenu::new, FeatureFlags.VANILLA_SET));

	private ModScreenHandlers() {
		// Static class.
	}

	public static void init(IEventBus modEventBus) {
		// Everything has been done by static initializers.
	}

	public static void listener(RegisterMenuScreensEvent event) {
		event.register(CRUSHER_SCREEN_HANDLER.get(), CrusherScreen::new);
		event.register(COMPRESSOR_SCREEN_HANDLER.get(), CompressorScreen::new);
	}
}
