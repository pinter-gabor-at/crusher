package eu.pintergabor.crusher.datagen;

import eu.pintergabor.crusher.blocks.ModBlocks;
import eu.pintergabor.crusher.datagen.recipe.CrusherRecipeGenerator;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

public class ModRecipeGenerator extends CrusherRecipeGenerator {

    public ModRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
    }

    @Override
    public void generate() {
        // The crusher
        createShaped(RecipeCategory.DECORATIONS, ModBlocks.CRUSHER)
                .pattern("###")
                .pattern("#P#")
                .pattern("###")
                .input('#', ItemTags.STONE_CRAFTING_MATERIALS)
                .input('P', Items.IRON_PICKAXE)
                .criterion("has_cobblestone", conditionsFromTag(ItemTags.STONE_CRAFTING_MATERIALS))
                .criterion(hasItem(Items.IRON_PICKAXE), conditionsFromItem(Items.IRON_PICKAXE))
                .offerTo(exporter);
        // Gravel from gravel sources.
        createCrushingTag(ModItemTagProvider.GRAVEL_SOURCES, Items.GRAVEL);
        // Sand from sandstones and gravel.
        createCrushingTag(ModItemTagProvider.SAND_SOURCES, Items.SAND);
        // Red sand from red sandstones and other redish blocks.
        createCrushingTag(ModItemTagProvider.RED_SAND_SOURCES, Items.RED_SAND);
        // Sand from sand fast.
        experience = 1.0f;
        cookingTime = 10;
        createCrushingItem(Items.SAND, Items.SAND);
        createCrushingItem(Items.RED_SAND, Items.RED_SAND);
    }
}
