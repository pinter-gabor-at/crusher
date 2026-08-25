package eu.pintergabor.crusher.datagen.recipegenerator;

import eu.pintergabor.crusher.blocks.ModBlocks;
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
		generator.createRecipe(Items.STONE_SPEAR, 1, Items.GRAVEL, 1);
		// 8 cobblestones => 1 furnace => 9 gravels => 9 cobblestones.
		// This allows the free generation of cobblestone.
		generator.createRecipe(Items.FURNACE, 1, Items.GRAVEL, 9);
		// Various machines. They are crushable, but it is pointless to crush them.
		generator.createRecipe(Items.DROPPER, 1, Items.GRAVEL, 9);
		generator.createRecipe(Items.DISPENSER, 1, Items.GRAVEL, 9);
		generator.createRecipe(Items.OBSERVER, 1, Items.GRAVEL, 9);
		generator.createRecipe(Items.SMOKER, 1, Items.GRAVEL, 9);
		generator.createRecipe(Items.FLETCHING_TABLE, 1, Items.GRAVEL, 2);
		generator.createRecipe(Items.LOOM, 1, Items.STRING, 2);
		generator.createRecipe(Items.GRINDSTONE, 1, Items.GRAVEL, 5);
		generator.createRecipe(Items.STONECUTTER, 1, Items.GRAVEL, 4);
		generator.createRecipe(Items.SMITHING_TABLE, 1, Items.RAW_IRON, 2);
		generator.createRecipe(ModBlocks.CRUSHER_ITEM, 1, Items.GRAVEL, 12);
		generator.createRecipe(ModBlocks.COMPRESSOR_ITEM, 1, Items.GRAVEL, 12);
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
		generator.createRecipe(Items.ENCHANTING_TABLE, 1, Items.SAND, 64);
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
		generator.createRecipe(Items.BRICK_SLAB, 1, Items.RED_SAND, 2);
		generator.createRecipe(ConventionalItemTags.RED_SANDSTONE_STAIRS, 1, Items.RED_SAND, 6);
		generator.createRecipe(Items.RED_SANDSTONE_STAIRS, 1, Items.RED_SAND, 6);
		generator.createRecipe(Items.RED_SANDSTONE_WALL, 1, Items.RED_SAND, 4);
		// Sulfur.
		generator.createRecipe(Items.SULFUR, 1, Items.SULFUR_SPIKE, 4);
		generator.createRecipe(Items.POTENT_SULFUR, 1, Items.SULFUR, 9);
		generator.createRecipe(Items.SULFUR_SLAB, 2, Items.SULFUR, 1);
		generator.createRecipe(Items.SULFUR_STAIRS, 2, Items.SULFUR, 3);
		generator.createRecipe(Items.SULFUR_WALL, 3, Items.SULFUR, 2);
		generator.createRecipe(Items.POLISHED_SULFUR, 1, Items.SULFUR, 1);
		generator.createRecipe(Items.POLISHED_SULFUR_SLAB, 2, Items.SULFUR, 1);
		generator.createRecipe(Items.POLISHED_SULFUR_STAIRS, 2, Items.SULFUR, 3);
		generator.createRecipe(Items.POLISHED_SULFUR_WALL, 1, Items.SULFUR, 1);
		generator.createRecipe(Items.SULFUR_BRICKS, 1, Items.SULFUR, 1);
		generator.createRecipe(Items.SULFUR_BRICK_SLAB, 2, Items.SULFUR, 1);
		generator.createRecipe(Items.SULFUR_BRICK_STAIRS, 2, Items.SULFUR, 3);
		generator.createRecipe(Items.SULFUR_BRICK_WALL, 1, Items.SULFUR, 1);
		generator.createRecipe(Items.CHISELED_SULFUR, 1, Items.SULFUR, 1);
		// Cinnabar.
		generator.createRecipe(Items.CINNABAR_SLAB, 2, Items.CINNABAR, 1);
		generator.createRecipe(Items.CINNABAR_STAIRS, 2, Items.CINNABAR, 3);
		generator.createRecipe(Items.CINNABAR_WALL, 3, Items.CINNABAR, 2);
		generator.createRecipe(Items.POLISHED_CINNABAR, 1, Items.CINNABAR, 1);
		generator.createRecipe(Items.POLISHED_CINNABAR_SLAB, 2, Items.CINNABAR, 1);
		generator.createRecipe(Items.POLISHED_CINNABAR_STAIRS, 2, Items.CINNABAR, 3);
		generator.createRecipe(Items.POLISHED_CINNABAR_WALL, 1, Items.CINNABAR, 1);
		generator.createRecipe(Items.CINNABAR_BRICKS, 1, Items.CINNABAR, 1);
		generator.createRecipe(Items.CINNABAR_BRICK_SLAB, 2, Items.CINNABAR, 1);
		generator.createRecipe(Items.CINNABAR_BRICK_STAIRS, 2, Items.CINNABAR, 3);
		generator.createRecipe(Items.CINNABAR_BRICK_WALL, 1, Items.CINNABAR, 1);
		generator.createRecipe(Items.CHISELED_CINNABAR, 1, Items.CINNABAR, 1);
		// Blaze powder from blaze rod.
		generator.createRecipe(Items.BLAZE_ROD, 1, Items.BLAZE_POWDER, 2);
		// Magma block.
		generator.createRecipe(Items.MAGMA_BLOCK, 1, Items.MAGMA_CREAM, 4);
		// 9 slime balls => 1 slime block => 10 slime balls.
		// This allows the free generation of slime balls.
		generator.createRecipe(Items.SLIME_BLOCK, 1, Items.SLIME_BALL, 10);
		// 1 slime ball + 1 blaze powder => 1 magma cream => 2 blaze powders.
		// This allows the free generation of blaze powder.
		generator.createRecipe(Items.MAGMA_CREAM, 1, Items.BLAZE_POWDER, 2);
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
		generator.createRecipe(ItemTags.FENCE_GATES, 1, Items.STICK, 8);
		generator.createRecipe(ItemTags.WOODEN_BUTTONS, 1, Items.STICK, 1);
		generator.createRecipe(Items.BOW, 1, Items.STICK, 3);
		generator.createRecipe(Items.CROSSBOW, 1, Items.STICK, 9);
		generator.createRecipe(Items.STICK, 1, Items.STICK, 1);
		generator.createRecipe(Items.TORCH, 1, Items.STICK, 2);
		generator.createRecipe(Items.SOUL_TORCH, 1, Items.STICK, 2);
		generator.createRecipe(Items.COPPER_TORCH, 1, Items.STICK, 2);
		generator.createRecipe(Items.JUKEBOX, 1, Items.STICK, 20);
		generator.createRecipe(ItemTags.SAPLINGS, 1, Items.STICK, 2);
		generator.createRecipe(ModItemTagProvider.WOODEN_SLABS, 1, Items.STICK, 4);
		generator.createRecipe(ModItemTagProvider.WOODEN_STAIRS, 1, Items.STICK, 3);
		generator.createRecipe(ModItemTagProvider.WOODEN_PRESSURE_PLATES, 1, Items.STICK, 4);
		generator.createRecipe(ModItemTagProvider.WOODEN_SIGNS, 1, Items.STICK, 4);
		generator.createRecipe(Items.ARMOR_STAND, 1, Items.STICK, 8);
		generator.createRecipe(Items.COMPOSTER, 1, Items.STICK, 14);
		generator.createRecipe(Items.ARROW, 1, Items.STICK, 1);
		generator.createRecipe(Items.SPECTRAL_ARROW, 1, Items.STICK, 1);
		// 7 sticks => 3 ladders => 9 sticks.
		// This allows the free generation of sticks, for those who discover it.
		generator.createRecipe(Items.LADDER, 1, Items.STICK, 3);
		// Bamboo.
		// 6 bamboos + 1 string => 1 scaffolding => 7 bamboos.
		// This allows the free generation of bamboos, for those who discover it.
		generator.createRecipe(Items.SCAFFOLDING, 1, Items.BAMBOO, 7);
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
		generator.createRecipe(Items.TRAPPED_CHEST, 1, Items.OAK_PLANKS, 8);
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
		generator.createRecipe(Items.WOODEN_SPEAR, 1, Items.OAK_PLANKS, 2);
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
		generator.createRecipe(Items.CHISELED_BOOKSHELF, 1, Items.PAPER, 32);
		generator.createRecipe(Items.LECTERN, 1, Items.PAPER, 48);
		generator.createRecipe(Items.CARTOGRAPHY_TABLE, 1, Items.PAPER, 6);
		// Wax from candles.
		generator.createRecipe(ItemTags.CANDLES, 1, Items.HONEYCOMB, 1);
		generator.createRecipe(Items.DYED_CANDLE, 1, Items.HONEYCOMB, 1);
		generator.createRecipe(Items.HONEYCOMB_BLOCK, 1, Items.HONEYCOMB, 4);
		generator.createRecipe(Items.BEEHIVE, 1, Items.HONEYCOMB, 3);
		generator.createRecipe(Items.BEE_NEST, 1, Items.HONEYCOMB, 3);
		// Glowstone.
		generator.createRecipe(Items.GLOWSTONE, 1, Items.GLOWSTONE_DUST, 4);
		// 4 glowstone dusts + 4 redstone dusts => 1 redstone lamp => 5 glowstone dusts.
		// This allows the generation of glowstone dust from redstone dust.
		generator.createRecipe(Items.REDSTONE_LAMP, 1, Items.GLOWSTONE_DUST, 5);
		// Redstone.
		generator.createRecipe(Items.REDSTONE_TORCH, 1, Items.REDSTONE, 1);
		generator.createRecipe(Items.REPEATER, 1, Items.REDSTONE, 3);
		// (8 sands => 8 glasses => 1 quartz.)
		// 3 redstone dusts + 1 quartz + 3 stones => 1 comparator => 4 redstone dusts.
		// This allows the generation of redstone dust in a complicated way.
		generator.createRecipe(Items.COMPARATOR, 1, Items.REDSTONE, 4);
		// End stone.
		generator.createRecipe(Items.END_STONE_BRICKS, 1, Items.END_STONE, 1);
		generator.createRecipe(Items.END_STONE_BRICK_SLAB, 2, Items.END_STONE, 1);
		generator.createRecipe(Items.END_STONE_BRICK_STAIRS, 2, Items.END_STONE, 3);
		generator.createRecipe(Items.END_STONE_BRICK_WALL, 1, Items.END_STONE, 1);
		// Prismarine.
		generator.createRecipe(Items.PRISMARINE, 1, Items.PRISMARINE_SHARD, 4);
		generator.createRecipe(Items.PRISMARINE_SLAB, 2, Items.PRISMARINE, 1);
		generator.createRecipe(Items.PRISMARINE_STAIRS, 2, Items.PRISMARINE, 3);
		generator.createRecipe(Items.PRISMARINE_WALL, 1, Items.PRISMARINE, 1);
		generator.createRecipe(Items.PRISMARINE_BRICKS, 1, Items.PRISMARINE, 1);
		generator.createRecipe(Items.PRISMARINE_BRICK_SLAB, 2, Items.PRISMARINE, 1);
		generator.createRecipe(Items.PRISMARINE_BRICK_STAIRS, 2, Items.PRISMARINE, 3);
		generator.createRecipe(Items.DARK_PRISMARINE_SLAB, 2, Items.DARK_PRISMARINE, 1);
		generator.createRecipe(Items.DARK_PRISMARINE_STAIRS, 2, Items.DARK_PRISMARINE, 3);
		// 8 shards + 1 black dye => 1 black prismarine => 9 shards.
		// This allows the generation of prismarine from black dye.
		generator.createRecipe(Items.DARK_PRISMARINE, 1, Items.PRISMARINE_SHARD, 9);
		// 4 shards + 5 crystals => 1 lantern => 9 crystals.
		// This allows the generation of prismarine crystals from shards.
		generator.createRecipe(Items.SEA_LANTERN, 1, Items.PRISMARINE_CRYSTALS, 7);
		// Resin.
		generator.createRecipe(Items.RESIN_BRICKS, 1, Items.RESIN_BRICK, 4);
		generator.createRecipe(Items.RESIN_BLOCK, 1, Items.RESIN_CLUMP, 9);
		generator.createRecipe(Items.RESIN_BRICK_SLAB, 2, Items.RESIN_BRICK, 4);
		generator.createRecipe(Items.RESIN_BRICK_STAIRS, 2, Items.RESIN_BRICK, 12);
		generator.createRecipe(Items.RESIN_BRICK_WALL, 1, Items.RESIN_BRICK, 4);
		generator.createRecipe(Items.CHISELED_RESIN_BRICKS, 1, Items.RESIN_BRICK, 4);
		// Mud.
		generator.createRecipe(Items.MUD_BRICKS, 1, Items.MUD, 1);
		generator.createRecipe(Items.MUD_BRICK_SLAB, 2, Items.MUD, 1);
		generator.createRecipe(Items.MUD_BRICK_STAIRS, 2, Items.MUD, 3);
		generator.createRecipe(Items.MUD_BRICK_WALL, 1, Items.MUD, 1);
		// Sugar.
		generator.createRecipe(Items.HONEY_BLOCK, 1, Items.SUGAR, 12);
		generator.createRecipe(Items.SUGAR_CANE, 1, Items.SUGAR, 2);
		// Gunpowder from TNT.
		// 5 gunpowders + 4 sands => 1 TNT => 6 gunpowder.
		// This allows the generation of gunpowder from sand.
		generator.createRecipe(Items.TNT, 1, Items.GUNPOWDER, 6);
		// Sponge.
		generator.createRecipe(Items.WET_SPONGE, 1, Items.SPONGE, 1);
		// Amethyst.
		generator.createRecipe(Items.AMETHYST_BLOCK, 1, Items.AMETHYST_SHARD, 4);
		// Dripstone.
		generator.createRecipe(Items.DRIPSTONE_BLOCK, 1, Items.POINTED_DRIPSTONE, 4);
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
		generator.createRecipe(Items.AMETHYST_SHARD, 1, Items.DYE.purple(), 32);
		generator.createRecipe(Items.CALCITE, 1, Items.DYE.white(), 8);
		generator.createRecipe(Items.POINTED_DRIPSTONE, 1, Items.DYE.brown(), 1);
		generator.createRecipe(Items.CHORUS_FLOWER, 1, Items.DYE.purple(), 1);
		generator.createRecipe(Items.CHORUS_FRUIT, 1, Items.DYE.purple(), 1);
		generator.createRecipe(Items.POPPED_CHORUS_FRUIT, 1, Items.DYE.purple(), 2);
		generator.createRecipe(Items.CHORUS_PLANT, 1, Items.DYE.purple(), 1);
		generator.createRecipe(Items.PURPUR_BLOCK, 1, Items.DYE.purple(), 8);
		generator.createRecipe(Items.PURPUR_PILLAR, 1, Items.DYE.purple(), 8);
		generator.createRecipe(Items.PURPUR_STAIRS, 1, Items.DYE.purple(), 12);
		generator.createRecipe(Items.PURPUR_SLAB, 1, Items.DYE.purple(), 4);
		// Various items from ores.
		generator.createRecipe(Items.COAL_ORE, 1, Items.COAL, 4);
		generator.createRecipe(Items.DEEPSLATE_COAL_ORE, 1, Items.COAL, 4);
		generator.createRecipe(Items.LAPIS_ORE, 1, Items.LAPIS_LAZULI, 32);
		generator.createRecipe(Items.DEEPSLATE_LAPIS_ORE, 1, Items.LAPIS_LAZULI, 32);
		generator.createRecipe(Items.REDSTONE_ORE, 1, Items.REDSTONE, 8);
		generator.createRecipe(Items.DEEPSLATE_REDSTONE_ORE, 1, Items.REDSTONE, 8);
		generator.createRecipe(Items.EMERALD_ORE, 1, Items.EMERALD, 4);
		generator.createRecipe(Items.DEEPSLATE_EMERALD_ORE, 1, Items.EMERALD, 4);
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
		// Copper nuggets from things made of copper.
		// 11 nuggets => 2 nuggets + 1 ingot => 1 chain => 11 nuggets
		generator.createRecipe(Items.COPPER_CHAIN, 1, Items.COPPER_NUGGET, 11);
		// 54 nuggets => 6 ingots => 16 copper bars => 64 nuggets
		// This allows the free generation of copper, for those who discover it.
		generator.createRecipe(Items.COPPER_BARS, 1, Items.COPPER_NUGGET, 4);
		// Copper from things made of copper.
		generator.createRecipe(Items.COPPER_ORE, 1, Items.RAW_COPPER, 4);
		generator.createRecipe(Items.DEEPSLATE_COPPER_ORE, 1, Items.RAW_COPPER, 1);
		generator.createRecipe(Items.COPPER_CHEST, 1, Items.RAW_COPPER, 8);
		generator.createRecipe(Items.LIGHTNING_ROD, 1, Items.RAW_COPPER, 3);
		generator.createRecipe(ModItemTagProvider.COPPER_DOORS, 1, Items.RAW_COPPER, 2);
		generator.createRecipe(ModItemTagProvider.COPPER_TRAPDOORS, 1, Items.RAW_COPPER, 4);
		generator.createRecipe(Items.COPPER_BULB, 1, Items.RAW_COPPER, 30);
		generator.createRecipe(ModItemTagProvider.COPPER_BLOCKS, 1, Items.RAW_COPPER, 9);
		generator.createRecipe(Items.RAW_COPPER_BLOCK, 1, Items.RAW_COPPER, 9);
		generator.createRecipe(ModItemTagProvider.COPPER_GRATES, 1, Items.RAW_COPPER, 9);
		// 8 nuggets + 1 copper torch containing 1 nugget => 1 lantern => 1 ingot.
		generator.createRecipe(Items.COPPER_LANTERN, 1, Items.RAW_COPPER, 1);
		// 9 ingots => 1 block => 4 cut blocks => 9 ingots.
		generator.createRecipe(ModItemTagProvider.CUT_COPPER_BLOCKS, 4, Items.RAW_COPPER, 9);
		generator.createRecipe(ModItemTagProvider.CHISELED_COPPER_BLOCKS, 4, Items.RAW_COPPER, 9);
		// 9 ingots => 1 block => 4 cut blocks => 4 stairs => 8 ingots.
		// This is not so good.
		generator.createRecipe(ModItemTagProvider.COPPER_STAIRS, 1, Items.RAW_COPPER, 2);
		// 9 ingots => 1 block => 4 cut blocks => 8 slabs => 8 ingots.
		// This is not so good.
		generator.createRecipe(ModItemTagProvider.COPPER_SLABS, 1, Items.RAW_COPPER, 1);
		// Iron from things made of iron.
		generator.createRecipe(Items.IRON_ORE, 1, Items.RAW_IRON, 4);
		generator.createRecipe(Items.DEEPSLATE_IRON_ORE, 1, Items.RAW_IRON, 4);
		generator.createRecipe(ModItemTagProvider.BUCKETS, 1, Items.RAW_IRON, 3);
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
		generator.createRecipe(Items.FLINT_AND_STEEL, 1, Items.RAW_IRON, 1);
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
		generator.createRecipe(Items.NETHER_GOLD_ORE, 1, Items.RAW_GOLD, 4);
		generator.createRecipe(Items.GOLDEN_AXE, 1, Items.RAW_GOLD, 3);
		generator.createRecipe(Items.GOLDEN_PICKAXE, 1, Items.RAW_GOLD, 3);
		generator.createRecipe(Items.GOLDEN_HOE, 1, Items.RAW_GOLD, 2);
		generator.createRecipe(Items.GOLDEN_SHOVEL, 1, Items.RAW_GOLD, 1);
		generator.createRecipe(Items.GOLDEN_SWORD, 1, Items.RAW_GOLD, 2);
		generator.createRecipe(Items.GOLDEN_SPEAR, 1, Items.RAW_GOLD, 1);
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
		generator.createRecipe(Items.DIAMOND_SPEAR, 1, Items.DIAMOND, 1);
		// Emerald.
		generator.createRecipe(Items.EMERALD_BLOCK, 1, Items.EMERALD, 9);
		// Ender perl.
		// 8 obsidians + 1 ender perl + 1 blaze powder => 1 ender chest => 2 ender perls.
		// This allows the generation of redstone dust in a complicated and expensive way.
		generator.createRecipe(Items.ENDER_CHEST, 1, Items.ENDER_PEARL, 2);
		// Sand from sand fast. It creates experience.
		generator.setParam(1.0F, 10);
		generator.createRecipe(Items.SAND, 1, Items.SAND, 1);
		generator.createRecipe(Items.RED_SAND, 1, Items.RED_SAND, 1);
		// Diamond from diamond and emerald from emerald even faster. It creates even more experiences.
		generator.setParam(1.5F, 8);
		generator.createRecipe(Items.DIAMOND, 1, Items.DIAMOND, 1);
		generator.createRecipe(Items.EMERALD, 1, Items.EMERALD, 1);
	}
}
