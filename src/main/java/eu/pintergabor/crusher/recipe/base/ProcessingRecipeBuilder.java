package eu.pintergabor.crusher.recipe.base;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;


/**
 * Similar to {@link SimpleCookingRecipeBuilder},
 * but with {@link ItemStack} output and without the campfire stuff.
 */
public class ProcessingRecipeBuilder implements RecipeBuilder {
	private final Ingredient ingredient;
	private final int ingredientCount;
	private final ItemStackTemplate result;
	private final float experience;
	private final int processingTime;
	private final RecipeUnlockAdvancementBuilder advancementBuilder;
	private final AbstractProcessingRecipe.Factory<?> factory;

	private ProcessingRecipeBuilder(
		@NonNull Ingredient ingredient,
		int ingredientCount,
		@NonNull ItemStackTemplate result,
		float experience,
		int processingTime,
		AbstractProcessingRecipe.@NonNull Factory<?> recipeFactory
	) {
		advancementBuilder = new RecipeUnlockAdvancementBuilder();
		this.ingredient = ingredient;
		this.ingredientCount = ingredientCount;
		this.result = result;
		this.experience = experience;
		this.processingTime = processingTime;
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
		@NonNull String string, @NonNull Criterion<?> criterion
	) {
		advancementBuilder.unlockedBy(string, criterion);
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
		final AbstractProcessingRecipe recipe =
			factory.create(
				ingredient,
				ingredientCount,
				result,
				experience,
				processingTime
			);
		final AdvancementHolder advancement =
			advancementBuilder.build(output, id, RecipeCategory.MISC);
		output.accept(id, recipe, advancement);
	}
}
