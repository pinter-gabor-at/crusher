package eu.pintergabor.crusher.main;

import eu.pintergabor.crusher.Mod;
import eu.pintergabor.crusher.blocks.ModBlocks;
import eu.pintergabor.crusher.blocks.ModStats;
import eu.pintergabor.crusher.recipe.CompressorRecipe;
import eu.pintergabor.crusher.recipe.CrusherRecipe;


public final class Main {

	/**
	 * Called from {@link Mod#onInitialize()}
	 */
	public static void init() {
		// Items and blocks.
		ModBlocks.init();
		// Recipes.
		CrusherRecipe.register();
		CompressorRecipe.register();
		// Statistics.
		ModStats.init();
	}
}
