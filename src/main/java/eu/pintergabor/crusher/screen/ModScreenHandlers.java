package eu.pintergabor.crusher.screen;

import java.util.function.Supplier;

import eu.pintergabor.crusher.Global;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;


/**
 * Screen handlers are registered similar to vanilla screen handlers in {@link MenuType}.
 */
public class ModScreenHandlers {
	private static final DeferredRegister<MenuType<?>> REGISTER =
		DeferredRegister.create(Registries.MENU, Global.MODID);
	public static final Supplier<MenuType<CrusherMenu>> CRUSHER_SCREEN_HANDLER =
		REGISTER.register("crusher", () ->
			new MenuType<>(CrusherMenu::new, FeatureFlags.VANILLA_SET));
	public static final Supplier<MenuType<CompressorMenu>> COMPRESSOR_SCREEN_HANDLER =
		REGISTER.register("compressor", () ->
			new MenuType<>(CompressorMenu::new, FeatureFlags.VANILLA_SET));

	public static void initMenu(IEventBus modEventBus) {
		modEventBus.register(REGISTER);
	}

	public static void initScreen(RegisterMenuScreensEvent event) {
		event.register(CRUSHER_SCREEN_HANDLER.get(), CrusherScreen::new);
		event.register(COMPRESSOR_SCREEN_HANDLER.get(), CompressorScreen::new);
	}
}
