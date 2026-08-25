package eu.pintergabor.crusher.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.crusher.Global;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.references.BlockItemId;
import net.minecraft.references.BlockItemIds;
import net.minecraft.references.ItemIds;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.WeatheringCopperCollection;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;


public final class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

	/**
	 * Items crushed to 1 gravel.
	 */
	public static final TagKey<Item> GRAVEL_SOURCES = createModItemTag("gravel_sources");

	/**
	 * Slab like items crushed to 0.5 gravel.
	 */
	public static final TagKey<Item> GRAVEL_SOURCES_SLABS = createModItemTag("gravel_sources_slabs");

	/**
	 * Stairs like items crushed to 1.5 gravel.
	 */
	public static final TagKey<Item> GRAVEL_SOURCES_STAIRS = createModItemTag("gravel_sources_stairs");

	/**
	 * Items crushed to 1 sand.
	 */
	public static final TagKey<Item> SAND_SOURCES = createModItemTag("sand_sources");

	/**
	 * Items crushed to 1 red sand.
	 */
	public static final TagKey<Item> RED_SAND_SOURCES = createModItemTag("red_sand_sources");

	/**
	 * Similar to {@link ConventionalItemTags#FRUIT_FOODS}, excluding the golden variants.
	 */
	public static final TagKey<Item> NORMAL_FRUIT_FOODS = createCItemTag("foods/normal_fruit");

	/**
	 * Similar to {@link ConventionalItemTags#VEGETABLE_FOODS}, excluding the golden variants.
	 */
	public static final TagKey<Item> NORMAL_VEGETABLE_FOODS = createCItemTag("foods/normal_vegetable");

	/**
	 * Wooden stairs.
	 */
	public static final TagKey<Item> WOODEN_STAIRS = createCItemTag("stairs/wood");

	/**
	 * Wooden slabs.
	 */
	public static final TagKey<Item> WOODEN_SLABS = createCItemTag("slabs/wood");

	/**
	 * Wooden pressure plates.
	 */
	public static final TagKey<Item> WOODEN_PRESSURE_PLATES = createCItemTag("pressure_plates/wood");

	/**
	 * Wooden signs.
	 */
	public static final TagKey<Item> WOODEN_SIGNS = createCItemTag("signs/wood");

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
	 * Copper stairs.
	 */
	public static final TagKey<Item> COPPER_STAIRS = createCItemTag("stairs/copper");

	/**
	 * Copper slabs.
	 */
	public static final TagKey<Item> COPPER_SLABS = createCItemTag("slabs/copper");

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
	 * Buckets.
	 */
	public static final TagKey<Item> BUCKETS = createCItemTag("buckets");

	public ModItemTagProvider(
		final @NonNull FabricPackOutput output,
		final @NonNull CompletableFuture<HolderLookup.Provider> completableFuture
	) {
		super(output, completableFuture);
	}

	/**
	 * Create a mod item tag.
	 */
	private static @NonNull TagKey<Item> createModItemTag(final @NonNull String path) {
		return TagKey.create(
			Registries.ITEM, Global.modId(path));
	}

	/**
	 * Create a conventional item tag.
	 */
	private static @NonNull TagKey<Item> createCItemTag(final @NonNull String path) {
		return TagKey.create(
			Registries.ITEM, Identifier.fromNamespaceAndPath("c", path));
	}

	@SuppressWarnings({"unchecked", "RedundantSuppression"})
	private void tagGravelSources() {
		final TagAppender<Item> tag = tag(GRAVEL_SOURCES);
		tag.addOptionalTag(ConventionalItemTags.STONES);
		tag.addOptionalTag(ConventionalItemTags.COBBLESTONES);
		tag.add(
			BlockItemIds.BASALT.item(),
			BlockItemIds.BLACKSTONE.item(),
			BlockItemIds.COBBLESTONE_WALL.item(),
			BlockItemIds.MOSSY_COBBLESTONE_WALL.item(),
			BlockItemIds.COBBLED_DEEPSLATE.item(),
			BlockItemIds.COBBLED_DEEPSLATE_WALL.item(),
			BlockItemIds.STONE.item(),
			BlockItemIds.STONE_STAIRS.item(),
			BlockItemIds.POLISHED_ANDESITE.item(),
			BlockItemIds.POLISHED_BASALT.item(),
			BlockItemIds.POLISHED_DIORITE.item(),
			BlockItemIds.POLISHED_GRANITE.item(),
			BlockItemIds.POLISHED_TUFF.item(),
			BlockItemIds.POLISHED_DEEPSLATE.item(),
			BlockItemIds.POLISHED_BLACKSTONE.item(),
			BlockItemIds.CHISELED_POLISHED_BLACKSTONE.item(),
			BlockItemIds.SMOOTH_STONE.item(),
			BlockItemIds.SMOOTH_BASALT.item(),
			BlockItemIds.CHISELED_TUFF.item(),
			BlockItemIds.CHISELED_TUFF_BRICKS.item(),
			BlockItemIds.CHISELED_DEEPSLATE.item(),
			BlockItemIds.STONE_BRICKS.item(),
			BlockItemIds.CHISELED_STONE_BRICKS.item(),
			BlockItemIds.STONE_BRICK_WALL.item(),
			BlockItemIds.MOSSY_STONE_BRICK_WALL.item(),
			BlockItemIds.ANDESITE_WALL.item(),
			BlockItemIds.DIORITE_WALL.item(),
			BlockItemIds.GRANITE_WALL.item(),
			BlockItemIds.TUFF_WALL.item(),
			BlockItemIds.TUFF_BRICKS.item(),
			BlockItemIds.TUFF_BRICK_WALL.item(),
			BlockItemIds.POLISHED_TUFF_WALL.item(),
			BlockItemIds.POLISHED_DEEPSLATE_WALL.item(),
			BlockItemIds.CRACKED_DEEPSLATE_BRICKS.item(),
			BlockItemIds.BLACKSTONE_WALL.item(),
			BlockItemIds.POLISHED_BLACKSTONE_BRICKS.item(),
			BlockItemIds.POLISHED_BLACKSTONE_BRICK_WALL.item(),
			BlockItemIds.CRACKED_NETHER_BRICKS.item(),
			BlockItemIds.CRACKED_POLISHED_BLACKSTONE_BRICKS.item(),
			BlockItemIds.MOSSY_STONE_BRICKS.item(),
			ItemIds.NETHER_BRICK,
			BlockItemIds.NETHER_BRICK_FENCE.item(),
			BlockItemIds.NETHER_BRICK_WALL.item(),
			BlockItemIds.NETHER_BRICKS.item(),
			BlockItemIds.CHISELED_NETHER_BRICKS.item(),
			BlockItemIds.DEEPSLATE.item(),
			BlockItemIds.DEEPSLATE_BRICK_WALL.item(),
			BlockItemIds.DEEPSLATE_BRICKS.item(),
			BlockItemIds.DEEPSLATE_TILES.item(),
			BlockItemIds.DEEPSLATE_TILE_WALL.item(),
			BlockItemIds.CRACKED_DEEPSLATE_TILES.item(),
			BlockItemIds.INFESTED_DEEPSLATE.item(),
			BlockItemIds.REINFORCED_DEEPSLATE.item(),
			ItemIds.FLINT,
			BlockItemIds.OBSIDIAN.item(),
			BlockItemIds.CRYING_OBSIDIAN.item());
	}

	@SuppressWarnings({"unchecked", "RedundantSuppression"})
	private void tagGravelSourcesSlabs() {
		final TagAppender<Item> tag = tag(GRAVEL_SOURCES_SLABS);
		tag.add(
			BlockItemIds.COBBLESTONE_SLAB.item(),
			BlockItemIds.MOSSY_COBBLESTONE_SLAB.item(),
			BlockItemIds.COBBLED_DEEPSLATE_SLAB.item(),
			BlockItemIds.STONE_SLAB.item(),
			BlockItemIds.STONE_BRICK_SLAB.item(),
			BlockItemIds.MOSSY_STONE_BRICK_SLAB.item(),
			BlockItemIds.ANDESITE_SLAB.item(),
			BlockItemIds.DIORITE_SLAB.item(),
			BlockItemIds.GRANITE_SLAB.item(),
			BlockItemIds.TUFF_SLAB.item(),
			BlockItemIds.TUFF_BRICK_SLAB.item(),
			BlockItemIds.POLISHED_ANDESITE_SLAB.item(),
			BlockItemIds.POLISHED_DIORITE_SLAB.item(),
			BlockItemIds.POLISHED_GRANITE_SLAB.item(),
			BlockItemIds.POLISHED_TUFF_SLAB.item(),
			BlockItemIds.POLISHED_DEEPSLATE_SLAB.item(),
			BlockItemIds.POLISHED_BLACKSTONE_BRICK_SLAB.item(),
			BlockItemIds.BLACKSTONE_SLAB.item(),
			BlockItemIds.POLISHED_BLACKSTONE_SLAB.item(),
			BlockItemIds.NETHER_BRICK_SLAB.item(),
			BlockItemIds.DEEPSLATE_BRICK_SLAB.item(),
			BlockItemIds.DEEPSLATE_TILE_SLAB.item());
	}

	@SuppressWarnings({"unchecked", "RedundantSuppression"})
	private void tagGravelSourcesStairs() {
		final TagAppender<Item> tag = tag(GRAVEL_SOURCES_STAIRS);
		tag.add(
			BlockItemIds.COBBLESTONE_STAIRS.item(),
			BlockItemIds.MOSSY_COBBLESTONE_STAIRS.item(),
			BlockItemIds.COBBLED_DEEPSLATE_STAIRS.item(),
			BlockItemIds.STONE_STAIRS.item(),
			BlockItemIds.STONE_BRICK_STAIRS.item(),
			BlockItemIds.MOSSY_STONE_BRICK_STAIRS.item(),
			BlockItemIds.ANDESITE_STAIRS.item(),
			BlockItemIds.DIORITE_STAIRS.item(),
			BlockItemIds.GRANITE_STAIRS.item(),
			BlockItemIds.TUFF_STAIRS.item(),
			BlockItemIds.TUFF_BRICK_STAIRS.item(),
			BlockItemIds.POLISHED_ANDESITE_STAIRS.item(),
			BlockItemIds.POLISHED_DIORITE_STAIRS.item(),
			BlockItemIds.POLISHED_GRANITE_STAIRS.item(),
			BlockItemIds.POLISHED_TUFF_STAIRS.item(),
			BlockItemIds.POLISHED_DEEPSLATE_STAIRS.item(),
			BlockItemIds.POLISHED_BLACKSTONE_BRICK_STAIRS.item(),
			BlockItemIds.BLACKSTONE_STAIRS.item(),
			BlockItemIds.NETHER_BRICK_STAIRS.item(),
			BlockItemIds.DEEPSLATE_BRICK_STAIRS.item(),
			BlockItemIds.DEEPSLATE_TILE_STAIRS.item());
	}

	@SuppressWarnings({"unchecked", "RedundantSuppression"})
	private void tagSandSources() {
		final TagAppender<Item> tag = tag(SAND_SOURCES);
		tag.add(
			BlockItemIds.GRAVEL.item(),
			BlockItemIds.SUSPICIOUS_GRAVEL.item(),
			BlockItemIds.SUSPICIOUS_SAND.item(),
			ItemIds.QUARTZ,
			BlockItemIds.QUARTZ_BLOCK.item(),
			BlockItemIds.QUARTZ_BRICKS.item(),
			BlockItemIds.QUARTZ_PILLAR.item(),
			BlockItemIds.QUARTZ_SLAB.item(),
			BlockItemIds.QUARTZ_STAIRS.item(),
			BlockItemIds.SMOOTH_QUARTZ.item(),
			BlockItemIds.SMOOTH_QUARTZ_SLAB.item(),
			BlockItemIds.SMOOTH_QUARTZ_STAIRS.item(),
			BlockItemIds.CHISELED_QUARTZ_BLOCK.item());
	}

	@SuppressWarnings({"unchecked", "RedundantSuppression"})
	private void tagRedSandSources() {
		final TagAppender<Item> tag = tag(RED_SAND_SOURCES);
		tag.add(
			BlockItemIds.BRICK_STAIRS.item(),
			BlockItemIds.BRICK_WALL.item(),
			BlockItemIds.BRICKS.item(),
			BlockItemIds.RED_NETHER_BRICK_STAIRS.item(),
			BlockItemIds.RED_NETHER_BRICK_WALL.item(),
			BlockItemIds.RED_NETHER_BRICKS.item());
	}

	@SuppressWarnings({"unchecked", "RedundantSuppression"})
	private void tagNormalFruitFoods() {
		final TagAppender<Item> tag = tag(NORMAL_FRUIT_FOODS);
		tag.add(
			ItemIds.APPLE,
			ItemIds.CHORUS_FRUIT,
			ItemIds.MELON_SLICE);
	}

	@SuppressWarnings({"unchecked", "RedundantSuppression"})
	private void tagNormalVegetableFoods() {
		final TagAppender<Item> tag = tag(NORMAL_VEGETABLE_FOODS);
		tag.add(
			BlockItemIds.CARROT_CROP.item(),
			BlockItemIds.POTATO_CROP.item(),
			ItemIds.BEETROOT,
			ItemIds.DRIED_KELP);
	}

	@SuppressWarnings({"unchecked", "RedundantSuppression"})
	private void tagWoodenStairs() {
		final TagAppender<Item> tag = tag(WOODEN_STAIRS);
		tag.add(
			BlockItemIds.OAK_STAIRS.item(),
			BlockItemIds.SPRUCE_STAIRS.item(),
			BlockItemIds.BIRCH_STAIRS.item(),
			BlockItemIds.JUNGLE_STAIRS.item(),
			BlockItemIds.ACACIA_STAIRS.item(),
			BlockItemIds.DARK_OAK_STAIRS.item(),
			BlockItemIds.MANGROVE_STAIRS.item(),
			BlockItemIds.CHERRY_STAIRS.item(),
			BlockItemIds.PALE_OAK_STAIRS.item(),
			BlockItemIds.BAMBOO_STAIRS.item(),
			BlockItemIds.BAMBOO_MOSAIC_STAIRS.item(),
			BlockItemIds.CRIMSON_STAIRS.item(),
			BlockItemIds.WARPED_STAIRS.item());
	}

	@SuppressWarnings({"unchecked", "RedundantSuppression"})
	private void tagWoodenSlabs() {
		final TagAppender<Item> tag = tag(WOODEN_SLABS);
		tag.add(
			BlockItemIds.OAK_SLAB.item(),
			BlockItemIds.SPRUCE_SLAB.item(),
			BlockItemIds.BIRCH_SLAB.item(),
			BlockItemIds.JUNGLE_SLAB.item(),
			BlockItemIds.ACACIA_SLAB.item(),
			BlockItemIds.DARK_OAK_SLAB.item(),
			BlockItemIds.MANGROVE_SLAB.item(),
			BlockItemIds.CHERRY_SLAB.item(),
			BlockItemIds.PALE_OAK_SLAB.item(),
			BlockItemIds.BAMBOO_SLAB.item(),
			BlockItemIds.CRIMSON_SLAB.item(),
			BlockItemIds.WARPED_SLAB.item());
	}

	@SuppressWarnings({"unchecked", "RedundantSuppression"})
	private void tagWoodenPressurePlates() {
		final TagAppender<Item> tag = tag(WOODEN_PRESSURE_PLATES);
		tag.add(
			BlockItemIds.OAK_PRESSURE_PLATE.item(),
			BlockItemIds.SPRUCE_PRESSURE_PLATE.item(),
			BlockItemIds.BIRCH_PRESSURE_PLATE.item(),
			BlockItemIds.JUNGLE_PRESSURE_PLATE.item(),
			BlockItemIds.ACACIA_PRESSURE_PLATE.item(),
			BlockItemIds.DARK_OAK_PRESSURE_PLATE.item(),
			BlockItemIds.MANGROVE_PRESSURE_PLATE.item(),
			BlockItemIds.CHERRY_PRESSURE_PLATE.item(),
			BlockItemIds.PALE_OAK_PRESSURE_PLATE.item(),
			BlockItemIds.BAMBOO_PRESSURE_PLATE.item(),
			BlockItemIds.CRIMSON_PRESSURE_PLATE.item(),
			BlockItemIds.WARPED_PRESSURE_PLATE.item());
	}

	@SuppressWarnings({"unchecked", "RedundantSuppression"})
	private void tagWoodenSigns() {
		final TagAppender<Item> tag = tag(WOODEN_SIGNS);
		tag.add(
			BlockItemIds.OAK_SIGN.item(),
			BlockItemIds.SPRUCE_SIGN.item(),
			BlockItemIds.BIRCH_SIGN.item(),
			BlockItemIds.JUNGLE_SIGN.item(),
			BlockItemIds.ACACIA_SIGN.item(),
			BlockItemIds.DARK_OAK_SIGN.item(),
			BlockItemIds.MANGROVE_SIGN.item(),
			BlockItemIds.CHERRY_SIGN.item(),
			BlockItemIds.PALE_OAK_SIGN.item(),
			BlockItemIds.BAMBOO_SIGN.item(),
			BlockItemIds.CRIMSON_SIGN.item(),
			BlockItemIds.WARPED_SIGN.item());
	}

	@SuppressWarnings({"unchecked", "RedundantSuppression"})
	private void tagAddWeatheringCopperCollection(
		final @NonNull TagAppender<Item> tag,
		final @NonNull WeatheringCopperCollection<BlockItemId> collection
	) {
		tag.addAll(collection.map(BlockItemId::item).asList());
	}

	private void tagCopperBlocks() {
		final TagAppender<Item> tag = tag(COPPER_BLOCKS);
		tagAddWeatheringCopperCollection(tag, BlockItemIds.COPPER_BLOCK);
	}

	private void tagCutCopperBlocks() {
		final TagAppender<Item> tag = tag(CUT_COPPER_BLOCKS);
		tagAddWeatheringCopperCollection(tag, BlockItemIds.CUT_COPPER);
	}

	private void tagChiseledCopperBlocks() {
		final TagAppender<Item> tag = tag(CHISELED_COPPER_BLOCKS);
		tagAddWeatheringCopperCollection(tag, BlockItemIds.CHISELED_COPPER);
	}

	private void tagCopperStairs() {
		final TagAppender<Item> tag = tag(COPPER_STAIRS);
		tagAddWeatheringCopperCollection(tag, BlockItemIds.CUT_COPPER_STAIRS);
	}

	private void tagCopperSlabs() {
		final TagAppender<Item> tag = tag(COPPER_SLABS);
		tagAddWeatheringCopperCollection(tag, BlockItemIds.CUT_COPPER_SLAB);
	}

	private void tagCopperDoors() {
		final TagAppender<Item> tag = tag(COPPER_DOORS);
		tagAddWeatheringCopperCollection(tag, BlockItemIds.COPPER_DOOR);
	}

	private void tagCopperTrapdoors() {
		final TagAppender<Item> tag = tag(COPPER_TRAPDOORS);
		tagAddWeatheringCopperCollection(tag, BlockItemIds.COPPER_TRAPDOOR);
	}

	private void tagCopperGrates() {
		final TagAppender<Item> tag = tag(COPPER_GRATES);
		tagAddWeatheringCopperCollection(tag, BlockItemIds.COPPER_GRATE);
	}

	private void tagCopperBulbs() {
		final TagAppender<Item> tag = tag(COPPER_BULBS);
		tagAddWeatheringCopperCollection(tag, BlockItemIds.COPPER_BULB);
	}

	@SuppressWarnings({"unchecked", "RedundantSuppression"})
	private void tagBuckets() {
		final TagAppender<Item> tag = tag(BUCKETS);
		tag.add(
			ItemIds.BUCKET,
			ItemIds.WATER_BUCKET,
			ItemIds.LAVA_BUCKET,
			ItemIds.MILK_BUCKET,
			ItemIds.TROPICAL_FISH_BUCKET,
			ItemIds.SALMON_BUCKET,
			ItemIds.PUFFERFISH_BUCKET,
			ItemIds.COD_BUCKET,
			ItemIds.AXOLOTL_BUCKET,
			ItemIds.TADPOLE_BUCKET,
			ItemIds.SULFUR_CUBE_BUCKET);
	}

	/**
	 * Create all tags.
	 */
	@Override
	protected void addTags(final HolderLookup.@NonNull Provider registries) {
		tagGravelSources();
		tagGravelSourcesSlabs();
		tagGravelSourcesStairs();
		tagSandSources();
		tagRedSandSources();
		tagNormalFruitFoods();
		tagNormalVegetableFoods();
		tagWoodenStairs();
		tagWoodenSlabs();
		tagWoodenPressurePlates();
		tagWoodenSigns();
		tagCopperBlocks();
		tagCutCopperBlocks();
		tagChiseledCopperBlocks();
		tagCopperStairs();
		tagCopperSlabs();
		tagCopperDoors();
		tagCopperTrapdoors();
		tagCopperGrates();
		tagCopperBulbs();
		tagBuckets();
	}
}
