package eu.pintergabor.crusher.rei;

import eu.pintergabor.crusher.blocks.ModBlocks;
import eu.pintergabor.crusher.screen.CompressorScreen;
import eu.pintergabor.crusher.screen.CrusherScreen;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import org.jspecify.annotations.NonNull;


public final class ModReiClient implements REIClientPlugin {

	@Override
	public void registerCategories(@NonNull CategoryRegistry registry) {
		registry.add(new CrusherCategory());
		registry.addWorkstations(ModReiCommon.CRUSHER, EntryStacks.of(ModBlocks.CRUSHER_ITEM));
		registry.add(new CompressorCategory());
		registry.addWorkstations(ModReiCommon.COMPRESSOR, EntryStacks.of(ModBlocks.COMPRESSOR_ITEM));
	}

	@Override
	public void registerScreens(@NonNull ScreenRegistry registry) {
		registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 78,
				((screen.height - 166) / 2) + 30, 20, 25),
			CrusherScreen.class, ModReiCommon.CRUSHER);
		registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 78,
				((screen.height - 166) / 2) + 30, 20, 25),
			CompressorScreen.class, ModReiCommon.COMPRESSOR);
	}
}
