package eu.pintergabor.crusher.recipe;

import java.util.function.Supplier;

import com.mojang.serialization.MapCodec;
import eu.pintergabor.crusher.blocks.ModBlocks;
import eu.pintergabor.crusher.main.Main;
import eu.pintergabor.crusher.recipe.base.AbstractProcessingRecipe;

import net.minecraft.network.RegistryFriendlyByteBuf;

import net.minecraft.network.codec.StreamCodec;

import org.jspecify.annotations.NonNull;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;


/**
 * Similar to {@link SmeltingRecipe},
 * but with unique serializer, type and category.
 */
public class CompressorRecipe extends AbstractProcessingRecipe {
	public static final String PROCESSING_NAME = "compressing";
	public static final MapCodec<CompressorRecipe> MAP_CODEC =
		processingMapCodec(CompressorRecipe::new, 100);
	public static final StreamCodec<RegistryFriendlyByteBuf, CompressorRecipe> STREAM_CODEC =
		processingStreamCodec(CompressorRecipe::new);
	public static final Supplier<RecipeBookCategory> CATEGORY =
		Main.RECIPE_BOOK_CATEGORIES.register(
			PROCESSING_NAME, RecipeBookCategory::new);
	public static Supplier<RecipeType<CompressorRecipe>> TYPE =
		Main.RECIPE_TYPES.register(PROCESSING_NAME, id ->
			new RecipeType<>() {
				@Override
				public String toString() {
					return id.toString();
				}
			});
	public static Supplier<RecipeSerializer<CompressorRecipe>> SERIALIZER =
		Main.RECIPE_SERIALIZERS.register(PROCESSING_NAME, () ->
			new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC));

	public CompressorRecipe(
		final @NonNull Ingredient ingredient,
		final int ingredientCount,
		final @NonNull ItemStackTemplate result,
		final float experience,
		final int processingTime
	) {
		super(
			ingredient,
			ingredientCount,
			result,
			experience,
			processingTime
		);
	}

	@Override
	protected Item getProcessorItem() {
		return ModBlocks.COMPRESOR_ITEM.get();
	}

	@Override
	public @NonNull RecipeSerializer<? extends AbstractProcessingRecipe> getSerializer() {
		return SERIALIZER.get();
	}

	@Override
	public @NonNull RecipeType<? extends AbstractProcessingRecipe> getType() {
		return TYPE.get();
	}

	@Override
	public @NonNull RecipeBookCategory recipeBookCategory() {
		return CATEGORY.get();
	}

	/**
	 * Extra initialization.
	 */
	public static void init() {
		// Everything has been done by static initializers.
	}
}
