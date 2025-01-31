package eu.pintergabor.crusher.datagen;

import eu.pintergabor.crusher.blocks.ModBlocks;
import eu.pintergabor.crusher.datagen.recipe.CrusherRecipeGenerator;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.item.ItemStack;
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
        // The crusher.
        createShaped(RecipeCategory.DECORATIONS, ModBlocks.CRUSHER_BLOCK)
                .pattern("###")
                .pattern("#P#")
                .pattern("###")
                .input('#', ItemTags.STONE_CRAFTING_MATERIALS)
                .input('P', Items.IRON_PICKAXE)
                .criterion("has_cobblestone", conditionsFromTag(ItemTags.STONE_CRAFTING_MATERIALS))
                .criterion(hasItem(Items.IRON_PICKAXE), conditionsFromItem(Items.IRON_PICKAXE))
                .offerTo(exporter);
        // Gravel from gravel sources.
        createCrushingTag(ModItemTagProvider.GRAVEL_SOURCES, Items.GRAVEL.getDefaultStack());
        // Sand from sandstones and gravel.
        createCrushingTag(ModItemTagProvider.SAND_SOURCES, Items.SAND.getDefaultStack());
        // Red sand from red sandstones and other redish blocks.
        createCrushingTag(ModItemTagProvider.RED_SAND_SOURCES, Items.RED_SAND.getDefaultStack());
        // Blaze powder from blaze rod.
        createCrushingItem(Items.BLAZE_ROD, new ItemStack(Items.BLAZE_POWDER, 2));
        // Copper from things made of copper.
        createCrushingItem(Items.COPPER_DOOR, new ItemStack(Items.COPPER_INGOT, 2));
        createCrushingItem(Items.WAXED_COPPER_DOOR, new ItemStack(Items.COPPER_INGOT, 2));
        createCrushingItem(Items.EXPOSED_COPPER_DOOR, new ItemStack(Items.COPPER_INGOT, 2));
        createCrushingItem(Items.WAXED_EXPOSED_COPPER_DOOR, new ItemStack(Items.COPPER_INGOT, 2));
        createCrushingItem(Items.WEATHERED_COPPER_DOOR, new ItemStack(Items.COPPER_INGOT, 2));
        createCrushingItem(Items.WAXED_WEATHERED_COPPER_DOOR, new ItemStack(Items.COPPER_INGOT, 2));
        createCrushingItem(Items.OXIDIZED_COPPER_DOOR, new ItemStack(Items.COPPER_INGOT, 2));
        createCrushingItem(Items.WAXED_OXIDIZED_COPPER_DOOR, new ItemStack(Items.COPPER_INGOT, 2));
        createCrushingItem(Items.COPPER_TRAPDOOR, new ItemStack(Items.COPPER_INGOT, 4));
        createCrushingItem(Items.WAXED_COPPER_TRAPDOOR, new ItemStack(Items.COPPER_INGOT, 4));
        createCrushingItem(Items.EXPOSED_COPPER_TRAPDOOR, new ItemStack(Items.COPPER_INGOT, 4));
        createCrushingItem(Items.WAXED_EXPOSED_COPPER_TRAPDOOR, new ItemStack(Items.COPPER_INGOT, 4));
        createCrushingItem(Items.WEATHERED_COPPER_TRAPDOOR, new ItemStack(Items.COPPER_INGOT, 4));
        createCrushingItem(Items.WAXED_WEATHERED_COPPER_TRAPDOOR, new ItemStack(Items.COPPER_INGOT, 4));
        createCrushingItem(Items.OXIDIZED_COPPER_TRAPDOOR, new ItemStack(Items.COPPER_INGOT, 4));
        createCrushingItem(Items.WAXED_OXIDIZED_COPPER_TRAPDOOR, new ItemStack(Items.COPPER_INGOT, 4));
        createCrushingItem(Items.COPPER_BLOCK, new ItemStack(Items.COPPER_INGOT, 9));
        createCrushingItem(Items.WAXED_COPPER_BLOCK, new ItemStack(Items.COPPER_INGOT, 9));
        createCrushingItem(Items.EXPOSED_COPPER, new ItemStack(Items.COPPER_INGOT, 9));
        createCrushingItem(Items.WAXED_EXPOSED_COPPER, new ItemStack(Items.COPPER_INGOT, 9));
        createCrushingItem(Items.WEATHERED_COPPER, new ItemStack(Items.COPPER_INGOT, 9));
        createCrushingItem(Items.WAXED_WEATHERED_COPPER, new ItemStack(Items.COPPER_INGOT, 9));
        createCrushingItem(Items.OXIDIZED_COPPER, new ItemStack(Items.COPPER_INGOT, 9));
        createCrushingItem(Items.WAXED_OXIDIZED_COPPER, new ItemStack(Items.COPPER_INGOT, 9));
        // Iron from things made of iron.
        createCrushingItem(Items.BUCKET, new ItemStack(Items.IRON_INGOT, 3));
        createCrushingItem(Items.WATER_BUCKET, new ItemStack(Items.IRON_INGOT, 3));
        createCrushingItem(Items.LAVA_BUCKET, new ItemStack(Items.IRON_INGOT, 3));
        createCrushingItem(Items.MILK_BUCKET, new ItemStack(Items.IRON_INGOT, 3));
        createCrushingItem(Items.TROPICAL_FISH_BUCKET, new ItemStack(Items.IRON_INGOT, 3));
        createCrushingItem(Items.SALMON_BUCKET, new ItemStack(Items.IRON_INGOT, 3));
        createCrushingItem(Items.PUFFERFISH_BUCKET, new ItemStack(Items.IRON_INGOT, 3));
        createCrushingItem(Items.COD_BUCKET, new ItemStack(Items.IRON_INGOT, 3));
        createCrushingItem(Items.AXOLOTL_BUCKET, new ItemStack(Items.IRON_INGOT, 3));
        createCrushingItem(Items.TADPOLE_BUCKET, new ItemStack(Items.IRON_INGOT, 3));
        createCrushingItem(Items.POWDER_SNOW_BUCKET, new ItemStack(Items.IRON_INGOT, 3));
        createCrushingItem(Items.SHEARS, new ItemStack(Items.IRON_INGOT, 2));
        createCrushingItem(Items.IRON_DOOR, new ItemStack(Items.IRON_INGOT, 2));
        createCrushingItem(Items.IRON_TRAPDOOR, new ItemStack(Items.IRON_INGOT, 4));
        createCrushingItem(Items.CAULDRON, new ItemStack(Items.IRON_INGOT, 7));
        createCrushingItem(Items.IRON_AXE, new ItemStack(Items.IRON_INGOT, 3));
        createCrushingItem(Items.IRON_PICKAXE, new ItemStack(Items.IRON_INGOT, 3));
        createCrushingItem(Items.IRON_HOE, new ItemStack(Items.IRON_INGOT, 2));
        createCrushingItem(Items.IRON_SHOVEL, new ItemStack(Items.IRON_INGOT, 1));
        createCrushingItem(Items.IRON_SWORD, new ItemStack(Items.IRON_INGOT, 2));
        createCrushingItem(Items.IRON_BLOCK, new ItemStack(Items.IRON_INGOT, 9));
        createCrushingItem(Items.IRON_HELMET, new ItemStack(Items.IRON_INGOT, 5));
        createCrushingItem(Items.IRON_CHESTPLATE, new ItemStack(Items.IRON_INGOT, 8));
        createCrushingItem(Items.IRON_LEGGINGS, new ItemStack(Items.IRON_INGOT, 7));
        createCrushingItem(Items.IRON_BOOTS, new ItemStack(Items.IRON_INGOT, 4));
        createCrushingItem(Items.IRON_HORSE_ARMOR, new ItemStack(Items.IRON_INGOT, 7));
        // Gold from things made of gold.
        cookingTime = 150;
        createCrushingItem(Items.GOLDEN_AXE, new ItemStack(Items.GOLD_INGOT, 3));
        createCrushingItem(Items.GOLDEN_PICKAXE, new ItemStack(Items.GOLD_INGOT, 3));
        createCrushingItem(Items.GOLDEN_HOE, new ItemStack(Items.GOLD_INGOT, 2));
        createCrushingItem(Items.GOLDEN_SHOVEL, new ItemStack(Items.GOLD_INGOT, 1));
        createCrushingItem(Items.GOLDEN_SWORD, new ItemStack(Items.GOLD_INGOT, 2));
        createCrushingItem(Items.GOLD_BLOCK, new ItemStack(Items.GOLD_INGOT, 9));
        createCrushingItem(Items.GOLDEN_HELMET, new ItemStack(Items.GOLD_INGOT, 5));
        createCrushingItem(Items.GOLDEN_CHESTPLATE, new ItemStack(Items.GOLD_INGOT, 8));
        createCrushingItem(Items.GOLDEN_LEGGINGS, new ItemStack(Items.GOLD_INGOT, 7));
        createCrushingItem(Items.GOLDEN_BOOTS, new ItemStack(Items.GOLD_INGOT, 4));
        createCrushingItem(Items.GOLDEN_HORSE_ARMOR, new ItemStack(Items.GOLD_INGOT, 7));
        // Diamond from things made of diamonds.
        cookingTime = 200;
        createCrushingItem(Items.DIAMOND_AXE, new ItemStack(Items.DIAMOND, 3));
        createCrushingItem(Items.DIAMOND_PICKAXE, new ItemStack(Items.DIAMOND, 3));
        createCrushingItem(Items.DIAMOND_HOE, new ItemStack(Items.DIAMOND, 2));
        createCrushingItem(Items.DIAMOND_SHOVEL, new ItemStack(Items.DIAMOND, 1));
        createCrushingItem(Items.DIAMOND_SWORD, new ItemStack(Items.DIAMOND, 2));
        createCrushingItem(Items.DIAMOND_BLOCK, new ItemStack(Items.DIAMOND, 9));
        createCrushingItem(Items.DIAMOND_HELMET, new ItemStack(Items.DIAMOND, 5));
        createCrushingItem(Items.DIAMOND_CHESTPLATE, new ItemStack(Items.DIAMOND, 8));
        createCrushingItem(Items.DIAMOND_LEGGINGS, new ItemStack(Items.DIAMOND, 7));
        createCrushingItem(Items.DIAMOND_BOOTS, new ItemStack(Items.DIAMOND, 4));
        createCrushingItem(Items.DIAMOND_HORSE_ARMOR, new ItemStack(Items.DIAMOND, 7));
        // Sand from sand fast.
        experience = 1.0f;
        cookingTime = 10;
        createCrushingItem(Items.SAND, Items.SAND.getDefaultStack());
        createCrushingItem(Items.RED_SAND, Items.RED_SAND.getDefaultStack());
    }
}
