package eu.pintergabor.crusher.recipe.base;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;


/**
 * Similar to {@link SimpleCookingRecipeBuilder},
 * but with {@link ItemStack} output and without the campfire stuff.
 */
public class ProcessingRecipeBuilder implements RecipeBuilder {
	private final RecipeCategory category;
	private final CookingBookCategory cookingCategory;
	private final ItemStack result;
	private final Ingredient ingredient;
	private final int ingregientCount;
	private final float experience;
	private final int cookingTime;
	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
	@Nullable
	private String group;
	private final AbstractProcessingRecipe.RecipeFactory<?> recipeFactory;

	private ProcessingRecipeBuilder(
		RecipeCategory category,
		CookingBookCategory cookingCategory,
		ItemStack result,
		Ingredient ingredient,
		int ingregientCount,
		float experience,
		int cookingTime,
		AbstractProcessingRecipe.RecipeFactory<?> recipeFactory
	) {
		this.category = category;
		this.cookingCategory = cookingCategory;
		this.result = result;
		this.ingredient = ingredient;
		this.ingregientCount = ingregientCount;
		this.experience = experience;
		this.cookingTime = cookingTime;
		this.recipeFactory = recipeFactory;
	}

	public static <T extends AbstractProcessingRecipe> ProcessingRecipeBuilder create(
		Ingredient ingredient,
		int ingregientCount,
		RecipeCategory category,
		ItemStack output,
		float experience,
		int cookingTime,
		AbstractProcessingRecipe.RecipeFactory<T> recipeFactory
	) {
		return new ProcessingRecipeBuilder(
			category,
			CookingBookCategory.MISC,
			output,
			ingredient,
			ingregientCount,
			experience,
			cookingTime,
			recipeFactory);
	}

	public @NotNull ProcessingRecipeBuilder unlockedBy(
		@NotNull String string, @NotNull Criterion<?> advancementCriterion) {
		criteria.put(string, advancementCriterion);
		return this;
	}

	public @NotNull ProcessingRecipeBuilder group(@Nullable String string) {
		group = string;
		return this;
	}

	@Override
	public @NotNull Item getResult() {
		return result.getItem();
	}

	@Override
	public void save(RecipeOutput output, @NotNull ResourceKey<Recipe<?>> resourceKey) {
		Advancement.Builder builder = output.advancement()
			.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceKey))
			.rewards(AdvancementRewards.Builder.recipe(resourceKey))
			.requirements(AdvancementRequirements.Strategy.OR);
		criteria.forEach(builder::addCriterion);
		AbstractProcessingRecipe abstractProcessingRecipe = recipeFactory
			.create(
				Objects.requireNonNullElse(group, ""),
				cookingCategory,
				ingredient,
				ingregientCount,
				result,
				experience,
				cookingTime);
		output.accept(
			resourceKey,
			abstractProcessingRecipe,
			builder.build(resourceKey.location()
				.withPrefix("recipes/" + category.getFolderName() + "/")));
	}
}
