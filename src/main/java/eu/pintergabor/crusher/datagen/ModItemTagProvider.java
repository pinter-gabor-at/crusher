package eu.pintergabor.crusher.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.crusher.Global;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;


public class ModItemTagProvider extends ItemTagsProvider {

	/**
	 * Items crushed to gravel.
	 */
	public static final TagKey<Item> GRAVEL_SOURCES = createModItemTag("gravel_sources");

	/**
	 * Items crushed to sand.
	 */
	public static final TagKey<Item> SAND_SOURCES = createModItemTag("sand_sources");
	/**
	 * Items crushed to red sand.
	 */
	public static final TagKey<Item> RED_SAND_SOURCES = createModItemTag("red_sand_sources");
	/**
	 * Similar to {@link Tags.Items#FOODS_FRUIT}, excluding the golden variants.
	 */
	public static final TagKey<Item> FOODS_NORMAL_FRUIT = createCItemTag("foods/normal_fruit");
	/**
	 * Similar to {@link Tags.Items#FOODS_VEGETABLE}, excluding the golden variants.
	 */
	public static final TagKey<Item> FOODS_NORMAL_VEGETABLE = createCItemTag("foods/normal_vegetable");
	/**
	 * Copper blocks.
	 */
	public static final TagKey<Item> COPPER_BLOCKS = createCItemTag("storage_blocks/copper");
	/**
	 * Cut copper blocks.
	 */
	public static final TagKey<Item> CUT_COPPER_BLOCKS = createCItemTag("cut_blocks/copper");
	/**
	 * Chiseled copper blocks.
	 */
	public static final TagKey<Item> CHISELED_COPPER_BLOCKS = createCItemTag("chiseled_blocks/copper");
	/**
	 * Copper doors.
	 */
	public static final TagKey<Item> COPPER_DOORS = createCItemTag("doors/copper");
	/**
	 * Copper trapdoors.
	 */
	public static final TagKey<Item> COPPER_TRAPDOORS = createCItemTag("trapdoors/copper");
	/**
	 * Copper grates.
	 */
	public static final TagKey<Item> COPPER_GRATES = createCItemTag("grates/copper");
	/**
	 * Copper bulbs.
	 */
	public static final TagKey<Item> COPPER_BULBS = createCItemTag("bulbs/copper");
	/**
	 * Copper slabs.
	 */
	public static final TagKey<Item> COPPER_SLABS = createCItemTag("slabs/copper");
	/**
	 * Copper stairs.
	 */
	public static final TagKey<Item> COPPER_STAIRS = createCItemTag("stairs/copper");
	/**
	 * Buckets.
	 */
	public static final TagKey<Item> BUCKETS = createCItemTag("buckets");

	public ModItemTagProvider(
		PackOutput output,
		CompletableFuture<HolderLookup.Provider> lookupProvider,
		CompletableFuture<TagsProvider.TagLookup<Block>> blockTagProvider) {
		super(output, lookupProvider, blockTagProvider, Global.MODID);
	}

	/**
	 * Create a mod item tag.
	 */
	private static @NotNull TagKey<Item> createModItemTag(String path) {
		return TagKey.create(
			Registries.ITEM, Global.modId(path));
	}

	/**
	 * Create a conventional item tag.
	 */
	private static @NotNull TagKey<Item> createCItemTag(String path) {
		return TagKey.create(
			Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", path));
	}

	/**
	 * Create all tags.
	 */
	@Override
	protected void addTags(@NotNull HolderLookup.Provider wrapperLookup) {
		tag(GRAVEL_SOURCES)
			.addTag(Tags.Items.STONES)
			.addTag(Tags.Items.COBBLESTONES)
			.add(
				Items.BASALT, Items.BLACKSTONE,
				Items.COBBLESTONE_SLAB, Items.COBBLESTONE_STAIRS, Items.COBBLESTONE_WALL,
				Items.MOSSY_COBBLESTONE_SLAB, Items.MOSSY_COBBLESTONE_STAIRS, Items.MOSSY_COBBLESTONE_WALL,
				Items.COBBLED_DEEPSLATE_SLAB, Items.COBBLED_DEEPSLATE_STAIRS, Items.COBBLED_DEEPSLATE_WALL,
				Items.POLISHED_ANDESITE, Items.POLISHED_BASALT, Items.POLISHED_DIORITE,
				Items.POLISHED_GRANITE, Items.POLISHED_TUFF, Items.POLISHED_DEEPSLATE,
				Items.POLISHED_BLACKSTONE,
				Items.SMOOTH_STONE, Items.SMOOTH_BASALT, Items.SMOOTH_QUARTZ,
				Items.CHISELED_TUFF, Items.CHISELED_TUFF_BRICKS, Items.CHISELED_DEEPSLATE,
				Items.STONE_BRICKS, Items.CHISELED_STONE_BRICKS,
				Items.STONE_BRICK_SLAB, Items.STONE_BRICK_STAIRS, Items.STONE_BRICK_WALL,
				Items.MOSSY_STONE_BRICK_SLAB, Items.MOSSY_STONE_BRICK_STAIRS, Items.MOSSY_STONE_BRICK_WALL,
				Items.ANDESITE_SLAB, Items.ANDESITE_STAIRS, Items.ANDESITE_WALL,
				Items.DIORITE_SLAB, Items.DIORITE_STAIRS, Items.DIORITE_WALL,
				Items.GRANITE_SLAB, Items.GRANITE_STAIRS, Items.GRANITE_WALL,
				Items.TUFF_SLAB, Items.TUFF_STAIRS, Items.TUFF_WALL,
				Items.POLISHED_ANDESITE_SLAB, Items.POLISHED_ANDESITE_STAIRS,
				Items.POLISHED_DIORITE_SLAB, Items.POLISHED_DIORITE_STAIRS,
				Items.POLISHED_GRANITE_SLAB, Items.POLISHED_GRANITE_STAIRS,
				Items.POLISHED_TUFF_SLAB, Items.POLISHED_TUFF_STAIRS, Items.POLISHED_TUFF_WALL,
				Items.CHISELED_TUFF_BRICKS, Items.CHISELED_DEEPSLATE, Items.CRACKED_DEEPSLATE_BRICKS,
				Items.BLACKSTONE_SLAB, Items.BLACKSTONE_STAIRS, Items.BLACKSTONE_WALL,
				Items.CRACKED_DEEPSLATE_BRICKS, Items.CRACKED_DEEPSLATE_TILES, Items.CRACKED_NETHER_BRICKS,
				Items.CRACKED_POLISHED_BLACKSTONE_BRICKS, Items.MOSSY_STONE_BRICKS,
				Items.NETHER_BRICK, Items.NETHER_BRICK_FENCE,
				Items.NETHER_BRICK_SLAB, Items.NETHER_BRICK_STAIRS, Items.NETHER_BRICK_WALL,
				Items.NETHER_BRICKS, Items.CRACKED_NETHER_BRICKS, Items.CHISELED_NETHER_BRICKS);
		tag(SAND_SOURCES)
			.add(
				Items.GRAVEL,
				Items.QUARTZ, Items.QUARTZ_BLOCK, Items.QUARTZ_BRICKS, Items.QUARTZ_PILLAR,
				Items.QUARTZ_SLAB, Items.QUARTZ_STAIRS,
				Items.SMOOTH_QUARTZ, Items.SMOOTH_QUARTZ_SLAB, Items.SMOOTH_QUARTZ_STAIRS,
				Items.CHISELED_QUARTZ_BLOCK, Items.NETHER_QUARTZ_ORE,
				Items.GLASS_BOTTLE,
				Items.TERRACOTTA, Items.WHITE_TERRACOTTA, Items.WHITE_GLAZED_TERRACOTTA);
		tag(RED_SAND_SOURCES)
			.add(
				Items.RED_TERRACOTTA, Items.RED_GLAZED_TERRACOTTA,
				Items.RED_NETHER_BRICK_SLAB, Items.RED_NETHER_BRICK_STAIRS, Items.RED_NETHER_BRICK_WALL,
				Items.RED_NETHER_BRICKS, Items.RED_STAINED_GLASS, Items.RED_STAINED_GLASS_PANE);
		tag(FOODS_NORMAL_FRUIT)
			.add(
				Items.APPLE, Items.CHORUS_FRUIT, Items.MELON_SLICE);
		tag(FOODS_NORMAL_VEGETABLE)
			.add(
				Items.CARROT, Items.POTATO, Items.BEETROOT);
		tag(COPPER_BLOCKS)
			.add(
				Items.COPPER_BLOCK, Items.WAXED_COPPER_BLOCK,
				Items.EXPOSED_COPPER, Items.WAXED_EXPOSED_COPPER,
				Items.WEATHERED_COPPER, Items.WAXED_WEATHERED_COPPER,
				Items.OXIDIZED_COPPER, Items.WAXED_OXIDIZED_COPPER);
		tag(CUT_COPPER_BLOCKS)
			.add(
				Items.CUT_COPPER, Items.WAXED_CUT_COPPER,
				Items.EXPOSED_CUT_COPPER, Items.WAXED_EXPOSED_CUT_COPPER,
				Items.WEATHERED_CUT_COPPER, Items.WAXED_WEATHERED_CUT_COPPER,
				Items.OXIDIZED_CUT_COPPER, Items.WAXED_OXIDIZED_CUT_COPPER);
		tag(CHISELED_COPPER_BLOCKS)
			.add(
				Items.CHISELED_COPPER, Items.WAXED_CHISELED_COPPER,
				Items.EXPOSED_CHISELED_COPPER, Items.WAXED_EXPOSED_CHISELED_COPPER,
				Items.WEATHERED_CHISELED_COPPER, Items.WAXED_WEATHERED_CHISELED_COPPER,
				Items.OXIDIZED_CHISELED_COPPER, Items.WAXED_OXIDIZED_CHISELED_COPPER);
		tag(COPPER_DOORS)
			.add(
				Items.COPPER_DOOR, Items.WAXED_COPPER_DOOR,
				Items.EXPOSED_COPPER_DOOR, Items.WAXED_EXPOSED_COPPER_DOOR,
				Items.WEATHERED_COPPER_DOOR, Items.WAXED_WEATHERED_COPPER_DOOR,
				Items.OXIDIZED_COPPER_DOOR, Items.WAXED_OXIDIZED_COPPER_DOOR);
		tag(COPPER_TRAPDOORS)
			.add(
				Items.COPPER_TRAPDOOR, Items.WAXED_COPPER_TRAPDOOR,
				Items.EXPOSED_COPPER_TRAPDOOR, Items.WAXED_EXPOSED_COPPER_TRAPDOOR,
				Items.WEATHERED_COPPER_TRAPDOOR, Items.WAXED_WEATHERED_COPPER_TRAPDOOR,
				Items.OXIDIZED_COPPER_TRAPDOOR, Items.WAXED_OXIDIZED_COPPER_TRAPDOOR);
		tag(COPPER_GRATES)
			.add(
				Items.COPPER_GRATE, Items.WAXED_COPPER_GRATE,
				Items.EXPOSED_COPPER_GRATE, Items.WAXED_EXPOSED_COPPER_GRATE,
				Items.WEATHERED_COPPER_GRATE, Items.WAXED_WEATHERED_COPPER_GRATE,
				Items.OXIDIZED_COPPER_GRATE, Items.WAXED_OXIDIZED_COPPER_GRATE);
		tag(COPPER_BULBS)
			.add(
				Items.COPPER_BULB, Items.WAXED_COPPER_BULB,
				Items.EXPOSED_COPPER_BULB, Items.WAXED_EXPOSED_COPPER_BULB,
				Items.WEATHERED_COPPER_BULB, Items.WAXED_WEATHERED_COPPER_BULB,
				Items.OXIDIZED_COPPER_BULB, Items.WAXED_OXIDIZED_COPPER_BULB);
		tag(COPPER_SLABS)
			.add(
				Items.CUT_COPPER_SLAB, Items.WAXED_CUT_COPPER_SLAB,
				Items.EXPOSED_CUT_COPPER_SLAB, Items.WAXED_EXPOSED_CUT_COPPER_SLAB,
				Items.WEATHERED_CUT_COPPER_SLAB, Items.WAXED_WEATHERED_CUT_COPPER_SLAB,
				Items.OXIDIZED_CUT_COPPER_SLAB, Items.WAXED_OXIDIZED_CUT_COPPER_SLAB);
		tag(COPPER_STAIRS)
			.add(
				Items.CUT_COPPER_STAIRS, Items.WAXED_CUT_COPPER_STAIRS,
				Items.EXPOSED_CUT_COPPER_STAIRS, Items.WAXED_EXPOSED_CUT_COPPER_STAIRS,
				Items.WEATHERED_CUT_COPPER_STAIRS, Items.WAXED_WEATHERED_CUT_COPPER_STAIRS,
				Items.OXIDIZED_CUT_COPPER_STAIRS, Items.WAXED_OXIDIZED_CUT_COPPER_STAIRS);
		tag(BUCKETS)
			.add(
				Items.BUCKET,
				Items.WATER_BUCKET,
				Items.LAVA_BUCKET,
				Items.MILK_BUCKET,
				Items.TROPICAL_FISH_BUCKET,
				Items.SALMON_BUCKET,
				Items.PUFFERFISH_BUCKET,
				Items.COD_BUCKET,
				Items.AXOLOTL_BUCKET,
				Items.TADPOLE_BUCKET,
				Items.POWDER_SNOW_BUCKET);
	}
}
