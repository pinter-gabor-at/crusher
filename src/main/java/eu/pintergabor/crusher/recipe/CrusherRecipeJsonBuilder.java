package eu.pintergabor.crusher.recipe;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class CrusherRecipeJsonBuilder implements CraftingRecipeJsonBuilder {
    private final RecipeCategory category;
    private final CookingRecipeCategory cookingCategory;
    private final Item output;
    private final Ingredient input;
    private final float experience;
    private final int cookingTime;
    private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    private String group;
    private final AbstractCookingRecipe.RecipeFactory<?> recipeFactory;

    private CrusherRecipeJsonBuilder(
            RecipeCategory category,
            CookingRecipeCategory cookingCategory,
            ItemConvertible output,
            Ingredient input,
            float experience,
            int cookingTime,
            AbstractCookingRecipe.RecipeFactory<?> recipeFactory
    ) {
        this.category = category;
        this.cookingCategory = cookingCategory;
        this.output = output.asItem();
        this.input = input;
        this.experience = experience;
        this.cookingTime = cookingTime;
        this.recipeFactory = recipeFactory;
    }

    public static <T extends AbstractCookingRecipe> CrusherRecipeJsonBuilder create(
            Ingredient input,
            RecipeCategory category,
            ItemConvertible output,
            float experience,
            int cookingTime,
            AbstractCookingRecipe.RecipeFactory<T> recipeFactory
    ) {
        return new CrusherRecipeJsonBuilder(category, CookingRecipeCategory.MISC, output, input, experience, cookingTime, recipeFactory);
    }

    public CrusherRecipeJsonBuilder criterion(String string, AdvancementCriterion<?> advancementCriterion) {
        criteria.put(string, advancementCriterion);
        return this;
    }

    public CrusherRecipeJsonBuilder group(@Nullable String string) {
        group = string;
        return this;
    }

    @Override
    public Item getOutputItem() {
        return output;
    }

    @Override
    public void offerTo(RecipeExporter exporter, RegistryKey<Recipe<?>> recipeKey) {
        Advancement.Builder builder = exporter.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeKey))
                .rewards(AdvancementRewards.Builder.recipe(recipeKey))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        criteria.forEach(builder::criterion);
        AbstractCookingRecipe abstractCookingRecipe = recipeFactory
                .create(Objects.requireNonNullElse(group, ""), cookingCategory, input, new ItemStack(output), experience, cookingTime);
        exporter.accept(recipeKey, abstractCookingRecipe, builder.build(recipeKey.getValue().withPrefixedPath("recipes/" + category.getName() + "/")));
    }
}

