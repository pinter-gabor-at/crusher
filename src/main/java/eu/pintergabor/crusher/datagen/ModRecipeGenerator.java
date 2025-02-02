package eu.pintergabor.crusher.datagen;

import eu.pintergabor.crusher.blocks.ModBlocks;
import eu.pintergabor.crusher.datagen.recipe.CrusherRecipeGenerator;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
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
        createCrushingItem(Items.STONE_AXE, new ItemStack(Items.GRAVEL, 3));
        createCrushingItem(Items.STONE_PICKAXE, new ItemStack(Items.GRAVEL, 3));
        createCrushingItem(Items.STONE_HOE, new ItemStack(Items.GRAVEL, 2));
        createCrushingItem(Items.STONE_SHOVEL, new ItemStack(Items.GRAVEL, 1));
        createCrushingItem(Items.STONE_SWORD, new ItemStack(Items.GRAVEL, 2));
        // 8 cobblestone => 1 furnace => 9 gravel => 9 cobblestone
        // This allows the free generation of cobblestone.
        createCrushingItem(Items.FURNACE, new ItemStack(Items.GRAVEL, 9));
        // Sand from sandstones and gravel.
        createCrushingTag(ModItemTagProvider.SAND_SOURCES, Items.SAND.getDefaultStack());
        // Red sand from red sandstones and other redish blocks.
        createCrushingTag(ModItemTagProvider.RED_SAND_SOURCES, Items.RED_SAND.getDefaultStack());
        // Blaze powder from blaze rod.
        createCrushingItem(Items.BLAZE_ROD, new ItemStack(Items.BLAZE_POWDER, 2));
        // Bone meal from bone.
        createCrushingItem(Items.BONE, new ItemStack(Items.BONE_MEAL, 6));
        createCrushingItem(Items.BONE_BLOCK, new ItemStack(Items.BONE_MEAL, 20));
        // Sticks from planks and other wooden things.
        createCrushingTag(ItemTags.PLANKS, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.STICK, Items.STICK.getDefaultStack());
        createCrushingItem(Items.TORCH, Items.STICK.getDefaultStack());
        createCrushingTag(ItemTags.SAPLINGS, new ItemStack(Items.STICK, 2));
        createCrushingItem(Items.OAK_STAIRS, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.SPRUCE_STAIRS, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.BIRCH_STAIRS, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.JUNGLE_STAIRS, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.ACACIA_STAIRS, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.DARK_OAK_STAIRS, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.MANGROVE_STAIRS, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.CHERRY_STAIRS, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.PALE_OAK_STAIRS, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.BAMBOO_STAIRS, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.CRIMSON_STAIRS, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.WARPED_STAIRS, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.OAK_SLAB, Items.STICK.getDefaultStack());
        createCrushingItem(Items.SPRUCE_SLAB, Items.STICK.getDefaultStack());
        createCrushingItem(Items.BIRCH_SLAB, Items.STICK.getDefaultStack());
        createCrushingItem(Items.JUNGLE_SLAB, Items.STICK.getDefaultStack());
        createCrushingItem(Items.ACACIA_SLAB, Items.STICK.getDefaultStack());
        createCrushingItem(Items.DARK_OAK_SLAB, Items.STICK.getDefaultStack());
        createCrushingItem(Items.MANGROVE_SLAB, Items.STICK.getDefaultStack());
        createCrushingItem(Items.CHERRY_SLAB, Items.STICK.getDefaultStack());
        createCrushingItem(Items.PALE_OAK_SLAB, Items.STICK.getDefaultStack());
        createCrushingItem(Items.BAMBOO_SLAB, Items.STICK.getDefaultStack());
        createCrushingItem(Items.CRIMSON_SLAB, Items.STICK.getDefaultStack());
        createCrushingItem(Items.WARPED_SLAB, Items.STICK.getDefaultStack());
        createCrushingItem(Items.OAK_FENCE, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.SPRUCE_FENCE, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.BIRCH_FENCE, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.JUNGLE_FENCE, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.ACACIA_FENCE, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.DARK_OAK_FENCE, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.MANGROVE_FENCE, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.CHERRY_FENCE, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.PALE_OAK_FENCE, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.BAMBOO_FENCE, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.CRIMSON_FENCE, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.WARPED_FENCE, new ItemStack(Items.STICK, 3));
        createCrushingItem(Items.OAK_FENCE_GATE, new ItemStack(Items.STICK, 8));
        createCrushingItem(Items.SPRUCE_FENCE_GATE, new ItemStack(Items.STICK, 8));
        createCrushingItem(Items.BIRCH_FENCE_GATE, new ItemStack(Items.STICK, 8));
        createCrushingItem(Items.JUNGLE_FENCE_GATE, new ItemStack(Items.STICK, 8));
        createCrushingItem(Items.ACACIA_FENCE_GATE, new ItemStack(Items.STICK, 8));
        createCrushingItem(Items.DARK_OAK_FENCE_GATE, new ItemStack(Items.STICK, 8));
        createCrushingItem(Items.MANGROVE_FENCE_GATE, new ItemStack(Items.STICK, 8));
        createCrushingItem(Items.CHERRY_FENCE_GATE, new ItemStack(Items.STICK, 8));
        createCrushingItem(Items.PALE_OAK_FENCE_GATE, new ItemStack(Items.STICK, 8));
        createCrushingItem(Items.BAMBOO_FENCE_GATE, new ItemStack(Items.STICK, 8));
        createCrushingItem(Items.CRIMSON_FENCE_GATE, new ItemStack(Items.STICK, 8));
        createCrushingItem(Items.WARPED_FENCE_GATE, new ItemStack(Items.STICK, 8));
        createCrushingItem(Items.OAK_SIGN, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.SPRUCE_SIGN, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.BIRCH_SIGN, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.JUNGLE_SIGN, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.ACACIA_SIGN, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.DARK_OAK_SIGN, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.MANGROVE_SIGN, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.CHERRY_SIGN, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.PALE_OAK_SIGN, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.BAMBOO_SIGN, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.CRIMSON_SIGN, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.WARPED_SIGN, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.OAK_PRESSURE_PLATE, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.SPRUCE_PRESSURE_PLATE, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.BIRCH_PRESSURE_PLATE, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.JUNGLE_PRESSURE_PLATE, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.ACACIA_PRESSURE_PLATE, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.DARK_OAK_PRESSURE_PLATE, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.MANGROVE_PRESSURE_PLATE, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.CHERRY_PRESSURE_PLATE, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.PALE_OAK_PRESSURE_PLATE, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.BAMBOO_PRESSURE_PLATE, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.CRIMSON_PRESSURE_PLATE, new ItemStack(Items.STICK, 4));
        createCrushingItem(Items.WARPED_PRESSURE_PLATE, new ItemStack(Items.STICK, 4));
        // 7 sticks => 3 ladders => 9 sticks
        // This allows free generation of sticks, for those who discover it.
        createCrushingItem(Items.LADDER, new ItemStack(Items.STICK, 3));
        // Planks from items made of planks.
        createCrushingItem(Items.OAK_DOOR, new ItemStack(Items.OAK_PLANKS, 2));
        createCrushingItem(Items.SPRUCE_DOOR, new ItemStack(Items.SPRUCE_PLANKS, 2));
        createCrushingItem(Items.BIRCH_DOOR, new ItemStack(Items.BIRCH_PLANKS, 2));
        createCrushingItem(Items.JUNGLE_DOOR, new ItemStack(Items.JUNGLE_PLANKS, 2));
        createCrushingItem(Items.ACACIA_DOOR, new ItemStack(Items.ACACIA_PLANKS, 2));
        createCrushingItem(Items.DARK_OAK_DOOR, new ItemStack(Items.DARK_OAK_PLANKS, 2));
        createCrushingItem(Items.MANGROVE_DOOR, new ItemStack(Items.MANGROVE_PLANKS, 2));
        createCrushingItem(Items.CHERRY_DOOR, new ItemStack(Items.CHERRY_PLANKS, 2));
        createCrushingItem(Items.PALE_OAK_DOOR, new ItemStack(Items.PALE_OAK_PLANKS, 2));
        createCrushingItem(Items.BAMBOO_DOOR, new ItemStack(Items.BAMBOO_PLANKS, 2));
        createCrushingItem(Items.CRIMSON_DOOR, new ItemStack(Items.CRIMSON_PLANKS, 2));
        createCrushingItem(Items.WARPED_DOOR, new ItemStack(Items.WARPED_PLANKS, 2));
        createCrushingItem(Items.OAK_TRAPDOOR, new ItemStack(Items.OAK_PLANKS, 3));
        createCrushingItem(Items.SPRUCE_TRAPDOOR, new ItemStack(Items.SPRUCE_PLANKS, 3));
        createCrushingItem(Items.BIRCH_TRAPDOOR, new ItemStack(Items.BIRCH_PLANKS, 3));
        createCrushingItem(Items.JUNGLE_TRAPDOOR, new ItemStack(Items.JUNGLE_PLANKS, 3));
        createCrushingItem(Items.ACACIA_TRAPDOOR, new ItemStack(Items.ACACIA_PLANKS, 3));
        createCrushingItem(Items.DARK_OAK_TRAPDOOR, new ItemStack(Items.DARK_OAK_PLANKS, 3));
        createCrushingItem(Items.MANGROVE_TRAPDOOR, new ItemStack(Items.MANGROVE_PLANKS, 3));
        createCrushingItem(Items.CHERRY_TRAPDOOR, new ItemStack(Items.CHERRY_PLANKS, 3));
        createCrushingItem(Items.PALE_OAK_TRAPDOOR, new ItemStack(Items.PALE_OAK_PLANKS, 3));
        createCrushingItem(Items.BAMBOO_TRAPDOOR, new ItemStack(Items.BAMBOO_PLANKS, 3));
        createCrushingItem(Items.CRIMSON_TRAPDOOR, new ItemStack(Items.CRIMSON_PLANKS, 3));
        createCrushingItem(Items.WARPED_TRAPDOOR, new ItemStack(Items.WARPED_PLANKS, 3));
        createCrushingItem(Items.OAK_BOAT, new ItemStack(Items.OAK_PLANKS, 5));
        createCrushingItem(Items.SPRUCE_BOAT, new ItemStack(Items.SPRUCE_PLANKS, 5));
        createCrushingItem(Items.BIRCH_BOAT, new ItemStack(Items.BIRCH_PLANKS, 5));
        createCrushingItem(Items.JUNGLE_BOAT, new ItemStack(Items.JUNGLE_PLANKS, 5));
        createCrushingItem(Items.ACACIA_BOAT, new ItemStack(Items.ACACIA_PLANKS, 5));
        createCrushingItem(Items.DARK_OAK_BOAT, new ItemStack(Items.DARK_OAK_PLANKS, 5));
        createCrushingItem(Items.MANGROVE_BOAT, new ItemStack(Items.MANGROVE_PLANKS, 5));
        createCrushingItem(Items.CHERRY_BOAT, new ItemStack(Items.CHERRY_PLANKS, 5));
        createCrushingItem(Items.PALE_OAK_BOAT, new ItemStack(Items.PALE_OAK_PLANKS, 5));
        createCrushingItem(Items.BAMBOO_RAFT, new ItemStack(Items.BAMBOO_PLANKS, 5));
        createCrushingItem(Items.CHEST, new ItemStack(Items.OAK_PLANKS, 8));
        createCrushingItem(Items.CRAFTING_TABLE, new ItemStack(Items.OAK_PLANKS, 4));
        createCrushingItem(Items.OAK_CHEST_BOAT, new ItemStack(Items.OAK_PLANKS, 13));
        createCrushingItem(Items.SPRUCE_CHEST_BOAT, new ItemStack(Items.SPRUCE_PLANKS, 13));
        createCrushingItem(Items.BIRCH_CHEST_BOAT, new ItemStack(Items.BIRCH_PLANKS, 13));
        createCrushingItem(Items.JUNGLE_CHEST_BOAT, new ItemStack(Items.JUNGLE_PLANKS, 13));
        createCrushingItem(Items.ACACIA_CHEST_BOAT, new ItemStack(Items.ACACIA_PLANKS, 13));
        createCrushingItem(Items.DARK_OAK_CHEST_BOAT, new ItemStack(Items.DARK_OAK_PLANKS, 13));
        createCrushingItem(Items.MANGROVE_CHEST_BOAT, new ItemStack(Items.MANGROVE_PLANKS, 13));
        createCrushingItem(Items.CHERRY_CHEST_BOAT, new ItemStack(Items.CHERRY_PLANKS, 13));
        createCrushingItem(Items.PALE_OAK_CHEST_BOAT, new ItemStack(Items.PALE_OAK_PLANKS, 13));
        createCrushingItem(Items.BAMBOO_CHEST_RAFT, new ItemStack(Items.BAMBOO_PLANKS, 13));
        createCrushingItem(Items.WOODEN_AXE, new ItemStack(Items.OAK_PLANKS, 3));
        createCrushingItem(Items.WOODEN_PICKAXE, new ItemStack(Items.OAK_PLANKS, 3));
        createCrushingItem(Items.WOODEN_HOE, new ItemStack(Items.OAK_PLANKS, 2));
        createCrushingItem(Items.WOODEN_SHOVEL, new ItemStack(Items.OAK_PLANKS, 1));
        createCrushingItem(Items.WOODEN_SWORD, new ItemStack(Items.OAK_PLANKS, 2));
        // Coal from logs.
        createCrushingTag(ItemTags.LOGS, Items.CHARCOAL.getDefaultStack());
        // Sugar, rotten flesh or dirt from food, except golden apple and golden carrot.
        createCrushingTag(ConventionalItemTags.BERRY_FOODS, Items.SUGAR.getDefaultStack());
        createCrushingTag(ConventionalItemTags.CANDY_FOODS, Items.SUGAR.getDefaultStack());
        createCrushingTag(ConventionalItemTags.COOKIE_FOODS, Items.SUGAR.getDefaultStack());
        createCrushingTag(ConventionalItemTags.PIE_FOODS, Items.SUGAR.getDefaultStack());
        createCrushingTag(ModItemTagProvider.NORMAL_FRUIT_FOODS, Items.SUGAR.getDefaultStack());
        createCrushingTag(ConventionalItemTags.FOOD_POISONING_FOODS, Items.ROTTEN_FLESH.getDefaultStack());
        createCrushingTag(ConventionalItemTags.RAW_FISH_FOODS, Items.ROTTEN_FLESH.getDefaultStack());
        createCrushingTag(ConventionalItemTags.COOKED_FISH_FOODS, Items.ROTTEN_FLESH.getDefaultStack());
        createCrushingTag(ConventionalItemTags.RAW_MEAT_FOODS, Items.ROTTEN_FLESH.getDefaultStack());
        createCrushingTag(ConventionalItemTags.COOKED_MEAT_FOODS, Items.ROTTEN_FLESH.getDefaultStack());
        createCrushingTag(ConventionalItemTags.BREAD_FOODS, Items.DIRT.getDefaultStack());
        createCrushingTag(ConventionalItemTags.EDIBLE_WHEN_PLACED_FOODS, Items.DIRT.getDefaultStack());
        createCrushingTag(ConventionalItemTags.SOUP_FOODS, Items.DIRT.getDefaultStack());
        createCrushingTag(ModItemTagProvider.NORMAL_VEGETABLE_FOODS, Items.DIRT.getDefaultStack());
        // 8 ingots + 1 apple => 1 golden apple => 9 ingots
        // This allows free generation of gold, for those who discover it.
        cookingTime = 200;
        createCrushingItem(Items.GOLDEN_APPLE, new ItemStack(Items.RAW_GOLD, 9));
        cookingTime = 600;
        createCrushingItem(Items.ENCHANTED_GOLDEN_APPLE, new ItemStack(Items.RAW_GOLD, 64));
        // 8 nugget + 1 carrot => 1 golden carrot => 1 ingots => 9 nuggets
        // This allows free generation of gold, for those who discover it.
        cookingTime = 100;
        createCrushingItem(Items.GOLDEN_CARROT, Items.RAW_GOLD.getDefaultStack());
        // Snow from ice
        createCrushingItem(Items.ICE, new ItemStack(Items.SNOW, 4));
        createCrushingItem(Items.BLUE_ICE, new ItemStack(Items.SNOW, 4));
        createCrushingItem(Items.PACKED_ICE, new ItemStack(Items.SNOW, 36));
        createCrushingItem(Items.SNOW, Items.SNOW.getDefaultStack());
        createCrushingItem(Items.SNOW_BLOCK, new ItemStack(Items.SNOW, 4));
        createCrushingItem(Items.SNOWBALL, Items.SNOW.getDefaultStack());
        // String from soft things.
        createCrushingTag(ItemTags.WOOL, new ItemStack(Items.STRING, 4));
        createCrushingTag(ItemTags.WOOL_CARPETS, Items.STRING.getDefaultStack());
        createCrushingItem(Items.LEAD, new ItemStack(Items.STRING, 2));
        // Wax from candles.
        createCrushingTag(ItemTags.CANDLES, Items.HONEYCOMB.getDefaultStack());
        createCrushingItem(Items.HONEYCOMB_BLOCK, new ItemStack(Items.HONEYCOMB, 4));
        // Glowstone.
        createCrushingItem(Items.GLOWSTONE, new ItemStack(Items.GLOWSTONE_DUST, 4));
        // Sugar.
        createCrushingItem(Items.HONEY_BLOCK, new ItemStack(Items.SUGAR, 12));
        createCrushingItem(Items.SUGAR_CANE, new ItemStack(Items.SUGAR, 2));
        // Gunpowder from TNT.
        // 5 gunpowders + 4 sands => 1 TNT => 6 gunpowder.
        // This allows the generation of gunpowder from sand.
        createCrushingItem(Items.TNT, new ItemStack(Items.GUNPOWDER, 6));
        // Sponge.
        createCrushingItem(Items.WET_SPONGE, Items.SPONGE.getDefaultStack());
        // Dyes from colored items.
        createCrushingItem(Items.LILY_OF_THE_VALLEY, new ItemStack(Items.WHITE_DYE, 2));
        createCrushingItem(Items.BONE_MEAL, new ItemStack(Items.WHITE_DYE, 2));
        createCrushingItem(Items.ORANGE_TULIP, new ItemStack(Items.ORANGE_DYE, 2));
        createCrushingItem(Items.TORCHFLOWER, new ItemStack(Items.ORANGE_DYE, 2));
        createCrushingItem(Items.OPEN_EYEBLOSSOM, new ItemStack(Items.ORANGE_DYE, 2));
        createCrushingItem(Items.ALLIUM, new ItemStack(Items.MAGENTA_DYE, 2));
        createCrushingItem(Items.LILAC, new ItemStack(Items.MAGENTA_DYE, 4));
        createCrushingItem(Items.BLUE_ORCHID, new ItemStack(Items.LIGHT_BLUE_DYE, 2));
        createCrushingItem(Items.DANDELION, new ItemStack(Items.YELLOW_DYE, 2));
        createCrushingItem(Items.SUNFLOWER, new ItemStack(Items.YELLOW_DYE, 4));
        createCrushingItem(Items.PEONY, new ItemStack(Items.PINK_DYE, 4));
        createCrushingItem(Items.PINK_PETALS, new ItemStack(Items.PINK_DYE, 2));
        createCrushingItem(Items.PINK_TULIP, new ItemStack(Items.PINK_DYE, 2));
        createCrushingItem(Items.CLOSED_EYEBLOSSOM, new ItemStack(Items.GRAY_DYE, 2));
        createCrushingItem(Items.OXEYE_DAISY, new ItemStack(Items.LIGHT_GRAY_DYE, 2));
        createCrushingItem(Items.WHITE_TULIP, new ItemStack(Items.LIGHT_GRAY_DYE, 2));
        createCrushingItem(Items.AZURE_BLUET, new ItemStack(Items.LIGHT_GRAY_DYE, 2));
        createCrushingItem(Items.PITCHER_PLANT, new ItemStack(Items.CYAN_DYE, 4));
        createCrushingItem(Items.LAPIS_LAZULI, new ItemStack(Items.BLUE_DYE, 3));
        createCrushingItem(Items.LAPIS_BLOCK, new ItemStack(Items.BLUE_DYE, 32));
        createCrushingItem(Items.CORNFLOWER, new ItemStack(Items.BLUE_DYE, 2));
        createCrushingItem(Items.COCOA_BEANS, new ItemStack(Items.BROWN_DYE, 2));
        createCrushingItem(Items.CACTUS, new ItemStack(Items.GREEN_DYE, 2));
        createCrushingItem(Items.ROSE_BUSH, new ItemStack(Items.RED_DYE, 4));
        createCrushingItem(Items.POPPY, new ItemStack(Items.RED_DYE, 2));
        createCrushingItem(Items.BEETROOT, new ItemStack(Items.RED_DYE, 2));
        createCrushingItem(Items.RED_TULIP, new ItemStack(Items.RED_DYE, 2));
        createCrushingItem(Items.REDSTONE, new ItemStack(Items.RED_DYE, 3));
        createCrushingItem(Items.REDSTONE_BLOCK, new ItemStack(Items.RED_DYE, 32));
        createCrushingItem(Items.INK_SAC, new ItemStack(Items.BLACK_DYE, 2));
        createCrushingItem(Items.WITHER_ROSE, new ItemStack(Items.BLACK_DYE, 2));
        createCrushingItem(Items.CHARCOAL, new ItemStack(Items.BLACK_DYE, 4));
        createCrushingItem(Items.COAL, new ItemStack(Items.BLACK_DYE, 3));
        createCrushingItem(Items.COAL_BLOCK, new ItemStack(Items.BLACK_DYE, 32));
        // Concrete powder from concrete.
        createCrushingItem(Items.WHITE_CONCRETE, Items.WHITE_CONCRETE_POWDER.getDefaultStack());
        createCrushingItem(Items.ORANGE_CONCRETE, Items.ORANGE_CONCRETE_POWDER.getDefaultStack());
        createCrushingItem(Items.MAGENTA_CONCRETE, Items.MAGENTA_CONCRETE_POWDER.getDefaultStack());
        createCrushingItem(Items.LIGHT_BLUE_CONCRETE, Items.LIGHT_BLUE_CONCRETE_POWDER.getDefaultStack());
        createCrushingItem(Items.YELLOW_CONCRETE, Items.YELLOW_CONCRETE_POWDER.getDefaultStack());
        createCrushingItem(Items.LIME_CONCRETE, Items.LIME_CONCRETE_POWDER.getDefaultStack());
        createCrushingItem(Items.PINK_CONCRETE, Items.PINK_CONCRETE_POWDER.getDefaultStack());
        createCrushingItem(Items.GRAY_CONCRETE, Items.GRAY_CONCRETE_POWDER.getDefaultStack());
        createCrushingItem(Items.LIGHT_GRAY_CONCRETE, Items.LIGHT_GRAY_CONCRETE_POWDER.getDefaultStack());
        createCrushingItem(Items.CYAN_CONCRETE, Items.CYAN_CONCRETE_POWDER.getDefaultStack());
        createCrushingItem(Items.PURPLE_CONCRETE, Items.PURPLE_CONCRETE_POWDER.getDefaultStack());
        createCrushingItem(Items.BLUE_CONCRETE, Items.BLUE_CONCRETE_POWDER.getDefaultStack());
        createCrushingItem(Items.BROWN_CONCRETE, Items.BROWN_CONCRETE_POWDER.getDefaultStack());
        createCrushingItem(Items.GREEN_CONCRETE, Items.GREEN_CONCRETE_POWDER.getDefaultStack());
        createCrushingItem(Items.RED_CONCRETE, Items.RED_CONCRETE_POWDER.getDefaultStack());
        createCrushingItem(Items.BLACK_CONCRETE, Items.BLACK_CONCRETE_POWDER.getDefaultStack());
        // Copper from things made of copper.
        createCrushingItem(Items.COPPER_ORE, new ItemStack(Items.RAW_COPPER, 4));
        createCrushingItem(Items.DEEPSLATE_COPPER_ORE, new ItemStack(Items.RAW_COPPER, 4));
        createCrushingItem(Items.COPPER_DOOR, new ItemStack(Items.RAW_COPPER, 2));
        createCrushingItem(Items.WAXED_COPPER_DOOR, new ItemStack(Items.RAW_COPPER, 2));
        createCrushingItem(Items.EXPOSED_COPPER_DOOR, new ItemStack(Items.RAW_COPPER, 2));
        createCrushingItem(Items.WAXED_EXPOSED_COPPER_DOOR, new ItemStack(Items.RAW_COPPER, 2));
        createCrushingItem(Items.WEATHERED_COPPER_DOOR, new ItemStack(Items.RAW_COPPER, 2));
        createCrushingItem(Items.WAXED_WEATHERED_COPPER_DOOR, new ItemStack(Items.RAW_COPPER, 2));
        createCrushingItem(Items.OXIDIZED_COPPER_DOOR, new ItemStack(Items.RAW_COPPER, 2));
        createCrushingItem(Items.WAXED_OXIDIZED_COPPER_DOOR, new ItemStack(Items.RAW_COPPER, 2));
        createCrushingItem(Items.COPPER_TRAPDOOR, new ItemStack(Items.RAW_COPPER, 4));
        createCrushingItem(Items.WAXED_COPPER_TRAPDOOR, new ItemStack(Items.RAW_COPPER, 4));
        createCrushingItem(Items.EXPOSED_COPPER_TRAPDOOR, new ItemStack(Items.RAW_COPPER, 4));
        createCrushingItem(Items.WAXED_EXPOSED_COPPER_TRAPDOOR, new ItemStack(Items.RAW_COPPER, 4));
        createCrushingItem(Items.WEATHERED_COPPER_TRAPDOOR, new ItemStack(Items.RAW_COPPER, 4));
        createCrushingItem(Items.WAXED_WEATHERED_COPPER_TRAPDOOR, new ItemStack(Items.RAW_COPPER, 4));
        createCrushingItem(Items.OXIDIZED_COPPER_TRAPDOOR, new ItemStack(Items.RAW_COPPER, 4));
        createCrushingItem(Items.WAXED_OXIDIZED_COPPER_TRAPDOOR, new ItemStack(Items.RAW_COPPER, 4));
        createCrushingItem(Items.COPPER_BLOCK, new ItemStack(Items.RAW_COPPER, 9));
        createCrushingItem(Items.RAW_COPPER_BLOCK, new ItemStack(Items.RAW_COPPER, 9));
        createCrushingItem(Items.WAXED_COPPER_BLOCK, new ItemStack(Items.RAW_COPPER, 9));
        createCrushingItem(Items.EXPOSED_COPPER, new ItemStack(Items.RAW_COPPER, 9));
        createCrushingItem(Items.WAXED_EXPOSED_COPPER, new ItemStack(Items.RAW_COPPER, 9));
        createCrushingItem(Items.WEATHERED_COPPER, new ItemStack(Items.RAW_COPPER, 9));
        createCrushingItem(Items.WAXED_WEATHERED_COPPER, new ItemStack(Items.RAW_COPPER, 9));
        createCrushingItem(Items.OXIDIZED_COPPER, new ItemStack(Items.RAW_COPPER, 9));
        createCrushingItem(Items.WAXED_OXIDIZED_COPPER, new ItemStack(Items.RAW_COPPER, 9));
        // Iron from things made of iron.
        createCrushingItem(Items.IRON_ORE, new ItemStack(Items.RAW_IRON, 4));
        createCrushingItem(Items.DEEPSLATE_IRON_ORE, new ItemStack(Items.RAW_IRON, 4));
        createCrushingItem(Items.BUCKET, new ItemStack(Items.RAW_IRON, 3));
        createCrushingItem(Items.WATER_BUCKET, new ItemStack(Items.RAW_IRON, 3));
        createCrushingItem(Items.LAVA_BUCKET, new ItemStack(Items.RAW_IRON, 3));
        createCrushingItem(Items.MILK_BUCKET, new ItemStack(Items.RAW_IRON, 3));
        createCrushingItem(Items.TROPICAL_FISH_BUCKET, new ItemStack(Items.RAW_IRON, 3));
        createCrushingItem(Items.SALMON_BUCKET, new ItemStack(Items.RAW_IRON, 3));
        createCrushingItem(Items.PUFFERFISH_BUCKET, new ItemStack(Items.RAW_IRON, 3));
        createCrushingItem(Items.COD_BUCKET, new ItemStack(Items.RAW_IRON, 3));
        createCrushingItem(Items.AXOLOTL_BUCKET, new ItemStack(Items.RAW_IRON, 3));
        createCrushingItem(Items.TADPOLE_BUCKET, new ItemStack(Items.RAW_IRON, 3));
        createCrushingItem(Items.POWDER_SNOW_BUCKET, new ItemStack(Items.RAW_IRON, 3));
        createCrushingItem(Items.SHEARS, new ItemStack(Items.RAW_IRON, 2));
        createCrushingItem(Items.IRON_DOOR, new ItemStack(Items.RAW_IRON, 2));
        createCrushingItem(Items.IRON_TRAPDOOR, new ItemStack(Items.RAW_IRON, 4));
        createCrushingItem(Items.CAULDRON, new ItemStack(Items.RAW_IRON, 7));
        createCrushingItem(Items.IRON_AXE, new ItemStack(Items.RAW_IRON, 3));
        createCrushingItem(Items.IRON_PICKAXE, new ItemStack(Items.RAW_IRON, 3));
        createCrushingItem(Items.IRON_HOE, new ItemStack(Items.RAW_IRON, 2));
        createCrushingItem(Items.IRON_SHOVEL, new ItemStack(Items.RAW_IRON, 1));
        createCrushingItem(Items.IRON_SWORD, new ItemStack(Items.RAW_IRON, 2));
        createCrushingItem(Items.IRON_BLOCK, new ItemStack(Items.RAW_IRON, 9));
        createCrushingItem(Items.RAW_IRON_BLOCK, new ItemStack(Items.RAW_IRON, 9));
        createCrushingItem(Items.IRON_HELMET, new ItemStack(Items.RAW_IRON, 5));
        createCrushingItem(Items.IRON_CHESTPLATE, new ItemStack(Items.RAW_IRON, 8));
        createCrushingItem(Items.IRON_LEGGINGS, new ItemStack(Items.RAW_IRON, 7));
        createCrushingItem(Items.IRON_BOOTS, new ItemStack(Items.RAW_IRON, 4));
        createCrushingItem(Items.CHAINMAIL_HELMET, new ItemStack(Items.RAW_IRON, 5));
        createCrushingItem(Items.CHAINMAIL_CHESTPLATE, new ItemStack(Items.RAW_IRON, 8));
        createCrushingItem(Items.CHAINMAIL_LEGGINGS, new ItemStack(Items.RAW_IRON, 7));
        createCrushingItem(Items.CHAINMAIL_BOOTS, new ItemStack(Items.RAW_IRON, 4));
        createCrushingItem(Items.IRON_HORSE_ARMOR, new ItemStack(Items.RAW_IRON, 7));
        // Iron nuggets from iron things.
        // 11 nuggets => 2 nuggets + 1 ingot => 1 chain => 11 nuggets
        createCrushingItem(Items.CHAIN, new ItemStack(Items.IRON_NUGGET, 11));
        // 54 nuggets => 6 ingots => 16 iron bars => 64 nuggets
        // This allows free generation of iron, for those who discover it.
        createCrushingItem(Items.IRON_BARS, new ItemStack(Items.IRON_NUGGET, 4));
        // Gold from things made of gold.
        cookingTime = 150;
        createCrushingItem(Items.GOLD_ORE, new ItemStack(Items.RAW_GOLD, 4));
        createCrushingItem(Items.DEEPSLATE_GOLD_ORE, new ItemStack(Items.RAW_GOLD, 4));
        createCrushingItem(Items.NETHER_GOLD_ORE, Items.RAW_GOLD.getDefaultStack());
        createCrushingItem(Items.GOLDEN_AXE, new ItemStack(Items.RAW_GOLD, 3));
        createCrushingItem(Items.GOLDEN_PICKAXE, new ItemStack(Items.RAW_GOLD, 3));
        createCrushingItem(Items.GOLDEN_HOE, new ItemStack(Items.RAW_GOLD, 2));
        createCrushingItem(Items.GOLDEN_SHOVEL, new ItemStack(Items.RAW_GOLD, 1));
        createCrushingItem(Items.GOLDEN_SWORD, new ItemStack(Items.RAW_GOLD, 2));
        createCrushingItem(Items.GOLD_BLOCK, new ItemStack(Items.RAW_GOLD, 9));
        createCrushingItem(Items.RAW_GOLD_BLOCK, new ItemStack(Items.RAW_GOLD, 9));
        createCrushingItem(Items.GOLDEN_HELMET, new ItemStack(Items.RAW_GOLD, 5));
        createCrushingItem(Items.GOLDEN_CHESTPLATE, new ItemStack(Items.RAW_GOLD, 8));
        createCrushingItem(Items.GOLDEN_LEGGINGS, new ItemStack(Items.RAW_GOLD, 7));
        createCrushingItem(Items.GOLDEN_BOOTS, new ItemStack(Items.RAW_GOLD, 4));
        createCrushingItem(Items.GOLDEN_HORSE_ARMOR, new ItemStack(Items.RAW_GOLD, 7));
        // Diamond from things made of diamonds.
        cookingTime = 200;
        createCrushingItem(Items.DIAMOND_ORE, new ItemStack(Items.DIAMOND, 4));
        createCrushingItem(Items.DEEPSLATE_DIAMOND_ORE, new ItemStack(Items.DIAMOND, 4));
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
        // Diamond from diamond, even faster
        experience = 1.5f;
        cookingTime = 8;
        createCrushingItem(Items.DIAMOND, Items.DIAMOND.getDefaultStack());
    }
}
