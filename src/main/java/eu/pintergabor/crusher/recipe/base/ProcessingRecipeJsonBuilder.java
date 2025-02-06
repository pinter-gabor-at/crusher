package eu.pintergabor.crusher.recipe.base;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Similar to {@link CookingRecipeJsonBuilder},
 * but with {@link ItemStack} output and without the campfire stuff.
 */
public class ProcessingRecipeJsonBuilder implements CraftingRecipeJsonBuilder {
    private final RecipeCategory category;
    private final CookingRecipeCategory cookingCategory;
    private final ItemStack output;
    private final Ingredient ingredient;
    private final int ingregientCount;
    private final float experience;
    private final int cookingTime;
    private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    private String group;
    private final AbstractProcessingRecipe.RecipeFactory<?> recipeFactory;

    private ProcessingRecipeJsonBuilder(
            RecipeCategory category,
            CookingRecipeCategory cookingCategory,
            ItemStack output,
            Ingredient ingredient,
            int ingregientCount,
            float experience,
            int cookingTime,
            AbstractProcessingRecipe.RecipeFactory<?> recipeFactory
    ) {
        this.category = category;
        this.cookingCategory = cookingCategory;
        this.output = output;
        this.ingredient = ingredient;
        this.ingregientCount = ingregientCount;
        this.experience = experience;
        this.cookingTime = cookingTime;
        this.recipeFactory = recipeFactory;
    }

    public static <T extends AbstractProcessingRecipe> ProcessingRecipeJsonBuilder create(
            Ingredient ingredient,
            int ingregientCount,
            RecipeCategory category,
            ItemStack output,
            float experience,
            int cookingTime,
            AbstractProcessingRecipe.RecipeFactory<T> recipeFactory
    ) {
        return new ProcessingRecipeJsonBuilder(
                category,
                CookingRecipeCategory.MISC,
                output,
                ingredient,
                ingregientCount,
                experience,
                cookingTime,
                recipeFactory);
    }

    public ProcessingRecipeJsonBuilder criterion(
            String string, AdvancementCriterion<?> advancementCriterion) {
        criteria.put(string, advancementCriterion);
        return this;
    }

    public ProcessingRecipeJsonBuilder group(@Nullable String string) {
        group = string;
        return this;
    }

    @Override
    public Item getOutputItem() {
        return output.getItem();
    }

    @Override
    public void offerTo(RecipeExporter exporter, RegistryKey<Recipe<?>> recipeKey) {
        Advancement.Builder builder = exporter.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeKey))
                .rewards(AdvancementRewards.Builder.recipe(recipeKey))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        criteria.forEach(builder::criterion);
        AbstractProcessingRecipe abstractProcessingRecipe = recipeFactory
                .create(
                        Objects.requireNonNullElse(group, ""),
                        cookingCategory,
                        ingredient,
                        ingregientCount,
                        output,
                        experience,
                        cookingTime);
        exporter.accept(
                recipeKey,
                abstractProcessingRecipe,
                builder.build(
                        recipeKey
                                .getValue()
                                .withPrefixedPath("recipes/" + category.getName() + "/")));
    }
}

