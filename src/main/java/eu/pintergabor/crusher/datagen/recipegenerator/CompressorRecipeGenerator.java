package eu.pintergabor.crusher.datagen.recipegenerator;

import eu.pintergabor.crusher.datagen.ModItemTagProvider;
import eu.pintergabor.crusher.datagen.recipebase.ProcessingRecipeGenerator;
import org.jspecify.annotations.NonNull;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;


public final class CompressorRecipeGenerator {

	private CompressorRecipeGenerator() {
		// Static class.
	}

	/**
	 * Generate compressor recipes.
	 */
	public static void generateRecipes(final @NonNull ProcessingRecipeGenerator generator) {
		// Defaults.
		generator.setParam(0.1F, 100);
		// Sandstone from sand.
		generator.createRecipe(Items.SAND, 4, Items.SANDSTONE, 1);
		generator.createRecipe(Items.RED_SAND, 4, Items.RED_SANDSTONE, 1);
		// Cobblestone from gravel.
		generator.createRecipe(Items.GRAVEL, 1, Items.COBBLESTONE, 1);
		// Blaze rod from blaze powder.
		generator.createRecipe(Items.BLAZE_POWDER, 2, Items.BLAZE_ROD, 1);
		// Blocks.
		generator.createRecipe(Items.AMETHYST_SHARD, 4, Items.AMETHYST_BLOCK, 1);
		generator.createRecipe(Items.BAMBOO, 9, Items.BAMBOO_BLOCK, 1);
		generator.createRecipe(Items.QUARTZ, 4, Items.QUARTZ_BLOCK, 1);
		generator.createRecipe(Items.COAL, 9, Items.COAL_BLOCK, 1);
		generator.createRecipe(Items.CHARCOAL, 9, Items.COAL_BLOCK, 1);
		generator.createRecipe(Items.RAW_COPPER, 9, Items.RAW_COPPER_BLOCK, 1);
		generator.createRecipe(Items.COPPER_INGOT, 9, Items.COPPER_BLOCK.weathering().unaffected(), 1);
		generator.createRecipe(Items.RAW_IRON, 9, Items.RAW_IRON_BLOCK, 1);
		generator.createRecipe(Items.IRON_INGOT, 9, Items.IRON_BLOCK, 1);
		generator.createRecipe(Items.RAW_GOLD, 9, Items.RAW_GOLD_BLOCK, 1);
		generator.createRecipe(Items.GOLD_INGOT, 9, Items.GOLD_BLOCK, 1);
		generator.createRecipe(Items.DIAMOND, 9, Items.DIAMOND_BLOCK, 1);
		generator.createRecipe(Items.DRIED_KELP, 9, Items.DRIED_KELP_BLOCK, 1);
		generator.createRecipe(Items.KELP, 9, Items.DRIED_KELP_BLOCK, 1);
		generator.createRecipe(Items.POINTED_DRIPSTONE, 4, Items.DRIPSTONE_BLOCK, 1);
		generator.createRecipe(Items.EMERALD, 9, Items.EMERALD_BLOCK, 1);
		generator.createRecipe(Items.WHEAT, 9, Items.HAY_BLOCK, 1);
		generator.createRecipe(Items.HONEYCOMB, 4, Items.HONEYCOMB_BLOCK, 1);
		generator.createRecipe(Items.LAPIS_LAZULI, 9, Items.LAPIS_BLOCK, 1);
		generator.createRecipe(Items.MAGMA_CREAM, 4, Items.MAGMA_BLOCK, 1);
		generator.createRecipe(Items.POPPED_CHORUS_FRUIT, 4, Items.PURPUR_BLOCK, 1);
		generator.createRecipe(Items.RESIN_CLUMP, 9, Items.RESIN_BLOCK, 1);
		generator.createRecipe(Items.SLIME_BALL, 4, Items.SLIME_BLOCK, 1);
		generator.createRecipe(Items.SNOWBALL, 4, Items.SNOW_BLOCK, 1);
		generator.createRecipe(Items.REDSTONE, 9, Items.REDSTONE_BLOCK, 1);
		generator.createRecipe(Items.ICE, 9, Items.PACKED_ICE, 1);
		// Special and irregular blocks.
		generator.createRecipe(Items.BONE_MEAL, 8, Items.BONE_BLOCK, 1);
		generator.createRecipe(Items.SUGAR, 8, Items.HONEY_BLOCK, 1);
		generator.createRecipe(Items.SNOW_BLOCK, 2, Items.ICE, 1);
		// Coal from logs and sticks.
		generator.createRecipe(ItemTags.LOGS, 1, Items.CHARCOAL, 1);
		generator.createRecipe(Items.STICK, 8, Items.CHARCOAL, 1);
		generator.createRecipe(Items.HAY_BLOCK, 2, Items.CHARCOAL, 1);
		// Blow up when compressing a diamond block or something explosive.
		generator.createRecipe(Items.GUNPOWDER, 1, Items.TNT, 1);
		generator.createRecipe(Items.TNT, 1, Items.TNT, 1);
		generator.createRecipe(Items.TNT_MINECART, 1, Items.TNT, 1);
		generator.createRecipe(Items.DIAMOND_BLOCK, 1, Items.TNT, 1);
		// Sponge.
		generator.createRecipe(Items.WET_SPONGE, 1, Items.SPONGE, 1);
		// Copper from things made of copper.
		generator.setParam(0.1F, 150);
		// Copper from things made of copper.
		generator.createRecipe(Items.COPPER_CHEST, 1, Items.COPPER_INGOT, 8);
		generator.createRecipe(Items.LIGHTNING_ROD, 1, Items.COPPER_INGOT, 3);
		generator.createRecipe(Items.COPPER_BULB, 1, Items.COPPER_BLOCK.weathering().unaffected(), 3);
		// 8 nuggets + 1 copper torch containint 1 nugget => 1 lantern => 1 ingot.
		generator.createRecipe(Items.COPPER_LANTERN, 1, Items.COPPER_INGOT, 1);
		// Using the stonecutter allows the free and very efficient generation of copper.
		// For example:
		// 9 ingots => 1 block => 4 cut blocks => 36 ingots.
		generator.createRecipe(ModItemTagProvider.CUT_COPPER_BLOCKS, 1, Items.COPPER_BLOCK.weathering().unaffected(), 1);
		generator.createRecipe(ModItemTagProvider.CHISELED_COPPER_BLOCKS, 1, Items.COPPER_BLOCK.weathering().unaffected(), 1);
		generator.createRecipe(ModItemTagProvider.COPPER_STAIRS, 2, Items.COPPER_BLOCK.weathering().unaffected(), 3);
		generator.createRecipe(ModItemTagProvider.COPPER_SLABS, 2, Items.COPPER_BLOCK.weathering().unaffected(), 1);
		generator.createRecipe(ModItemTagProvider.COPPER_DOORS, 1, Items.COPPER_INGOT, 2);
		generator.createRecipe(ModItemTagProvider.COPPER_TRAPDOORS, 1, Items.COPPER_INGOT, 4);
		generator.createRecipe(ModItemTagProvider.COPPER_GRATES, 1, Items.COPPER_INGOT, 9);
		// Iron from things made of iron.
		generator.createRecipe(ModItemTagProvider.BUCKETS, 1, Items.IRON_INGOT, 3);
		generator.createRecipe(Items.SHEARS, 1, Items.IRON_INGOT, 2);
		generator.createRecipe(Items.IRON_DOOR, 1, Items.IRON_INGOT, 2);
		generator.createRecipe(Items.IRON_TRAPDOOR, 1, Items.IRON_INGOT, 4);
		generator.createRecipe(Items.CAULDRON, 1, Items.IRON_INGOT, 7);
		generator.createRecipe(Items.IRON_AXE, 1, Items.IRON_INGOT, 3);
		generator.createRecipe(Items.IRON_PICKAXE, 1, Items.IRON_INGOT, 3);
		generator.createRecipe(Items.IRON_HOE, 1, Items.IRON_INGOT, 2);
		generator.createRecipe(Items.IRON_SHOVEL, 1, Items.IRON_INGOT, 1);
		generator.createRecipe(Items.IRON_SWORD, 1, Items.IRON_INGOT, 2);
		generator.createRecipe(Items.IRON_BLOCK, 1, Items.IRON_INGOT, 9);
		generator.createRecipe(Items.IRON_HELMET, 1, Items.IRON_INGOT, 5);
		generator.createRecipe(Items.IRON_CHESTPLATE, 1, Items.IRON_INGOT, 8);
		generator.createRecipe(Items.IRON_LEGGINGS, 1, Items.IRON_INGOT, 7);
		generator.createRecipe(Items.IRON_BOOTS, 1, Items.IRON_INGOT, 4);
		generator.createRecipe(Items.CHAINMAIL_HELMET, 1, Items.IRON_INGOT, 5);
		generator.createRecipe(Items.CHAINMAIL_CHESTPLATE, 1, Items.IRON_INGOT, 8);
		generator.createRecipe(Items.CHAINMAIL_LEGGINGS, 1, Items.IRON_INGOT, 7);
		generator.createRecipe(Items.CHAINMAIL_BOOTS, 1, Items.IRON_INGOT, 4);
		generator.createRecipe(Items.IRON_HORSE_ARMOR, 1, Items.IRON_INGOT, 7);
		generator.createRecipe(Items.RAIL, 8, Items.IRON_INGOT, 3);
		generator.createRecipe(Items.ACTIVATOR_RAIL, 1, Items.IRON_INGOT, 1);
		generator.createRecipe(Items.DETECTOR_RAIL, 1, Items.IRON_INGOT, 1);
		generator.createRecipe(Items.ANVIL, 1, Items.IRON_INGOT, 31);
		generator.createRecipe(Items.CHIPPED_ANVIL, 1, Items.IRON_INGOT, 20);
		generator.createRecipe(Items.DAMAGED_ANVIL, 1, Items.IRON_INGOT, 10);
		generator.createRecipe(Items.MINECART, 1, Items.IRON_INGOT, 5);
		generator.createRecipe(Items.CHEST_MINECART, 1, Items.IRON_INGOT, 5);
		generator.createRecipe(Items.FURNACE_MINECART, 1, Items.IRON_INGOT, 5);
		generator.createRecipe(Items.HOPPER_MINECART, 1, Items.IRON_INGOT, 10);
		generator.createRecipe(Items.HOPPER, 1, Items.IRON_INGOT, 5);
		generator.createRecipe(Items.PISTON, 1, Items.IRON_INGOT, 1);
		generator.createRecipe(Items.STICKY_PISTON, 1, Items.IRON_INGOT, 1);
		// Gold from things made of gold.
		generator.setParam(0.1F, 200);
		generator.createRecipe(Items.GOLDEN_AXE, 1, Items.GOLD_INGOT, 3);
		generator.createRecipe(Items.GOLDEN_PICKAXE, 1, Items.GOLD_INGOT, 3);
		generator.createRecipe(Items.GOLDEN_HOE, 1, Items.GOLD_INGOT, 2);
		generator.createRecipe(Items.GOLDEN_SHOVEL, 1, Items.GOLD_INGOT, 1);
		generator.createRecipe(Items.GOLDEN_SWORD, 1, Items.GOLD_INGOT, 2);
		generator.createRecipe(Items.GOLD_BLOCK, 1, Items.GOLD_INGOT, 9);
		generator.createRecipe(Items.GOLDEN_HELMET, 1, Items.GOLD_INGOT, 5);
		generator.createRecipe(Items.GOLDEN_CHESTPLATE, 1, Items.GOLD_INGOT, 8);
		generator.createRecipe(Items.GOLDEN_LEGGINGS, 1, Items.GOLD_INGOT, 7);
		generator.createRecipe(Items.GOLDEN_BOOTS, 1, Items.GOLD_INGOT, 4);
		generator.createRecipe(Items.GOLDEN_HORSE_ARMOR, 1, Items.GOLD_INGOT, 7);
		generator.createRecipe(Items.POWERED_RAIL, 1, Items.GOLD_INGOT, 1);
		// Diamond from coal blocks, but very slowly.
		// 72 coals or charcoals => 8 coal blocks => 1 diamond.
		generator.setParam(1.0F, 1000);
		generator.createRecipe(Items.COAL_BLOCK, 8, Items.DIAMOND, 1);
	}
}
