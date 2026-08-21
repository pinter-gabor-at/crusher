package eu.pintergabor.crusher.rei;

import java.util.List;
import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import eu.pintergabor.crusher.recipe.CompressorRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NonNull;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeHolder;


public record CompressorDisplay(
	EntryIngredient input,
	EntryIngredient output,
	float experience,
	int processingTime,
	Optional<Identifier> location
) implements ProcessingDisplay {

	public static final MapCodec<CompressorDisplay> MAPCODEC =
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
			Identifier.CODEC.optionalFieldOf("location").forGetter(CompressorDisplay::location)
		).apply(instance, CompressorDisplay::new));
	public static final StreamCodec<RegistryFriendlyByteBuf, CompressorDisplay> STREAMCODEC =
		StreamCodec.composite(
			EntryIngredient.streamCodec(), CompressorDisplay::input,
			EntryIngredient.streamCodec(), CompressorDisplay::output,
			ByteBufCodecs.FLOAT, CompressorDisplay::experience,
			ByteBufCodecs.INT, CompressorDisplay::processingTime,
			ByteBufCodecs.optional(Identifier.STREAM_CODEC), CompressorDisplay::location,
			CompressorDisplay::new);
	public static final DisplaySerializer<CompressorDisplay>
		SERIALIZER = DisplaySerializer.of(MAPCODEC, STREAMCODEC);

	public CompressorDisplay(@NonNull RecipeHolder<CompressorRecipe> entry) {
		this(entry.id().identifier(), entry.value());
	}

	public CompressorDisplay(Identifier id, @NonNull CompressorRecipe recipe) {
		this(
			EntryIngredients.ofIngredient(recipe.input()),
			EntryIngredients.of(recipe.result().create()),
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
	public double getProcessingTime() {
		return processingTime;
	}
}
