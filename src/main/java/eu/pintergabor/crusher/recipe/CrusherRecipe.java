package eu.pintergabor.crusher.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import eu.pintergabor.crusher.blocks.ModBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class CrusherRecipe extends AbstractCookingRecipe {
    public static RecipeSerializer<CrusherRecipe> CRUSHER_SERIALIZER;
    public static RecipeType<CrusherRecipe> CRUSHER_TYPE;
    public static RecipeBookCategory CRUSHER_CATEGORY;

    public CrusherRecipe(
            String group,
            CookingRecipeCategory category,
            Ingredient ingredient,
            ItemStack result,
            float experience,
            int cookingTime) {
        super(group, category, ingredient, result, experience, cookingTime);
    }

    @Override
    public RecipeSerializer<? extends AbstractCookingRecipe> getSerializer() {
        return CRUSHER_SERIALIZER;
    }

    @Override
    public RecipeType<? extends AbstractCookingRecipe> getType() {
        return CRUSHER_TYPE;
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return CRUSHER_CATEGORY;
    }

    @Override
    protected Item getCookerItem() {
        return ModBlocks.CRUSHER_ITEM;
    }

    @Override
    public ItemStack result() {
        return super.result();
    }

    public static class Serializer<T extends CrusherRecipe> implements RecipeSerializer<T> {
        private final MapCodec<T> codec;
        private final PacketCodec<RegistryByteBuf, T> packetCodec;

        public Serializer(AbstractCookingRecipe.RecipeFactory<T> factory, int defaultCookingTime) {
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
                                            .forGetter(CrusherRecipe::result),
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
                    ItemStack.PACKET_CODEC, CrusherRecipe::result,
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

    public static void register() {
        CRUSHER_SERIALIZER =
                RecipeSerializer.register(
                        "crusher",
                        new Serializer<>(CrusherRecipe::new, 100));
        CRUSHER_TYPE =
                RecipeType.register("crusher");
        CRUSHER_CATEGORY =
                Registry.register(
                        Registries.RECIPE_BOOK_CATEGORY,
                        "crusher",
                        new RecipeBookCategory());
    }
}