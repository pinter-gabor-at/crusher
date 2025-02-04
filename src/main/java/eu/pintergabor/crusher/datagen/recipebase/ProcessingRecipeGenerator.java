package eu.pintergabor.crusher.datagen.recipebase;

import eu.pintergabor.crusher.recipe.CompressorRecipe;
import eu.pintergabor.crusher.recipe.CrusherRecipe;
import eu.pintergabor.crusher.recipe.base.ProcessingRecipeBuilder;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;


/**
 * Generate crusher and compressor recipes.
 */
public abstract class ProcessingRecipeGenerator extends RecipeProvider {
	public float experience = 0.1F;
	public int cookingTime = 100;

	public ProcessingRecipeGenerator(HolderLookup.Provider registries, RecipeOutput output) {
		super(registries, output);
	}

	/**
	 * Create crushing recipe from input item.
	 *
	 * @param input       Input item.
	 * @param inputCount  Number of input items.
	 * @param result      Output item.
	 * @param resultCount Number of output items.
	 */
	@SuppressWarnings({"unused", "SameParameterValue"})
	protected void createCrusherRecipe(
		ItemLike input,
		int inputCount,
		ItemLike result,
		int resultCount) {
		final Ingredient ingredient = Ingredient.of(input);
		ProcessingRecipeBuilder.create(
				ingredient,
				inputCount,
				RecipeCategory.MISC,
				new ItemStack(result, resultCount),
				experience,
				cookingTime,
				CrusherRecipe::new)
			.unlockedBy(getHasName(input), has(input))
			.save(output,
				getItemName(result.asItem()) + "_from_crushing_" + getItemName(input));
	}

	/**
	 * Create crushing recipe from input item tag.
	 *
	 * @param tag         Input item tag.
	 * @param tagCount    Number of input items.
	 * @param result      Output item.
	 * @param resultCount Number of output items.
	 */
	@SuppressWarnings({"unused", "SameParameterValue"})
	protected void createCrusherRecipe(
		TagKey<Item> tag,
		int tagCount,
		ItemLike result,
		int resultCount) {
		try {
			final HolderLookup.RegistryLookup<Item> registryLookup = registries.lookupOrThrow(Registries.ITEM);
			final Ingredient ingredient = Ingredient.of(registryLookup.getOrThrow(tag));
			ProcessingRecipeBuilder.create(
					ingredient,
					tagCount,
					RecipeCategory.MISC,
					new ItemStack(result, resultCount),
					experience,
					cookingTime,
					CrusherRecipe::new)
				.unlockedBy("has_" + tag.location().getPath(), has(tag))
				.save(output,
					getItemName(result.asItem()) + "_from_crushing_" + tag.location().getPath());
		} catch (IllegalStateException e) {
			// If tag does not exist, then do not generate the recipe.
		}
	}

	/**
	 * Create compressing recipe from input item
	 *
	 * @param input       Input item
	 * @param inputCount  Number of input items
	 * @param result      Output item
	 * @param resultCount Number of output items
	 */
	@SuppressWarnings({"unused", "SameParameterValue"})
	protected void createCompressorRecipe(
		ItemLike input,
		int inputCount,
		ItemLike result,
		int resultCount) {
		final Ingredient ingredient = Ingredient.of(input);
		ProcessingRecipeBuilder.create(
				ingredient,
				inputCount,
				RecipeCategory.MISC,
				new ItemStack(result, resultCount),
				experience,
				cookingTime,
				CompressorRecipe::new)
			.unlockedBy(getHasName(input), has(input))
			.save(output,
				getItemName(result.asItem()) + "_from_compressing_" + getItemName(input));
	}

	/**
	 * Create compressing recipe from input item tag
	 *
	 * @param tag         Input item tag
	 * @param tagCount    Number of input items
	 * @param result      Output item
	 * @param resultCount Number of output items
	 */
	@SuppressWarnings({"unused", "SameParameterValue"})
	protected void createCompressorRecipe(
		TagKey<Item> tag,
		int tagCount,
		ItemLike result,
		int resultCount) {
		try {
			final HolderLookup.RegistryLookup<Item> registryLookup = registries.lookupOrThrow(Registries.ITEM);
			final Ingredient ingredient = Ingredient.of(registryLookup.getOrThrow(tag));
			ProcessingRecipeBuilder.create(
					ingredient,
					tagCount,
					RecipeCategory.MISC,
					new ItemStack(result, resultCount),
					experience,
					cookingTime,
					CompressorRecipe::new)
				.unlockedBy("has_" + tag.location().getPath(), has(tag))
				.save(output,
					getItemName(result.asItem()) + "_from_compressing_" + tag.location().getPath());
		} catch (IllegalStateException e) {
			// If tag does not exist, then do not generate the recipe.
		}
	}
}
