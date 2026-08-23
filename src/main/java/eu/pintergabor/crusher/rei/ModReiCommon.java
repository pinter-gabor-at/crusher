package eu.pintergabor.crusher.rei;

import eu.pintergabor.crusher.Global;
import eu.pintergabor.crusher.recipe.CompressorRecipe;
import eu.pintergabor.crusher.recipe.CrusherRecipe;
import eu.pintergabor.crusher.rei.display.CompressorDisplay;
import eu.pintergabor.crusher.rei.display.CrusherDisplay;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REICommonPlugin;
import me.shedaniel.rei.api.common.registry.display.ServerDisplayRegistry;
import org.jspecify.annotations.NonNull;


/**
 * See <a href="https://github.com/shedaniel/RoughlyEnoughItems/wiki">REI Wiki</a>
 */
public final class ModReiCommon implements REICommonPlugin {
	public static final CategoryIdentifier<CrusherDisplay> CRUSHER =
		CategoryIdentifier.of(Global.modName("crusher"));
	public static final CategoryIdentifier<CompressorDisplay> COMPRESSOR =
		CategoryIdentifier.of(Global.modName("compressor"));

	/**
	 * See <a href="https://www.craft.do/s/qj8mHyTVd7qkOZ/b/B540A417-4D23-46E9-99AC-B52AD9B0EC28/Display-Serializers-(Server)">Wiki: registerDisplaySerializer</a>
	 */
	@Override
	public void registerDisplaySerializer(final @NonNull DisplaySerializerRegistry registry) {
		registry.register(CRUSHER.getIdentifier(), CrusherDisplay.SERIALIZER);
		registry.register(COMPRESSOR.getIdentifier(), CompressorDisplay.SERIALIZER);
	}

	@Override
	public void registerDisplays(final @NonNull ServerDisplayRegistry registry) {
		registry.beginRecipeFiller(CrusherRecipe.class).fill(CrusherDisplay::new);
		registry.beginRecipeFiller(CompressorRecipe.class).fill(CompressorDisplay::new);
	}
}
