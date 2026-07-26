package eu.pintergabor.crusher.datagen.recipebase;

import eu.pintergabor.crusher.Global;
import eu.pintergabor.crusher.recipe.CompressorRecipe;
import eu.pintergabor.crusher.recipe.CrusherRecipe;
import eu.pintergabor.crusher.recipe.base.AbstractProcessingRecipe;
import eu.pintergabor.crusher.recipe.base.ProcessingRecipeBuilder;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.WeatheringCopperItems;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;


/**
 * Generate crusher and compressor recipes.
 */
public abstract class ProcessingRecipeGenerator extends RecipeProvider {
	public float experience = 0.1F;
	public int processingTime = 100;

	public ProcessingRecipeGenerator(HolderLookup.Provider registries, RecipeOutput output) {
		super(registries, output);
	}

	/**
	 * Create a crushing or a compressing recipe from an input item.
	 *
	 * @param input       Input item.
	 * @param inputCount  Number of input items.
	 * @param result      Output item.
	 * @param resultCount Number of output items.
	 * @param factory     Recipe generator.
	 * @param from        "_from_crushing_" or "_from_compressing_"
	 */
	private <T extends AbstractProcessingRecipe> void createRecipe(
		final @NonNull ItemLike input, final int inputCount,
		final @NonNull ItemLike result, int resultCount,
		final AbstractProcessingRecipe.@NonNull Factory<T> factory,
		final @NonNull String from
	) {
		final Ingredient ingredient = Ingredient.of(input);
		final String recipeName = Global.modName(
			getItemName(result.asItem()) + from + getItemName(input));
		ProcessingRecipeBuilder.create(
				ingredient,
				inputCount,
				new ItemStackTemplate(result.asItem(), resultCount),
				experience,
				processingTime,
				factory
			)
			.unlockedBy(getHasName(input), has(input))
			.save(output, recipeName);
	}

	/**
	 * Create crushing recipe from an input item.
	 *
	 * @param input       Input item.
	 * @param inputCount  Number of input items.
	 * @param result      Output item.
	 * @param resultCount Number of output items.
	 */
	@SuppressWarnings({"unused", "SameParameterValue"})
	protected void createCrusherRecipe(
		final @NonNull ItemLike input, final int inputCount,
		final @NonNull ItemLike result, final int resultCount
	) {
		createRecipe(
			input, inputCount,
			result, resultCount,
			CrusherRecipe::new, "_from_crushing_"
		);
	}

	/**
	 * Create compressing recipe from an input item.
	 *
	 * @param input       Input item.
	 * @param inputCount  Number of input items.
	 * @param result      Output item.
	 * @param resultCount Number of output items.
	 */
	@SuppressWarnings({"unused", "SameParameterValue"})
	protected void createCompressorRecipe(
		final @NonNull ItemLike input, final int inputCount,
		final @NonNull ItemLike result, final int resultCount
	) {
		createRecipe(
			input, inputCount,
			result, resultCount,
			CompressorRecipe::new, "_from_compressing_"
		);
	}

	/**
	 * Create a crushing or a compressing recipe from an input item tag.
	 *
	 * @param tag         Input item tag.
	 * @param tagCount    Number of input items.
	 * @param result      Output item.
	 * @param resultCount Number of output items.
	 * @param factory     Recipe generator.
	 * @param from        "_from_crushing_" or "_from_compressing_"
	 */
	private <T extends AbstractProcessingRecipe> void createRecipe(
		final @NonNull TagKey<Item> tag, final int tagCount,
		final @NonNull ItemLike result, final int resultCount,
		AbstractProcessingRecipe.@NonNull Factory<T> factory,
		final @NonNull String from
	) {
		try {
			final HolderLookup.RegistryLookup<Item> registryLookup =
				registries.lookupOrThrow(Registries.ITEM);
			final Ingredient ingredient = Ingredient.of(registryLookup.getOrThrow(tag));
			final String recipeName = Global.modName(
				getItemName(result.asItem()) + from + tag.location().getPath());
			ProcessingRecipeBuilder.create(
					ingredient,
					tagCount,
					new ItemStackTemplate(result.asItem(), resultCount),
					experience,
					processingTime,
					factory
				)
				.unlockedBy("has_" + tag.location().getPath(), has(tag))
				.save(output, recipeName);
		} catch (IllegalStateException e) {
			// If the tag does not exist, then do not generate the recipe.
		}
	}

	/**
	 * Create crushing recipe from an input item tag.
	 *
	 * @param tag         Input item tag.
	 * @param tagCount    Number of input items.
	 * @param result      Output item.
	 * @param resultCount Number of output items.
	 */
	@SuppressWarnings({"unused", "SameParameterValue"})
	protected void createCrusherRecipe(
		final @NonNull TagKey<Item> tag, final int tagCount,
		final @NonNull ItemLike result, final int resultCount
	) {
		createRecipe(
			tag, tagCount,
			result, resultCount,
			CrusherRecipe::new,
			"_from_crushing_"
		);
	}

	/**
	 * Create compressing recipe from an input item tag.
	 *
	 * @param tag         Input item tag.
	 * @param tagCount    Number of input items.
	 * @param result      Output item.
	 * @param resultCount Number of output items.
	 */
	@SuppressWarnings({"unused", "SameParameterValue"})
	protected void createCompressorRecipe(
		final @NonNull TagKey<Item> tag, final int tagCount,
		final @NonNull ItemLike result, final int resultCount
	) {
		createRecipe(
			tag, tagCount,
			result, resultCount,
			CompressorRecipe::new,
			"_from_compressing_"
		);
	}

	/**
	 * Create crushing recipe from a wheathering copper input item.
	 *
	 * @param copperItem  Input item.
	 * @param inputCount  Number of input items.
	 * @param result      Output item.
	 * @param resultCount Number of output items.
	 */
	@SuppressWarnings({"unused", "SameParameterValue"})
	protected void createCrusherRecipe(
		final @NonNull WeatheringCopperItems copperItem, final int inputCount,
		final @NonNull ItemLike result, final int resultCount
	) {
		copperItem.forEach(item ->
			createCrusherRecipe(item, inputCount, result, resultCount));
	}
}
