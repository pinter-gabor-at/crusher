package eu.pintergabor.crusher.blocks;

import eu.pintergabor.crusher.main.Main;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;


/**
 * Mod blocks, items and entities.
 */
public class ModBlocks {
	// Blocks.
	public static DeferredBlock<Block> CRUSHER_BLOCK;
	public static DeferredBlock<Block> COMPRESSOR_BLOCK;
	// Items.
	public static DeferredItem<BlockItem> CRUSHER_ITEM;
	public static DeferredItem<BlockItem> COMPRESOR_ITEM;
	// Entities.
	public static DeferredHolder<BlockEntityType<?>, BlockEntityType<CrusherBlockEntity>> CRUSHER_ENTITY;
	public static DeferredHolder<BlockEntityType<?>, BlockEntityType<CompressorBlockEntity>> COMPRESSOR_ENTITY;

	/**
	 * Initialize block types.
	 */
	private static void initBlockTypes() {
		Main.BLOCK_TYPES.register(
			"crusher",
			() -> CrusherBlock.CODEC);
		Main.BLOCK_TYPES.register(
			"compressor",
			() -> CompressorBlock.CODEC);
	}

	/**
	 * Initialize blocks.
	 */
	private static void initBlocks() {
		final Block.Properties blockProps = Block.Properties.of()
			.forceSolidOn()
			.strength(0.5F, 6.0F)
			.requiresCorrectToolForDrops();
		CRUSHER_BLOCK = Main.BLOCKS.register("crusher", id ->
			new CrusherBlock(blockProps
				.setId(ResourceKey.create(Registries.BLOCK, id))));
		COMPRESSOR_BLOCK = Main.BLOCKS.register("compressor", id ->
			new CompressorBlock(blockProps
				.setId(ResourceKey.create(Registries.BLOCK, id))));
	}

	/**
	 * Initialize items.
	 */
	private static void initItems() {
		CRUSHER_ITEM = Main.ITEMS.registerSimpleBlockItem(CRUSHER_BLOCK);
		COMPRESOR_ITEM = Main.ITEMS.registerSimpleBlockItem(COMPRESSOR_BLOCK);
	}

	/**
	 * Initialize block entities.
	 */
	private static void initEntities() {
		CRUSHER_ENTITY = Main.BLOCK_ENTITY_TYPES.register("crusher", () ->
			new BlockEntityType<>(
				CrusherBlockEntity::new, CRUSHER_BLOCK.get()));
		COMPRESSOR_ENTITY = Main.BLOCK_ENTITY_TYPES.register("compressor", () ->
			new BlockEntityType<>(
				CompressorBlockEntity::new, COMPRESSOR_BLOCK.get()));
	}

	/**
	 * Create and register all blocks, items and block entities.
	 */
	public static void init() {
		// Block types.
		initBlockTypes();
		// Blocks.
		initBlocks();
		// Items.
		initItems();
		// Entities.
		initEntities();
	}
}
