package eu.pintergabor.crusher.datagen.recipegenerator;

import eu.pintergabor.crusher.datagen.ModItemTagProvider;
import eu.pintergabor.crusher.datagen.recipebase.ProcessingRecipeGenerator;
import org.jspecify.annotations.NonNull;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;


public final class CrusherRecipeGenerator {

	private CrusherRecipeGenerator() {
		// Static class.
	}

	/**
	 * Generate crusher recipes.
	 */
	public static void generateRecipes(final @NonNull ProcessingRecipeGenerator generator) {
		// Defaults.
		generator.setParam(0.1F, 100);
		// Gravel from gravel sources.
		generator.createRecipe(ModItemTagProvider.GRAVEL_SOURCES, 1, Items.GRAVEL, 1);
		generator.createRecipe(ModItemTagProvider.GRAVEL_SOURCES_SLABS, 2, Items.GRAVEL, 1);
		generator.createRecipe(ModItemTagProvider.GRAVEL_SOURCES_STAIRS, 2, Items.GRAVEL, 3);
		generator.createRecipe(Items.STONE_AXE, 1, Items.GRAVEL, 3);
		generator.createRecipe(Items.STONE_PICKAXE, 1, Items.GRAVEL, 3);
		generator.createRecipe(Items.STONE_HOE, 1, Items.GRAVEL, 2);
		generator.createRecipe(Items.STONE_SHOVEL, 1, Items.GRAVEL, 1);
		generator.createRecipe(Items.STONE_SWORD, 1, Items.GRAVEL, 2);
		// 6 sands => 6 glasses => 16 glass panes => 
		// 8 cobblestones => 1 furnace => 9 gravels => 9 cobblestones.
		// This allows the free generation of cobblestone.
		generator.createRecipe(Items.FURNACE, 1, Items.GRAVEL, 9);
		// Sand from sandstones and gravel.
		generator.createRecipe(ModItemTagProvider.SAND_SOURCES, 1, Items.SAND, 1);
		generator.createRecipe(Items.NETHER_QUARTZ_ORE, 1, Items.SAND, 4);
		generator.createRecipe(Items.GLASS_BOTTLE, 1, Items.SAND, 1);
		generator.createRecipe(Items.TERRACOTTA, 1, Items.SAND, 1);
		generator.createRecipe(Items.DYED_TERRACOTTA, 1, Items.SAND, 1);
		generator.createRecipe(Items.GLAZED_TERRACOTTA, 1, Items.SAND, 1);
		generator.createRecipe(ConventionalItemTags.UNCOLORED_SANDSTONE_BLOCKS, 1, Items.SAND, 4);
		generator.createRecipe(Items.SANDSTONE, 1, Items.SAND, 4);
		generator.createRecipe(Items.CHISELED_SANDSTONE, 1, Items.SAND, 4);
		generator.createRecipe(Items.CUT_SANDSTONE, 1, Items.SAND, 4);
		generator.createRecipe(ConventionalItemTags.UNCOLORED_SANDSTONE_SLABS, 1, Items.SAND, 2);
		generator.createRecipe(Items.SANDSTONE_SLAB, 1, Items.SAND, 2);
		generator.createRecipe(ConventionalItemTags.UNCOLORED_SANDSTONE_STAIRS, 1, Items.SAND, 6);
		generator.createRecipe(Items.SANDSTONE_STAIRS, 1, Items.SAND, 6);
		generator.createRecipe(Items.SANDSTONE_WALL, 1, Items.SAND, 4);
		generator.createRecipe(ConventionalItemTags.GLASS_BLOCKS, 1, Items.SAND, 1);
		generator.createRecipe(Items.STAINED_GLASS, 1, Items.SAND, 1);
		// 6 sands => 6 glass blocks => 16 glass panes => 8 sands.
		// This allows the free generation of sand.
		generator.createRecipe(ConventionalItemTags.GLASS_PANES, 2, Items.SAND, 1);
		generator.createRecipe(Items.GLASS_PANE, 2, Items.SAND, 1);
		generator.createRecipe(Items.STAINED_GLASS_PANE, 2, Items.SAND, 1);
		// Red sand from red sandstones and other redish blocks.
		generator.createRecipe(ModItemTagProvider.RED_SAND_SOURCES, 1, Items.RED_SAND, 1);
		generator.createRecipe(ConventionalItemTags.RED_SANDSTONE_BLOCKS, 1, Items.RED_SAND, 4);
		generator.createRecipe(Items.RED_SANDSTONE, 1, Items.RED_SAND, 4);
		generator.createRecipe(ConventionalItemTags.RED_SANDSTONE_SLABS, 1, Items.RED_SAND, 2);
		generator.createRecipe(Items.RED_SANDSTONE_SLAB, 1, Items.RED_SAND, 2);
		generator.createRecipe(Items.RED_NETHER_BRICK_SLAB, 1, Items.RED_SAND, 2);
		generator.createRecipe(ConventionalItemTags.RED_SANDSTONE_STAIRS, 1, Items.RED_SAND, 6);
		generator.createRecipe(Items.RED_SANDSTONE_STAIRS, 1, Items.RED_SAND, 6);
		generator.createRecipe(Items.RED_SANDSTONE_WALL, 1, Items.RED_SAND, 4);
		// Blaze powder from blaze rod.
		generator.createRecipe(Items.BLAZE_ROD, 1, Items.BLAZE_POWDER, 2);
		// Bone meal from bone.
		generator.createRecipe(Items.BONE, 1, Items.BONE_MEAL, 6);
		// 9 bone meals => 1 bone block => 10 bone meals.
		// This allows the free generation of bone meal.
		generator.createRecipe(Items.BONE_BLOCK, 1, Items.BONE_MEAL, 10);
		// Sticks from planks and other wooden things.
		generator.createRecipe(ItemTags.PLANKS, 1, Items.STICK, 2);
		generator.createRecipe(ItemTags.WOODEN_SLABS, 1, Items.STICK, 4);
		generator.createRecipe(ItemTags.WOODEN_STAIRS, 1, Items.STICK, 3);
		generator.createRecipe(ItemTags.WOODEN_PRESSURE_PLATES, 1, Items.STICK, 4);
		generator.createRecipe(ItemTags.WOODEN_DOORS, 1, Items.STICK, 4);
		generator.createRecipe(ItemTags.WOODEN_TRAPDOORS, 1, Items.STICK, 6);
		generator.createRecipe(ItemTags.WOODEN_FENCES, 1, Items.STICK, 3);
		generator.createRecipe(ItemTags.WOODEN_BUTTONS, 1, Items.STICK, 1);
		generator.createRecipe(Items.CHISELED_BOOKSHELF, 1, Items.STICK, 18);
		generator.createRecipe(Items.BOW, 1, Items.STICK, 3);
		generator.createRecipe(Items.CROSSBOW, 1, Items.STICK, 9);
		generator.createRecipe(Items.STICK, 1, Items.STICK, 1);
		generator.createRecipe(Items.TORCH, 1, Items.STICK, 2);
		generator.createRecipe(ItemTags.SAPLINGS, 1, Items.STICK, 2);
		generator.createRecipe(ModItemTagProvider.WOODEN_SLABS, 1, Items.STICK, 4);
		generator.createRecipe(ModItemTagProvider.WOODEN_STAIRS, 1, Items.STICK, 3);
		generator.createRecipe(ModItemTagProvider.WOODEN_PRESSURE_PLATES, 1, Items.STICK, 4);
		generator.createRecipe(ModItemTagProvider.WOODEN_SIGNS, 1, Items.STICK, 4);
		generator.createRecipe(ModItemTagProvider.WOODEN_FENCES, 1, Items.STICK, 3);
		generator.createRecipe(ModItemTagProvider.WOODEN_FENCE_GATES, 1, Items.STICK, 8);
		// 7 sticks => 3 ladders => 9 sticks.
		// This allows the free generation of sticks, for those who discover it.
		generator.createRecipe(Items.LADDER, 1, Items.STICK, 3);
		// Planks from items made of planks.
		generator.createRecipe(Items.OAK_SHELF, 1, Items.OAK_PLANKS, 24);
		generator.createRecipe(Items.SPRUCE_SHELF, 1, Items.SPRUCE_PLANKS, 24);
		generator.createRecipe(Items.BIRCH_SHELF, 1, Items.BIRCH_PLANKS, 24);
		generator.createRecipe(Items.JUNGLE_SHELF, 1, Items.JUNGLE_PLANKS, 24);
		generator.createRecipe(Items.ACACIA_SHELF, 1, Items.ACACIA_PLANKS, 24);
		generator.createRecipe(Items.DARK_OAK_SHELF, 1, Items.DARK_OAK_PLANKS, 24);
		generator.createRecipe(Items.MANGROVE_SHELF, 1, Items.MANGROVE_PLANKS, 24);
		generator.createRecipe(Items.CHERRY_SHELF, 1, Items.CHERRY_PLANKS, 24);
		generator.createRecipe(Items.PALE_OAK_SHELF, 1, Items.PALE_OAK_PLANKS, 24);
		generator.createRecipe(Items.BAMBOO_SHELF, 1, Items.BAMBOO_PLANKS, 24);
		generator.createRecipe(Items.CRIMSON_SHELF, 1, Items.CRIMSON_PLANKS, 24);
		generator.createRecipe(Items.WARPED_SHELF, 1, Items.WARPED_PLANKS, 24);
		generator.createRecipe(Items.OAK_BOAT, 1, Items.OAK_PLANKS, 5);
		generator.createRecipe(Items.SPRUCE_BOAT, 1, Items.SPRUCE_PLANKS, 5);
		generator.createRecipe(Items.BIRCH_BOAT, 1, Items.BIRCH_PLANKS, 5);
		generator.createRecipe(Items.JUNGLE_BOAT, 1, Items.JUNGLE_PLANKS, 5);
		generator.createRecipe(Items.ACACIA_BOAT, 1, Items.ACACIA_PLANKS, 5);
		generator.createRecipe(Items.DARK_OAK_BOAT, 1, Items.DARK_OAK_PLANKS, 5);
		generator.createRecipe(Items.MANGROVE_BOAT, 1, Items.MANGROVE_PLANKS, 5);
		generator.createRecipe(Items.CHERRY_BOAT, 1, Items.CHERRY_PLANKS, 5);
		generator.createRecipe(Items.PALE_OAK_BOAT, 1, Items.PALE_OAK_PLANKS, 5);
		generator.createRecipe(Items.BAMBOO_RAFT, 1, Items.BAMBOO_PLANKS, 5);
		generator.createRecipe(Items.CHEST, 1, Items.OAK_PLANKS, 8);
		generator.createRecipe(Items.CRAFTING_TABLE, 1, Items.OAK_PLANKS, 4);
		generator.createRecipe(Items.OAK_CHEST_BOAT, 1, Items.OAK_PLANKS, 13);
		generator.createRecipe(Items.SPRUCE_CHEST_BOAT, 1, Items.SPRUCE_PLANKS, 13);
		generator.createRecipe(Items.BIRCH_CHEST_BOAT, 1, Items.BIRCH_PLANKS, 13);
		generator.createRecipe(Items.JUNGLE_CHEST_BOAT, 1, Items.JUNGLE_PLANKS, 13);
		generator.createRecipe(Items.ACACIA_CHEST_BOAT, 1, Items.ACACIA_PLANKS, 13);
		generator.createRecipe(Items.DARK_OAK_CHEST_BOAT, 1, Items.DARK_OAK_PLANKS, 13);
		generator.createRecipe(Items.MANGROVE_CHEST_BOAT, 1, Items.MANGROVE_PLANKS, 13);
		generator.createRecipe(Items.CHERRY_CHEST_BOAT, 1, Items.CHERRY_PLANKS, 13);
		generator.createRecipe(Items.PALE_OAK_CHEST_BOAT, 1, Items.PALE_OAK_PLANKS, 13);
		generator.createRecipe(Items.BAMBOO_CHEST_RAFT, 1, Items.BAMBOO_PLANKS, 13);
		generator.createRecipe(Items.WOODEN_AXE, 1, Items.OAK_PLANKS, 4);
		generator.createRecipe(Items.WOODEN_PICKAXE, 1, Items.OAK_PLANKS, 4);
		generator.createRecipe(Items.WOODEN_HOE, 1, Items.OAK_PLANKS, 3);
		generator.createRecipe(Items.WOODEN_SHOVEL, 1, Items.OAK_PLANKS, 2);
		generator.createRecipe(Items.WOODEN_SWORD, 1, Items.OAK_PLANKS, 3);
		// Coal from logs.
		generator.createRecipe(ItemTags.LOGS, 1, Items.CHARCOAL, 1);
		// Sugar, rotten flesh or dirt from food, except golden apple and golden carrot.
		generator.createRecipe(ConventionalItemTags.BERRY_FOODS, 1, Items.SUGAR, 1);
		generator.createRecipe(ConventionalItemTags.CANDY_FOODS, 1, Items.SUGAR, 1);
		generator.createRecipe(ConventionalItemTags.COOKIE_FOODS, 1, Items.SUGAR, 1);
		generator.createRecipe(ConventionalItemTags.PIE_FOODS, 1, Items.SUGAR, 1);
		generator.createRecipe(ModItemTagProvider.NORMAL_FRUIT_FOODS, 1, Items.SUGAR, 1);
		generator.createRecipe(ConventionalItemTags.FOOD_POISONING_FOODS, 1, Items.ROTTEN_FLESH, 1);
		generator.createRecipe(ConventionalItemTags.RAW_FISH_FOODS, 1, Items.ROTTEN_FLESH, 1);
		generator.createRecipe(ConventionalItemTags.COOKED_FISH_FOODS, 1, Items.ROTTEN_FLESH, 1);
		generator.createRecipe(ConventionalItemTags.RAW_MEAT_FOODS, 1, Items.ROTTEN_FLESH, 1);
		generator.createRecipe(ConventionalItemTags.COOKED_MEAT_FOODS, 1, Items.ROTTEN_FLESH, 1);
		generator.createRecipe(ConventionalItemTags.BREAD_FOODS, 4, Items.DIRT, 1);
		generator.createRecipe(ConventionalItemTags.EDIBLE_WHEN_PLACED_FOODS, 1, Items.DIRT, 1);
		generator.createRecipe(ConventionalItemTags.SOUP_FOODS, 4, Items.DIRT, 1);
		generator.createRecipe(ModItemTagProvider.NORMAL_VEGETABLE_FOODS, 4, Items.DIRT, 1);
		// 8 ingots + 1 apple => 1 golden apple => 9 ingots.
		// This allows the free generation of gold, for those who discover it.
		generator.setParam(0.1F, 200);
		generator.createRecipe(Items.GOLDEN_APPLE, 1, Items.RAW_GOLD, 9);
		generator.setParam(0.1F, 600);
		generator.createRecipe(Items.ENCHANTED_GOLDEN_APPLE, 1, Items.RAW_GOLD, 64);
		// 8 nuggets + 1 carrot => 1 golden carrot => 1 ingots => 9 nuggets.
		// This allows the free generation of gold, for those who discover it.
		generator.setParam(0.1F, 100);
		generator.createRecipe(Items.GOLDEN_CARROT, 1, Items.RAW_GOLD, 1);
		// Snow from ice.
		generator.createRecipe(Items.ICE, 1, Items.SNOW, 4);
		generator.createRecipe(Items.BLUE_ICE, 1, Items.SNOW, 4);
		generator.createRecipe(Items.PACKED_ICE, 1, Items.SNOW, 36);
		generator.createRecipe(Items.SNOW, 1, Items.SNOW, 1);
		generator.createRecipe(Items.SNOW_BLOCK, 1, Items.SNOW, 4);
		generator.createRecipe(Items.SNOWBALL, 1, Items.SNOW, 1);
		// String from soft things.
		generator.createRecipe(ItemTags.WOOL, 1, Items.STRING, 4);
		generator.createRecipe(Items.WOOL, 1, Items.STRING, 4);
		generator.createRecipe(Items.BED, 1, Items.STRING, 12);
		generator.createRecipe(Items.LEAD, 1, Items.STRING, 2);
		generator.createRecipe(Items.BANNER, 1, Items.STRING, 24);
		// 8 strings => 2 wools => 3 carpets => 9 strings.
		// This allows the free generation of strings.
		generator.createRecipe(ItemTags.WOOL_CARPETS, 1, Items.STRING, 3);
		generator.createRecipe(Items.CARPET, 1, Items.STRING, 3);
		// Paper from books and bookshelves.
		// Creates plenty of paper, but does it worth it?
		generator.createRecipe(Items.BOOK, 1, Items.PAPER, 4);
		generator.createRecipe(Items.BOOKSHELF, 1, Items.PAPER, 32);
		generator.createRecipe(Items.LECTERN, 1, Items.PAPER, 48);
		// Wax from candles.
		generator.createRecipe(ItemTags.CANDLES, 1, Items.HONEYCOMB, 1);
		generator.createRecipe(Items.DYED_CANDLE, 1, Items.HONEYCOMB, 1);
		generator.createRecipe(Items.HONEYCOMB_BLOCK, 1, Items.HONEYCOMB, 4);
		// Glowstone.
		generator.createRecipe(Items.GLOWSTONE, 1, Items.GLOWSTONE_DUST, 4);
		// Sugar.
		generator.createRecipe(Items.HONEY_BLOCK, 1, Items.SUGAR, 12);
		generator.createRecipe(Items.SUGAR_CANE, 1, Items.SUGAR, 2);
		// Gunpowder from TNT.
		// 5 gunpowders + 4 sands => 1 TNT => 6 gunpowder.
		// This allows the generation of gunpowder from sand.
		generator.createRecipe(Items.TNT, 1, Items.GUNPOWDER, 6);
		// Sponge.
		generator.createRecipe(Items.WET_SPONGE, 1, Items.SPONGE, 1);
		// Dyes from colored items.
		generator.createRecipe(Items.LILY_OF_THE_VALLEY, 1, Items.DYE.white(), 2);
		generator.createRecipe(Items.BONE_MEAL, 1, Items.DYE.white(), 2);
		generator.createRecipe(Items.ORANGE_TULIP, 1, Items.DYE.orange(), 2);
		generator.createRecipe(Items.TORCHFLOWER, 1, Items.DYE.orange(), 2);
		generator.createRecipe(Items.OPEN_EYEBLOSSOM, 1, Items.DYE.orange(), 2);
		generator.createRecipe(Items.ALLIUM, 1, Items.DYE.magenta(), 2);
		generator.createRecipe(Items.LILAC, 1, Items.DYE.magenta(), 4);
		generator.createRecipe(Items.BLUE_ORCHID, 1, Items.DYE.lightBlue(), 2);
		generator.createRecipe(Items.DANDELION, 1, Items.DYE.yellow(), 2);
		generator.createRecipe(Items.SUNFLOWER, 1, Items.DYE.yellow(), 4);
		generator.createRecipe(Items.PEONY, 1, Items.DYE.pink(), 4);
		generator.createRecipe(Items.PINK_PETALS, 1, Items.DYE.pink(), 2);
		generator.createRecipe(Items.PINK_TULIP, 1, Items.DYE.pink(), 2);
		generator.createRecipe(Items.CLOSED_EYEBLOSSOM, 1, Items.DYE.gray(), 2);
		generator.createRecipe(Items.OXEYE_DAISY, 1, Items.DYE.lightGray(), 2);
		generator.createRecipe(Items.WHITE_TULIP, 1, Items.DYE.lightGray(), 2);
		generator.createRecipe(Items.AZURE_BLUET, 1, Items.DYE.lightGray(), 2);
		generator.createRecipe(Items.PITCHER_PLANT, 1, Items.DYE.cyan(), 4);
		generator.createRecipe(Items.LAPIS_LAZULI, 1, Items.DYE.blue(), 3);
		generator.createRecipe(Items.LAPIS_BLOCK, 1, Items.DYE.blue(), 32);
		generator.createRecipe(Items.CORNFLOWER, 1, Items.DYE.blue(), 2);
		generator.createRecipe(Items.COCOA_BEANS, 1, Items.DYE.brown(), 2);
		generator.createRecipe(Items.CACTUS, 1, Items.DYE.green(), 2);
		generator.createRecipe(Items.ROSE_BUSH, 1, Items.DYE.red(), 4);
		generator.createRecipe(Items.POPPY, 1, Items.DYE.red(), 2);
		generator.createRecipe(Items.BEETROOT, 1, Items.DYE.red(), 2);
		generator.createRecipe(Items.RED_TULIP, 1, Items.DYE.red(), 2);
		generator.createRecipe(Items.REDSTONE, 1, Items.DYE.red(), 3);
		generator.createRecipe(Items.REDSTONE_BLOCK, 1, Items.DYE.red(), 32);
		generator.createRecipe(Items.INK_SAC, 1, Items.DYE.black(), 2);
		generator.createRecipe(Items.WITHER_ROSE, 1, Items.DYE.black(), 2);
		generator.createRecipe(Items.CHARCOAL, 1, Items.DYE.black(), 4);
		generator.createRecipe(Items.COAL, 1, Items.DYE.black(), 3);
		generator.createRecipe(Items.COAL_BLOCK, 1, Items.DYE.black(), 32);
		// Concrete powder from concrete.
		generator.createRecipe(Items.CONCRETE.white(), 1, Items.CONCRETE_POWDER.white(), 1);
		generator.createRecipe(Items.CONCRETE.orange(), 1, Items.CONCRETE_POWDER.orange(), 1);
		generator.createRecipe(Items.CONCRETE.magenta(), 1, Items.CONCRETE_POWDER.magenta(), 1);
		generator.createRecipe(Items.CONCRETE.lightBlue(), 1, Items.CONCRETE_POWDER.lightBlue(), 1);
		generator.createRecipe(Items.CONCRETE.yellow(), 1, Items.CONCRETE_POWDER.yellow(), 1);
		generator.createRecipe(Items.CONCRETE.lime(), 1, Items.CONCRETE_POWDER.lime(), 1);
		generator.createRecipe(Items.CONCRETE.pink(), 1, Items.CONCRETE_POWDER.pink(), 1);
		generator.createRecipe(Items.CONCRETE.gray(), 1, Items.CONCRETE_POWDER.gray(), 1);
		generator.createRecipe(Items.CONCRETE.lightGray(), 1, Items.CONCRETE_POWDER.lightGray(), 1);
		generator.createRecipe(Items.CONCRETE.cyan(), 1, Items.CONCRETE_POWDER.cyan(), 1);
		generator.createRecipe(Items.CONCRETE.purple(), 1, Items.CONCRETE_POWDER.purple(), 1);
		generator.createRecipe(Items.CONCRETE.blue(), 1, Items.CONCRETE_POWDER.blue(), 1);
		generator.createRecipe(Items.CONCRETE.brown(), 1, Items.CONCRETE_POWDER.brown(), 1);
		generator.createRecipe(Items.CONCRETE.green(), 1, Items.CONCRETE_POWDER.green(), 1);
		generator.createRecipe(Items.CONCRETE.red(), 1, Items.CONCRETE_POWDER.red(), 1);
		generator.createRecipe(Items.CONCRETE.black(), 1, Items.CONCRETE_POWDER.black(), 1);
		// Copper nuggets from copper things.
		// 11 nuggets => 2 nuggets + 1 ingot => 1 chain => 11 nuggets
		generator.createRecipe(Items.COPPER_CHAIN, 1, Items.COPPER_NUGGET, 11);
		// 54 nuggets => 6 ingots => 16 copper bars => 64 nuggets
		// This allows the free generation of copper, for those who discover it.
		generator.createRecipe(Items.COPPER_BARS, 1, Items.COPPER_NUGGET, 4);
		// Copper from things made of copper.
		generator.createRecipe(Items.COPPER_ORE, 1, Items.RAW_COPPER, 1);
		generator.createRecipe(Items.DEEPSLATE_COPPER_ORE, 1, Items.RAW_COPPER, 1);
		generator.createRecipe(Items.COPPER_CHEST, 1, Items.RAW_COPPER, 8);
		generator.createRecipe(Items.LIGHTNING_ROD, 1, Items.RAW_COPPER, 3);
		// 8 nuggets + 1 copper torch containing 1 nugget => 1 lantern => 1 ingot.
		generator.createRecipe(Items.COPPER_LANTERN, 1, Items.RAW_COPPER, 1);
		generator.createRecipe(ModItemTagProvider.COPPER_BLOCKS, 1, Items.RAW_COPPER, 9);
		// 27 ingots => 3 blocks => 1 bulb => 27 ingots.
		generator.createRecipe(ModItemTagProvider.COPPER_BULBS, 1, Items.RAW_COPPER, 27);
		// Using the stonecutter allows the free and very efficient generation of copper.
		// For example:
		// 9 ingots => 1 block => 4 cut blocks => 36 ingots.
		generator.createRecipe(ModItemTagProvider.CUT_COPPER_BLOCKS, 1, Items.RAW_COPPER, 9);
		generator.createRecipe(ModItemTagProvider.CHISELED_COPPER_BLOCKS, 1, Items.RAW_COPPER, 9);
		generator.createRecipe(ModItemTagProvider.COPPER_STAIRS, 2, Items.RAW_COPPER, 27);
		generator.createRecipe(ModItemTagProvider.COPPER_SLABS, 2, Items.RAW_COPPER, 9);
		generator.createRecipe(ModItemTagProvider.COPPER_DOORS, 1, Items.RAW_COPPER, 2);
		generator.createRecipe(ModItemTagProvider.COPPER_TRAPDOORS, 1, Items.RAW_COPPER, 4);
		generator.createRecipe(ModItemTagProvider.COPPER_GRATES, 1, Items.RAW_COPPER, 9);
		// Iron from things made of iron.
		generator.createRecipe(Items.IRON_ORE, 1, Items.RAW_IRON, 4);
		generator.createRecipe(Items.DEEPSLATE_IRON_ORE, 1, Items.RAW_IRON, 4);
		generator.createRecipe(Items.BUCKET, 1, Items.RAW_IRON, 3);
		generator.createRecipe(Items.WATER_BUCKET, 1, Items.RAW_IRON, 3);
		generator.createRecipe(Items.LAVA_BUCKET, 1, Items.RAW_IRON, 3);
		generator.createRecipe(Items.MILK_BUCKET, 1, Items.RAW_IRON, 3);
		generator.createRecipe(Items.TROPICAL_FISH_BUCKET, 1, Items.RAW_IRON, 3);
		generator.createRecipe(Items.SALMON_BUCKET, 1, Items.RAW_IRON, 3);
		generator.createRecipe(Items.PUFFERFISH_BUCKET, 1, Items.RAW_IRON, 3);
		generator.createRecipe(Items.COD_BUCKET, 1, Items.RAW_IRON, 3);
		generator.createRecipe(Items.AXOLOTL_BUCKET, 1, Items.RAW_IRON, 3);
		generator.createRecipe(Items.TADPOLE_BUCKET, 1, Items.RAW_IRON, 3);
		generator.createRecipe(Items.POWDER_SNOW_BUCKET, 1, Items.RAW_IRON, 3);
		generator.createRecipe(Items.SHEARS, 1, Items.RAW_IRON, 2);
		generator.createRecipe(Items.IRON_DOOR, 1, Items.RAW_IRON, 2);
		generator.createRecipe(Items.IRON_TRAPDOOR, 1, Items.RAW_IRON, 3);
		generator.createRecipe(Items.CAULDRON, 1, Items.RAW_IRON, 7);
		generator.createRecipe(Items.IRON_AXE, 1, Items.RAW_IRON, 3);
		generator.createRecipe(Items.IRON_PICKAXE, 1, Items.RAW_IRON, 3);
		generator.createRecipe(Items.IRON_HOE, 1, Items.RAW_IRON, 2);
		generator.createRecipe(Items.IRON_SHOVEL, 1, Items.RAW_IRON, 1);
		generator.createRecipe(Items.IRON_SWORD, 1, Items.RAW_IRON, 2);
		generator.createRecipe(Items.IRON_BLOCK, 1, Items.RAW_IRON, 9);
		generator.createRecipe(Items.RAW_IRON_BLOCK, 1, Items.RAW_IRON, 9);
		generator.createRecipe(Items.IRON_HELMET, 1, Items.RAW_IRON, 5);
		generator.createRecipe(Items.IRON_CHESTPLATE, 1, Items.RAW_IRON, 8);
		generator.createRecipe(Items.IRON_LEGGINGS, 1, Items.RAW_IRON, 7);
		generator.createRecipe(Items.IRON_BOOTS, 1, Items.RAW_IRON, 4);
		generator.createRecipe(Items.CHAINMAIL_HELMET, 1, Items.RAW_IRON, 5);
		generator.createRecipe(Items.CHAINMAIL_CHESTPLATE, 1, Items.RAW_IRON, 8);
		generator.createRecipe(Items.CHAINMAIL_LEGGINGS, 1, Items.RAW_IRON, 7);
		generator.createRecipe(Items.CHAINMAIL_BOOTS, 1, Items.RAW_IRON, 4);
		generator.createRecipe(Items.IRON_HORSE_ARMOR, 1, Items.RAW_IRON, 7);
		generator.createRecipe(Items.RAIL, 8, Items.RAW_IRON, 3);
		generator.createRecipe(Items.ACTIVATOR_RAIL, 1, Items.RAW_IRON, 1);
		generator.createRecipe(Items.DETECTOR_RAIL, 1, Items.RAW_IRON, 1);
		generator.createRecipe(Items.ANVIL, 1, Items.RAW_IRON, 31);
		generator.createRecipe(Items.CHIPPED_ANVIL, 1, Items.RAW_IRON, 20);
		generator.createRecipe(Items.DAMAGED_ANVIL, 1, Items.RAW_IRON, 10);
		generator.createRecipe(Items.MINECART, 1, Items.RAW_IRON, 5);
		generator.createRecipe(Items.CHEST_MINECART, 1, Items.RAW_IRON, 5);
		generator.createRecipe(Items.FURNACE_MINECART, 1, Items.RAW_IRON, 5);
		generator.createRecipe(Items.HOPPER_MINECART, 1, Items.RAW_IRON, 10);
		generator.createRecipe(Items.HOPPER, 1, Items.RAW_IRON, 5);
		generator.createRecipe(Items.PISTON, 1, Items.RAW_IRON, 1);
		generator.createRecipe(Items.STICKY_PISTON, 1, Items.RAW_IRON, 1);
		// 8 nuggets + 1 torch => 1 lantern => 1 ingot.
		// This allows free generation of iron, for those who discover it.
		generator.createRecipe(Items.LANTERN, 1, Items.RAW_IRON, 1);
		// Iron nuggets from iron things.
		// 11 nuggets => 2 nuggets + 1 ingot => 1 chain => 11 nuggets
		generator.createRecipe(Items.IRON_CHAIN, 1, Items.IRON_NUGGET, 11);
		// 54 nuggets => 6 ingots => 16 iron bars => 64 nuggets
		// This allows free generation of iron, for those who discover it.
		generator.createRecipe(Items.IRON_BARS, 1, Items.IRON_NUGGET, 4);
		// Gold from things made of gold.
		generator.setParam(0.1F, 150);
		generator.createRecipe(Items.GOLD_ORE, 1, Items.RAW_GOLD, 4);
		generator.createRecipe(Items.DEEPSLATE_GOLD_ORE, 1, Items.RAW_GOLD, 4);
		generator.createRecipe(Items.NETHER_GOLD_ORE, 1, Items.RAW_GOLD, 1);
		generator.createRecipe(Items.GOLDEN_AXE, 1, Items.RAW_GOLD, 3);
		generator.createRecipe(Items.GOLDEN_PICKAXE, 1, Items.RAW_GOLD, 3);
		generator.createRecipe(Items.GOLDEN_HOE, 1, Items.RAW_GOLD, 2);
		generator.createRecipe(Items.GOLDEN_SHOVEL, 1, Items.RAW_GOLD, 1);
		generator.createRecipe(Items.GOLDEN_SWORD, 1, Items.RAW_GOLD, 2);
		generator.createRecipe(Items.GOLD_BLOCK, 1, Items.RAW_GOLD, 9);
		generator.createRecipe(Items.RAW_GOLD_BLOCK, 1, Items.RAW_GOLD, 9);
		generator.createRecipe(Items.GOLDEN_HELMET, 1, Items.RAW_GOLD, 5);
		generator.createRecipe(Items.GOLDEN_CHESTPLATE, 1, Items.RAW_GOLD, 8);
		generator.createRecipe(Items.GOLDEN_LEGGINGS, 1, Items.RAW_GOLD, 7);
		generator.createRecipe(Items.GOLDEN_BOOTS, 1, Items.RAW_GOLD, 4);
		generator.createRecipe(Items.GOLDEN_HORSE_ARMOR, 1, Items.RAW_GOLD, 7);
		generator.createRecipe(Items.POWERED_RAIL, 1, Items.RAW_GOLD, 1);
		// Diamond from things made of diamonds.
		generator.setParam(0.1F, 200);
		generator.createRecipe(Items.DIAMOND_ORE, 1, Items.DIAMOND, 4);
		generator.createRecipe(Items.DEEPSLATE_DIAMOND_ORE, 1, Items.DIAMOND, 4);
		generator.createRecipe(Items.DIAMOND_AXE, 1, Items.DIAMOND, 3);
		generator.createRecipe(Items.DIAMOND_PICKAXE, 1, Items.DIAMOND, 3);
		generator.createRecipe(Items.DIAMOND_HOE, 1, Items.DIAMOND, 2);
		generator.createRecipe(Items.DIAMOND_SHOVEL, 1, Items.DIAMOND, 1);
		generator.createRecipe(Items.DIAMOND_SWORD, 1, Items.DIAMOND, 2);
		generator.createRecipe(Items.DIAMOND_BLOCK, 1, Items.DIAMOND, 9);
		generator.createRecipe(Items.DIAMOND_HELMET, 1, Items.DIAMOND, 5);
		generator.createRecipe(Items.DIAMOND_CHESTPLATE, 1, Items.DIAMOND, 8);
		generator.createRecipe(Items.DIAMOND_LEGGINGS, 1, Items.DIAMOND, 7);
		generator.createRecipe(Items.DIAMOND_BOOTS, 1, Items.DIAMOND, 4);
		generator.createRecipe(Items.DIAMOND_HORSE_ARMOR, 1, Items.DIAMOND, 7);
		// Sand from sand fast. It creates experience.
		generator.setParam(1.0F, 10);
		generator.createRecipe(Items.SAND, 1, Items.SAND, 1);
		generator.createRecipe(Items.RED_SAND, 1, Items.RED_SAND, 1);
		// Diamond from diamond, even faster. It creates even more experience.
		generator.setParam(1.5F, 8);
		generator.createRecipe(Items.DIAMOND, 1, Items.DIAMOND, 1);
	}
}
