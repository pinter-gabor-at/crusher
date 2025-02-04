package eu.pintergabor.crusher.recipe.base;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.*;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * A recipe that has only one input ingredient. It can be used by any type
 * of recipe as long as its subclass implements the proper interface.
 */
public abstract class OneStackRecipe implements Recipe<OneStackRecipeInput> {
    private final Ingredient ingredient;
    private final int ingredientCount;
    private final ItemStack result;
    private final String group;
    @Nullable
    private IngredientPlacement ingredientPlacement;

    public OneStackRecipe(
            String group,
            ItemStack input,
            ItemStack result) {
        this(
                group,
                Ingredient.ofItem(input.getItem()),
                input.getCount(),
                result
        );
    }

    public OneStackRecipe(
            String group,
            Ingredient ingredient,
            int ingredientCount,
            ItemStack result) {
        this.group = group;
        this.ingredient = ingredient;
        this.ingredientCount = ingredientCount;
        this.result = result;
    }

    @Override
    public abstract RecipeSerializer<? extends OneStackRecipe> getSerializer();

    @Override
    public abstract RecipeType<? extends OneStackRecipe> getType();

    public boolean matches(OneStackRecipeInput oneStackRecipeInput, World world) {
        return ingredient.test(oneStackRecipeInput.itemStack());
    }

    @Override
    public String getGroup() {
        return group;
    }

    public Ingredient ingredient() {
        return ingredient;
    }

    public int ingredientCount() {
        return ingredientCount;
    }

    public ItemStack result() {
        return result;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        if (ingredientPlacement == null) {
            ingredientPlacement = IngredientPlacement.forSingleSlot(ingredient);
        }
        return ingredientPlacement;
    }

    public ItemStack craft(OneStackRecipeInput oneStackRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup) {
        return result.copy();
    }

    @FunctionalInterface
    public interface RecipeFactory<T extends OneStackRecipe> {
        T create(String group, Ingredient ingredient, int ingredientCount, ItemStack result);
    }

    public static class Serializer<T extends OneStackRecipe> implements RecipeSerializer<T> {
        private final MapCodec<T> codec;
        private final PacketCodec<RegistryByteBuf, T> packetCodec;

        protected Serializer(OneStackRecipe.RecipeFactory<T> recipeFactory) {
            codec = RecordCodecBuilder.mapCodec(
                    instance -> instance.group(
                                    Codec.STRING.optionalFieldOf("group", "")
                                            .forGetter(OneStackRecipe::getGroup),
                                    Ingredient.CODEC.fieldOf("ingredient").
                                            forGetter(OneStackRecipe::ingredient),
                                    Codec.INT.fieldOf("count")
                                            .orElse(1)
                                            .forGetter(OneStackRecipe::ingredientCount),
                                    ItemStack.VALIDATED_CODEC.fieldOf("result")
                                            .forGetter(OneStackRecipe::result)
                            )
                            .apply(instance, recipeFactory::create)
            );
            packetCodec = PacketCodec.tuple(
                    PacketCodecs.STRING, OneStackRecipe::getGroup,
                    Ingredient.PACKET_CODEC, OneStackRecipe::ingredient,
                    PacketCodecs.INTEGER, OneStackRecipe::ingredientCount,
                    ItemStack.PACKET_CODEC, OneStackRecipe::result,
                    recipeFactory::create
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
