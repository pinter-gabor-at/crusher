package eu.pintergabor.crusher.screen;

import eu.pintergabor.crusher.Global;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;


/**
 * Screen handlers are registered similar to vanilla screen handlers in {@link MenuType}.
 */
public class ModScreenHandlers {
	public static final MenuType<CrusherMenu> CRUSHER_SCREEN_HANDLER =
		Registry.register(BuiltInRegistries.MENU, Global.modId("crusher"),
			new MenuType<>(CrusherMenu::new, FeatureFlags.VANILLA_SET));
	public static final MenuType<CompressorMenu> COMPRESSOR_SCREEN_HANDLER =
		Registry.register(BuiltInRegistries.MENU, Global.modId("compressor"),
			new MenuType<>(CompressorMenu::new, FeatureFlags.VANILLA_SET));
}
