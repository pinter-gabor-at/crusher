package eu.pintergabor.crusher.datagen.recipebase;

import eu.pintergabor.crusher.recipe.CompressorRecipe;
import eu.pintergabor.crusher.recipe.CrusherRecipe;
import eu.pintergabor.crusher.recipe.base.ProcessingRecipeJsonBuilder;
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

/**
 * Generate crusher recipes
 */
public abstract class ProcessingRecipeGenerator extends RecipeGenerator {
    public float experience = 0.1f;
    public int cookingTime = 100;

    public ProcessingRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
    }

    /**
     * Create crushing recipe from input item
     *
     * @param input  Input item
     * @param output Output item
     * @param count  Number of output items
     */
    @SuppressWarnings({"unused", "SameParameterValue"})
    protected void createCrusherRecipe(
            ItemConvertible input,
            ItemConvertible output,
            int count
    ) {
        ProcessingRecipeJsonBuilder.create(
                        Ingredient.ofItem(input),
                        RecipeCategory.MISC,
                        new ItemStack(output, count),
                        experience,
                        cookingTime,
                        CrusherRecipe::new)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter, getItemPath(output.asItem()) + "_from_crushing_" + getItemPath(input));
    }

    /**
     * Create crushing recipe from input item tag
     *
     * @param tag    Input item tag
     * @param output Output item
     * @param count  Number of output items
     */
    @SuppressWarnings({"unused", "SameParameterValue"})
    protected void createCrusherRecipe(
            TagKey<Item> tag,
            ItemConvertible output,
            int count
    ) {
        try {
            RegistryWrapper.Impl<Item> registryLookup = registries.getOrThrow(RegistryKeys.ITEM);
            Ingredient i = Ingredient.fromTag(registryLookup.getOrThrow(tag));
            ProcessingRecipeJsonBuilder.create(
                            i,
                            RecipeCategory.MISC,
                            new ItemStack(output, count),
                            experience,
                            cookingTime,
                            CrusherRecipe::new)
                    .criterion("has_" + tag.id().getPath(), conditionsFromTag(tag))
                    .offerTo(exporter, getItemPath(output.asItem()) + "_from_crushing_" + tag.id().getPath());
        } catch (IllegalStateException e) {
            // If tag does not exist, then do not generate the recipe.
        }
    }

    /**
     * Create compressing recipe from input item
     *
     * @param input  Input item
     * @param output Output item
     * @param count  Number of output items
     */
    @SuppressWarnings({"unused", "SameParameterValue"})
    protected void createCompressorRecipe(
            ItemConvertible input,
            ItemConvertible output,
            int count
    ) {
        ProcessingRecipeJsonBuilder.create(
                        Ingredient.ofItem(input),
                        RecipeCategory.MISC,
                        new ItemStack(output, count),
                        experience,
                        cookingTime,
                        CompressorRecipe::new)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter, getItemPath(output.asItem()) + "_from_compressing_" + getItemPath(input));
    }

    /**
     * Create compressing recipe from input item tag
     *
     * @param tag    Input item tag
     * @param output Output item
     * @param count  Number of output items
     */
    @SuppressWarnings({"unused", "SameParameterValue"})
    protected void createCompressorRecipe(
            TagKey<Item> tag,
            ItemConvertible output,
            int count
    ) {
        try {
            RegistryWrapper.Impl<Item> registryLookup = registries.getOrThrow(RegistryKeys.ITEM);
            Ingredient i = Ingredient.fromTag(registryLookup.getOrThrow(tag));
            ProcessingRecipeJsonBuilder.create(
                            i,
                            RecipeCategory.MISC,
                            new ItemStack(output, count),
                            experience,
                            cookingTime,
                            CompressorRecipe::new)
                    .criterion("has_" + tag.id().getPath(), conditionsFromTag(tag))
                    .offerTo(exporter, getItemPath(output.asItem()) + "_from_compressing_" + tag.id().getPath());
        } catch (IllegalStateException e) {
            // If tag does not exist, then do not generate the recipe.
        }
    }
}
