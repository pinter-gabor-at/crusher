package eu.pintergabor.crusher.rei;

import java.util.List;
import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import eu.pintergabor.crusher.recipe.CrusherRecipe;
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


public record CrusherDisplay(
	EntryIngredient input,
	EntryIngredient output,
	float experience,
	int processingTime,
	Optional<Identifier> location
) implements ProcessingDisplay {

	public static final MapCodec<CrusherDisplay> MAPCODEC =
		RecordCodecBuilder.mapCodec(instance -> instance.group(
			EntryIngredient.codec().fieldOf("input")
				.forGetter(CrusherDisplay::input),
			EntryIngredient.codec().fieldOf("output")
				.forGetter(CrusherDisplay::output),
			Codec.FLOAT.fieldOf("experience")
				.orElse(0F)
				.forGetter(CrusherDisplay::experience),
			Codec.INT.fieldOf("processingtime")
				.orElse(0)
				.forGetter(CrusherDisplay::processingTime),
			Identifier.CODEC.optionalFieldOf("location").forGetter(CrusherDisplay::location)
		).apply(instance, CrusherDisplay::new));
	public static final StreamCodec<RegistryFriendlyByteBuf, CrusherDisplay> STREAMCODEC =
		StreamCodec.composite(
			EntryIngredient.streamCodec(), CrusherDisplay::input,
			EntryIngredient.streamCodec(), CrusherDisplay::output,
			ByteBufCodecs.FLOAT, CrusherDisplay::experience,
			ByteBufCodecs.INT, CrusherDisplay::processingTime,
			ByteBufCodecs.optional(Identifier.STREAM_CODEC), CrusherDisplay::location,
			CrusherDisplay::new);
	public static final DisplaySerializer<CrusherDisplay>
		SERIALIZER = DisplaySerializer.of(MAPCODEC, STREAMCODEC);

	public CrusherDisplay(@NonNull RecipeHolder<CrusherRecipe> entry) {
		this(entry.id().identifier(), entry.value());
	}

	public CrusherDisplay(Identifier id, @NonNull CrusherRecipe recipe) {
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
		return ModReiCommon.CRUSHER;
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
