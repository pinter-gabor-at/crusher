package eu.pintergabor.crusher.datagen;

import eu.pintergabor.crusher.Global;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    /**
     * Items crushed to gravel.
     */
    public static final TagKey<Item> GRAVEL_SOURCES = TagKey.of(
            RegistryKeys.ITEM, Global.ModIdentifier("gravel_sources"));
    /**
     * Items crushed to sand.
     */
    public static final TagKey<Item> SAND_SOURCES = TagKey.of(
            RegistryKeys.ITEM, Global.ModIdentifier("sand_sources"));
    /**
     * Items crushed to red sand.
     */
    public static final TagKey<Item> RED_SAND_SOURCES = TagKey.of(
            RegistryKeys.ITEM, Global.ModIdentifier("red_sand_sources"));
    /**
     * Similar to {@link ConventionalItemTags#FRUIT_FOODS}, excluding the golden variants.
     */
    public static final TagKey<Item> NORMAL_FRUIT_FOODS = TagKey.of(
            RegistryKeys.ITEM, Identifier.of("c", "foods/normal_fruit"));
    /**
     * Similar to {@link ConventionalItemTags#VEGETABLE_FOODS}, excluding the golden variants.
     */
    public static final TagKey<Item> NORMAL_VEGETABLE_FOODS = TagKey.of(
            RegistryKeys.ITEM, Identifier.of("c", "foods/normal_vegetable"));

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(GRAVEL_SOURCES)
                .forceAddTag(ConventionalItemTags.STONES)
                .forceAddTag(ConventionalItemTags.COBBLESTONES)
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
        getOrCreateTagBuilder(SAND_SOURCES)
                .forceAddTag(ConventionalItemTags.UNCOLORED_SANDSTONE_BLOCKS)
                .forceAddTag(ConventionalItemTags.UNCOLORED_SANDSTONE_SLABS)
                .forceAddTag(ConventionalItemTags.UNCOLORED_SANDSTONE_STAIRS)
                .forceAddTag(ConventionalItemTags.GLASS_BLOCKS)
                .forceAddTag(ConventionalItemTags.GLASS_PANES)
                .add(
                        Items.GRAVEL,
                        Items.QUARTZ, Items.QUARTZ_BLOCK, Items.QUARTZ_BRICKS, Items.QUARTZ_PILLAR,
                        Items.QUARTZ_SLAB, Items.QUARTZ_STAIRS,
                        Items.SMOOTH_QUARTZ, Items.SMOOTH_QUARTZ_SLAB, Items.SMOOTH_QUARTZ_STAIRS,
                        Items.CHISELED_QUARTZ_BLOCK, Items.NETHER_QUARTZ_ORE,
                        Items.GLASS_BOTTLE,
                        Items.TERRACOTTA, Items.WHITE_TERRACOTTA, Items.WHITE_GLAZED_TERRACOTTA);
        getOrCreateTagBuilder(RED_SAND_SOURCES)
                .forceAddTag(ConventionalItemTags.RED_SANDSTONE_BLOCKS)
                .forceAddTag(ConventionalItemTags.RED_SANDSTONE_SLABS)
                .forceAddTag(ConventionalItemTags.RED_SANDSTONE_STAIRS)
                .add(
                        Items.RED_TERRACOTTA, Items.RED_GLAZED_TERRACOTTA,
                        Items.RED_NETHER_BRICK_SLAB, Items.RED_NETHER_BRICK_STAIRS, Items.RED_NETHER_BRICK_WALL,
                        Items.RED_NETHER_BRICKS, Items.RED_STAINED_GLASS, Items.RED_STAINED_GLASS_PANE);
        getOrCreateTagBuilder(NORMAL_FRUIT_FOODS)
                .add(
                        Items.APPLE, Items.CHORUS_FRUIT, Items.MELON_SLICE);
        getOrCreateTagBuilder(NORMAL_VEGETABLE_FOODS)
                .add(
                        Items.CARROT, Items.POTATO, Items.BEETROOT);
    }
}
