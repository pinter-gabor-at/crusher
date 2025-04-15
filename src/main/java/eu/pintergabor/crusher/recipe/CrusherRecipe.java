package eu.pintergabor.crusher.recipe;

import java.util.function.Supplier;

import eu.pintergabor.crusher.blocks.ModBlocks;
import eu.pintergabor.crusher.main.Main;
import eu.pintergabor.crusher.recipe.base.AbstractProcessingRecipe;
import org.jetbrains.annotations.NotNull;

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
public class CrusherRecipe extends AbstractProcessingRecipe {
	public static final Supplier<RecipeBookCategory> CATEGORY =
		Main.RECIPE_BOOK_CATEGORIES.register(
			"crusher", RecipeBookCategory::new);
	public static Supplier<RecipeType<CrusherRecipe>> TYPE =
		Main.RECIPE_TYPES.register("crushing", id ->
			new RecipeType<>() {
				@Override
				public String toString() {
					return id.toString();
				}
			});
	public static Supplier<RecipeSerializer<CrusherRecipe>> SERIALIZER =
		Main.RECIPE_SERIALIZERS.register("crushing", () ->
			new Serializer<>(CrusherRecipe::new));

	public CrusherRecipe(
		String group,
		CookingBookCategory category,
		Ingredient ingredient,
		int ingredientCount,
		ItemStack result,
		float experience,
		int cookingTime) {
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
		return ModBlocks.CRUSHER_ITEM.get();
	}

	@Override
	public @NotNull RecipeSerializer<? extends AbstractProcessingRecipe> getSerializer() {
		return SERIALIZER.get();
	}

	@Override
	public @NotNull RecipeType<? extends AbstractProcessingRecipe> getType() {
		return TYPE.get();
	}

	@Override
	public @NotNull RecipeBookCategory recipeBookCategory() {
		return CATEGORY.get();
	}

	/**
	 * Extra initialization.
	 */
	public static void init() {
		// Everything has been done by static initializers.
	}
}
