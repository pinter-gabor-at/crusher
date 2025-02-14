package eu.pintergabor.crusher.rei;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.recipe.display.SlotDisplay;
import net.minecraft.resource.featuretoggle.FeatureSet;

public record CrusherRecipeDisplay(SlotDisplay ingredient, SlotDisplay fuel, SlotDisplay result,
                                   SlotDisplay craftingStation, int duration, float experience)
        implements RecipeDisplay {
    public static final MapCodec<CrusherRecipeDisplay> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            SlotDisplay.CODEC.fieldOf("ingredient").forGetter(CrusherRecipeDisplay::ingredient),
                            SlotDisplay.CODEC.fieldOf("fuel").forGetter(CrusherRecipeDisplay::fuel),
                            SlotDisplay.CODEC.fieldOf("result").forGetter(CrusherRecipeDisplay::result),
                            SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(CrusherRecipeDisplay::craftingStation),
                            Codec.INT.fieldOf("duration").forGetter(CrusherRecipeDisplay::duration),
                            Codec.FLOAT.fieldOf("experience").forGetter(CrusherRecipeDisplay::experience)
                    )
                    .apply(instance, CrusherRecipeDisplay::new)
    );
    public static final PacketCodec<RegistryByteBuf, CrusherRecipeDisplay> PACKET_CODEC = PacketCodec.tuple(
            SlotDisplay.PACKET_CODEC,
            CrusherRecipeDisplay::ingredient,
            SlotDisplay.PACKET_CODEC,
            CrusherRecipeDisplay::fuel,
            SlotDisplay.PACKET_CODEC,
            CrusherRecipeDisplay::result,
            SlotDisplay.PACKET_CODEC,
            CrusherRecipeDisplay::craftingStation,
            PacketCodecs.VAR_INT,
            CrusherRecipeDisplay::duration,
            PacketCodecs.FLOAT,
            CrusherRecipeDisplay::experience,
            CrusherRecipeDisplay::new
    );
    public static final RecipeDisplay.Serializer<CrusherRecipeDisplay> SERIALIZER = new RecipeDisplay.Serializer<>(CODEC, PACKET_CODEC);

    @Override
    public RecipeDisplay.Serializer<CrusherRecipeDisplay> serializer() {
        return SERIALIZER;
    }

    @Override
    public boolean isEnabled(FeatureSet features) {
        return true;
    }
}
