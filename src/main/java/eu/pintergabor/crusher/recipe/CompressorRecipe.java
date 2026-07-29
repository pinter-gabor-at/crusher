package eu.pintergabor.crusher.recipe;

import com.mojang.serialization.MapCodec;
import eu.pintergabor.crusher.blocks.ModBlocks;
import eu.pintergabor.crusher.recipe.base.AbstractProcessingRecipe;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeBookCategories;
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
	public static RecipeSerializer<CompressorRecipe> SERIALIZER;
	public static RecipeType<AbstractProcessingRecipe> TYPE;
	public static RecipeBookCategory CATEGORY;

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
		return ModBlocks.COMPRESSOR_ITEM;
	}

	@Override
	public @NonNull RecipeSerializer<? extends AbstractProcessingRecipe> getSerializer() {
		return SERIALIZER;
	}

	@Override
	public @NonNull RecipeType<? extends AbstractProcessingRecipe> getType() {
		return TYPE;
	}

	@Override
	public @NonNull RecipeBookCategory recipeBookCategory() {
		return CATEGORY;
	}

	/**
	 * Register unique serializer, type and category.
	 * <p>
	 * See {@link RecipeSerializer}, {@link RecipeType} and {@link RecipeBookCategories} for examples.
	 */
	public static void register() {
		SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);
		Registry.register(
			BuiltInRegistries.RECIPE_SERIALIZER,
			PROCESSING_NAME,
			SERIALIZER
		);
		TYPE =
			RecipeType.register(PROCESSING_NAME);
		CATEGORY =
			Registry.register(
				BuiltInRegistries.RECIPE_BOOK_CATEGORY,
				PROCESSING_NAME,
				new RecipeBookCategory()
			);
	}
}
