package eu.pintergabor.crusher.recipe.base;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jetbrains.annotations.NotNull;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.display.FurnaceRecipeDisplay;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;


/**
 * Similar to {@link SmeltingRecipe} or {@link AbstractCookingRecipe},
 * but with unique serializer, type and category.
 */
public abstract class AbstractProcessingRecipe extends OneStackRecipe {
	private final CookingBookCategory category;
	private final float experience;
	private final int cookingTime;

	/**
	 * Create recipe.
	 *
	 * @param input  Encodes both the input item and the quantity required.
	 * @param result Encodes both the output item and the quantity produced.
	 */
	@SuppressWarnings("unused")
	public AbstractProcessingRecipe(
		String group,
		CookingBookCategory category,
		ItemStack input,
		ItemStack result,
		float experience,
		int cookingTime) {
		super(group, input, result);
		this.category = category;
		this.experience = experience;
		this.cookingTime = cookingTime;
	}

	/**
	 * Create recipe.
	 *
	 * @param input      The input item.
	 * @param inputCount And the input quantity required.
	 * @param result     Encodes both the output item and the quantity produced.
	 */
	@SuppressWarnings("unused")
	public AbstractProcessingRecipe(
		String group,
		CookingBookCategory category,
		Ingredient input,
		int inputCount,
		ItemStack result,
		float experience,
		int cookingTime) {
		super(group, input, inputCount, result);
		this.category = category;
		this.experience = experience;
		this.cookingTime = cookingTime;
	}

	@Override
	public abstract @NotNull RecipeSerializer<? extends AbstractProcessingRecipe> getSerializer();

	@Override
	public abstract @NotNull RecipeType<? extends AbstractProcessingRecipe> getType();

	public float experience() {
		return experience;
	}

	public int cookingTime() {
		return cookingTime;
	}

	public CookingBookCategory category() {
		return category;
	}

	protected abstract Item getProcessorItem();

	@Override
	public @NotNull List<RecipeDisplay> display() {
		return List.of(
			new FurnaceRecipeDisplay(
				this.input().display(),
				SlotDisplay.AnyFuel.INSTANCE,
				new SlotDisplay.ItemStackSlotDisplay(result()),
				new SlotDisplay.ItemSlotDisplay(getProcessorItem()),
				this.cookingTime,
				this.experience));
	}

	@FunctionalInterface
	public interface RecipeFactory<T extends AbstractProcessingRecipe> {
		T create(
			String group,
			CookingBookCategory category,
			Ingredient ingredient,
			int ingredientCount,
			ItemStack result,
			float experience,
			int cookingTime);
	}

	/**
	 * Similar to {@link AbstractCookingRecipe.Serializer},
	 * but with {@link ItemStack} output.
	 *
	 * @param <T>
	 */
	public static class Serializer<T extends AbstractProcessingRecipe> implements RecipeSerializer<T> {
		private final MapCodec<T> codec;
		private final StreamCodec<RegistryFriendlyByteBuf, T> streamCodec;

		public Serializer(RecipeFactory<T> factory, int defaultCookingTime) {
			codec = RecordCodecBuilder.mapCodec(
				instance -> instance.group(
						Codec.STRING.optionalFieldOf("group", "")
							.forGetter(OneStackRecipe::group),
						CookingBookCategory.CODEC.fieldOf("category")
							.orElse(CookingBookCategory.MISC)
							.forGetter(AbstractProcessingRecipe::category),
						Ingredient.CODEC.fieldOf("ingredient").
							forGetter(OneStackRecipe::input),
						Codec.INT.fieldOf("ingredient_count")
							.orElse(1)
							.forGetter(OneStackRecipe::inputCount),
						ItemStack.STRICT_CODEC.fieldOf("result")
							.forGetter(OneStackRecipe::result),
						Codec.FLOAT.fieldOf("experience")
							.orElse(0.0f)
							.forGetter(AbstractProcessingRecipe::experience),
						Codec.INT.fieldOf("cookingtime")
							.orElse(defaultCookingTime)
							.forGetter(AbstractProcessingRecipe::cookingTime)
					)
					.apply(instance, factory::create)
			);
			streamCodec = StreamCodec.composite(
				ByteBufCodecs.STRING_UTF8, OneStackRecipe::group,
				CookingBookCategory.STREAM_CODEC, AbstractProcessingRecipe::category,
				Ingredient.CONTENTS_STREAM_CODEC, OneStackRecipe::input,
				ByteBufCodecs.INT, OneStackRecipe::inputCount,
				ItemStack.STREAM_CODEC, OneStackRecipe::result,
				ByteBufCodecs.FLOAT, AbstractProcessingRecipe::experience,
				ByteBufCodecs.INT, AbstractProcessingRecipe::cookingTime,
				factory::create);
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
