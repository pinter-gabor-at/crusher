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
        createCrusherRecipe(ModItemTagProvider.GRAVEL_SOURCES, Items.GRAVEL, 1);
        createCrusherRecipe(Items.STONE_AXE, Items.GRAVEL, 3);
        createCrusherRecipe(Items.STONE_PICKAXE, Items.GRAVEL, 3);
        createCrusherRecipe(Items.STONE_HOE, Items.GRAVEL, 2);
        createCrusherRecipe(Items.STONE_SHOVEL, Items.GRAVEL, 1);
        createCrusherRecipe(Items.STONE_SWORD, Items.GRAVEL, 2);
        // 8 cobblestone => 1 furnace => 9 gravel => 9 cobblestone
        // This allows the free generation of cobblestone.
        createCrusherRecipe(Items.FURNACE, Items.GRAVEL, 9);
        // Sand from sandstones and gravel.
        createCrusherRecipe(ModItemTagProvider.SAND_SOURCES, Items.SAND, 1);
        // Red sand from red sandstones and other redish blocks.
        createCrusherRecipe(ModItemTagProvider.RED_SAND_SOURCES, Items.RED_SAND, 1);
        // Blaze powder from blaze rod.
        createCrusherRecipe(Items.BLAZE_ROD, Items.BLAZE_POWDER, 2);
        // Bone meal from bone.
        createCrusherRecipe(Items.BONE, Items.BONE_MEAL, 6);
        createCrusherRecipe(Items.BONE_BLOCK, Items.BONE_MEAL, 20);
        // Sticks from planks and other wooden things.
        createCrusherRecipe(ItemTags.PLANKS, Items.STICK, 4);
        createCrusherRecipe(Items.STICK, Items.STICK, 1);
        createCrusherRecipe(Items.TORCH, Items.STICK, 1);
        createCrusherRecipe(ItemTags.SAPLINGS, Items.STICK, 2);
        createCrusherRecipe(Items.OAK_STAIRS, Items.STICK, 3);
        createCrusherRecipe(Items.SPRUCE_STAIRS, Items.STICK, 3);
        createCrusherRecipe(Items.BIRCH_STAIRS, Items.STICK, 3);
        createCrusherRecipe(Items.JUNGLE_STAIRS, Items.STICK, 3);
        createCrusherRecipe(Items.ACACIA_STAIRS, Items.STICK, 3);
        createCrusherRecipe(Items.DARK_OAK_STAIRS, Items.STICK, 3);
        createCrusherRecipe(Items.MANGROVE_STAIRS, Items.STICK, 3);
        createCrusherRecipe(Items.CHERRY_STAIRS, Items.STICK, 3);
        createCrusherRecipe(Items.PALE_OAK_STAIRS, Items.STICK, 3);
        createCrusherRecipe(Items.BAMBOO_STAIRS, Items.STICK, 3);
        createCrusherRecipe(Items.CRIMSON_STAIRS, Items.STICK, 3);
        createCrusherRecipe(Items.WARPED_STAIRS, Items.STICK, 3);
        createCrusherRecipe(Items.OAK_SLAB, Items.STICK, 1);
        createCrusherRecipe(Items.SPRUCE_SLAB, Items.STICK, 1);
        createCrusherRecipe(Items.BIRCH_SLAB, Items.STICK, 1);
        createCrusherRecipe(Items.JUNGLE_SLAB, Items.STICK, 1);
        createCrusherRecipe(Items.ACACIA_SLAB, Items.STICK, 1);
        createCrusherRecipe(Items.DARK_OAK_SLAB, Items.STICK, 1);
        createCrusherRecipe(Items.MANGROVE_SLAB, Items.STICK, 1);
        createCrusherRecipe(Items.CHERRY_SLAB, Items.STICK, 1);
        createCrusherRecipe(Items.PALE_OAK_SLAB, Items.STICK, 1);
        createCrusherRecipe(Items.BAMBOO_SLAB, Items.STICK, 1);
        createCrusherRecipe(Items.CRIMSON_SLAB, Items.STICK, 1);
        createCrusherRecipe(Items.WARPED_SLAB, Items.STICK, 1);
        createCrusherRecipe(Items.OAK_FENCE, Items.STICK, 3);
        createCrusherRecipe(Items.SPRUCE_FENCE, Items.STICK, 3);
        createCrusherRecipe(Items.BIRCH_FENCE, Items.STICK, 3);
        createCrusherRecipe(Items.JUNGLE_FENCE, Items.STICK, 3);
        createCrusherRecipe(Items.ACACIA_FENCE, Items.STICK, 3);
        createCrusherRecipe(Items.DARK_OAK_FENCE, Items.STICK, 3);
        createCrusherRecipe(Items.MANGROVE_FENCE, Items.STICK, 3);
        createCrusherRecipe(Items.CHERRY_FENCE, Items.STICK, 3);
        createCrusherRecipe(Items.PALE_OAK_FENCE, Items.STICK, 3);
        createCrusherRecipe(Items.BAMBOO_FENCE, Items.STICK, 3);
        createCrusherRecipe(Items.CRIMSON_FENCE, Items.STICK, 3);
        createCrusherRecipe(Items.WARPED_FENCE, Items.STICK, 3);
        createCrusherRecipe(Items.OAK_FENCE_GATE, Items.STICK, 8);
        createCrusherRecipe(Items.SPRUCE_FENCE_GATE, Items.STICK, 8);
        createCrusherRecipe(Items.BIRCH_FENCE_GATE, Items.STICK, 8);
        createCrusherRecipe(Items.JUNGLE_FENCE_GATE, Items.STICK, 8);
        createCrusherRecipe(Items.ACACIA_FENCE_GATE, Items.STICK, 8);
        createCrusherRecipe(Items.DARK_OAK_FENCE_GATE, Items.STICK, 8);
        createCrusherRecipe(Items.MANGROVE_FENCE_GATE, Items.STICK, 8);
        createCrusherRecipe(Items.CHERRY_FENCE_GATE, Items.STICK, 8);
        createCrusherRecipe(Items.PALE_OAK_FENCE_GATE, Items.STICK, 8);
        createCrusherRecipe(Items.BAMBOO_FENCE_GATE, Items.STICK, 8);
        createCrusherRecipe(Items.CRIMSON_FENCE_GATE, Items.STICK, 8);
        createCrusherRecipe(Items.WARPED_FENCE_GATE, Items.STICK, 8);
        createCrusherRecipe(Items.OAK_SIGN, Items.STICK, 4);
        createCrusherRecipe(Items.SPRUCE_SIGN, Items.STICK, 4);
        createCrusherRecipe(Items.BIRCH_SIGN, Items.STICK, 4);
        createCrusherRecipe(Items.JUNGLE_SIGN, Items.STICK, 4);
        createCrusherRecipe(Items.ACACIA_SIGN, Items.STICK, 4);
        createCrusherRecipe(Items.DARK_OAK_SIGN, Items.STICK, 4);
        createCrusherRecipe(Items.MANGROVE_SIGN, Items.STICK, 4);
        createCrusherRecipe(Items.CHERRY_SIGN, Items.STICK, 4);
        createCrusherRecipe(Items.PALE_OAK_SIGN, Items.STICK, 4);
        createCrusherRecipe(Items.BAMBOO_SIGN, Items.STICK, 4);
        createCrusherRecipe(Items.CRIMSON_SIGN, Items.STICK, 4);
        createCrusherRecipe(Items.WARPED_SIGN, Items.STICK, 4);
        createCrusherRecipe(Items.OAK_PRESSURE_PLATE, Items.STICK, 4);
        createCrusherRecipe(Items.SPRUCE_PRESSURE_PLATE, Items.STICK, 4);
        createCrusherRecipe(Items.BIRCH_PRESSURE_PLATE, Items.STICK, 4);
        createCrusherRecipe(Items.JUNGLE_PRESSURE_PLATE, Items.STICK, 4);
        createCrusherRecipe(Items.ACACIA_PRESSURE_PLATE, Items.STICK, 4);
        createCrusherRecipe(Items.DARK_OAK_PRESSURE_PLATE, Items.STICK, 4);
        createCrusherRecipe(Items.MANGROVE_PRESSURE_PLATE, Items.STICK, 4);
        createCrusherRecipe(Items.CHERRY_PRESSURE_PLATE, Items.STICK, 4);
        createCrusherRecipe(Items.PALE_OAK_PRESSURE_PLATE, Items.STICK, 4);
        createCrusherRecipe(Items.BAMBOO_PRESSURE_PLATE, Items.STICK, 4);
        createCrusherRecipe(Items.CRIMSON_PRESSURE_PLATE, Items.STICK, 4);
        createCrusherRecipe(Items.WARPED_PRESSURE_PLATE, Items.STICK, 4);
        // 7 sticks => 3 ladders => 9 sticks
        // This allows free generation of sticks, for those who discover it.
        createCrusherRecipe(Items.LADDER, Items.STICK, 3);
        // Planks from items made of planks.
        createCrusherRecipe(Items.OAK_DOOR, Items.OAK_PLANKS, 2);
        createCrusherRecipe(Items.SPRUCE_DOOR, Items.SPRUCE_PLANKS, 2);
        createCrusherRecipe(Items.BIRCH_DOOR, Items.BIRCH_PLANKS, 2);
        createCrusherRecipe(Items.JUNGLE_DOOR, Items.JUNGLE_PLANKS, 2);
        createCrusherRecipe(Items.ACACIA_DOOR, Items.ACACIA_PLANKS, 2);
        createCrusherRecipe(Items.DARK_OAK_DOOR, Items.DARK_OAK_PLANKS, 2);
        createCrusherRecipe(Items.MANGROVE_DOOR, Items.MANGROVE_PLANKS, 2);
        createCrusherRecipe(Items.CHERRY_DOOR, Items.CHERRY_PLANKS, 2);
        createCrusherRecipe(Items.PALE_OAK_DOOR, Items.PALE_OAK_PLANKS, 2);
        createCrusherRecipe(Items.BAMBOO_DOOR, Items.BAMBOO_PLANKS, 2);
        createCrusherRecipe(Items.CRIMSON_DOOR, Items.CRIMSON_PLANKS, 2);
        createCrusherRecipe(Items.WARPED_DOOR, Items.WARPED_PLANKS, 2);
        createCrusherRecipe(Items.OAK_TRAPDOOR, Items.OAK_PLANKS, 3);
        createCrusherRecipe(Items.SPRUCE_TRAPDOOR, Items.SPRUCE_PLANKS, 3);
        createCrusherRecipe(Items.BIRCH_TRAPDOOR, Items.BIRCH_PLANKS, 3);
        createCrusherRecipe(Items.JUNGLE_TRAPDOOR, Items.JUNGLE_PLANKS, 3);
        createCrusherRecipe(Items.ACACIA_TRAPDOOR, Items.ACACIA_PLANKS, 3);
        createCrusherRecipe(Items.DARK_OAK_TRAPDOOR, Items.DARK_OAK_PLANKS, 3);
        createCrusherRecipe(Items.MANGROVE_TRAPDOOR, Items.MANGROVE_PLANKS, 3);
        createCrusherRecipe(Items.CHERRY_TRAPDOOR, Items.CHERRY_PLANKS, 3);
        createCrusherRecipe(Items.PALE_OAK_TRAPDOOR, Items.PALE_OAK_PLANKS, 3);
        createCrusherRecipe(Items.BAMBOO_TRAPDOOR, Items.BAMBOO_PLANKS, 3);
        createCrusherRecipe(Items.CRIMSON_TRAPDOOR, Items.CRIMSON_PLANKS, 3);
        createCrusherRecipe(Items.WARPED_TRAPDOOR, Items.WARPED_PLANKS, 3);
        createCrusherRecipe(Items.OAK_BOAT, Items.OAK_PLANKS, 5);
        createCrusherRecipe(Items.SPRUCE_BOAT, Items.SPRUCE_PLANKS, 5);
        createCrusherRecipe(Items.BIRCH_BOAT, Items.BIRCH_PLANKS, 5);
        createCrusherRecipe(Items.JUNGLE_BOAT, Items.JUNGLE_PLANKS, 5);
        createCrusherRecipe(Items.ACACIA_BOAT, Items.ACACIA_PLANKS, 5);
        createCrusherRecipe(Items.DARK_OAK_BOAT, Items.DARK_OAK_PLANKS, 5);
        createCrusherRecipe(Items.MANGROVE_BOAT, Items.MANGROVE_PLANKS, 5);
        createCrusherRecipe(Items.CHERRY_BOAT, Items.CHERRY_PLANKS, 5);
        createCrusherRecipe(Items.PALE_OAK_BOAT, Items.PALE_OAK_PLANKS, 5);
        createCrusherRecipe(Items.BAMBOO_RAFT, Items.BAMBOO_PLANKS, 5);
        createCrusherRecipe(Items.CHEST, Items.OAK_PLANKS, 8);
        createCrusherRecipe(Items.CRAFTING_TABLE, Items.OAK_PLANKS, 4);
        createCrusherRecipe(Items.OAK_CHEST_BOAT, Items.OAK_PLANKS, 13);
        createCrusherRecipe(Items.SPRUCE_CHEST_BOAT, Items.SPRUCE_PLANKS, 13);
        createCrusherRecipe(Items.BIRCH_CHEST_BOAT, Items.BIRCH_PLANKS, 13);
        createCrusherRecipe(Items.JUNGLE_CHEST_BOAT, Items.JUNGLE_PLANKS, 13);
        createCrusherRecipe(Items.ACACIA_CHEST_BOAT, Items.ACACIA_PLANKS, 13);
        createCrusherRecipe(Items.DARK_OAK_CHEST_BOAT, Items.DARK_OAK_PLANKS, 13);
        createCrusherRecipe(Items.MANGROVE_CHEST_BOAT, Items.MANGROVE_PLANKS, 13);
        createCrusherRecipe(Items.CHERRY_CHEST_BOAT, Items.CHERRY_PLANKS, 13);
        createCrusherRecipe(Items.PALE_OAK_CHEST_BOAT, Items.PALE_OAK_PLANKS, 13);
        createCrusherRecipe(Items.BAMBOO_CHEST_RAFT, Items.BAMBOO_PLANKS, 13);
        createCrusherRecipe(Items.WOODEN_AXE, Items.OAK_PLANKS, 3);
        createCrusherRecipe(Items.WOODEN_PICKAXE, Items.OAK_PLANKS, 3);
        createCrusherRecipe(Items.WOODEN_HOE, Items.OAK_PLANKS, 2);
        createCrusherRecipe(Items.WOODEN_SHOVEL, Items.OAK_PLANKS, 1);
        createCrusherRecipe(Items.WOODEN_SWORD, Items.OAK_PLANKS, 2);
        // Coal from logs.
        createCrusherRecipe(ItemTags.LOGS, Items.CHARCOAL, 1);
        // Sugar, rotten flesh or dirt from food, except golden apple and golden carrot.
        createCrusherRecipe(ConventionalItemTags.BERRY_FOODS, Items.SUGAR, 1);
        createCrusherRecipe(ConventionalItemTags.CANDY_FOODS, Items.SUGAR, 1);
        createCrusherRecipe(ConventionalItemTags.COOKIE_FOODS, Items.SUGAR, 1);
        createCrusherRecipe(ConventionalItemTags.PIE_FOODS, Items.SUGAR, 1);
        createCrusherRecipe(ModItemTagProvider.NORMAL_FRUIT_FOODS, Items.SUGAR, 1);
        createCrusherRecipe(ConventionalItemTags.FOOD_POISONING_FOODS, Items.ROTTEN_FLESH, 1);
        createCrusherRecipe(ConventionalItemTags.RAW_FISH_FOODS, Items.ROTTEN_FLESH, 1);
        createCrusherRecipe(ConventionalItemTags.COOKED_FISH_FOODS, Items.ROTTEN_FLESH, 1);
        createCrusherRecipe(ConventionalItemTags.RAW_MEAT_FOODS, Items.ROTTEN_FLESH, 1);
        createCrusherRecipe(ConventionalItemTags.COOKED_MEAT_FOODS, Items.ROTTEN_FLESH, 1);
        createCrusherRecipe(ConventionalItemTags.BREAD_FOODS, Items.DIRT, 1);
        createCrusherRecipe(ConventionalItemTags.EDIBLE_WHEN_PLACED_FOODS, Items.DIRT, 1);
        createCrusherRecipe(ConventionalItemTags.SOUP_FOODS, Items.DIRT, 1);
        createCrusherRecipe(ModItemTagProvider.NORMAL_VEGETABLE_FOODS, Items.DIRT, 1);
        // 8 ingots + 1 apple => 1 golden apple => 9 ingots
        // This allows free generation of gold, for those who discover it.
        cookingTime = 200;
        createCrusherRecipe(Items.GOLDEN_APPLE, Items.RAW_GOLD, 9);
        cookingTime = 600;
        createCrusherRecipe(Items.ENCHANTED_GOLDEN_APPLE, Items.RAW_GOLD, 64);
        // 8 nugget + 1 carrot => 1 golden carrot => 1 ingots => 9 nuggets
        // This allows free generation of gold, for those who discover it.
        cookingTime = 100;
        createCrusherRecipe(Items.GOLDEN_CARROT, Items.RAW_GOLD, 1);
        // Snow from ice
        createCrusherRecipe(Items.ICE, Items.SNOW, 4);
        createCrusherRecipe(Items.BLUE_ICE, Items.SNOW, 4);
        createCrusherRecipe(Items.PACKED_ICE, Items.SNOW, 36);
        createCrusherRecipe(Items.SNOW, Items.SNOW, 1);
        createCrusherRecipe(Items.SNOW_BLOCK, Items.SNOW, 4);
        createCrusherRecipe(Items.SNOWBALL, Items.SNOW, 1);
        // String from soft things.
        createCrusherRecipe(ItemTags.WOOL, Items.STRING, 4);
        createCrusherRecipe(ItemTags.WOOL_CARPETS, Items.STRING, 1);
        createCrusherRecipe(Items.LEAD, Items.STRING, 2);
        // Wax from candles.
        createCrusherRecipe(ItemTags.CANDLES, Items.HONEYCOMB, 1);
        createCrusherRecipe(Items.HONEYCOMB_BLOCK, Items.HONEYCOMB, 4);
        // Glowstone.
        createCrusherRecipe(Items.GLOWSTONE, Items.GLOWSTONE_DUST, 4);
        // Sugar.
        createCrusherRecipe(Items.HONEY_BLOCK, Items.SUGAR, 12);
        createCrusherRecipe(Items.SUGAR_CANE, Items.SUGAR, 2);
        // Gunpowder from TNT.
        // 5 gunpowders + 4 sands => 1 TNT => 6 gunpowder.
        // This allows the generation of gunpowder from sand.
        createCrusherRecipe(Items.TNT, Items.GUNPOWDER, 6);
        // Sponge.
        createCrusherRecipe(Items.WET_SPONGE, Items.SPONGE, 1);
        // Dyes from colored items.
        createCrusherRecipe(Items.LILY_OF_THE_VALLEY, Items.WHITE_DYE, 2);
        createCrusherRecipe(Items.BONE_MEAL, Items.WHITE_DYE, 2);
        createCrusherRecipe(Items.ORANGE_TULIP, Items.ORANGE_DYE, 2);
        createCrusherRecipe(Items.TORCHFLOWER, Items.ORANGE_DYE, 2);
        createCrusherRecipe(Items.OPEN_EYEBLOSSOM, Items.ORANGE_DYE, 2);
        createCrusherRecipe(Items.ALLIUM, Items.MAGENTA_DYE, 2);
        createCrusherRecipe(Items.LILAC, Items.MAGENTA_DYE, 4);
        createCrusherRecipe(Items.BLUE_ORCHID, Items.LIGHT_BLUE_DYE, 2);
        createCrusherRecipe(Items.DANDELION, Items.YELLOW_DYE, 2);
        createCrusherRecipe(Items.SUNFLOWER, Items.YELLOW_DYE, 4);
        createCrusherRecipe(Items.PEONY, Items.PINK_DYE, 4);
        createCrusherRecipe(Items.PINK_PETALS, Items.PINK_DYE, 2);
        createCrusherRecipe(Items.PINK_TULIP, Items.PINK_DYE, 2);
        createCrusherRecipe(Items.CLOSED_EYEBLOSSOM, Items.GRAY_DYE, 2);
        createCrusherRecipe(Items.OXEYE_DAISY, Items.LIGHT_GRAY_DYE, 2);
        createCrusherRecipe(Items.WHITE_TULIP, Items.LIGHT_GRAY_DYE, 2);
        createCrusherRecipe(Items.AZURE_BLUET, Items.LIGHT_GRAY_DYE, 2);
        createCrusherRecipe(Items.PITCHER_PLANT, Items.CYAN_DYE, 4);
        createCrusherRecipe(Items.LAPIS_LAZULI, Items.BLUE_DYE, 3);
        createCrusherRecipe(Items.LAPIS_BLOCK, Items.BLUE_DYE, 32);
        createCrusherRecipe(Items.CORNFLOWER, Items.BLUE_DYE, 2);
        createCrusherRecipe(Items.COCOA_BEANS, Items.BROWN_DYE, 2);
        createCrusherRecipe(Items.CACTUS, Items.GREEN_DYE, 2);
        createCrusherRecipe(Items.ROSE_BUSH, Items.RED_DYE, 4);
        createCrusherRecipe(Items.POPPY, Items.RED_DYE, 2);
        createCrusherRecipe(Items.BEETROOT, Items.RED_DYE, 2);
        createCrusherRecipe(Items.RED_TULIP, Items.RED_DYE, 2);
        createCrusherRecipe(Items.REDSTONE, Items.RED_DYE, 3);
        createCrusherRecipe(Items.REDSTONE_BLOCK, Items.RED_DYE, 32);
        createCrusherRecipe(Items.INK_SAC, Items.BLACK_DYE, 2);
        createCrusherRecipe(Items.WITHER_ROSE, Items.BLACK_DYE, 2);
        createCrusherRecipe(Items.CHARCOAL, Items.BLACK_DYE, 4);
        createCrusherRecipe(Items.COAL, Items.BLACK_DYE, 3);
        createCrusherRecipe(Items.COAL_BLOCK, Items.BLACK_DYE, 32);
        // Concrete powder from concrete.
        createCrusherRecipe(Items.WHITE_CONCRETE, Items.WHITE_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.ORANGE_CONCRETE, Items.ORANGE_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.MAGENTA_CONCRETE, Items.MAGENTA_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.LIGHT_BLUE_CONCRETE, Items.LIGHT_BLUE_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.YELLOW_CONCRETE, Items.YELLOW_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.LIME_CONCRETE, Items.LIME_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.PINK_CONCRETE, Items.PINK_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.GRAY_CONCRETE, Items.GRAY_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.LIGHT_GRAY_CONCRETE, Items.LIGHT_GRAY_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.CYAN_CONCRETE, Items.CYAN_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.PURPLE_CONCRETE, Items.PURPLE_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.BLUE_CONCRETE, Items.BLUE_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.BROWN_CONCRETE, Items.BROWN_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.GREEN_CONCRETE, Items.GREEN_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.RED_CONCRETE, Items.RED_CONCRETE_POWDER, 1);
        createCrusherRecipe(Items.BLACK_CONCRETE, Items.BLACK_CONCRETE_POWDER, 1);
        // Copper from things made of copper.
        createCrusherRecipe(Items.COPPER_ORE, Items.RAW_COPPER, 4);
        createCrusherRecipe(Items.DEEPSLATE_COPPER_ORE, Items.RAW_COPPER, 4);
        createCrusherRecipe(Items.COPPER_DOOR, Items.RAW_COPPER, 2);
        createCrusherRecipe(Items.WAXED_COPPER_DOOR, Items.RAW_COPPER, 2);
        createCrusherRecipe(Items.EXPOSED_COPPER_DOOR, Items.RAW_COPPER, 2);
        createCrusherRecipe(Items.WAXED_EXPOSED_COPPER_DOOR, Items.RAW_COPPER, 2);
        createCrusherRecipe(Items.WEATHERED_COPPER_DOOR, Items.RAW_COPPER, 2);
        createCrusherRecipe(Items.WAXED_WEATHERED_COPPER_DOOR, Items.RAW_COPPER, 2);
        createCrusherRecipe(Items.OXIDIZED_COPPER_DOOR, Items.RAW_COPPER, 2);
        createCrusherRecipe(Items.WAXED_OXIDIZED_COPPER_DOOR, Items.RAW_COPPER, 2);
        createCrusherRecipe(Items.COPPER_TRAPDOOR, Items.RAW_COPPER, 4);
        createCrusherRecipe(Items.WAXED_COPPER_TRAPDOOR, Items.RAW_COPPER, 4);
        createCrusherRecipe(Items.EXPOSED_COPPER_TRAPDOOR, Items.RAW_COPPER, 4);
        createCrusherRecipe(Items.WAXED_EXPOSED_COPPER_TRAPDOOR, Items.RAW_COPPER, 4);
        createCrusherRecipe(Items.WEATHERED_COPPER_TRAPDOOR, Items.RAW_COPPER, 4);
        createCrusherRecipe(Items.WAXED_WEATHERED_COPPER_TRAPDOOR, Items.RAW_COPPER, 4);
        createCrusherRecipe(Items.OXIDIZED_COPPER_TRAPDOOR, Items.RAW_COPPER, 4);
        createCrusherRecipe(Items.WAXED_OXIDIZED_COPPER_TRAPDOOR, Items.RAW_COPPER, 4);
        createCrusherRecipe(Items.COPPER_BLOCK, Items.RAW_COPPER, 9);
        createCrusherRecipe(Items.RAW_COPPER_BLOCK, Items.RAW_COPPER, 9);
        createCrusherRecipe(Items.WAXED_COPPER_BLOCK, Items.RAW_COPPER, 9);
        createCrusherRecipe(Items.EXPOSED_COPPER, Items.RAW_COPPER, 9);
        createCrusherRecipe(Items.WAXED_EXPOSED_COPPER, Items.RAW_COPPER, 9);
        createCrusherRecipe(Items.WEATHERED_COPPER, Items.RAW_COPPER, 9);
        createCrusherRecipe(Items.WAXED_WEATHERED_COPPER, Items.RAW_COPPER, 9);
        createCrusherRecipe(Items.OXIDIZED_COPPER, Items.RAW_COPPER, 9);
        createCrusherRecipe(Items.WAXED_OXIDIZED_COPPER, Items.RAW_COPPER, 9);
        // Iron from things made of iron.
        createCrusherRecipe(Items.IRON_ORE, Items.RAW_IRON, 4);
        createCrusherRecipe(Items.DEEPSLATE_IRON_ORE, Items.RAW_IRON, 4);
        createCrusherRecipe(Items.BUCKET, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.WATER_BUCKET, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.LAVA_BUCKET, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.MILK_BUCKET, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.TROPICAL_FISH_BUCKET, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.SALMON_BUCKET, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.PUFFERFISH_BUCKET, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.COD_BUCKET, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.AXOLOTL_BUCKET, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.TADPOLE_BUCKET, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.POWDER_SNOW_BUCKET, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.SHEARS, Items.RAW_IRON, 2);
        createCrusherRecipe(Items.IRON_DOOR, Items.RAW_IRON, 2);
        createCrusherRecipe(Items.IRON_TRAPDOOR, Items.RAW_IRON, 4);
        createCrusherRecipe(Items.CAULDRON, Items.RAW_IRON, 7);
        createCrusherRecipe(Items.IRON_AXE, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.IRON_PICKAXE, Items.RAW_IRON, 3);
        createCrusherRecipe(Items.IRON_HOE, Items.RAW_IRON, 2);
        createCrusherRecipe(Items.IRON_SHOVEL, Items.RAW_IRON, 1);
        createCrusherRecipe(Items.IRON_SWORD, Items.RAW_IRON, 2);
        createCrusherRecipe(Items.IRON_BLOCK, Items.RAW_IRON, 9);
        createCrusherRecipe(Items.RAW_IRON_BLOCK, Items.RAW_IRON, 9);
        createCrusherRecipe(Items.IRON_HELMET, Items.RAW_IRON, 5);
        createCrusherRecipe(Items.IRON_CHESTPLATE, Items.RAW_IRON, 8);
        createCrusherRecipe(Items.IRON_LEGGINGS, Items.RAW_IRON, 7);
        createCrusherRecipe(Items.IRON_BOOTS, Items.RAW_IRON, 4);
        createCrusherRecipe(Items.CHAINMAIL_HELMET, Items.RAW_IRON, 5);
        createCrusherRecipe(Items.CHAINMAIL_CHESTPLATE, Items.RAW_IRON, 8);
        createCrusherRecipe(Items.CHAINMAIL_LEGGINGS, Items.RAW_IRON, 7);
        createCrusherRecipe(Items.CHAINMAIL_BOOTS, Items.RAW_IRON, 4);
        createCrusherRecipe(Items.IRON_HORSE_ARMOR, Items.RAW_IRON, 7);
        // Iron nuggets from iron things.
        // 11 nuggets => 2 nuggets + 1 ingot => 1 chain => 11 nuggets
        createCrusherRecipe(Items.CHAIN, Items.IRON_NUGGET, 11);
        // 54 nuggets => 6 ingots => 16 iron bars => 64 nuggets
        // This allows free generation of iron, for those who discover it.
        createCrusherRecipe(Items.IRON_BARS, Items.IRON_NUGGET, 4);
        // Gold from things made of gold.
        cookingTime = 150;
        createCrusherRecipe(Items.GOLD_ORE, Items.RAW_GOLD, 4);
        createCrusherRecipe(Items.DEEPSLATE_GOLD_ORE, Items.RAW_GOLD, 4);
        createCrusherRecipe(Items.NETHER_GOLD_ORE, Items.RAW_GOLD, 1);
        createCrusherRecipe(Items.GOLDEN_AXE, Items.RAW_GOLD, 3);
        createCrusherRecipe(Items.GOLDEN_PICKAXE, Items.RAW_GOLD, 3);
        createCrusherRecipe(Items.GOLDEN_HOE, Items.RAW_GOLD, 2);
        createCrusherRecipe(Items.GOLDEN_SHOVEL, Items.RAW_GOLD, 1);
        createCrusherRecipe(Items.GOLDEN_SWORD, Items.RAW_GOLD, 2);
        createCrusherRecipe(Items.GOLD_BLOCK, Items.RAW_GOLD, 9);
        createCrusherRecipe(Items.RAW_GOLD_BLOCK, Items.RAW_GOLD, 9);
        createCrusherRecipe(Items.GOLDEN_HELMET, Items.RAW_GOLD, 5);
        createCrusherRecipe(Items.GOLDEN_CHESTPLATE, Items.RAW_GOLD, 8);
        createCrusherRecipe(Items.GOLDEN_LEGGINGS, Items.RAW_GOLD, 7);
        createCrusherRecipe(Items.GOLDEN_BOOTS, Items.RAW_GOLD, 4);
        createCrusherRecipe(Items.GOLDEN_HORSE_ARMOR, Items.RAW_GOLD, 7);
        // Diamond from things made of diamonds.
        cookingTime = 200;
        createCrusherRecipe(Items.DIAMOND_ORE, Items.DIAMOND, 4);
        createCrusherRecipe(Items.DEEPSLATE_DIAMOND_ORE, Items.DIAMOND, 4);
        createCrusherRecipe(Items.DIAMOND_AXE, Items.DIAMOND, 3);
        createCrusherRecipe(Items.DIAMOND_PICKAXE, Items.DIAMOND, 3);
        createCrusherRecipe(Items.DIAMOND_HOE, Items.DIAMOND, 2);
        createCrusherRecipe(Items.DIAMOND_SHOVEL, Items.DIAMOND, 1);
        createCrusherRecipe(Items.DIAMOND_SWORD, Items.DIAMOND, 2);
        createCrusherRecipe(Items.DIAMOND_BLOCK, Items.DIAMOND, 9);
        createCrusherRecipe(Items.DIAMOND_HELMET, Items.DIAMOND, 5);
        createCrusherRecipe(Items.DIAMOND_CHESTPLATE, Items.DIAMOND, 8);
        createCrusherRecipe(Items.DIAMOND_LEGGINGS, Items.DIAMOND, 7);
        createCrusherRecipe(Items.DIAMOND_BOOTS, Items.DIAMOND, 4);
        createCrusherRecipe(Items.DIAMOND_HORSE_ARMOR, Items.DIAMOND, 7);
        // Sand from sand fast.
        experience = 1.0f;
        cookingTime = 10;
        createCrusherRecipe(Items.SAND, Items.SAND, 1);
        createCrusherRecipe(Items.RED_SAND, Items.RED_SAND, 1);
        // Diamond from diamond, even faster.
        experience = 1.5f;
        cookingTime = 8;
        createCrusherRecipe(Items.DIAMOND, Items.DIAMOND, 1);
    }

    private void generateCompressorRecipes() {
        // Defaults.
        experience = 0.1f;
        cookingTime = 100;
        // Sandstone from sand.
        createCompressorRecipe(Items.SAND, Items.SANDSTONE, 1);
        // Cobblestone from gravel.
        createCompressorRecipe(Items.GRAVEL, Items.COBBLESTONE, 1);
        // Blaze rod from blaze powder.
        // TODO: handle multiple inputs.
        // createCompressorRecipe(Items.BLAZE_POWDER, Items.BLAZE_ROD, 1);
        // TODO: blow up when compressing diamond or TNT.
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
