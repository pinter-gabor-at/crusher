package eu.pintergabor.crusher.recipe.base;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;

import org.jspecify.annotations.NonNull;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

import org.jspecify.annotations.Nullable;


/**
 * Similar to {@link SimpleCookingRecipeBuilder},
 * but with {@link ItemStack} output and without the campfire stuff.
 */
public class ProcessingRecipeBuilder implements RecipeBuilder {
	private final Ingredient ingredient;
	private final int ingredientCount;
	private final ItemStackTemplate result;
	private final float experience;
	private final int cookingTime;
	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
	private final AbstractProcessingRecipe.Factory<?> factory;

	private ProcessingRecipeBuilder(
		@NonNull Ingredient ingredient,
		int ingredientCount,
		@NonNull ItemStackTemplate result,
		float experience,
		int cookingTime,
		AbstractProcessingRecipe.@NonNull Factory<?> recipeFactory
	) {
		this.ingredient = ingredient;
		this.ingredientCount = ingredientCount;
		this.result = result;
		this.experience = experience;
		this.cookingTime = cookingTime;
		this.factory = recipeFactory;
	}

	public static <T extends AbstractProcessingRecipe> @NonNull ProcessingRecipeBuilder create(
		@NonNull Ingredient ingredient,
		int ingregientCount,
		@NonNull ItemStackTemplate output,
		float experience,
		int cookingTime,
		AbstractProcessingRecipe.Factory<T> factory
	) {
		return new ProcessingRecipeBuilder(
			ingredient,
			ingregientCount,
			output,
			experience,
			cookingTime,
			factory
		);
	}

	public @NonNull ProcessingRecipeBuilder unlockedBy(
		@NonNull String string, @NonNull Criterion<?> advancementCriterion
	) {
		criteria.put(string, advancementCriterion);
		return this;
	}

	@Override
	public @NonNull RecipeBuilder group(@Nullable String group) {
		return this;
	}

	@Override
	public @NonNull ResourceKey<Recipe<?>> defaultId() {
		return RecipeBuilder.getDefaultRecipeId(result);
	}

	@Override
	public void save(
		final @NonNull RecipeOutput output,
		final @NonNull ResourceKey<Recipe<?>> id
	) {
		Advancement.Builder builder = output.advancement()
			.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
			.rewards(AdvancementRewards.Builder.recipe(id))
			.requirements(AdvancementRequirements.Strategy.OR);
		criteria.forEach(builder::addCriterion);
		AbstractProcessingRecipe abstractProcessingRecipe = factory
			.create(
				ingredient,
				ingredientCount,
				result,
				experience,
				cookingTime
			);
		output.accept(
			id,
			abstractProcessingRecipe,
			builder.build(
				id.identifier()
					.withPrefix("recipes/" + RecipeCategory.MISC.getFolderName() + "/")
			)
		);
	}
}
