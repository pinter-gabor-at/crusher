package eu.pintergabor.crusher.datagen;

import eu.pintergabor.crusher.blocks.ModBlocks;
import eu.pintergabor.crusher.datagen.recipe.CrusherRecipeGenerator;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

public class ModRecipeGenerator extends RecipeGenerator {
    final CrusherRecipeGenerator crusherRecipeGenerator;

    public ModRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
        crusherRecipeGenerator = new CrusherRecipeGenerator(registries, exporter);
    }

    @Override
    public void generate() {
        // The crusher.
        createShaped(RecipeCategory.DECORATIONS, ModBlocks.CRUSHER_BLOCK)
                .pattern("###")
                .pattern("P P")
                .pattern("###")
                .input('#', ItemTags.STONE_CRAFTING_MATERIALS)
                .input('P', Items.IRON_PICKAXE)
                .criterion("has_cobblestone", conditionsFromTag(ItemTags.STONE_CRAFTING_MATERIALS))
                .criterion(hasItem(Items.IRON_PICKAXE), conditionsFromItem(Items.IRON_PICKAXE))
                .offerTo(exporter);
        // The crushing recipes.
        crusherRecipeGenerator.generate();
    }
}
