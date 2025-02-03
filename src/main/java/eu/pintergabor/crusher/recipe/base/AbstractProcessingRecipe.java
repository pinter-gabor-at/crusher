package eu.pintergabor.crusher.recipe.base;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CookingRecipeCategory;

/**
 * Similar to {@link SmeltingRecipe},
 * but with unique serializer, type and category.
 */
public abstract class AbstractProcessingRecipe extends AbstractCookingRecipe {

    public AbstractProcessingRecipe(
            String group,
            CookingRecipeCategory category,
            Ingredient ingredient,
            ItemStack result,
            float experience,
            int cookingTime) {
        super(group, category, ingredient, result, experience, cookingTime);
    }

    @Override
    public ItemStack result() {
        return super.result();
    }

    /**
     * Similar to {@link AbstractCookingRecipe.Serializer},
     * but with {@link ItemStack} output
     *
     * @param <T>
     */
    public static class Serializer<T extends AbstractProcessingRecipe> implements RecipeSerializer<T> {
        private final MapCodec<T> codec;
        private final PacketCodec<RegistryByteBuf, T> packetCodec;

        public Serializer(RecipeFactory<T> factory, int defaultCookingTime) {
            codec = RecordCodecBuilder.mapCodec(
                    instance -> instance.group(
                                    Codec.STRING.optionalFieldOf("group", "")
                                            .forGetter(SingleStackRecipe::getGroup),
                                    CookingRecipeCategory.CODEC.fieldOf("category")
                                            .orElse(CookingRecipeCategory.MISC)
                                            .forGetter(AbstractCookingRecipe::getCategory),
                                    Ingredient.CODEC.fieldOf("ingredient")
                                            .forGetter(SingleStackRecipe::ingredient),
                                    ItemStack.VALIDATED_CODEC.fieldOf("result")
                                            .forGetter(AbstractProcessingRecipe::result),
                                    Codec.FLOAT.fieldOf("experience")
                                            .orElse(0.0f)
                                            .forGetter(AbstractCookingRecipe::getExperience),
                                    Codec.INT.fieldOf("cookingtime")
                                            .orElse(defaultCookingTime)
                                            .forGetter(AbstractCookingRecipe::getCookingTime)
                            )
                            .apply(instance, factory::create)
            );
            packetCodec = PacketCodec.tuple(
                    PacketCodecs.STRING, SingleStackRecipe::getGroup,
                    CookingRecipeCategory.PACKET_CODEC, AbstractCookingRecipe::getCategory,
                    Ingredient.PACKET_CODEC, SingleStackRecipe::ingredient,
                    ItemStack.PACKET_CODEC, AbstractProcessingRecipe::result,
                    PacketCodecs.FLOAT, AbstractCookingRecipe::getExperience,
                    PacketCodecs.INTEGER, AbstractCookingRecipe::getCookingTime,
                    factory::create
            );
        }

        @Override
        public MapCodec<T> codec() {
            return codec;
        }

        @Override
        public PacketCodec<RegistryByteBuf, T> packetCodec() {
            return packetCodec;
        }
    }
}