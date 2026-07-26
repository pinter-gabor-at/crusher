package eu.pintergabor.crusher.recipe.base;


import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.Level;


/**
 * A recipe that has only one input ingredient. It can be used by any type
 * of recipe as long as its subclass implements the proper interface.
 * <p>
 * Similar to {@link SingleItemRecipe}, but allows more than one of the same item.
 */
public abstract class OneStackRecipe implements Recipe<OneStackRecipeInput> {
	private final Ingredient input;
	private final int inputCount;
	private final ItemStackTemplate result;
	private @Nullable PlacementInfo placementInfo;

	/**
	 * Create recipe.
	 *
	 * @param input      The input item.
	 * @param inputCount The number of items to use in the recipe.
	 */
	@SuppressWarnings("unused")
	public OneStackRecipe(
		final @NonNull Ingredient input,
		final int inputCount,
		final @NonNull ItemStackTemplate result
	) {
		this.input = input;
		this.inputCount = inputCount;
		this.result = result;
	}

	/**
	 * Create recipe.
	 *
	 * @param input Encodes both the input item and the quantity required.
	 */
	@SuppressWarnings("unused")
	public OneStackRecipe(
		final @NonNull ItemStack input,
		final @NonNull ItemStackTemplate result
	) {
		this(Ingredient.of(input.getItem()),
			input.getCount(),
			result);
	}

	@Override
	public abstract @NonNull RecipeSerializer<? extends OneStackRecipe> getSerializer();

	@Override
	public abstract @NonNull RecipeType<? extends OneStackRecipe> getType();

	public boolean matches(@NonNull OneStackRecipeInput input, @NonNull Level level) {
		return this.input.test(input.getItemStack());
	}

	public boolean showNotification() {
		return false;
	}

	public @NonNull Ingredient input() {
		return input;
	}

	public int inputCount() {
		return inputCount;
	}

	public @NonNull ItemStackTemplate result() {
		return result;
	}

	@Override
	public @NonNull PlacementInfo placementInfo() {
		if (placementInfo == null) {
			placementInfo = PlacementInfo.create(input);
		}
		return placementInfo;
	}

	@Override
	public @NonNull ItemStack assemble(
		@NonNull OneStackRecipeInput recipeInput
	) {
		return result.create();
	}
}
