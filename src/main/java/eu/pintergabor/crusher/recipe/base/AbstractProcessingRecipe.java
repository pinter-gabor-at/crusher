package eu.pintergabor.crusher.recipe.base;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.recipe.display.FurnaceRecipeDisplay;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.recipe.display.SlotDisplay;


/**
 * Similar to {@link SmeltingRecipe},
 * but with unique serializer, type and category.
 */
public abstract class AbstractProcessingRecipe extends OneStackRecipe {
    private final CookingRecipeCategory category;
    private final float experience;
    private final int cookingTime;

    public AbstractProcessingRecipe(
        String group,
        CookingRecipeCategory category,
        ItemStack input,
        ItemStack result,
        float experience,
        int cookingTime) {
        super(group, input, result);
        this.category = category;
        this.experience = experience;
        this.cookingTime = cookingTime;
    }

    public AbstractProcessingRecipe(
        String group,
        CookingRecipeCategory category,
        Ingredient ingredient,
        int ingredientCount,
        ItemStack result,
        float experience,
        int cookingTime) {
        super(group, ingredient, ingredientCount, result);
        this.category = category;
        this.experience = experience;
        this.cookingTime = cookingTime;
    }

    @Override
    public abstract RecipeSerializer<? extends AbstractProcessingRecipe> getSerializer();

    @Override
    public abstract RecipeType<? extends AbstractProcessingRecipe> getType();

    public float getExperience() {
        return experience;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public CookingRecipeCategory getCategory() {
        return category;
    }

    protected abstract Item getCookerItem();

    @Override
    public List<RecipeDisplay> getDisplays() {
        return List.of(
            new FurnaceRecipeDisplay(
                this.ingredient().toDisplay(),
                SlotDisplay.AnyFuelSlotDisplay.INSTANCE,
                new SlotDisplay.StackSlotDisplay(this.result()),
                new SlotDisplay.ItemSlotDisplay(this.getCookerItem()),
                this.cookingTime,
                this.experience
            )
        );
    }

    @FunctionalInterface
    public interface RecipeFactory<T extends AbstractProcessingRecipe> {
        T create(
            String group,
            CookingRecipeCategory category,
            Ingredient ingredient,
            int ingredientCount,
            ItemStack result,
            float experience,
            int cookingTime
        );
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
                            .forGetter(OneStackRecipe::getGroup),
                        CookingRecipeCategory.CODEC.fieldOf("category")
                            .orElse(CookingRecipeCategory.MISC)
                            .forGetter(AbstractProcessingRecipe::getCategory),
                        Ingredient.CODEC.fieldOf("ingredient").
                            forGetter(OneStackRecipe::ingredient),
                        Codec.INT.fieldOf("ingredient_count")
                            .orElse(1)
                            .forGetter(OneStackRecipe::ingredientCount),
                        ItemStack.VALIDATED_CODEC.fieldOf("result")
                            .forGetter(OneStackRecipe::result),
                        Codec.FLOAT.fieldOf("experience")
                            .orElse(0.0f)
                            .forGetter(AbstractProcessingRecipe::getExperience),
                        Codec.INT.fieldOf("cookingtime")
                            .orElse(defaultCookingTime)
                            .forGetter(AbstractProcessingRecipe::getCookingTime)
                    )
                    .apply(instance, factory::create)
            );
            packetCodec = PacketCodec.tuple(
                PacketCodecs.STRING, OneStackRecipe::getGroup,
                CookingRecipeCategory.PACKET_CODEC, AbstractProcessingRecipe::getCategory,
                Ingredient.PACKET_CODEC, OneStackRecipe::ingredient,
                PacketCodecs.INTEGER, OneStackRecipe::ingredientCount,
                ItemStack.PACKET_CODEC, OneStackRecipe::result,
                PacketCodecs.FLOAT, AbstractProcessingRecipe::getExperience,
                PacketCodecs.INTEGER, AbstractProcessingRecipe::getCookingTime,
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
