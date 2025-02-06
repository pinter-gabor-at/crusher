package eu.pintergabor.crusher.datagen;

import eu.pintergabor.crusher.blocks.ModBlocks;
import eu.pintergabor.crusher.datagen.recipebase.ProcessingRecipeGenerator;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

public class ModRecipeGenerator extends ProcessingRecipeGenerator {

    public ModRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
    }

    private void generateCrusherRecipes() {
        // Defaults.
        experience = 0.1f;
        cookingTime = 100;
        // Gravel from gravel sources.
        createCrusherRecipe(ModItemTagProvider.GRAVEL_SOURCES, 1, Items.GRAVEL, 1);
        createCrusherRecipe(Items.STONE_AXE, 1, Items.GRAVEL, 3);
        createCrusherRecipe(Items.STONE_PICKAXE, 1, Items.GRAVEL, 3);
        createCrusherRecipe(Items.STONE_HOE, 1, Items.GRAVEL, 2);
        createCrusherRecipe(Items.STONE_SHOVEL, 1, Items.GRAVEL, 1);
        createCrusherRecipe(Items.STONE_SWORD, 1, Items.GRAVEL, 2);
        // 8 cobblestone => 1 furnace => 9 gravel => 9 cobblestone
        // This allows the free generation of cobblestone.
        createCrusherRecipe(Items.FURNACE, 1, Items.GRAVEL, 9);
        // Sand from sandstones and gravel.
        createCrusherRecipe(ModItemTagProvider.SAND_SOURCES, 1, Items.SAND, 1);
        // Red sand from red sandstones and other redish blocks.
        createCrusherRecipe(ModItemTagProvider.RED_SAND_SOURCES, 1, Items.RED_SAND, 1);
        // Blaze powder from blaze rod.
        createCrusherRecipe(Items.BLAZE_ROD, 1, Items.BLAZE_POWDER, 2);
        // Bone meal from bone.
        createCrusherRecipe(Items.BONE, 1, Items.BONE_MEAL, 6);
        // 8 bone meals => 1 bone block => 10 bone meals
        // This allows the free generation of bone meal.
        createCrusherRecipe(Items.BONE_BLOCK, 1, Items.BONE_MEAL, 10);
        // Sticks from planks and other wooden things.
        createCrusherRecipe(ItemTags.PLANKS, 1, Items.STICK, 4);
        createCrusherRecipe(Items.STICK, 1, Items.STICK, 1);
        createCrusherRecipe(Items.TORCH, 1, Items.STICK, 1);
        createCrusherRecipe(ItemTags.SAPLINGS, 1, Items.STICK, 2);
        createCrusherRecipe(Items.OAK_STAIRS, 1, Items.STICK, 3);
        createCrusherRecipe(Items.SPRUCE_STAIRS, 1, Items.STICK, 3);
        createCrusherRecipe(Items.BIRCH_STAIRS, 1, Items.STICK, 3);
        createCrusherRecipe(Items.JUNGLE_STAIRS, 1, Items.STICK, 3);
        createCrusherRecipe(Items.ACACIA_STAIRS, 1, Items.STICK, 3);
        createCrusherRecipe(Items.DARK_OAK_STAIRS, 1, Items.STICK, 3);
        createCrusherRecipe(Items.MANGROVE_STAIRS, 1, Items.STICK, 3);
        createCrusherRecipe(Items.CHERRY_STAIRS, 1, Items.STICK, 3);
        createCrusherRecipe(Items.PALE_OAK_STAIRS, 1, Items.STICK, 3);
        createCrusherRecipe(Items.BAMBOO_STAIRS, 1, Items.STICK, 3);
        createCrusherRecipe(Items.CRIMSON_STAIRS, 1, Items.STICK, 3);
        createCrusherRecipe(Items.WARPED_STAIRS, 1, Items.STICK, 3);
        createCrusherRecipe(Items.OAK_SLAB, 1, Items.STICK, 1);
        createCrusherRecipe(Items.SPRUCE_SLAB, 1, Items.STICK, 1);
        createCrusherRecipe(Items.BIRCH_SLAB, 1, Items.STICK, 1);
        createCrusherRecipe(Items.JUNGLE_SLAB, 1, Items.STICK, 1);
        createCrusherRecipe(Items.ACACIA_SLAB, 1, Items.STICK, 1);
        createCrusherRecipe(Items.DARK_OAK_SLAB, 1, Items.STICK, 1);
        createCrusherRecipe(Items.MANGROVE_SLAB, 1, Items.STICK, 1);
        createCrusherRecipe(Items.CHERRY_SLAB, 1, Items.STICK, 1);
        createCrusherRecipe(Items.PALE_OAK_SLAB, 1, Items.STICK, 1);
        createCrusherRecipe(Items.BAMBOO_SLAB, 1, Items.STICK, 1);
        createCrusherRecipe(Items.CRIMSON_SLAB, 1, Items.STICK, 1);
        createCrusherRecipe(Items.WARPED_SLAB, 1, Items.STICK, 1);
        createCrusherRecipe(Items.OAK_FENCE, 1, Items.STICK, 3);
        createCrusherRecipe(Items.SPRUCE_FENCE, 1, Items.STICK, 3);
        createCrusherRecipe(Items.BIRCH_FENCE, 1, Items.STICK, 3);
        createCrusherRecipe(Items.JUNGLE_FENCE, 1, Items.STICK, 3);
        createCrusherRecipe(Items.ACACIA_FENCE, 1, Items.STICK, 3);
        createCrusherRecipe(Items.DARK_OAK_FENCE, 1, Items.STICK, 3);
        createCrusherRecipe(Items.MANGROVE_FENCE, 1, Items.STICK, 3);
        createCrusherRecipe(Items.CHERRY_FENCE, 1, Items.STICK, 3);
        createCrusherRecipe(Items.PALE_OAK_FENCE, 1, Items.STICK, 3);
        createCrusherRecipe(Items.BAMBOO_FENCE, 1, Items.STICK, 3);
        createCrusherRecipe(Items.CRIMSON_FENCE, 1, Items.STICK, 3);
        createCrusherRecipe(Items.WARPED_FENCE, 1, Items.STICK, 3);
        createCrusherRecipe(Items.OAK_FENCE_GATE, 1, Items.STICK, 8);
        createCrusherRecipe(Items.SPRUCE_FENCE_GATE, 1, Items.STICK, 8);
        createCrusherRecipe(Items.BIRCH_FENCE_GATE, 1, Items.STICK, 8);
        createCrusherRecipe(Items.JUNGLE_FENCE_GATE, 1, Items.STICK, 8);
        createCrusherRecipe(Items.ACACIA_FENCE_GATE, 1, Items.STICK, 8);
        createCrusherRecipe(Items.DARK_OAK_FENCE_GATE, 1, Items.STICK, 8);
        createCrusherRecipe(Items.MANGROVE_FENCE_GATE, 1, Items.STICK, 8);
        createCrusherRecipe(Items.CHERRY_FENCE_GATE, 1, Items.STICK, 8);
        createCrusherRecipe(Items.PALE_OAK_FENCE_GATE, 1, Items.STICK, 8);
        createCrusherRecipe(Items.BAMBOO_FENCE_GATE, 1, Items.STICK, 8);
        createCrusherRecipe(Items.CRIMSON_FENCE_GATE, 1, Items.STICK, 8);
        createCrusherRecipe(Items.WARPED_FENCE_GATE, 1, Items.STICK, 8);
        createCrusherRecipe(Items.OAK_SIGN, 1, Items.STICK, 4);
        createCrusherRecipe(Items.SPRUCE_SIGN, 1, Items.STICK, 4);
        createCrusherRecipe(Items.BIRCH_SIGN, 1, Items.STICK, 4);
        createCrusherRecipe(Items.JUNGLE_SIGN, 1, Items.STICK, 4);
        createCrusherRecipe(Items.ACACIA_SIGN, 1, Items.STICK, 4);
        createCrusherRecipe(Items.DARK_OAK_SIGN, 1, Items.STICK, 4);
        createCrusherRecipe(Items.MANGROVE_SIGN, 1, Items.STICK, 4);
        createCrusherRecipe(Items.CHERRY_SIGN, 1, Items.STICK, 4);
        createCrusherRecipe(Items.PALE_OAK_SIGN, 1, Items.STICK, 4);
        createCrusherRecipe(Items.BAMBOO_SIGN, 1, Items.STICK, 4);
        createCrusherRecipe(Items.CRIMSON_SIGN, 1, Items.STICK, 4);
        createCrusherRecipe(Items.WARPED_SIGN, 1, Items.STICK, 4);
        createCrusherRecipe(Items.OAK_PRESSURE_PLATE, 1, Items.STICK, 4);
        createCrusherRecipe(Items.SPRUCE_PRESSURE_PLATE, 1, Items.STICK, 4);
        createCrusherRecipe(Items.BIRCH_PRESSURE_PLATE, 1, Items.STICK, 4);
        createCrusherRecipe(Items.JUNGLE_PRESSURE_PLATE, 1, Items.STICK, 4);
        createCrusherRecipe(Items.ACACIA_PRESSURE_PLATE, 1, Items.STICK, 4);
        createCrusherRecipe(Items.DARK_OAK_PRESSURE_PLATE, 1, Items.STICK, 4);
        createCrusherRecipe(Items.MANGROVE_PRESSURE_PLATE, 1, Items.STICK, 4);
        createCrusherRecipe(Items.CHERRY_PRESSURE_PLATE, 1, Items.STICK, 4);
        createCrusherRecipe(Items.PALE_OAK_PRESSURE_PLATE, 1, Items.STICK, 4);
        createCrusherRecipe(Items.BAMBOO_PRESSURE_PLATE, 1, Items.STICK, 4);
        createCrusherRecipe(Items.CRIMSON_PRESSURE_PLATE, 1, Items.STICK, 4);
        createCrusherRecipe(Items.WARPED_PRESSURE_PLATE, 1, Items.STICK, 4);
        // 7 sticks => 3 ladders => 9 sticks
        // This allows free generation of sticks, for those who discover it.
        createCrusherRecipe(Items.LADDER, 1, Items.STICK, 3);
        // Planks from items made of planks.
        createCrusherRecipe(Items.OAK_DOOR, 1, Items.OAK_PLANKS, 2);
        createCrusherRecipe(Items.SPRUCE_DOOR, 1, Items.SPRUCE_PLANKS, 2);
        createCrusherRecipe(Items.BIRCH_DOOR, 1, Items.BIRCH_PLANKS, 2);
        createCrusherRecipe(Items.JUNGLE_DOOR, 1, Items.JUNGLE_PLANKS, 2);
        createCrusherRecipe(Items.ACACIA_DOOR, 1, Items.ACACIA_PLANKS, 2);
        createCrusherRecipe(Items.DARK_OAK_DOOR, 1, Items.DARK_OAK_PLANKS, 2);
        createCrusherRecipe(Items.MANGROVE_DOOR, 1, Items.MANGROVE_PLANKS, 2);
        createCrusherRecipe(Items.CHERRY_DOOR, 1, Items.CHERRY_PLANKS, 2);
        createCrusherRecipe(Items.PALE_OAK_DOOR, 1, Items.PALE_OAK_PLANKS, 2);
        createCrusherRecipe(Items.BAMBOO_DOOR, 1, Items.BAMBOO_PLANKS, 2);
        createCrusherRecipe(Items.CRIMSON_DOOR, 1, Items.CRIMSON_PLANKS, 2);
        createCrusherRecipe(Items.WARPED_DOOR, 1, Items.WARPED_PLANKS, 2);
        createCrusherRecipe(Items.OAK_TRAPDOOR, 1, Items.OAK_PLANKS, 3);
        createCrusherRecipe(Items.SPRUCE_TRAPDOOR, 1, Items.SPRUCE_PLANKS, 3);
        createCrusherRecipe(Items.BIRCH_TRAPDOOR, 1, Items.BIRCH_PLANKS, 3);
        createCrusherRecipe(Items.JUNGLE_TRAPDOOR, 1, Items.JUNGLE_PLANKS, 3);
        createCrusherRecipe(Items.ACACIA_TRAPDOOR, 1, Items.ACACIA_PLANKS, 3);
        createCrusherRecipe(Items.DARK_OAK_TRAPDOOR, 1, Items.DARK_OAK_PLANKS, 3);
        createCrusherRecipe(Items.MANGROVE_TRAPDOOR, 1, Items.MANGROVE_PLANKS, 3);
        createCrusherRecipe(Items.CHERRY_TRAPDOOR, 1, Items.CHERRY_PLANKS, 3);
        createCrusherRecipe(Items.PALE_OAK_TRAPDOOR, 1, Items.PALE_OAK_PLANKS, 3);
        createCrusherRecipe(Items.BAMBOO_TRAPDOOR, 1, Items.BAMBOO_PLANKS, 3);
        createCrusherRecipe(Items.CRIMSON_TRAPDOOR, 1, Items.CRIMSON_PLANKS, 3);
        createCrusherRecipe(Items.WARPED_TRAPDOOR, 1, Items.WARPED_PLANKS, 3);
        createCrusherRecipe(Items.OAK_BOAT, 1, Items.OAK_PLANKS, 5);
        createCrusherRecipe(Items.SPRUCE_BOAT, 1, Items.SPRUCE_PLANKS, 5);
        createCrusherRecipe(Items.BIRCH_BOAT, 1, Items.BIRCH_PLANKS, 5);
        createCrusherRecipe(Items.JUNGLE_BOAT, 1, Items.JUNGLE_PLANKS, 5);
        createCrusherRecipe(Items.ACACIA_BOAT, 1, Items.ACACIA_PLANKS, 5);
        createCrusherRecipe(Items.DARK_OAK_BOAT, 1, Items.DARK_OAK_PLANKS, 5);
        createCrusherRecipe(Items.MANGROVE_BOAT, 1, Items.MANGROVE_PLANKS, 5);
        createCrusherRecipe(Items.CHERRY_BOAT, 1, Items.CHERRY_PLANKS, 5);
        createCrusherRecipe(Items.PALE_OAK_BOAT, 1, Items.PALE_OAK_PLANKS, 5);
        createCrusherRecipe(Items.BAMBOO_RAFT, 1, Items.BAMBOO_PLANKS, 5);
        createCrusherRecipe(Items.CHEST, 1, Items.OAK_PLANKS, 8);
        createCrusherRecipe(Items.CRAFTING_TABLE, 1, Items.OAK_PLANKS, 4);
        createCrusherRecipe(Items.OAK_CHEST_BOAT, 1, Items.OAK_PLANKS, 13);
        createCrusherRecipe(Items.SPRUCE_CHEST_BOAT, 1, Items.SPRUCE_PLANKS, 13);
        createCrusherRecipe(Items.BIRCH_CHEST_BOAT, 1, Items.BIRCH_PLANKS, 13);
        createCrusherRecipe(Items.JUNGLE_CHEST_BOAT, 1, Items.JUNGLE_PLANKS, 13);
        createCrusherRecipe(Items.ACACIA_CHEST_BOAT, 1, Items.ACACIA_PLANKS, 13);
        createCrusherRecipe(Items.DARK_OAK_CHEST_BOAT, 1, Items.DARK_OAK_PLANKS, 13);
        createCrusherRecipe(Items.MANGROVE_CHEST_BOAT, 1, Items.MANGROVE_PLANKS, 13);
        createCrusherRecipe(Items.CHERRY_CHEST_BOAT, 1, Items.CHERRY_PLANKS, 13);
        createCrusherRecipe(Items.PALE_OAK_CHEST_BOAT, 1, Items.PALE_OAK_PLANKS, 13);
        createCrusherRecipe(Items.BAMBOO_CHEST_RAFT, 1, Items.BAMBOO_PLANKS, 13);
        createCrusherRecipe(Items.WOODEN_AXE, 1, Items.OAK_PLANKS, 3);
        createCrusherRecipe(Items.WOODEN_PICKAXE, 1, Items.OAK_PLANKS, 3);
        createCrusherRecipe(Items.WOODEN_HOE, 1, Items.OAK_PLANKS, 2);
        createCrusherRecipe(Items.WOODEN_SHOVEL, 1, Items.OAK_PLANKS, 1);
        createCrusherRecipe(Items.WOODEN_SWORD, 1, Items.OAK_PLANKS, 2);
        // Coal from logs.
        createCrusherRecipe(ItemTags.LOGS, 1, Items.CHARCOAL, 1);
        // Sugar, rotten flesh or dirt from food, except golden apple and golden carrot.
        createCrusherRecipe(ConventionalItemTags.BERRY_FOODS, 1, Items.SUGAR, 1);
        createCrusherRecipe(ConventionalItemTags.CANDY_FOODS, 1, Items.SUGAR, 1);
        createCrusherRecipe(ConventionalItemTags.COOKIE_FOODS, 1, Items.SUGAR, 1);
        createCrusherRecipe(ConventionalItemTags.PIE_FOODS, 1, Items.SUGAR, 1);
        createCrusherRecipe(ModItemTagProvider.NORMAL_FRUIT_FOODS, 1, Items.SUGAR, 1);
        createCrusherRecipe(ConventionalItemTags.FOOD_POISONING_FOODS, 1, Items.ROTTEN_FLESH, 1);
        createCrusherRecipe(ConventionalItemTags.RAW_FISH_FOODS, 1, Items.ROTTEN_FLESH, 1);
        createCrusherRecipe(ConventionalItemTags.COOKED_FISH_FOODS, 1, Items.ROTTEN_FLESH, 1);
        createCrusherRecipe(ConventionalItemTags.RAW_MEAT_FOODS, 1, Items.ROTTEN_FLESH, 1);
        createCrusherRecipe(ConventionalItemTags.COOKED_MEAT_FOODS, 1, Items.ROTTEN_FLESH, 1);
        createCrusherRecipe(ConventionalItemTags.BREAD_FOODS, 1, Items.DIRT, 1);
        createCrusherRecipe(ConventionalItemTags.EDIBLE_WHEN_PLACED_FOODS, 1, Items.DIRT, 1);
        createCrusherRecipe(ConventionalItemTags.SOUP_FOODS, 1, Items.DIRT, 1);
        createCrusherRecipe(ModItemTagProvider.NORMAL_VEGETABLE_FOODS, 1, Items.DIRT, 1);
        // 8 ingots + 1 apple => 1 golden apple => 9 ingots
        // This allows free generation of gold, for those who discover it.
        cookingTime = 200;
        createCrusherRecipe(Items.GOLDEN_APPLE, 1, Items.RAW_GOLD, 9);
        cookingTime = 600;
        createCrusherRecipe(Items.ENCHANTED_GOLDEN_APPLE, 1, Items.RAW_GOLD, 64);
        // 8 nugget + 1 carrot => 1 golden carrot => 1 ingots => 9 nuggets
        // This allows free generation of gold, for those who discover it.
        cookingTime = 100;
        createCrusherRecipe(Items.GOLDEN_CARROT, 1, Items.RAW_GOLD, 1);
        // Snow from ice
        createCrusherRecipe(Items.ICE, 1, Items.SNOW, 4);
        createCrusherRecipe(Items.BLUE_ICE, 1, Items.SNOW, 4);
        createCrusherRecipe(Items.PACKED_ICE, 1, Items.SNOW, 36);
        createCrusherRecipe(Items.SNOW, 1, Items.SNOW, 1);
        createCrusherRecipe(Items.SNOW_BLOCK, 1, Items.SNOW, 4);
        createCrusherRecipe(Items.SNOWBALL, 1, Items.SNOW, 1);
        // String from soft things.
        createCrusherRecipe(ItemTags.WOOL, 1, Items.STRING, 4);
        createCrusherRecipe(ItemTags.WOOL_CARPETS, 1, Items.STRING, 1);
        createCrusherRecipe(Items.LEAD, 1, Items.STRING, 2);
        // Wax from candles.
        createCrusherRecipe(ItemTags.CANDLES, 1, Items.HONEYCOMB, 1);
        createCrusherRecipe(Items.HONEYCOMB_BLOCK, 1, Items.HONEYCOMB, 4);
        // Glowstone.
        createCrusherRecipe(Items.GLOWSTONE, 1, Items.GLOWSTONE_DUST, 4);
        // Sugar.
        createCrusherRecipe(Items.HONEY_BLOCK, 1, Items.SUGAR, 12);
        createCrusherRecipe(Items.SUGAR_CANE, 1, Items.SUGAR, 2);
        // Gunpowder from TNT.
        // 5 gunpowders + 4 sands => 1 TNT => 6 gunpowder.
        // This allows the generation of gunpowder from sand.
        createCrusherRecipe(Items.TNT, 1, Items.GUNPOWDER, 6);
        // Sponge.
        createCrusherRecipe(Items.WET_SPONGE, 1, Items.SPONGE, 1);
        // Dyes from colored items.
        createCrusherRecipe(Items.LILY_OF_THE_VALLEY, 1, Items.WHITE_DYE, 2);
        createCrusherRecipe(Items.BONE_MEAL, 1, Items.WHITE_DYE, 2);
        createCrusherRecipe(Items.ORANGE_TULIP, 1, Items.ORANGE_DYE, 2);
        createCrusherRecipe(Items.TORCHFLOWER, 1, Items.ORANGE_DYE, 2);
        createCrusherRecipe(Items.OPEN_EYEBLOSSOM, 1, Items.ORANGE_DYE, 2);
        createCrusherRecipe(Items.ALLIUM, 1, Items.MAGENTA_DYE, 2);
        createCrusherRecipe(Items.LILAC, 1, Items.MAGENTA_DYE, 4);
        createCrusherRecipe(Items.BLUE_ORCHID, 1, Items.LIGHT_BLUE_DYE, 2);
        createCrusherRecipe(Items.DANDELION, 1, Items.YELLOW_DYE, 2);
        createCrusherRecipe(Items.SUNFLOWER, 1, Items.YELLOW_DYE, 4);
        createCrusherRecipe(Items.PEONY, 1, Items.PINK_DYE, 4);
        createCrusherRecipe(Items.PINK_PETALS, 1, Items.PINK_DYE, 2);
        createCrusherRecipe(Items.PINK_TULIP, 1, Items.PINK_DYE, 2);
        createCrusherRecipe(Items.CLOSED_EYEBLOSSOM, 1, Items.GRAY_DYE, 2);
        createCrusherRecipe(Items.OXEYE_DAISY, 1, Items.LIGHT_GRAY_DYE, 2);
        createCrusherRecipe(Items.WHITE_TULIP, 1, Items.LIGHT_GRAY_DYE, 2);
        createCrusherRecipe(Items.AZURE_BLUET, 1, Items.LIGHT_GRAY_DYE, 2);
        createCrusherRecipe(Items.PITCHER_PLANT, 1, Items.CYAN_DYE, 4);
        createCrusherRecipe(Items.LAPIS_LAZULI, 1, Items.BLUE_DYE, 3);
        createCrusherRecipe(Items.LAPIS_BLOCK, 1, Items.BLUE_DYE, 32);
        createCrusherRecipe(Items.CORNFLOWER, 1, Items.BLUE_DYE, 2);
        createCrusherRecipe(Items.COCOA_BEANS, 1, Items.BROWN_DYE, 2);
        createCrusherRecipe(Items.CACTUS, 1, Items.GREEN_DYE, 2);
        createCrusherRecipe(Items.ROSE_BUSH, 1, Items.RED_DYE, 4);
        createCrusherRecipe(Items.POPPY, 1, Items.RED_DYE, 2);
        createCrusherRecipe(Items.BEETROOT, 1, Items.RED_DYE, 2);
        createCrusherRecipe(Items.RED_TULIP, 1, Items.RED_DYE, 2);
        createCrusherRecipe(Items.REDSTONE, 1, Items.RED_DYE, 3);
        createCrusherRecipe(Items.REDSTONE_BLOCK, 1, Items.RED_DYE, 32);
        createCrusherRecipe(Items.INK_SAC, 1, Items.BLACK_DYE, 2);
        createCrusherRecipe(Items.WITHER_ROSE, 1, Items.BLACK_DYE, 2);
        createCrusherRecipe(Items.CHARCOAL, 1, Items.BLACK_DYE, 4);
        createCrusherRecipe(Items.COAL, 1, Items.BLACK_DYE, 3);
        createCrusherRecipe(Items.COAL_BLOCK, 1, Items.BLACK_DYE, 32);
        // Concrete powder from concrete.
        createCrusherRecipe(Items.WHITE_CONCRETE, 1, Items.WHITE_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.ORANGE_CONCRETE, 1, Items.ORANGE_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.MAGENTA_CONCRETE, 1, Items.MAGENTA_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.LIGHT_BLUE_CONCRETE, 1, Items.LIGHT_BLUE_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.YELLOW_CONCRETE, 1, Items.YELLOW_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.LIME_CONCRETE, 1, Items.LIME_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.PINK_CONCRETE, 1, Items.PINK_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.GRAY_CONCRETE, 1, Items.GRAY_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.LIGHT_GRAY_CONCRETE, 1, Items.LIGHT_GRAY_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.CYAN_CONCRETE, 1, Items.CYAN_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.PURPLE_CONCRETE, 1, Items.PURPLE_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.BLUE_CONCRETE, 1, Items.BLUE_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.BROWN_CONCRETE, 1, Items.BROWN_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.GREEN_CONCRETE, 1, Items.GREEN_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.RED_CONCRETE, 1, Items.RED_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.BLACK_CONCRETE, 1, Items.BLACK_CONCRETE_POWDER, 1);
        // Copper from things made of copper.
        createCrusherRecipe(Items.COPPER_ORE, 1, Items.RAW_COPPER, 4);
        createCrusherRecipe(Items.DEEPSLATE_COPPER_ORE, 1, Items.RAW_COPPER, 4);
        createCrusherRecipe(Items.COPPER_DOOR, 1, Items.RAW_COPPER, 2);
        createCrusherRecipe(Items.WAXED_COPPER_DOOR, 1, Items.RAW_COPPER, 2);
        createCrusherRecipe(Items.EXPOSED_COPPER_DOOR, 1, Items.RAW_COPPER, 2);
        createCrusherRecipe(Items.WAXED_EXPOSED_COPPER_DOOR, 1, Items.RAW_COPPER, 2);
        createCrusherRecipe(Items.WEATHERED_COPPER_DOOR, 1, Items.RAW_COPPER, 2);
        createCrusherRecipe(Items.WAXED_WEATHERED_COPPER_DOOR, 1, Items.RAW_COPPER, 2);
        createCrusherRecipe(Items.OXIDIZED_COPPER_DOOR, 1, Items.RAW_COPPER, 2);
        createCrusherRecipe(Items.WAXED_OXIDIZED_COPPER_DOOR, 1, Items.RAW_COPPER, 2);
        createCrusherRecipe(Items.COPPER_TRAPDOOR, 1, Items.RAW_COPPER, 4);
        createCrusherRecipe(Items.WAXED_COPPER_TRAPDOOR, 1, Items.RAW_COPPER, 4);
        createCrusherRecipe(Items.EXPOSED_COPPER_TRAPDOOR, 1, Items.RAW_COPPER, 4);
        createCrusherRecipe(Items.WAXED_EXPOSED_COPPER_TRAPDOOR, 1, Items.RAW_COPPER, 4);
        createCrusherRecipe(Items.WEATHERED_COPPER_TRAPDOOR, 1, Items.RAW_COPPER, 4);
        createCrusherRecipe(Items.WAXED_WEATHERED_COPPER_TRAPDOOR, 1, Items.RAW_COPPER, 4);
        createCrusherRecipe(Items.OXIDIZED_COPPER_TRAPDOOR, 1, Items.RAW_COPPER, 4);
        createCrusherRecipe(Items.WAXED_OXIDIZED_COPPER_TRAPDOOR, 1, Items.RAW_COPPER, 4);
        createCrusherRecipe(Items.COPPER_BLOCK, 1, Items.RAW_COPPER, 9);
        createCrusherRecipe(Items.RAW_COPPER_BLOCK, 1, Items.RAW_COPPER, 9);
        createCrusherRecipe(Items.WAXED_COPPER_BLOCK, 1, Items.RAW_COPPER, 9);
        createCrusherRecipe(Items.EXPOSED_COPPER, 1, Items.RAW_COPPER, 9);
        createCrusherRecipe(Items.WAXED_EXPOSED_COPPER, 1, Items.RAW_COPPER, 9);
        createCrusherRecipe(Items.WEATHERED_COPPER, 1, Items.RAW_COPPER, 9);
        createCrusherRecipe(Items.WAXED_WEATHERED_COPPER, 1, Items.RAW_COPPER, 9);
        createCrusherRecipe(Items.OXIDIZED_COPPER, 1, Items.RAW_COPPER, 9);
        createCrusherRecipe(Items.WAXED_OXIDIZED_COPPER, 1, Items.RAW_COPPER, 9);
        // Iron from things made of iron.
        createCrusherRecipe(Items.IRON_ORE, 1, Items.RAW_IRON, 4);
        createCrusherRecipe(Items.DEEPSLATE_IRON_ORE, 1, Items.RAW_IRON, 4);
        createCrusherRecipe(Items.BUCKET, 1, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.WATER_BUCKET, 1, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.LAVA_BUCKET, 1, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.MILK_BUCKET, 1, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.TROPICAL_FISH_BUCKET, 1, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.SALMON_BUCKET, 1, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.PUFFERFISH_BUCKET, 1, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.COD_BUCKET, 1, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.AXOLOTL_BUCKET, 1, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.TADPOLE_BUCKET, 1, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.POWDER_SNOW_BUCKET, 1, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.SHEARS, 1, Items.RAW_IRON, 2);
        createCrusherRecipe(Items.IRON_DOOR, 1, Items.RAW_IRON, 2);
        createCrusherRecipe(Items.IRON_TRAPDOOR, 1, Items.RAW_IRON, 4);
        createCrusherRecipe(Items.CAULDRON, 1, Items.RAW_IRON, 7);
        createCrusherRecipe(Items.IRON_AXE, 1, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.IRON_PICKAXE, 1, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.IRON_HOE, 1, Items.RAW_IRON, 2);
        createCrusherRecipe(Items.IRON_SHOVEL, 1, Items.RAW_IRON, 1);
        createCrusherRecipe(Items.IRON_SWORD, 1, Items.RAW_IRON, 2);
        createCrusherRecipe(Items.IRON_BLOCK, 1, Items.RAW_IRON, 9);
        createCrusherRecipe(Items.RAW_IRON_BLOCK, 1, Items.RAW_IRON, 9);
        createCrusherRecipe(Items.IRON_HELMET, 1, Items.RAW_IRON, 5);
        createCrusherRecipe(Items.IRON_CHESTPLATE, 1, Items.RAW_IRON, 8);
        createCrusherRecipe(Items.IRON_LEGGINGS, 1, Items.RAW_IRON, 7);
        createCrusherRecipe(Items.IRON_BOOTS, 1, Items.RAW_IRON, 4);
        createCrusherRecipe(Items.CHAINMAIL_HELMET, 1, Items.RAW_IRON, 5);
        createCrusherRecipe(Items.CHAINMAIL_CHESTPLATE, 1, Items.RAW_IRON, 8);
        createCrusherRecipe(Items.CHAINMAIL_LEGGINGS, 1, Items.RAW_IRON, 7);
        createCrusherRecipe(Items.CHAINMAIL_BOOTS, 1, Items.RAW_IRON, 4);
        createCrusherRecipe(Items.IRON_HORSE_ARMOR, 1, Items.RAW_IRON, 7);
        // Iron nuggets from iron things.
        // 11 nuggets => 2 nuggets + 1 ingot => 1 chain => 11 nuggets
        createCrusherRecipe(Items.CHAIN, 1, Items.IRON_NUGGET, 11);
        // 54 nuggets => 6 ingots => 16 iron bars => 64 nuggets
        // This allows free generation of iron, for those who discover it.
        createCrusherRecipe(Items.IRON_BARS, 1, Items.IRON_NUGGET, 4);
        // Gold from things made of gold.
        cookingTime = 150;
        createCrusherRecipe(Items.GOLD_ORE, 1, Items.RAW_GOLD, 4);
        createCrusherRecipe(Items.DEEPSLATE_GOLD_ORE, 1, Items.RAW_GOLD, 4);
        createCrusherRecipe(Items.NETHER_GOLD_ORE, 1, Items.RAW_GOLD, 1);
        createCrusherRecipe(Items.GOLDEN_AXE, 1, Items.RAW_GOLD, 3);
        createCrusherRecipe(Items.GOLDEN_PICKAXE, 1, Items.RAW_GOLD, 3);
        createCrusherRecipe(Items.GOLDEN_HOE, 1, Items.RAW_GOLD, 2);
        createCrusherRecipe(Items.GOLDEN_SHOVEL, 1, Items.RAW_GOLD, 1);
        createCrusherRecipe(Items.GOLDEN_SWORD, 1, Items.RAW_GOLD, 2);
        createCrusherRecipe(Items.GOLD_BLOCK, 1, Items.RAW_GOLD, 9);
        createCrusherRecipe(Items.RAW_GOLD_BLOCK, 1, Items.RAW_GOLD, 9);
        createCrusherRecipe(Items.GOLDEN_HELMET, 1, Items.RAW_GOLD, 5);
        createCrusherRecipe(Items.GOLDEN_CHESTPLATE, 1, Items.RAW_GOLD, 8);
        createCrusherRecipe(Items.GOLDEN_LEGGINGS, 1, Items.RAW_GOLD, 7);
        createCrusherRecipe(Items.GOLDEN_BOOTS, 1, Items.RAW_GOLD, 4);
        createCrusherRecipe(Items.GOLDEN_HORSE_ARMOR, 1, Items.RAW_GOLD, 7);
        // Diamond from things made of diamonds.
        cookingTime = 200;
        createCrusherRecipe(Items.DIAMOND_ORE, 1, Items.DIAMOND, 4);
        createCrusherRecipe(Items.DEEPSLATE_DIAMOND_ORE, 1, Items.DIAMOND, 4);
        createCrusherRecipe(Items.DIAMOND_AXE, 1, Items.DIAMOND, 3);
        createCrusherRecipe(Items.DIAMOND_PICKAXE, 1, Items.DIAMOND, 3);
        createCrusherRecipe(Items.DIAMOND_HOE, 1, Items.DIAMOND, 2);
        createCrusherRecipe(Items.DIAMOND_SHOVEL, 1, Items.DIAMOND, 1);
        createCrusherRecipe(Items.DIAMOND_SWORD, 1, Items.DIAMOND, 2);
        createCrusherRecipe(Items.DIAMOND_BLOCK, 1, Items.DIAMOND, 9);
        createCrusherRecipe(Items.DIAMOND_HELMET, 1, Items.DIAMOND, 5);
        createCrusherRecipe(Items.DIAMOND_CHESTPLATE, 1, Items.DIAMOND, 8);
        createCrusherRecipe(Items.DIAMOND_LEGGINGS, 1, Items.DIAMOND, 7);
        createCrusherRecipe(Items.DIAMOND_BOOTS, 1, Items.DIAMOND, 4);
        createCrusherRecipe(Items.DIAMOND_HORSE_ARMOR, 1, Items.DIAMOND, 7);
        // Sand from sand fast.
        experience = 1.0f;
        cookingTime = 10;
        createCrusherRecipe(Items.SAND, 1, Items.SAND, 1);
        createCrusherRecipe(Items.RED_SAND, 1, Items.RED_SAND, 1);
        // Diamond from diamond, even faster.
        experience = 1.5f;
        cookingTime = 8;
        createCrusherRecipe(Items.DIAMOND, 1, Items.DIAMOND, 1);
    }

    private void generateCompressorRecipes() {
        // Defaults.
        experience = 0.1f;
        cookingTime = 100;
        // Sandstone from sand.
        createCompressorRecipe(Items.SAND, 1, Items.SANDSTONE, 1);
        // Cobblestone from gravel.
        createCompressorRecipe(Items.GRAVEL, 1, Items.COBBLESTONE, 1);
        // Blaze rod from blaze powder.
        createCompressorRecipe(Items.BLAZE_POWDER, 2, Items.BLAZE_ROD, 1);
        // Blocks.
        createCompressorRecipe(Items.AMETHYST_SHARD, 4, Items.AMETHYST_BLOCK, 1);
        createCompressorRecipe(Items.BAMBOO, 9, Items.BAMBOO_BLOCK, 1);
        createCompressorRecipe(Items.QUARTZ, 4, Items.QUARTZ_BLOCK, 1);
        createCompressorRecipe(Items.COAL, 9, Items.COAL_BLOCK, 1);
        createCompressorRecipe(Items.CHARCOAL, 9, Items.COAL_BLOCK, 1);
        createCompressorRecipe(Items.RAW_COPPER, 9, Items.RAW_COPPER_BLOCK, 1);
        createCompressorRecipe(Items.COPPER_INGOT, 9, Items.COPPER_BLOCK, 1);
        createCompressorRecipe(Items.RAW_IRON, 9, Items.RAW_IRON_BLOCK, 1);
        createCompressorRecipe(Items.IRON_INGOT, 9, Items.IRON_BLOCK, 1);
        createCompressorRecipe(Items.RAW_GOLD, 9, Items.RAW_GOLD_BLOCK, 1);
        createCompressorRecipe(Items.GOLD_INGOT, 9, Items.GOLD_BLOCK, 1);
        createCompressorRecipe(Items.DIAMOND, 9, Items.DIAMOND_BLOCK, 1);
        createCompressorRecipe(Items.DRIED_KELP, 9, Items.DRIED_KELP_BLOCK, 1);
        createCompressorRecipe(Items.KELP, 9, Items.DRIED_KELP_BLOCK, 1);
        createCompressorRecipe(Items.POINTED_DRIPSTONE, 4, Items.DRIPSTONE_BLOCK, 1);
        createCompressorRecipe(Items.EMERALD, 9, Items.EMERALD_BLOCK, 1);
        createCompressorRecipe(Items.WHEAT, 9, Items.HAY_BLOCK, 1);
        createCompressorRecipe(Items.HONEYCOMB, 4, Items.HONEYCOMB_BLOCK, 1);
        createCompressorRecipe(Items.LAPIS_LAZULI, 9, Items.LAPIS_BLOCK, 1);
        createCompressorRecipe(Items.MAGMA_CREAM, 4, Items.MAGMA_BLOCK, 1);
        createCompressorRecipe(Items.POPPED_CHORUS_FRUIT, 4, Items.PURPUR_BLOCK, 1);
        createCompressorRecipe(Items.RESIN_CLUMP, 9, Items.RESIN_BLOCK, 1);
        createCompressorRecipe(Items.SLIME_BALL, 4, Items.SLIME_BLOCK, 1);
        createCompressorRecipe(Items.SNOWBALL, 4, Items.SNOW_BLOCK, 1);
        createCompressorRecipe(Items.REDSTONE, 9, Items.REDSTONE_BLOCK, 1);
        createCompressorRecipe(Items.ICE, 9, Items.PACKED_ICE, 1);
        // Special and irregular blocks.
        createCompressorRecipe(Items.BONE_MEAL, 8, Items.BONE_BLOCK, 1);
        createCompressorRecipe(Items.SUGAR, 8, Items.HONEY_BLOCK, 1);
        createCompressorRecipe(Items.SNOW_BLOCK, 2, Items.ICE, 1);
        // Blow up when compressing a diamond block or something explosive.
        createCompressorRecipe(Items.GUNPOWDER, 1, Items.TNT, 1);
        createCompressorRecipe(Items.TNT, 1, Items.TNT, 1);
        createCompressorRecipe(Items.DIAMOND_BLOCK, 1, Items.TNT, 1);
        // Diamond from coal blocks, but very slowly.
        experience = 1.0f;
        cookingTime = 1000;
        createCompressorRecipe(Items.COAL_BLOCK, 16, Items.DIAMOND, 1);
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
        generateCrusherRecipes();
        // The compressor.
        createShaped(RecipeCategory.DECORATIONS, ModBlocks.COMPRESSOR_BLOCK)
                .pattern("###")
                .pattern("P P")
                .pattern("###")
                .input('#', ItemTags.STONE_CRAFTING_MATERIALS)
                .input('P', Items.PISTON)
                .criterion("has_cobblestone", conditionsFromTag(ItemTags.STONE_CRAFTING_MATERIALS))
                .criterion(hasItem(Items.PISTON), conditionsFromItem(Items.PISTON))
                .offerTo(exporter);
        // The compressing recipes.
        generateCompressorRecipes();
    }
}
