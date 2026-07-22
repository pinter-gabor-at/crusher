package eu.pintergabor.crusher.recipe.base;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jspecify.annotations.NonNull;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
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
	private final float experience;
	private final int processingTime;

	/**
	 * Create recipe.
	 *
	 * @param input  Encodes both the input item and the quantity required.
	 * @param result Encodes both the output item and the quantity produced.
	 */
	@SuppressWarnings("unused")
	public AbstractProcessingRecipe(
		final @NonNull ItemStack input,
		final @NonNull ItemStackTemplate result,
		final float experience,
		final int processingTime
	) {
		super(input, result);
		this.experience = experience;
		this.processingTime = processingTime;
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
		final @NonNull Ingredient input,
		final int inputCount,
		final @NonNull ItemStackTemplate result,
		final float experience,
		final int processingTime
	) {
		super(input, inputCount, result);
		this.experience = experience;
		this.processingTime = processingTime;
	}

	@Override
	public abstract @NonNull RecipeSerializer<? extends AbstractProcessingRecipe> getSerializer();

	@Override
	public abstract @NonNull RecipeType<? extends AbstractProcessingRecipe> getType();

	public float experience() {
		return experience;
	}

	public int processingTime() {
		return processingTime;
	}

	protected abstract Item getProcessorItem();

	/**
	 * No grouping by default.
	 *
	 * @return ""
	 */
	@Override
	public @NonNull String group() {
		return "";
	}

	@Override
	public @NonNull List<RecipeDisplay> display() {
		return List.of(
			new FurnaceRecipeDisplay(
				input().display(),
				SlotDisplay.AnyFuel.INSTANCE,
				new SlotDisplay.ItemStackSlotDisplay(result()),
				new SlotDisplay.ItemSlotDisplay(getProcessorItem()),
				processingTime,
				experience
			)
		);
	}

	@FunctionalInterface
	public interface Factory<T extends AbstractProcessingRecipe> {
		T create(
			Ingredient ingredient,
			int ingredientCount,
			ItemStackTemplate result,
			float experience,
			int processingTime
		);
	}

	public static <T extends AbstractProcessingRecipe> @NonNull MapCodec<T>
	processingMapCodec(
		final AbstractProcessingRecipe.@NonNull Factory<T> factory,
		int defaultProcessingTime
	) {
		return RecordCodecBuilder.mapCodec((i) -> i.group(
			Ingredient.CODEC.fieldOf("ingredient")
				.forGetter(OneStackRecipe::input),
			Codec.INT.fieldOf("ingredient_count")
				.orElse(1)
				.forGetter(OneStackRecipe::inputCount),
			ItemStackTemplate.CODEC.fieldOf("result")
				.forGetter(OneStackRecipe::result),
			Codec.FLOAT.fieldOf("experience")
				.orElse(0F)
				.forGetter(AbstractProcessingRecipe::experience),
			Codec.INT.fieldOf("processingtime")
				.orElse(defaultProcessingTime)
				.forGetter(AbstractProcessingRecipe::processingTime)
		).apply(i, factory::create));
	}

	public static <T extends AbstractProcessingRecipe> @NonNull StreamCodec<RegistryFriendlyByteBuf, T>
	processingStreamCodec(
		final AbstractProcessingRecipe.@NonNull Factory<T> factory
	) {
		return StreamCodec.composite(
			Ingredient.CONTENTS_STREAM_CODEC, OneStackRecipe::input,
			ByteBufCodecs.INT, OneStackRecipe::inputCount,
			ItemStackTemplate.STREAM_CODEC, OneStackRecipe::result,
			ByteBufCodecs.FLOAT, AbstractProcessingRecipe::experience,
			ByteBufCodecs.INT, AbstractProcessingRecipe::processingTime,
			factory::create
		);
	}
}
