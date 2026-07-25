package eu.pintergabor.crusher.datagen;

import eu.pintergabor.crusher.blocks.ModBlocks;
import eu.pintergabor.crusher.datagen.recipebase.ProcessingRecipeGenerator;
import eu.pintergabor.crusher.recipe.CompressorRecipe;
import eu.pintergabor.crusher.recipe.CrusherRecipe;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;


public final class ModRecipeGenerator extends RecipeProvider {
	private final ProcessingRecipeGenerator crusherRecipe;
	private final ProcessingRecipeGenerator compressorRecipe;

	public ModRecipeGenerator(
		final HolderLookup.@NonNull Provider registries,
		final @NonNull RecipeOutput output
	) {
		super(registries, output);
		crusherRecipe = new ProcessingRecipeGenerator(
			registries, output,
			CrusherRecipe::new, "_from_crushing_"
		) {
			@Override
			public void buildRecipes() {
				// The crusher.
				buildProcessor(ModBlocks.CRUSHER_BLOCK, Items.IRON_PICKAXE);
				// The crushing recipes.
				generateCrusherRecipes();
			}
		};
		compressorRecipe = new ProcessingRecipeGenerator(
			registries, output,
			CompressorRecipe::new, "_from_compressing_"
		) {
			@Override
			public void buildRecipes() {
				// The compressor.
				buildProcessor(ModBlocks.COMPRESSOR_BLOCK, Items.PISTON);
				// The compressing recipes.
				generateCompressorRecipes();
			}
		};
	}

	@Override
	public void buildRecipes() {
		crusherRecipe.buildRecipes();
		compressorRecipe.buildRecipes();
	}

	/**
	 * Generate crusher recipes.
	 */
	private void generateCrusherRecipes() {
		// Defaults.
		crusherRecipe.setParam(0.1F, 100);
		// Gravel from gravel sources.
		crusherRecipe.createRecipe(ModItemTagProvider.GRAVEL_SOURCES, 1, Items.GRAVEL, 1);
		crusherRecipe.createRecipe(ModItemTagProvider.GRAVEL_SOURCES_SLABS, 2, Items.GRAVEL, 1);
		crusherRecipe.createRecipe(ModItemTagProvider.GRAVEL_SOURCES_STAIRS, 2, Items.GRAVEL, 3);
		crusherRecipe.createRecipe(Items.STONE_AXE, 1, Items.GRAVEL, 3);
		crusherRecipe.createRecipe(Items.STONE_PICKAXE, 1, Items.GRAVEL, 3);
		crusherRecipe.createRecipe(Items.STONE_HOE, 1, Items.GRAVEL, 2);
		crusherRecipe.createRecipe(Items.STONE_SHOVEL, 1, Items.GRAVEL, 1);
		crusherRecipe.createRecipe(Items.STONE_SWORD, 1, Items.GRAVEL, 2);
		// 6 sands => 6 glasses => 16 glass panes => 
		// 8 cobblestones => 1 furnace => 9 gravels => 9 cobblestones.
		// This allows the free generation of cobblestone.
		crusherRecipe.createRecipe(Items.FURNACE, 1, Items.GRAVEL, 9);
		// Sand from sandstones and gravel.
		crusherRecipe.createRecipe(ModItemTagProvider.SAND_SOURCES, 1, Items.SAND, 1);
		crusherRecipe.createRecipe(Items.NETHER_QUARTZ_ORE, 1, Items.SAND, 4);
		crusherRecipe.createRecipe(Items.GLASS_BOTTLE, 1, Items.SAND, 1);
		crusherRecipe.createRecipe(Items.TERRACOTTA, 1, Items.SAND, 1);
		crusherRecipe.createRecipe(Items.DYED_TERRACOTTA, 1, Items.SAND, 1);
		crusherRecipe.createRecipe(Items.GLAZED_TERRACOTTA, 1, Items.SAND, 1);
		crusherRecipe.createRecipe(ConventionalItemTags.UNCOLORED_SANDSTONE_BLOCKS, 1, Items.SAND, 4);
		crusherRecipe.createRecipe(Items.SANDSTONE, 1, Items.SAND, 4);
		crusherRecipe.createRecipe(Items.CHISELED_SANDSTONE, 1, Items.SAND, 4);
		crusherRecipe.createRecipe(Items.CUT_SANDSTONE, 1, Items.SAND, 4);
		crusherRecipe.createRecipe(ConventionalItemTags.UNCOLORED_SANDSTONE_SLABS, 1, Items.SAND, 2);
		crusherRecipe.createRecipe(Items.SANDSTONE_SLAB, 1, Items.SAND, 2);
		crusherRecipe.createRecipe(ConventionalItemTags.UNCOLORED_SANDSTONE_STAIRS, 1, Items.SAND, 6);
		crusherRecipe.createRecipe(Items.SANDSTONE_STAIRS, 1, Items.SAND, 6);
		crusherRecipe.createRecipe(Items.SANDSTONE_WALL, 1, Items.SAND, 4);
		crusherRecipe.createRecipe(ConventionalItemTags.GLASS_BLOCKS, 1, Items.SAND, 1);
		crusherRecipe.createRecipe(Items.STAINED_GLASS, 1, Items.SAND, 1);
		// 6 sands => 6 glass blocks => 16 glass panes => 8 sands.
		// This allows the free generation of sand.
		crusherRecipe.createRecipe(ConventionalItemTags.GLASS_PANES, 2, Items.SAND, 1);
		crusherRecipe.createRecipe(Items.GLASS_PANE, 2, Items.SAND, 1);
		crusherRecipe.createRecipe(Items.STAINED_GLASS_PANE, 2, Items.SAND, 1);
		// Red sand from red sandstones and other redish blocks.
		crusherRecipe.createRecipe(ModItemTagProvider.RED_SAND_SOURCES, 1, Items.RED_SAND, 1);
		crusherRecipe.createRecipe(ConventionalItemTags.RED_SANDSTONE_BLOCKS, 1, Items.RED_SAND, 4);
		crusherRecipe.createRecipe(Items.RED_SANDSTONE, 1, Items.RED_SAND, 4);
		crusherRecipe.createRecipe(ConventionalItemTags.RED_SANDSTONE_SLABS, 1, Items.RED_SAND, 2);
		crusherRecipe.createRecipe(Items.RED_SANDSTONE_SLAB, 1, Items.RED_SAND, 2);
		crusherRecipe.createRecipe(Items.RED_NETHER_BRICK_SLAB, 1, Items.RED_SAND, 2);
		crusherRecipe.createRecipe(ConventionalItemTags.RED_SANDSTONE_STAIRS, 1, Items.RED_SAND, 6);
		crusherRecipe.createRecipe(Items.RED_SANDSTONE_STAIRS, 1, Items.RED_SAND, 6);
		crusherRecipe.createRecipe(Items.RED_SANDSTONE_WALL, 1, Items.RED_SAND, 4);
		// Blaze powder from blaze rod.
		crusherRecipe.createRecipe(Items.BLAZE_ROD, 1, Items.BLAZE_POWDER, 2);
		// Bone meal from bone.
		crusherRecipe.createRecipe(Items.BONE, 1, Items.BONE_MEAL, 6);
		// 9 bone meals => 1 bone block => 10 bone meals.
		// This allows the free generation of bone meal.
		crusherRecipe.createRecipe(Items.BONE_BLOCK, 1, Items.BONE_MEAL, 10);
		// Sticks from planks and other wooden things.
		crusherRecipe.createRecipe(ItemTags.PLANKS, 1, Items.STICK, 4);
		crusherRecipe.createRecipe(Items.STICK, 1, Items.STICK, 1);
		crusherRecipe.createRecipe(Items.TORCH, 1, Items.STICK, 2);
		crusherRecipe.createRecipe(ItemTags.SAPLINGS, 1, Items.STICK, 2);
		crusherRecipe.createRecipe(ModItemTagProvider.WOODEN_STAIRS, 1, Items.STICK, 3);
		crusherRecipe.createRecipe(ModItemTagProvider.WOODEN_SLABS, 1, Items.STICK, 1);
		crusherRecipe.createRecipe(ModItemTagProvider.WOODEN_PRESSURE_PLATES, 1, Items.STICK, 4);
		crusherRecipe.createRecipe(ModItemTagProvider.WOODEN_SIGNS, 1, Items.STICK, 4);
		crusherRecipe.createRecipe(ModItemTagProvider.WOODEN_FENCES, 1, Items.STICK, 3);
		crusherRecipe.createRecipe(ModItemTagProvider.WOODEN_FENCE_GATES, 1, Items.STICK, 8);
		// 7 sticks => 3 ladders => 9 sticks.
		// This allows the free generation of sticks, for those who discover it.
		crusherRecipe.createRecipe(Items.LADDER, 1, Items.STICK, 3);
		// Planks from items made of planks.
		crusherRecipe.createRecipe(Items.OAK_DOOR, 1, Items.OAK_PLANKS, 2);
		crusherRecipe.createRecipe(Items.SPRUCE_DOOR, 1, Items.SPRUCE_PLANKS, 2);
		crusherRecipe.createRecipe(Items.BIRCH_DOOR, 1, Items.BIRCH_PLANKS, 2);
		crusherRecipe.createRecipe(Items.JUNGLE_DOOR, 1, Items.JUNGLE_PLANKS, 2);
		crusherRecipe.createRecipe(Items.ACACIA_DOOR, 1, Items.ACACIA_PLANKS, 2);
		crusherRecipe.createRecipe(Items.DARK_OAK_DOOR, 1, Items.DARK_OAK_PLANKS, 2);
		crusherRecipe.createRecipe(Items.MANGROVE_DOOR, 1, Items.MANGROVE_PLANKS, 2);
		crusherRecipe.createRecipe(Items.CHERRY_DOOR, 1, Items.CHERRY_PLANKS, 2);
		crusherRecipe.createRecipe(Items.PALE_OAK_DOOR, 1, Items.PALE_OAK_PLANKS, 2);
		crusherRecipe.createRecipe(Items.BAMBOO_DOOR, 1, Items.BAMBOO_PLANKS, 2);
		crusherRecipe.createRecipe(Items.CRIMSON_DOOR, 1, Items.CRIMSON_PLANKS, 2);
		crusherRecipe.createRecipe(Items.WARPED_DOOR, 1, Items.WARPED_PLANKS, 2);
		crusherRecipe.createRecipe(Items.OAK_TRAPDOOR, 1, Items.OAK_PLANKS, 3);
		crusherRecipe.createRecipe(Items.SPRUCE_TRAPDOOR, 1, Items.SPRUCE_PLANKS, 3);
		crusherRecipe.createRecipe(Items.BIRCH_TRAPDOOR, 1, Items.BIRCH_PLANKS, 3);
		crusherRecipe.createRecipe(Items.JUNGLE_TRAPDOOR, 1, Items.JUNGLE_PLANKS, 3);
		crusherRecipe.createRecipe(Items.ACACIA_TRAPDOOR, 1, Items.ACACIA_PLANKS, 3);
		crusherRecipe.createRecipe(Items.DARK_OAK_TRAPDOOR, 1, Items.DARK_OAK_PLANKS, 3);
		crusherRecipe.createRecipe(Items.MANGROVE_TRAPDOOR, 1, Items.MANGROVE_PLANKS, 3);
		crusherRecipe.createRecipe(Items.CHERRY_TRAPDOOR, 1, Items.CHERRY_PLANKS, 3);
		crusherRecipe.createRecipe(Items.PALE_OAK_TRAPDOOR, 1, Items.PALE_OAK_PLANKS, 3);
		crusherRecipe.createRecipe(Items.BAMBOO_TRAPDOOR, 1, Items.BAMBOO_PLANKS, 3);
		crusherRecipe.createRecipe(Items.CRIMSON_TRAPDOOR, 1, Items.CRIMSON_PLANKS, 3);
		crusherRecipe.createRecipe(Items.WARPED_TRAPDOOR, 1, Items.WARPED_PLANKS, 3);
		crusherRecipe.createRecipe(Items.OAK_BOAT, 1, Items.OAK_PLANKS, 5);
		crusherRecipe.createRecipe(Items.SPRUCE_BOAT, 1, Items.SPRUCE_PLANKS, 5);
		crusherRecipe.createRecipe(Items.BIRCH_BOAT, 1, Items.BIRCH_PLANKS, 5);
		crusherRecipe.createRecipe(Items.JUNGLE_BOAT, 1, Items.JUNGLE_PLANKS, 5);
		crusherRecipe.createRecipe(Items.ACACIA_BOAT, 1, Items.ACACIA_PLANKS, 5);
		crusherRecipe.createRecipe(Items.DARK_OAK_BOAT, 1, Items.DARK_OAK_PLANKS, 5);
		crusherRecipe.createRecipe(Items.MANGROVE_BOAT, 1, Items.MANGROVE_PLANKS, 5);
		crusherRecipe.createRecipe(Items.CHERRY_BOAT, 1, Items.CHERRY_PLANKS, 5);
		crusherRecipe.createRecipe(Items.PALE_OAK_BOAT, 1, Items.PALE_OAK_PLANKS, 5);
		crusherRecipe.createRecipe(Items.BAMBOO_RAFT, 1, Items.BAMBOO_PLANKS, 5);
		crusherRecipe.createRecipe(Items.CHEST, 1, Items.OAK_PLANKS, 8);
		crusherRecipe.createRecipe(Items.CRAFTING_TABLE, 1, Items.OAK_PLANKS, 4);
		crusherRecipe.createRecipe(Items.OAK_CHEST_BOAT, 1, Items.OAK_PLANKS, 13);
		crusherRecipe.createRecipe(Items.SPRUCE_CHEST_BOAT, 1, Items.SPRUCE_PLANKS, 13);
		crusherRecipe.createRecipe(Items.BIRCH_CHEST_BOAT, 1, Items.BIRCH_PLANKS, 13);
		crusherRecipe.createRecipe(Items.JUNGLE_CHEST_BOAT, 1, Items.JUNGLE_PLANKS, 13);
		crusherRecipe.createRecipe(Items.ACACIA_CHEST_BOAT, 1, Items.ACACIA_PLANKS, 13);
		crusherRecipe.createRecipe(Items.DARK_OAK_CHEST_BOAT, 1, Items.DARK_OAK_PLANKS, 13);
		crusherRecipe.createRecipe(Items.MANGROVE_CHEST_BOAT, 1, Items.MANGROVE_PLANKS, 13);
		crusherRecipe.createRecipe(Items.CHERRY_CHEST_BOAT, 1, Items.CHERRY_PLANKS, 13);
		crusherRecipe.createRecipe(Items.PALE_OAK_CHEST_BOAT, 1, Items.PALE_OAK_PLANKS, 13);
		crusherRecipe.createRecipe(Items.BAMBOO_CHEST_RAFT, 1, Items.BAMBOO_PLANKS, 13);
		crusherRecipe.createRecipe(Items.WOODEN_AXE, 1, Items.OAK_PLANKS, 3);
		crusherRecipe.createRecipe(Items.WOODEN_PICKAXE, 1, Items.OAK_PLANKS, 3);
		crusherRecipe.createRecipe(Items.WOODEN_HOE, 1, Items.OAK_PLANKS, 2);
		crusherRecipe.createRecipe(Items.WOODEN_SHOVEL, 1, Items.OAK_PLANKS, 1);
		crusherRecipe.createRecipe(Items.WOODEN_SWORD, 1, Items.OAK_PLANKS, 2);
		// Coal from logs.
		crusherRecipe.createRecipe(ItemTags.LOGS, 1, Items.CHARCOAL, 1);
		// Sugar, rotten flesh or dirt from food, except golden apple and golden carrot.
		crusherRecipe.createRecipe(ConventionalItemTags.BERRY_FOODS, 1, Items.SUGAR, 1);
		crusherRecipe.createRecipe(ConventionalItemTags.CANDY_FOODS, 1, Items.SUGAR, 1);
		crusherRecipe.createRecipe(ConventionalItemTags.COOKIE_FOODS, 1, Items.SUGAR, 1);
		crusherRecipe.createRecipe(ConventionalItemTags.PIE_FOODS, 1, Items.SUGAR, 1);
		crusherRecipe.createRecipe(ModItemTagProvider.NORMAL_FRUIT_FOODS, 1, Items.SUGAR, 1);
		crusherRecipe.createRecipe(ConventionalItemTags.FOOD_POISONING_FOODS, 1, Items.ROTTEN_FLESH, 1);
		crusherRecipe.createRecipe(ConventionalItemTags.RAW_FISH_FOODS, 1, Items.ROTTEN_FLESH, 1);
		crusherRecipe.createRecipe(ConventionalItemTags.COOKED_FISH_FOODS, 1, Items.ROTTEN_FLESH, 1);
		crusherRecipe.createRecipe(ConventionalItemTags.RAW_MEAT_FOODS, 1, Items.ROTTEN_FLESH, 1);
		crusherRecipe.createRecipe(ConventionalItemTags.COOKED_MEAT_FOODS, 1, Items.ROTTEN_FLESH, 1);
		crusherRecipe.createRecipe(ConventionalItemTags.BREAD_FOODS, 4, Items.DIRT, 1);
		crusherRecipe.createRecipe(ConventionalItemTags.EDIBLE_WHEN_PLACED_FOODS, 1, Items.DIRT, 1);
		crusherRecipe.createRecipe(ConventionalItemTags.SOUP_FOODS, 4, Items.DIRT, 1);
		crusherRecipe.createRecipe(ModItemTagProvider.NORMAL_VEGETABLE_FOODS, 4, Items.DIRT, 1);
		// 8 ingots + 1 apple => 1 golden apple => 9 ingots.
		// This allows the free generation of gold, for those who discover it.
		crusherRecipe.setParam(0.1F, 200);
		crusherRecipe.createRecipe(Items.GOLDEN_APPLE, 1, Items.RAW_GOLD, 9);
		crusherRecipe.setParam(0.1F, 600);
		crusherRecipe.createRecipe(Items.ENCHANTED_GOLDEN_APPLE, 1, Items.RAW_GOLD, 64);
		// 8 nuggets + 1 carrot => 1 golden carrot => 1 ingots => 9 nuggets.
		// This allows the free generation of gold, for those who discover it.
		crusherRecipe.setParam(0.1F, 100);
		crusherRecipe.createRecipe(Items.GOLDEN_CARROT, 1, Items.RAW_GOLD, 1);
		// Snow from ice.
		crusherRecipe.createRecipe(Items.ICE, 1, Items.SNOW, 4);
		crusherRecipe.createRecipe(Items.BLUE_ICE, 1, Items.SNOW, 4);
		crusherRecipe.createRecipe(Items.PACKED_ICE, 1, Items.SNOW, 36);
		crusherRecipe.createRecipe(Items.SNOW, 1, Items.SNOW, 1);
		crusherRecipe.createRecipe(Items.SNOW_BLOCK, 1, Items.SNOW, 4);
		crusherRecipe.createRecipe(Items.SNOWBALL, 1, Items.SNOW, 1);
		// String from soft things.
		crusherRecipe.createRecipe(ItemTags.WOOL, 1, Items.STRING, 4);
		crusherRecipe.createRecipe(Items.WOOL, 1, Items.STRING, 4);
		crusherRecipe.createRecipe(Items.BED, 1, Items.STRING, 12);
		crusherRecipe.createRecipe(Items.LEAD, 1, Items.STRING, 2);
		crusherRecipe.createRecipe(Items.BANNER, 1, Items.STRING, 24);
		// 4 strings => 2 wools => 3 carpets => 9 strings.
		// This allows the free generation of strings.
		crusherRecipe.createRecipe(ItemTags.WOOL_CARPETS, 1, Items.STRING, 3);
		crusherRecipe.createRecipe(Items.CARPET, 1, Items.STRING, 3);
		// Wax from candles.
		crusherRecipe.createRecipe(ItemTags.CANDLES, 1, Items.HONEYCOMB, 1);
		crusherRecipe.createRecipe(Items.DYED_CANDLE, 1, Items.HONEYCOMB, 1);
		crusherRecipe.createRecipe(Items.HONEYCOMB_BLOCK, 1, Items.HONEYCOMB, 4);
		// Glowstone.
		crusherRecipe.createRecipe(Items.GLOWSTONE, 1, Items.GLOWSTONE_DUST, 4);
		// Sugar.
		crusherRecipe.createRecipe(Items.HONEY_BLOCK, 1, Items.SUGAR, 12);
		crusherRecipe.createRecipe(Items.SUGAR_CANE, 1, Items.SUGAR, 2);
		// Gunpowder from TNT.
		// 5 gunpowders + 4 sands => 1 TNT => 6 gunpowder.
		// This allows the generation of gunpowder from sand.
		crusherRecipe.createRecipe(Items.TNT, 1, Items.GUNPOWDER, 6);
		// Sponge.
		crusherRecipe.createRecipe(Items.WET_SPONGE, 1, Items.SPONGE, 1);
		// Dyes from colored items.
		crusherRecipe.createRecipe(Items.LILY_OF_THE_VALLEY, 1, Items.DYE.white(), 2);
		crusherRecipe.createRecipe(Items.BONE_MEAL, 1, Items.DYE.white(), 2);
		crusherRecipe.createRecipe(Items.ORANGE_TULIP, 1, Items.DYE.orange(), 2);
		crusherRecipe.createRecipe(Items.TORCHFLOWER, 1, Items.DYE.orange(), 2);
		crusherRecipe.createRecipe(Items.OPEN_EYEBLOSSOM, 1, Items.DYE.orange(), 2);
		crusherRecipe.createRecipe(Items.ALLIUM, 1, Items.DYE.magenta(), 2);
		crusherRecipe.createRecipe(Items.LILAC, 1, Items.DYE.magenta(), 4);
		crusherRecipe.createRecipe(Items.BLUE_ORCHID, 1, Items.DYE.lightBlue(), 2);
		crusherRecipe.createRecipe(Items.DANDELION, 1, Items.DYE.yellow(), 2);
		crusherRecipe.createRecipe(Items.SUNFLOWER, 1, Items.DYE.yellow(), 4);
		crusherRecipe.createRecipe(Items.PEONY, 1, Items.DYE.pink(), 4);
		crusherRecipe.createRecipe(Items.PINK_PETALS, 1, Items.DYE.pink(), 2);
		crusherRecipe.createRecipe(Items.PINK_TULIP, 1, Items.DYE.pink(), 2);
		crusherRecipe.createRecipe(Items.CLOSED_EYEBLOSSOM, 1, Items.DYE.gray(), 2);
		crusherRecipe.createRecipe(Items.OXEYE_DAISY, 1, Items.DYE.lightGray(), 2);
		crusherRecipe.createRecipe(Items.WHITE_TULIP, 1, Items.DYE.lightGray(), 2);
		crusherRecipe.createRecipe(Items.AZURE_BLUET, 1, Items.DYE.lightGray(), 2);
		crusherRecipe.createRecipe(Items.PITCHER_PLANT, 1, Items.DYE.cyan(), 4);
		crusherRecipe.createRecipe(Items.LAPIS_LAZULI, 1, Items.DYE.blue(), 3);
		crusherRecipe.createRecipe(Items.LAPIS_BLOCK, 1, Items.DYE.blue(), 32);
		crusherRecipe.createRecipe(Items.CORNFLOWER, 1, Items.DYE.blue(), 2);
		crusherRecipe.createRecipe(Items.COCOA_BEANS, 1, Items.DYE.brown(), 2);
		crusherRecipe.createRecipe(Items.CACTUS, 1, Items.DYE.green(), 2);
		crusherRecipe.createRecipe(Items.ROSE_BUSH, 1, Items.DYE.red(), 4);
		crusherRecipe.createRecipe(Items.POPPY, 1, Items.DYE.red(), 2);
		crusherRecipe.createRecipe(Items.BEETROOT, 1, Items.DYE.red(), 2);
		crusherRecipe.createRecipe(Items.RED_TULIP, 1, Items.DYE.red(), 2);
		crusherRecipe.createRecipe(Items.REDSTONE, 1, Items.DYE.red(), 3);
		crusherRecipe.createRecipe(Items.REDSTONE_BLOCK, 1, Items.DYE.red(), 32);
		crusherRecipe.createRecipe(Items.INK_SAC, 1, Items.DYE.black(), 2);
		crusherRecipe.createRecipe(Items.WITHER_ROSE, 1, Items.DYE.black(), 2);
		crusherRecipe.createRecipe(Items.CHARCOAL, 1, Items.DYE.black(), 4);
		crusherRecipe.createRecipe(Items.COAL, 1, Items.DYE.black(), 3);
		crusherRecipe.createRecipe(Items.COAL_BLOCK, 1, Items.DYE.black(), 32);
		// Concrete powder from concrete.
		crusherRecipe.createRecipe(Items.CONCRETE.white(), 1, Items.CONCRETE_POWDER.white(), 1);
		crusherRecipe.createRecipe(Items.CONCRETE.orange(), 1, Items.CONCRETE_POWDER.orange(), 1);
		crusherRecipe.createRecipe(Items.CONCRETE.magenta(), 1, Items.CONCRETE_POWDER.magenta(), 1);
		crusherRecipe.createRecipe(Items.CONCRETE.lightBlue(), 1, Items.CONCRETE_POWDER.lightBlue(), 1);
		crusherRecipe.createRecipe(Items.CONCRETE.yellow(), 1, Items.CONCRETE_POWDER.yellow(), 1);
		crusherRecipe.createRecipe(Items.CONCRETE.lime(), 1, Items.CONCRETE_POWDER.lime(), 1);
		crusherRecipe.createRecipe(Items.CONCRETE.pink(), 1, Items.CONCRETE_POWDER.pink(), 1);
		crusherRecipe.createRecipe(Items.CONCRETE.gray(), 1, Items.CONCRETE_POWDER.gray(), 1);
		crusherRecipe.createRecipe(Items.CONCRETE.lightGray(), 1, Items.CONCRETE_POWDER.lightGray(), 1);
		crusherRecipe.createRecipe(Items.CONCRETE.cyan(), 1, Items.CONCRETE_POWDER.cyan(), 1);
		crusherRecipe.createRecipe(Items.CONCRETE.purple(), 1, Items.CONCRETE_POWDER.purple(), 1);
		crusherRecipe.createRecipe(Items.CONCRETE.blue(), 1, Items.CONCRETE_POWDER.blue(), 1);
		crusherRecipe.createRecipe(Items.CONCRETE.brown(), 1, Items.CONCRETE_POWDER.brown(), 1);
		crusherRecipe.createRecipe(Items.CONCRETE.green(), 1, Items.CONCRETE_POWDER.green(), 1);
		crusherRecipe.createRecipe(Items.CONCRETE.red(), 1, Items.CONCRETE_POWDER.red(), 1);
		crusherRecipe.createRecipe(Items.CONCRETE.black(), 1, Items.CONCRETE_POWDER.black(), 1);
		// Copper nuggets from copper things.
		crusherRecipe.createRecipe(Items.COPPER_LANTERN, 1, Items.COPPER_NUGGET, 8);
		// 11 nuggets => 2 nuggets + 1 ingot => 1 chain => 11 nuggets
		crusherRecipe.createRecipe(Items.COPPER_CHAIN, 1, Items.COPPER_NUGGET, 11);
		// 54 nuggets => 6 ingots => 16 copper bars => 64 nuggets
		// This allows the free generation of copper, for those who discover it.
		crusherRecipe.createRecipe(Items.COPPER_BARS, 1, Items.COPPER_NUGGET, 4);
		// Copper from things made of copper.
		crusherRecipe.createRecipe(Items.COPPER_ORE, 1, Items.RAW_COPPER, 1);
		crusherRecipe.createRecipe(Items.DEEPSLATE_COPPER_ORE, 1, Items.RAW_COPPER, 1);
		crusherRecipe.createRecipe(Items.COPPER_CHEST, 1, Items.RAW_COPPER, 8);
		crusherRecipe.createRecipe(Items.LIGHTNING_ROD, 1, Items.RAW_COPPER, 3);
		// 8 nuggets + 1 copper torch containint 1 nugget => 1 lantern => 1 ingot.
		crusherRecipe.createRecipe(Items.COPPER_LANTERN, 1, Items.RAW_COPPER, 1);
		crusherRecipe.createRecipe(ModItemTagProvider.COPPER_BLOCKS, 1, Items.RAW_COPPER, 9);
		// 27 ingots => 3 blocks => 1 bulb => 27 ingots.
		crusherRecipe.createRecipe(ModItemTagProvider.COPPER_BULBS, 1, Items.RAW_COPPER, 27);
		// Using the stonecutter allows the free and very efficient generation of copper.
		// For example:
		// 9 ingots => 1 block => 4 cut blocks => 36 ingots.
		crusherRecipe.createRecipe(ModItemTagProvider.CUT_COPPER_BLOCKS, 1, Items.RAW_COPPER, 9);
		crusherRecipe.createRecipe(ModItemTagProvider.CHISELED_COPPER_BLOCKS, 1, Items.RAW_COPPER, 9);
		crusherRecipe.createRecipe(ModItemTagProvider.COPPER_STAIRS, 2, Items.RAW_COPPER, 27);
		crusherRecipe.createRecipe(ModItemTagProvider.COPPER_SLABS, 2, Items.RAW_COPPER, 9);
		crusherRecipe.createRecipe(ModItemTagProvider.COPPER_DOORS, 1, Items.RAW_COPPER, 2);
		crusherRecipe.createRecipe(ModItemTagProvider.COPPER_TRAPDOORS, 1, Items.RAW_COPPER, 4);
		crusherRecipe.createRecipe(ModItemTagProvider.COPPER_GRATES, 1, Items.RAW_COPPER, 9);
		// Iron from things made of iron.
		crusherRecipe.createRecipe(Items.IRON_ORE, 1, Items.RAW_IRON, 4);
		crusherRecipe.createRecipe(Items.DEEPSLATE_IRON_ORE, 1, Items.RAW_IRON, 4);
		crusherRecipe.createRecipe(Items.BUCKET, 1, Items.RAW_IRON, 3);
		crusherRecipe.createRecipe(Items.WATER_BUCKET, 1, Items.RAW_IRON, 3);
		crusherRecipe.createRecipe(Items.LAVA_BUCKET, 1, Items.RAW_IRON, 3);
		crusherRecipe.createRecipe(Items.MILK_BUCKET, 1, Items.RAW_IRON, 3);
		crusherRecipe.createRecipe(Items.TROPICAL_FISH_BUCKET, 1, Items.RAW_IRON, 3);
		crusherRecipe.createRecipe(Items.SALMON_BUCKET, 1, Items.RAW_IRON, 3);
		crusherRecipe.createRecipe(Items.PUFFERFISH_BUCKET, 1, Items.RAW_IRON, 3);
		crusherRecipe.createRecipe(Items.COD_BUCKET, 1, Items.RAW_IRON, 3);
		crusherRecipe.createRecipe(Items.AXOLOTL_BUCKET, 1, Items.RAW_IRON, 3);
		crusherRecipe.createRecipe(Items.TADPOLE_BUCKET, 1, Items.RAW_IRON, 3);
		crusherRecipe.createRecipe(Items.POWDER_SNOW_BUCKET, 1, Items.RAW_IRON, 3);
		crusherRecipe.createRecipe(Items.SHEARS, 1, Items.RAW_IRON, 2);
		crusherRecipe.createRecipe(Items.IRON_DOOR, 1, Items.RAW_IRON, 2);
		crusherRecipe.createRecipe(Items.IRON_TRAPDOOR, 1, Items.RAW_IRON, 3);
		crusherRecipe.createRecipe(Items.CAULDRON, 1, Items.RAW_IRON, 7);
		crusherRecipe.createRecipe(Items.IRON_AXE, 1, Items.RAW_IRON, 3);
		crusherRecipe.createRecipe(Items.IRON_PICKAXE, 1, Items.RAW_IRON, 3);
		crusherRecipe.createRecipe(Items.IRON_HOE, 1, Items.RAW_IRON, 2);
		crusherRecipe.createRecipe(Items.IRON_SHOVEL, 1, Items.RAW_IRON, 1);
		crusherRecipe.createRecipe(Items.IRON_SWORD, 1, Items.RAW_IRON, 2);
		crusherRecipe.createRecipe(Items.IRON_BLOCK, 1, Items.RAW_IRON, 9);
		crusherRecipe.createRecipe(Items.RAW_IRON_BLOCK, 1, Items.RAW_IRON, 9);
		crusherRecipe.createRecipe(Items.IRON_HELMET, 1, Items.RAW_IRON, 5);
		crusherRecipe.createRecipe(Items.IRON_CHESTPLATE, 1, Items.RAW_IRON, 8);
		crusherRecipe.createRecipe(Items.IRON_LEGGINGS, 1, Items.RAW_IRON, 7);
		crusherRecipe.createRecipe(Items.IRON_BOOTS, 1, Items.RAW_IRON, 4);
		crusherRecipe.createRecipe(Items.CHAINMAIL_HELMET, 1, Items.RAW_IRON, 5);
		crusherRecipe.createRecipe(Items.CHAINMAIL_CHESTPLATE, 1, Items.RAW_IRON, 8);
		crusherRecipe.createRecipe(Items.CHAINMAIL_LEGGINGS, 1, Items.RAW_IRON, 7);
		crusherRecipe.createRecipe(Items.CHAINMAIL_BOOTS, 1, Items.RAW_IRON, 4);
		crusherRecipe.createRecipe(Items.IRON_HORSE_ARMOR, 1, Items.RAW_IRON, 7);
		crusherRecipe.createRecipe(Items.RAIL, 8, Items.RAW_IRON, 3);
		crusherRecipe.createRecipe(Items.ACTIVATOR_RAIL, 1, Items.RAW_IRON, 1);
		crusherRecipe.createRecipe(Items.DETECTOR_RAIL, 1, Items.RAW_IRON, 1);
		crusherRecipe.createRecipe(Items.ANVIL, 1, Items.RAW_IRON, 31);
		crusherRecipe.createRecipe(Items.CHIPPED_ANVIL, 1, Items.RAW_IRON, 20);
		crusherRecipe.createRecipe(Items.DAMAGED_ANVIL, 1, Items.RAW_IRON, 10);
		crusherRecipe.createRecipe(Items.MINECART, 1, Items.RAW_IRON, 5);
		crusherRecipe.createRecipe(Items.CHEST_MINECART, 1, Items.RAW_IRON, 5);
		crusherRecipe.createRecipe(Items.FURNACE_MINECART, 1, Items.RAW_IRON, 5);
		crusherRecipe.createRecipe(Items.HOPPER_MINECART, 1, Items.RAW_IRON, 10);
		crusherRecipe.createRecipe(Items.HOPPER, 1, Items.RAW_IRON, 5);
		crusherRecipe.createRecipe(Items.PISTON, 1, Items.RAW_IRON, 1);
		crusherRecipe.createRecipe(Items.STICKY_PISTON, 1, Items.RAW_IRON, 1);
		// Iron nuggets from iron things.
		// 11 nuggets => 2 nuggets + 1 ingot => 1 chain => 11 nuggets
		crusherRecipe.createRecipe(Items.IRON_CHAIN, 1, Items.IRON_NUGGET, 11);
		// 54 nuggets => 6 ingots => 16 iron bars => 64 nuggets
		// This allows free generation of iron, for those who discover it.
		crusherRecipe.createRecipe(Items.IRON_BARS, 1, Items.IRON_NUGGET, 4);
		crusherRecipe.createRecipe(Items.LANTERN, 1, Items.IRON_NUGGET, 8);
		// Gold from things made of gold.
		crusherRecipe.setParam(0.1F, 150);
		crusherRecipe.createRecipe(Items.GOLD_ORE, 1, Items.RAW_GOLD, 4);
		crusherRecipe.createRecipe(Items.DEEPSLATE_GOLD_ORE, 1, Items.RAW_GOLD, 4);
		crusherRecipe.createRecipe(Items.NETHER_GOLD_ORE, 1, Items.RAW_GOLD, 1);
		crusherRecipe.createRecipe(Items.GOLDEN_AXE, 1, Items.RAW_GOLD, 3);
		crusherRecipe.createRecipe(Items.GOLDEN_PICKAXE, 1, Items.RAW_GOLD, 3);
		crusherRecipe.createRecipe(Items.GOLDEN_HOE, 1, Items.RAW_GOLD, 2);
		crusherRecipe.createRecipe(Items.GOLDEN_SHOVEL, 1, Items.RAW_GOLD, 1);
		crusherRecipe.createRecipe(Items.GOLDEN_SWORD, 1, Items.RAW_GOLD, 2);
		crusherRecipe.createRecipe(Items.GOLD_BLOCK, 1, Items.RAW_GOLD, 9);
		crusherRecipe.createRecipe(Items.RAW_GOLD_BLOCK, 1, Items.RAW_GOLD, 9);
		crusherRecipe.createRecipe(Items.GOLDEN_HELMET, 1, Items.RAW_GOLD, 5);
		crusherRecipe.createRecipe(Items.GOLDEN_CHESTPLATE, 1, Items.RAW_GOLD, 8);
		crusherRecipe.createRecipe(Items.GOLDEN_LEGGINGS, 1, Items.RAW_GOLD, 7);
		crusherRecipe.createRecipe(Items.GOLDEN_BOOTS, 1, Items.RAW_GOLD, 4);
		crusherRecipe.createRecipe(Items.GOLDEN_HORSE_ARMOR, 1, Items.RAW_GOLD, 7);
		crusherRecipe.createRecipe(Items.POWERED_RAIL, 1, Items.RAW_GOLD, 1);
		// Diamond from things made of diamonds.
		crusherRecipe.setParam(0.1F, 200);
		crusherRecipe.createRecipe(Items.DIAMOND_ORE, 1, Items.DIAMOND, 4);
		crusherRecipe.createRecipe(Items.DEEPSLATE_DIAMOND_ORE, 1, Items.DIAMOND, 4);
		crusherRecipe.createRecipe(Items.DIAMOND_AXE, 1, Items.DIAMOND, 3);
		crusherRecipe.createRecipe(Items.DIAMOND_PICKAXE, 1, Items.DIAMOND, 3);
		crusherRecipe.createRecipe(Items.DIAMOND_HOE, 1, Items.DIAMOND, 2);
		crusherRecipe.createRecipe(Items.DIAMOND_SHOVEL, 1, Items.DIAMOND, 1);
		crusherRecipe.createRecipe(Items.DIAMOND_SWORD, 1, Items.DIAMOND, 2);
		crusherRecipe.createRecipe(Items.DIAMOND_BLOCK, 1, Items.DIAMOND, 9);
		crusherRecipe.createRecipe(Items.DIAMOND_HELMET, 1, Items.DIAMOND, 5);
		crusherRecipe.createRecipe(Items.DIAMOND_CHESTPLATE, 1, Items.DIAMOND, 8);
		crusherRecipe.createRecipe(Items.DIAMOND_LEGGINGS, 1, Items.DIAMOND, 7);
		crusherRecipe.createRecipe(Items.DIAMOND_BOOTS, 1, Items.DIAMOND, 4);
		crusherRecipe.createRecipe(Items.DIAMOND_HORSE_ARMOR, 1, Items.DIAMOND, 7);
		// Sand from sand fast. It creates experience.
		crusherRecipe.setParam(1.0F, 10);
		crusherRecipe.createRecipe(Items.SAND, 1, Items.SAND, 1);
		crusherRecipe.createRecipe(Items.RED_SAND, 1, Items.RED_SAND, 1);
		// Diamond from diamond, even faster. It creates even more experience.
		crusherRecipe.setParam(1.5F, 8);
		crusherRecipe.createRecipe(Items.DIAMOND, 1, Items.DIAMOND, 1);
	}

	/**
	 * Generate compressor recipes.
	 */
	private void generateCompressorRecipes() {
		// Defaults.
		compressorRecipe.setParam(0.1F, 100);
		// Sandstone from sand.
		compressorRecipe.createRecipe(Items.SAND, 4, Items.SANDSTONE, 1);
		compressorRecipe.createRecipe(Items.RED_SAND, 4, Items.RED_SANDSTONE, 1);
		// Cobblestone from gravel.
		compressorRecipe.createRecipe(Items.GRAVEL, 1, Items.COBBLESTONE, 1);
		// Blaze rod from blaze powder.
		compressorRecipe.createRecipe(Items.BLAZE_POWDER, 2, Items.BLAZE_ROD, 1);
		// Blocks.
		compressorRecipe.createRecipe(Items.AMETHYST_SHARD, 4, Items.AMETHYST_BLOCK, 1);
		compressorRecipe.createRecipe(Items.BAMBOO, 9, Items.BAMBOO_BLOCK, 1);
		compressorRecipe.createRecipe(Items.QUARTZ, 4, Items.QUARTZ_BLOCK, 1);
		compressorRecipe.createRecipe(Items.COAL, 9, Items.COAL_BLOCK, 1);
		compressorRecipe.createRecipe(Items.CHARCOAL, 9, Items.COAL_BLOCK, 1);
		compressorRecipe.createRecipe(Items.RAW_COPPER, 9, Items.RAW_COPPER_BLOCK, 1);
		compressorRecipe.createRecipe(Items.COPPER_INGOT, 9, Items.COPPER_BLOCK.weathering().unaffected(), 1);
		compressorRecipe.createRecipe(Items.RAW_IRON, 9, Items.RAW_IRON_BLOCK, 1);
		compressorRecipe.createRecipe(Items.IRON_INGOT, 9, Items.IRON_BLOCK, 1);
		compressorRecipe.createRecipe(Items.RAW_GOLD, 9, Items.RAW_GOLD_BLOCK, 1);
		compressorRecipe.createRecipe(Items.GOLD_INGOT, 9, Items.GOLD_BLOCK, 1);
		compressorRecipe.createRecipe(Items.DIAMOND, 9, Items.DIAMOND_BLOCK, 1);
		compressorRecipe.createRecipe(Items.DRIED_KELP, 9, Items.DRIED_KELP_BLOCK, 1);
		compressorRecipe.createRecipe(Items.KELP, 9, Items.DRIED_KELP_BLOCK, 1);
		compressorRecipe.createRecipe(Items.POINTED_DRIPSTONE, 4, Items.DRIPSTONE_BLOCK, 1);
		compressorRecipe.createRecipe(Items.EMERALD, 9, Items.EMERALD_BLOCK, 1);
		compressorRecipe.createRecipe(Items.WHEAT, 9, Items.HAY_BLOCK, 1);
		compressorRecipe.createRecipe(Items.HONEYCOMB, 4, Items.HONEYCOMB_BLOCK, 1);
		compressorRecipe.createRecipe(Items.LAPIS_LAZULI, 9, Items.LAPIS_BLOCK, 1);
		compressorRecipe.createRecipe(Items.MAGMA_CREAM, 4, Items.MAGMA_BLOCK, 1);
		compressorRecipe.createRecipe(Items.POPPED_CHORUS_FRUIT, 4, Items.PURPUR_BLOCK, 1);
		compressorRecipe.createRecipe(Items.RESIN_CLUMP, 9, Items.RESIN_BLOCK, 1);
		compressorRecipe.createRecipe(Items.SLIME_BALL, 4, Items.SLIME_BLOCK, 1);
		compressorRecipe.createRecipe(Items.SNOWBALL, 4, Items.SNOW_BLOCK, 1);
		compressorRecipe.createRecipe(Items.REDSTONE, 9, Items.REDSTONE_BLOCK, 1);
		compressorRecipe.createRecipe(Items.ICE, 9, Items.PACKED_ICE, 1);
		// Special and irregular blocks.
		compressorRecipe.createRecipe(Items.BONE_MEAL, 8, Items.BONE_BLOCK, 1);
		compressorRecipe.createRecipe(Items.SUGAR, 8, Items.HONEY_BLOCK, 1);
		compressorRecipe.createRecipe(Items.SNOW_BLOCK, 2, Items.ICE, 1);
		// Blow up when compressing a diamond block or something explosive.
		compressorRecipe.createRecipe(Items.GUNPOWDER, 1, Items.TNT, 1);
		compressorRecipe.createRecipe(Items.TNT, 1, Items.TNT, 1);
		compressorRecipe.createRecipe(Items.TNT_MINECART, 1, Items.TNT, 1);
		compressorRecipe.createRecipe(Items.DIAMOND_BLOCK, 1, Items.TNT, 1);
		// Sponge.
		compressorRecipe.createRecipe(Items.WET_SPONGE, 1, Items.SPONGE, 1);
		// Copper from things made of copper.
		compressorRecipe.setParam(0.1F, 150);
		// Copper from things made of copper.
		compressorRecipe.createRecipe(Items.COPPER_CHEST, 1, Items.COPPER_INGOT, 8);
		compressorRecipe.createRecipe(Items.LIGHTNING_ROD, 1, Items.COPPER_INGOT, 3);
		compressorRecipe.createRecipe(Items.COPPER_BULB, 1, Items.COPPER_BLOCK.weathering().unaffected(), 3);
		// 8 nuggets + 1 copper torch containint 1 nugget => 1 lantern => 1 ingot.
		compressorRecipe.createRecipe(Items.COPPER_LANTERN, 1, Items.COPPER_INGOT, 1);
		// Using the stonecutter allows the free and very efficient generation of copper.
		// For example:
		// 9 ingots => 1 block => 4 cut blocks => 36 ingots.
		compressorRecipe.createRecipe(ModItemTagProvider.CUT_COPPER_BLOCKS, 1, Items.COPPER_BLOCK.weathering().unaffected(), 1);
		compressorRecipe.createRecipe(ModItemTagProvider.CHISELED_COPPER_BLOCKS, 1, Items.COPPER_BLOCK.weathering().unaffected(), 1);
		compressorRecipe.createRecipe(ModItemTagProvider.COPPER_STAIRS, 2, Items.COPPER_BLOCK.weathering().unaffected(), 3);
		compressorRecipe.createRecipe(ModItemTagProvider.COPPER_SLABS, 2, Items.COPPER_BLOCK.weathering().unaffected(), 1);
		compressorRecipe.createRecipe(ModItemTagProvider.COPPER_DOORS, 1, Items.COPPER_INGOT, 2);
		compressorRecipe.createRecipe(ModItemTagProvider.COPPER_TRAPDOORS, 1, Items.COPPER_INGOT, 4);
		compressorRecipe.createRecipe(ModItemTagProvider.COPPER_GRATES, 1, Items.COPPER_INGOT, 9);
		// Iron from things made of iron.
		compressorRecipe.createRecipe(ModItemTagProvider.BUCKETS, 1, Items.IRON_INGOT, 3);
		compressorRecipe.createRecipe(Items.SHEARS, 1, Items.IRON_INGOT, 2);
		compressorRecipe.createRecipe(Items.IRON_DOOR, 1, Items.IRON_INGOT, 2);
		compressorRecipe.createRecipe(Items.IRON_TRAPDOOR, 1, Items.IRON_INGOT, 4);
		compressorRecipe.createRecipe(Items.CAULDRON, 1, Items.IRON_INGOT, 7);
		compressorRecipe.createRecipe(Items.IRON_AXE, 1, Items.IRON_INGOT, 3);
		compressorRecipe.createRecipe(Items.IRON_PICKAXE, 1, Items.IRON_INGOT, 3);
		compressorRecipe.createRecipe(Items.IRON_HOE, 1, Items.IRON_INGOT, 2);
		compressorRecipe.createRecipe(Items.IRON_SHOVEL, 1, Items.IRON_INGOT, 1);
		compressorRecipe.createRecipe(Items.IRON_SWORD, 1, Items.IRON_INGOT, 2);
		compressorRecipe.createRecipe(Items.IRON_BLOCK, 1, Items.IRON_INGOT, 9);
		compressorRecipe.createRecipe(Items.IRON_HELMET, 1, Items.IRON_INGOT, 5);
		compressorRecipe.createRecipe(Items.IRON_CHESTPLATE, 1, Items.IRON_INGOT, 8);
		compressorRecipe.createRecipe(Items.IRON_LEGGINGS, 1, Items.IRON_INGOT, 7);
		compressorRecipe.createRecipe(Items.IRON_BOOTS, 1, Items.IRON_INGOT, 4);
		compressorRecipe.createRecipe(Items.CHAINMAIL_HELMET, 1, Items.IRON_INGOT, 5);
		compressorRecipe.createRecipe(Items.CHAINMAIL_CHESTPLATE, 1, Items.IRON_INGOT, 8);
		compressorRecipe.createRecipe(Items.CHAINMAIL_LEGGINGS, 1, Items.IRON_INGOT, 7);
		compressorRecipe.createRecipe(Items.CHAINMAIL_BOOTS, 1, Items.IRON_INGOT, 4);
		compressorRecipe.createRecipe(Items.IRON_HORSE_ARMOR, 1, Items.IRON_INGOT, 7);
		compressorRecipe.createRecipe(Items.RAIL, 8, Items.IRON_INGOT, 3);
		compressorRecipe.createRecipe(Items.ACTIVATOR_RAIL, 1, Items.IRON_INGOT, 1);
		compressorRecipe.createRecipe(Items.DETECTOR_RAIL, 1, Items.IRON_INGOT, 1);
		compressorRecipe.createRecipe(Items.ANVIL, 1, Items.IRON_INGOT, 31);
		compressorRecipe.createRecipe(Items.CHIPPED_ANVIL, 1, Items.IRON_INGOT, 20);
		compressorRecipe.createRecipe(Items.DAMAGED_ANVIL, 1, Items.IRON_INGOT, 10);
		compressorRecipe.createRecipe(Items.MINECART, 1, Items.IRON_INGOT, 5);
		compressorRecipe.createRecipe(Items.CHEST_MINECART, 1, Items.IRON_INGOT, 5);
		compressorRecipe.createRecipe(Items.FURNACE_MINECART, 1, Items.IRON_INGOT, 5);
		compressorRecipe.createRecipe(Items.HOPPER_MINECART, 1, Items.IRON_INGOT, 10);
		compressorRecipe.createRecipe(Items.HOPPER, 1, Items.IRON_INGOT, 5);
		compressorRecipe.createRecipe(Items.PISTON, 1, Items.IRON_INGOT, 1);
		compressorRecipe.createRecipe(Items.STICKY_PISTON, 1, Items.IRON_INGOT, 1);
		// Gold from things made of gold.
		compressorRecipe.setParam(0.1F, 200);
		compressorRecipe.createRecipe(Items.GOLDEN_AXE, 1, Items.GOLD_INGOT, 3);
		compressorRecipe.createRecipe(Items.GOLDEN_PICKAXE, 1, Items.GOLD_INGOT, 3);
		compressorRecipe.createRecipe(Items.GOLDEN_HOE, 1, Items.GOLD_INGOT, 2);
		compressorRecipe.createRecipe(Items.GOLDEN_SHOVEL, 1, Items.GOLD_INGOT, 1);
		compressorRecipe.createRecipe(Items.GOLDEN_SWORD, 1, Items.GOLD_INGOT, 2);
		compressorRecipe.createRecipe(Items.GOLD_BLOCK, 1, Items.GOLD_INGOT, 9);
		compressorRecipe.createRecipe(Items.GOLDEN_HELMET, 1, Items.GOLD_INGOT, 5);
		compressorRecipe.createRecipe(Items.GOLDEN_CHESTPLATE, 1, Items.GOLD_INGOT, 8);
		compressorRecipe.createRecipe(Items.GOLDEN_LEGGINGS, 1, Items.GOLD_INGOT, 7);
		compressorRecipe.createRecipe(Items.GOLDEN_BOOTS, 1, Items.GOLD_INGOT, 4);
		compressorRecipe.createRecipe(Items.GOLDEN_HORSE_ARMOR, 1, Items.GOLD_INGOT, 7);
		compressorRecipe.createRecipe(Items.POWERED_RAIL, 1, Items.GOLD_INGOT, 1);
		// Diamond from coal blocks, but very slowly.
		// 144 coals or charcoals => 16 coal blocks => 1 diamond.
		compressorRecipe.setParam(1.0F, 1000);
		compressorRecipe.createRecipe(Items.COAL_BLOCK, 16, Items.DIAMOND, 1);
	}
}
