package eu.pintergabor.crusher.rei;

import eu.pintergabor.crusher.blocks.ModBlocks;
import eu.pintergabor.crusher.rei.category.CompressorCategory;
import eu.pintergabor.crusher.rei.category.CrusherCategory;
import eu.pintergabor.crusher.screen.CompressorScreen;
import eu.pintergabor.crusher.screen.CrusherScreen;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.forge.REIPluginClient;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

import net.minecraft.client.gui.screens.Screen;


/**
 * See <a href="https://github.com/shedaniel/RoughlyEnoughItems/wiki">REI Wiki</a>
 */
@REIPluginClient
public final class ModReiClient implements REIClientPlugin {

	/**
	 * Define the click area.
	 */
	@Contract(value = "_ -> new", pure = true)
	private static <T extends Screen> @NonNull Rectangle getClickArea(
		final @NonNull T screen
	) {
		return new Rectangle(((screen.width - 176) / 2) + 78,
			((screen.height - 166) / 2) + 30, 20, 25);
	}

	/**
	 * See <a href="https://www.craft.do/s/qj8mHyTVd7qkOZ/b/7B02E98B-421E-41C2-958F-A9FD700BE6B6/Categories-(Client)">Wiki: registerCategories</a>
	 */
	@Override
	public void registerCategories(final @NonNull CategoryRegistry registry) {
		registry.add(new CrusherCategory());
		registry.addWorkstations(ModReiCommon.CRUSHER, EntryStacks.of(ModBlocks.CRUSHER_ITEM));
		registry.add(new CompressorCategory());
		registry.addWorkstations(ModReiCommon.COMPRESSOR, EntryStacks.of(ModBlocks.COMPRESSOR_ITEM));
	}

	@Override
	public void registerScreens(final @NonNull ScreenRegistry registry) {
		registry.registerClickArea(ModReiClient::getClickArea,
			CrusherScreen.class, ModReiCommon.CRUSHER);
		registry.registerClickArea(ModReiClient::getClickArea,
			CompressorScreen.class, ModReiCommon.COMPRESSOR);
	}

}
