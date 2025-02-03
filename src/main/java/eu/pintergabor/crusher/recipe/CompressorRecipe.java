package eu.pintergabor.crusher.recipe;

import eu.pintergabor.crusher.blocks.ModBlocks;
import eu.pintergabor.crusher.recipe.base.AbstractProcessingRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.recipe.book.RecipeBookCategories;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

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
            CookingRecipeCategory category,
            Ingredient ingredient,
            ItemStack result,
            float experience,
            int cookingTime) {
        super(group, category, ingredient, result, experience, cookingTime);
    }

    @Override
    protected Item getCookerItem() {
        return ModBlocks.CRUSHER_ITEM;
    }

    @Override
    public RecipeSerializer<? extends AbstractCookingRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<? extends AbstractCookingRecipe> getType() {
        return TYPE;
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
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
                        Registries.RECIPE_BOOK_CATEGORY,
                        "compressor",
                        new RecipeBookCategory());
    }
}