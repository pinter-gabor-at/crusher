package eu.pintergabor.crusher.recipe.base;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
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
	 * @param inputCount The number of items to use in the recipe.
	 */
	public OneStackRecipe(
		String group,
		Ingredient input,
		int inputCount,
		ItemStack result) {
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
	public OneStackRecipe(
		String group,
		ItemStack input,
		ItemStack result) {
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

	public boolean matches(OneStackRecipeInput oneStackRecipeInput, Level world) {
		return input.test(oneStackRecipeInput.itemStack());
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
	public @NotNull ItemStack assemble(OneStackRecipeInput input, HolderLookup.Provider registries) {
		return result.copy();
	}

	@FunctionalInterface
	public interface RecipeFactory<T extends OneStackRecipe> {
		T create(String group, Ingredient ingredient, int ingredientCount, ItemStack result);
	}

	public static class Serializer<T extends OneStackRecipe> implements RecipeSerializer<T> {
		private final MapCodec<T> codec;
		private final StreamCodec<RegistryFriendlyByteBuf, T> streamCodec;

		protected Serializer(OneStackRecipe.RecipeFactory<T> recipeFactory) {
			codec = RecordCodecBuilder.mapCodec(
				instance -> instance.group(
						Codec.STRING.optionalFieldOf("group", "")
							.forGetter(OneStackRecipe::group),
						Ingredient.CODEC.fieldOf("ingredient").
							forGetter(OneStackRecipe::input),
						Codec.INT.fieldOf("count")
							.orElse(1)
							.forGetter(OneStackRecipe::inputCount),
						ItemStack.STRICT_CODEC.fieldOf("result")
							.forGetter(OneStackRecipe::result)
					)
					.apply(instance, recipeFactory::create)
			);
			streamCodec = StreamCodec.composite(
				ByteBufCodecs.STRING_UTF8, OneStackRecipe::group,
				Ingredient.CONTENTS_STREAM_CODEC, OneStackRecipe::input,
				ByteBufCodecs.INT, OneStackRecipe::inputCount,
				ItemStack.STREAM_CODEC, OneStackRecipe::result,
				recipeFactory::create
			);
		}

		@Override
		public @NotNull MapCodec<T> codec() {
			return codec;
		}

		@Override
		public @NotNull StreamCodec<RegistryFriendlyByteBuf, T> streamCodec() {
			return streamCodec;
		}
	}
}
