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
public class CrusherRecipe extends AbstractProcessingRecipe {

    public CrusherRecipe(
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

    /**
     * Register unique serializer, type and category.
     * <p>
     * See {@link RecipeSerializer}, {@link RecipeType} and {@link RecipeBookCategories} for examples.
     */
    public static void register() {
        CRUSHER_SERIALIZER =
                RecipeSerializer.register(
                        "crushing",
                        new Serializer<>(CrusherRecipe::new, 100));
        CRUSHER_TYPE =
                RecipeType.register("crushing");
        CRUSHER_CATEGORY =
                Registry.register(
                        Registries.RECIPE_BOOK_CATEGORY,
                        "crusher",
                        new RecipeBookCategory());
    }
}