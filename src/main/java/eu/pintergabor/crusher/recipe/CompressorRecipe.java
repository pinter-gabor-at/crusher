package eu.pintergabor.crusher.recipe;

import eu.pintergabor.crusher.blocks.ModBlocks;
import eu.pintergabor.crusher.recipe.base.AbstractProcessingRecipe;
import org.jetbrains.annotations.NotNull;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;


/**
 * Similar to {@link SmeltingRecipe},
 * but with unique serializer, type and category.
 */
public class CompressorRecipe extends AbstractProcessingRecipe {
	public static RecipeSerializer<AbstractProcessingRecipe> SERIALIZER;
	public static RecipeType<AbstractProcessingRecipe> TYPE;
	public static RecipeBookCategory CATEGORY;

	public CompressorRecipe(
		String group,
		CookingBookCategory category,
		Ingredient ingredient,
		int ingredientCount,
		ItemStack result,
		float experience,
		int cookingTime
	) {
		super(
			group,
			category,
			ingredient,
			ingredientCount,
			result,
			experience,
			cookingTime);
	}

	@Override
	protected Item getProcessorItem() {
		return ModBlocks.COMPRESOR_ITEM;
	}

	@Override
	public @NotNull RecipeSerializer<? extends AbstractProcessingRecipe> getSerializer() {
		return SERIALIZER;
	}

	@Override
	public @NotNull RecipeType<? extends AbstractProcessingRecipe> getType() {
		return TYPE;
	}

	@Override
	public @NotNull RecipeBookCategory recipeBookCategory() {
		return CATEGORY;
	}

	/**
	 * Register unique serializer, type and category.
	 * <p>
	 * See {@link RecipeSerializer}, {@link RecipeType} and {@link RecipeBookCategories} for examples.
	 */
	public static void register() {
		SERIALIZER =
			RecipeSerializer.register(
				"compressing",
				new Serializer<>(CompressorRecipe::new, 100));
		TYPE =
			RecipeType.register("compressing");
		CATEGORY =
			Registry.register(
				BuiltInRegistries.RECIPE_BOOK_CATEGORY,
				"compressor",
				new RecipeBookCategory());
	}
}
