package eu.pintergabor.crusher.screen;

import eu.pintergabor.crusher.Global;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;


public class ModScreenHandlers {
	public static final ScreenHandlerType<CrusherScreenHandler> CRUSHER_SCREEN_HANDLER =
		Registry.register(Registries.SCREEN_HANDLER, Global.modId("crusher"),
			new ScreenHandlerType<>(CrusherScreenHandler::new, FeatureFlags.VANILLA_FEATURES));
	public static final ScreenHandlerType<CompressorScreenHandler> COMPRESSOR_SCREEN_HANDLER =
		Registry.register(Registries.SCREEN_HANDLER, Global.modId("compressor"),
			new ScreenHandlerType<>(CompressorScreenHandler::new, FeatureFlags.VANILLA_FEATURES));
}
