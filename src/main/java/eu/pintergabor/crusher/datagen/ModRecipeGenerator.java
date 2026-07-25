package eu.pintergabor.crusher.datagen;

import eu.pintergabor.crusher.blocks.ModBlocks;
import eu.pintergabor.crusher.datagen.recipebase.ProcessingRecipeGenerator;
import eu.pintergabor.crusher.datagen.recipegenerator.CompressorRecipeGenerator;
import eu.pintergabor.crusher.datagen.recipegenerator.CrusherRecipeGenerator;
import eu.pintergabor.crusher.recipe.CompressorRecipe;
import eu.pintergabor.crusher.recipe.CrusherRecipe;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;


public final class ModRecipeGenerator extends RecipeProvider {
	private final ProcessingRecipeGenerator crusherRecipe;
	private final ProcessingRecipeGenerator compressorRecipe;

	public ModRecipeGenerator(
		final HolderLookup.@NonNull Provider registries,
		final @NonNull RecipeOutput output
	) {
		super(registries, output);
		crusherRecipe = new ProcessingRecipeGenerator(
			registries, output,
			CrusherRecipe::new, "_from_crushing_"
		) {
			@Override
			public void buildRecipes() {
				// The crusher.
				buildProcessor(ModBlocks.CRUSHER_BLOCK, Items.IRON_PICKAXE);
				// The crushing recipes.
				generateCrusherRecipes();
			}
		};
		compressorRecipe = new ProcessingRecipeGenerator(
			registries, output,
			CompressorRecipe::new, "_from_compressing_"
		) {
			@Override
			public void buildRecipes() {
				// The compressor.
				buildProcessor(ModBlocks.COMPRESSOR_BLOCK, Items.PISTON);
				// The compressing recipes.
				generateCompressorRecipes();
			}
		};
	}

	@Override
	public void buildRecipes() {
		crusherRecipe.buildRecipes();
		compressorRecipe.buildRecipes();
	}

	/**
	 * Generate crusher recipes.
	 */
	private void generateCrusherRecipes() {
		CrusherRecipeGenerator.generateRecipes(crusherRecipe);
	}

	/**
	 * Generate compressor recipes.
	 */
	private void generateCompressorRecipes() {
		CompressorRecipeGenerator.generateRecipes(compressorRecipe);
	}
}
