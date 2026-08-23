package eu.pintergabor.crusher.rei.display;

import java.util.List;
import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import eu.pintergabor.crusher.recipe.CompressorRecipe;
import eu.pintergabor.crusher.recipe.base.AbstractProcessingRecipe;
import eu.pintergabor.crusher.rei.ModReiCommon;
import eu.pintergabor.crusher.rei.base.ProcessingDisplay;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NonNull;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeHolder;


/**
 * See <a href="https://www.craft.do/s/qj8mHyTVd7qkOZ/b/F8F59595-CBCE-4DF1-8C2D-77FC906446CB/Displays-(Client)">Wiki: Displays</a>
 */
public record CompressorDisplay(
	EntryIngredient input,
	EntryIngredient output,
	float experience,
	int processingTime,
	Optional<Identifier> location
) implements ProcessingDisplay {

	// Serializers.
	private static final MapCodec<CompressorDisplay> MAP_CODEC =
		RecordCodecBuilder.mapCodec(instance -> instance.group(
			EntryIngredient.codec().fieldOf("input")
				.forGetter(CompressorDisplay::input),
			EntryIngredient.codec().fieldOf("output")
				.forGetter(CompressorDisplay::output),
			Codec.FLOAT.fieldOf("experience")
				.orElse(0F)
				.forGetter(CompressorDisplay::experience),
			Codec.INT.fieldOf("processingtime")
				.orElse(0)
				.forGetter(CompressorDisplay::processingTime),
			Identifier.CODEC.optionalFieldOf("location")
				.forGetter(CompressorDisplay::location)
		).apply(instance, CompressorDisplay::new));
	private static final StreamCodec<RegistryFriendlyByteBuf, CompressorDisplay> STREAM_CODEC =
		StreamCodec.composite(
			EntryIngredient.streamCodec(), CompressorDisplay::input,
			EntryIngredient.streamCodec(), CompressorDisplay::output,
			ByteBufCodecs.FLOAT, CompressorDisplay::experience,
			ByteBufCodecs.INT, CompressorDisplay::processingTime,
			ByteBufCodecs.optional(Identifier.STREAM_CODEC), CompressorDisplay::location,
			CompressorDisplay::new);
	public static final DisplaySerializer<CompressorDisplay>
		SERIALIZER = DisplaySerializer.of(MAP_CODEC, STREAM_CODEC);

	/**
	 * Constructor for serializers.
	 */
	public CompressorDisplay(
		final @NonNull RecipeHolder<CompressorRecipe> entry
	) {
		this(
			entry.id().identifier(),
			entry.value()
		);
	}

	/**
	 * Constructor for recipe entry generation.
	 */
	public CompressorDisplay(
		final @NonNull Identifier id,
		final @NonNull AbstractProcessingRecipe recipe
	) {
		this(
			ProcessingDisplay.getEntryInput(recipe),
			ProcessingDisplay.getEntryOutput(recipe),
			recipe.experience(),
			recipe.processingTime(),
			Optional.of(id)
		);
	}

	@Contract(value = " -> new", pure = true)
	@Override
	public @NonNull @Unmodifiable List<EntryIngredient> getInputEntries() {
		return List.of(input);
	}

	@Contract(value = " -> new", pure = true)
	@Override
	public @NonNull @Unmodifiable List<EntryIngredient> getOutputEntries() {
		return List.of(output);
	}

	@Override
	public CategoryIdentifier<?> getCategoryIdentifier() {
		return ModReiCommon.COMPRESSOR;
	}

	@Override
	public Optional<Identifier> getDisplayLocation() {
		return location;
	}

	@Override
	public @NonNull DisplaySerializer<? extends Display> getSerializer() {
		return SERIALIZER;
	}

	@Override
	public double getExperience() {
		return experience;
	}

	@Override
	public int getProcessingTime() {
		return processingTime;
	}
}
