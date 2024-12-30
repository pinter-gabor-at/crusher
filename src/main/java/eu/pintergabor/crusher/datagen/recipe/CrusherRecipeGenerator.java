package eu.pintergabor.crusher.datagen.recipe;

import eu.pintergabor.crusher.recipe.CrusherRecipe;
import eu.pintergabor.crusher.recipe.CrusherRecipeJsonBuilder;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;


public abstract class CrusherRecipeGenerator extends RecipeGenerator {
    public float experience = 0.1f;
    public int cookingTime = 100;

    protected CrusherRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
    }

    /**
     * Create crushing recipe from input item
     * @param input Input item
     * @param output Output item
     */
    @SuppressWarnings({"unused", "SameParameterValue"})
    public void createCrushingItem(
            ItemConvertible input,
            ItemStack output
    ) {
        CrusherRecipeJsonBuilder.create(
                        Ingredient.ofItem(input),
                        RecipeCategory.MISC,
                        output,
                        experience,
                        cookingTime,
                        CrusherRecipe::new)
                .group("crushing")
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter, getItemPath(output.getItem()) + "_from_crushing_" + getItemPath(input));
    }

    /**
     * Create crushing recipe from input item tag
     * @param tag Input item tag
     * @param output Output item
     */
    @SuppressWarnings({"unused", "SameParameterValue"})
    public void createCrushingTag(
            TagKey<Item> tag,
            ItemStack output
    ) {
        try {
            RegistryWrapper.Impl<Item> registryLookup = registries.getOrThrow(RegistryKeys.ITEM);
            Ingredient i = Ingredient.fromTag(registryLookup.getOrThrow(tag));
            CrusherRecipeJsonBuilder.create(
                            i,
                            RecipeCategory.MISC,
                            output,
                            experience,
                            cookingTime,
                            CrusherRecipe::new)
                    .group("crushing")
                    .criterion("has_" + tag.id().getPath(), conditionsFromTag(tag))
                    .offerTo(exporter, getItemPath(output.getItem()) + "_from_crushing_" + tag.id().getPath());
        } catch (IllegalStateException e) {
            // If tag does not exist, then do not generate the recipe.
        }
    }
}
