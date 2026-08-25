package eu.pintergabor.crusher.datagen.recipebase;

import eu.pintergabor.crusher.Global;
import eu.pintergabor.crusher.recipe.base.AbstractProcessingRecipe;
import eu.pintergabor.crusher.recipe.base.ProcessingRecipeBuilder;

import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ColorCollection;
import net.minecraft.world.level.block.WeatheringCopperCollection;


/**
 * Generate crusher and compressor recipes.
 */
public abstract class ProcessingRecipeGenerator extends RecipeProvider {
	protected float experience = 0.1F;
	protected int processingTime = 100;
	private final AbstractProcessingRecipe.Factory<AbstractProcessingRecipe> factory;
	private final String from;

	/**
	 * @param factory {@code CrusherRecipe::new} or {@code CompressorRecipe::new}.
	 * @param from    {@code "_from_crushing_"} or {@code "_from_compressing_"}.
	 */
	public ProcessingRecipeGenerator(
		final HolderLookup.@NonNull Provider registries,
		final @NonNull RecipeOutput output,
		final AbstractProcessingRecipe.@NonNull Factory<AbstractProcessingRecipe> factory,
		final @NonNull String from
	) {
		super(registries, output);
		this.factory = factory;
		this.from = from;
	}

	/**
	 * Set experience and processing time for all receipes generated afterward.
	 */
	public void setParam(final float experience, final int processingTime) {
		this.experience = experience;
		this.processingTime = processingTime;
	}

	/**
	 * Generate processing machine recipes.
	 *
	 * @param block    Processor block to create.
	 * @param mainItem Main ingredient.
	 */
	protected void buildProcessor(
		final @NonNull Block block,
		final @NonNull Item mainItem
	) {
		shaped(RecipeCategory.DECORATIONS, block)
			.pattern("###")
			.pattern("P P")
			.pattern("###")
			.define('#', ItemTags.STONE_CRAFTING_MATERIALS)
			.define('P', mainItem)
			.unlockedBy("has_cobblestone", has(ItemTags.STONE_CRAFTING_MATERIALS))
			.unlockedBy(getHasName(mainItem), has(mainItem))
			.save(output);
	}

	/**
	 * Create a processing recipe from an input item.
	 *
	 * @param input       Input item.
	 * @param inputCount  Number of input items.
	 * @param result      Output item.
	 * @param resultCount Number of output items.
	 */
	public void createRecipe(
		final @NonNull ItemLike input, final int inputCount,
		final @NonNull ItemLike result, int resultCount
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
	 * Create a processing recipe from an input item tag.
	 *
	 * @param tag         Input item tag.
	 * @param tagCount    Number of input items.
	 * @param result      Output item.
	 * @param resultCount Number of output items.
	 */
	public void createRecipe(
		final @NonNull TagKey<Item> tag, final int tagCount,
		final @NonNull ItemLike result, final int resultCount
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
	 * Create a processing recipe from a wheathering copper input item.
	 *
	 * @param copperItem  Input item.
	 * @param inputCount  Number of input items.
	 * @param result      Output item.
	 * @param resultCount Number of output items.
	 */
	@SuppressWarnings({"unused", "SameParameterValue"})
	public void createRecipe(
		final @NonNull WeatheringCopperCollection<Item> copperItem, final int inputCount,
		final @NonNull ItemLike result, final int resultCount
	) {
		copperItem.forEach(item ->
			createRecipe(item, inputCount, result, resultCount));
	}

	/**
	 * Create crushing recipe from a dyed input item.
	 *
	 * @param dyedItem    Input item.
	 * @param inputCount  Number of input items.
	 * @param result      Output item.
	 * @param resultCount Number of output items.
	 */
	@SuppressWarnings({"unused", "SameParameterValue"})
	public void createRecipe(
		final @NonNull ColorCollection<Item> dyedItem, final int inputCount,
		final @NonNull ItemLike result, final int resultCount
	) {
		dyedItem.forEach(item ->
			createRecipe(item, inputCount, result, resultCount));
	}


}
