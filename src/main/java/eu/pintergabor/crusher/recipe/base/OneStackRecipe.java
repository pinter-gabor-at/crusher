package eu.pintergabor.crusher.recipe.base;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
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
	private final ItemStack result;
	private final String group;
	private @Nullable PlacementInfo ingredientPlacement;

	/**
	 * Create recipe.
	 *
	 * @param input      The input item.
	 * @param inputCount The number of items to use in the recipe.
	 */
	@SuppressWarnings("unused")
	public OneStackRecipe(
		String group,
		Ingredient input,
		int inputCount,
		ItemStack result
	) {
		this.group = group;
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
		String group,
		ItemStack input,
		ItemStack result
	) {
		this(
			group,
			Ingredient.of(input.getItem()),
			input.getCount(),
			result);
	}

	@Override
	public abstract @NotNull RecipeSerializer<? extends OneStackRecipe> getSerializer();

	@Override
	public abstract @NotNull RecipeType<? extends OneStackRecipe> getType();

	public boolean matches(OneStackRecipeInput recipeInput, @NotNull Level level) {
		return input.test(recipeInput.getItemStack());
	}

	@Override
	public @NotNull String group() {
		return group;
	}

	public Ingredient input() {
		return input;
	}

	public int inputCount() {
		return inputCount;
	}

	public ItemStack result() {
		return result;
	}

	@Override
	public @NotNull PlacementInfo placementInfo() {
		if (ingredientPlacement == null) {
			ingredientPlacement = PlacementInfo.create(input);
		}
		return ingredientPlacement;
	}

	@Override
	public @NotNull ItemStack assemble(
		@NotNull OneStackRecipeInput recipeInput,
		HolderLookup.@NotNull Provider registries
	) {
		return result.copy();
	}
}
