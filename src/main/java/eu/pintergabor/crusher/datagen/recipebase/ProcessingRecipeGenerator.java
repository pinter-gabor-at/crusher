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
    public float experience = 0.1F;
    public int cookingTime = 100;

    public ProcessingRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
    }

    /**
     * Create crushing recipe from input item
     *
     * @param input       Input item
     * @param inputCount  Number of input items
     * @param output      Output item
     * @param outputCount Number of output items
     */
    @SuppressWarnings({"unused", "SameParameterValue"})
    protected void createCrusherRecipe(
        ItemConvertible input,
        int inputCount,
        ItemConvertible output,
        int outputCount
    ) {
        final Ingredient ingredient = Ingredient.ofItem(input);
        ProcessingRecipeJsonBuilder.create(
                ingredient,
                inputCount,
                RecipeCategory.MISC,
                new ItemStack(output, outputCount),
                experience,
                cookingTime,
                CrusherRecipe::new)
            .criterion(hasItem(input), conditionsFromItem(input))
            .offerTo(exporter,
                getItemPath(output.asItem()) + "_from_crushing_" + getItemPath(input));
    }

    /**
     * Create crushing recipe from input item tag
     *
     * @param tag         Input item tag
     * @param tagCount    Number of input items
     * @param output      Output item
     * @param outputCount Number of output items
     */
    @SuppressWarnings({"unused", "SameParameterValue"})
    protected void createCrusherRecipe(
        TagKey<Item> tag,
        int tagCount,
        ItemConvertible output,
        int outputCount
    ) {
        try {
            final RegistryWrapper.Impl<Item> registryLookup = registries.getOrThrow(RegistryKeys.ITEM);
            final Ingredient ingredient = Ingredient.fromTag(registryLookup.getOrThrow(tag));
            ProcessingRecipeJsonBuilder.create(
                    ingredient,
                    tagCount,
                    RecipeCategory.MISC,
                    new ItemStack(output, outputCount),
                    experience,
                    cookingTime,
                    CrusherRecipe::new)
                .criterion("has_" + tag.id().getPath(), conditionsFromTag(tag))
                .offerTo(exporter,
                    getItemPath(output.asItem()) + "_from_crushing_" + tag.id().getPath());
        } catch (IllegalStateException e) {
            // If tag does not exist, then do not generate the recipe.
        }
    }

    /**
     * Create compressing recipe from input item
     *
     * @param input       Input item
     * @param inputCount  Number of input items
     * @param output      Output item
     * @param outputCount Number of output items
     */
    @SuppressWarnings({"unused", "SameParameterValue"})
    protected void createCompressorRecipe(
        ItemConvertible input,
        int inputCount,
        ItemConvertible output,
        int outputCount
    ) {
        final Ingredient ingredient = Ingredient.ofItem(input);
        ProcessingRecipeJsonBuilder.create(
                ingredient,
                inputCount,
                RecipeCategory.MISC,
                new ItemStack(output, outputCount),
                experience,
                cookingTime,
                CompressorRecipe::new)
            .criterion(hasItem(input), conditionsFromItem(input))
            .offerTo(exporter,
                getItemPath(output.asItem()) + "_from_compressing_" + getItemPath(input));
    }

    /**
     * Create compressing recipe from input item tag
     *
     * @param tag         Input item tag
     * @param tagCount    Number of input items
     * @param output      Output item
     * @param outputCount Number of output items
     */
    @SuppressWarnings({"unused", "SameParameterValue"})
    protected void createCompressorRecipe(
        TagKey<Item> tag,
        int tagCount,
        ItemConvertible output,
        int outputCount
    ) {
        try {
            final RegistryWrapper.Impl<Item> registryLookup = registries.getOrThrow(RegistryKeys.ITEM);
            final Ingredient ingredient = Ingredient.fromTag(registryLookup.getOrThrow(tag));
            ProcessingRecipeJsonBuilder.create(
                    ingredient,
                    tagCount,
                    RecipeCategory.MISC,
                    new ItemStack(output, outputCount),
                    experience,
                    cookingTime,
                    CompressorRecipe::new)
                .criterion("has_" + tag.id().getPath(), conditionsFromTag(tag))
                .offerTo(exporter,
                    getItemPath(output.asItem()) + "_from_compressing_" + tag.id().getPath());
        } catch (IllegalStateException e) {
            // If tag does not exist, then do not generate the recipe.
        }
    }
}
