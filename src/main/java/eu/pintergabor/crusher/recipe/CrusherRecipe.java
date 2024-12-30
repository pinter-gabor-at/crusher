package eu.pintergabor.crusher.recipe;

import eu.pintergabor.crusher.blocks.ModBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class CrusherRecipe extends AbstractCookingRecipe {
    public static RecipeSerializer<CrusherRecipe> CRUSHER_SERIALIZER;
    public static RecipeType<CrusherRecipe> CRUSHER_TYPE;
    public static RecipeBookCategory CRUSHER_CATEGORY;

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
    public RecipeSerializer<? extends AbstractCookingRecipe> getSerializer() {
        return CRUSHER_SERIALIZER;
    }

    @Override
    public RecipeType<? extends AbstractCookingRecipe> getType() {
        return CRUSHER_TYPE;
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return CRUSHER_CATEGORY;
    }

    @Override
    protected Item getCookerItem() {
        return ModBlocks.CRUSHER_ITEM;
    }

    @Override
    public ItemStack result() {
        return super.result();
    }

    public static void register() {
        CRUSHER_SERIALIZER =
                RecipeSerializer.register(
                        "crusher",
                        new AbstractCookingRecipe.Serializer<>(CrusherRecipe::new, 100));
        CRUSHER_TYPE =
                RecipeType.register("crusher");
        CRUSHER_CATEGORY =
                Registry.register(
                        Registries.RECIPE_BOOK_CATEGORY,
                        "crusher",
                        new RecipeBookCategory());
    }
}