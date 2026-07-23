package eu.pintergabor.crusher.blocks;

import java.util.function.Function;

import eu.pintergabor.crusher.Global;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;


/**
 * Mod blocks, items and entities.
 */
public final class ModBlocks {
	public static Block CRUSHER_BLOCK;
	public static ResourceKey<Block> CRUSHER_BLOCK_ID;
	public static BlockItemId CRUSHER_BLOCK_ITEMID;
	public static Block COMPRESSOR_BLOCK;
	public static ResourceKey<Block> COMPRESSOR_BLOCK_ID;
	public static Item CRUSHER_ITEM;
	public static Item COMPRESSOR_ITEM;
	public static BlockEntityType<CrusherBlockEntity> CRUSHER_ENTITY;
	public static BlockEntityType<CompressorBlockEntity> COMPRESSOR_ENTITY;

	/**
	 * Create {@link ResourceKey} BlockId.
	 *
	 * @param path The name of the entity, without MODID.
	 * @return The new blockId.
	 */
	private static @NonNull ResourceKey<Block> createBlockId(
		final @NonNull String path
	) {
		return ResourceKey.create(Registries.BLOCK, Global.modId(path));
	}

	/**
	 * Create {@link ResourceKey} BlockId.
	 *
	 * @param path The name of the entity, without MODID.
	 * @return The new blockId.
	 */
	private static @NonNull BlockItemId createBlockItemId(
		final @NonNull String path
	) {
		//return ResourceKey.create(Registries.BLOCK, Global.modId(path));
		return BlockItemId.create(Global.modId(path), Global.modId(path));
	}

	/**
	 * Create and register a {@link Block}.
	 *
	 * @param blockId The id of the block.
	 * @param factory The constructor of the block.
	 * @return The new block.
	 */
	private static @NonNull Block registerBlock(
		final @NonNull ResourceKey<Block> blockId,
		final @NonNull Function<BlockBehaviour.Properties, Block> factory
	) {
		return Blocks.register(
			blockId,
			factory,
			Block.Properties.of()
				.forceSolidOn()
				.strength(0.5F, 6.0F)
				.requiresCorrectToolForDrops());
	}

	/**
	 * Create and register a {@link BlockEntityType}.
	 *
	 * @param path    The name of the entity, without MODID.
	 * @param factory The constructor of the entity.
	 * @param block   The blocks this entity is associated with.
	 * @param <T>     The entity class.
	 * @return The new entity type.
	 */
	private static <T extends BlockEntity> @NonNull BlockEntityType<T> registerEntity(
		final @NonNull String path,
		final FabricBlockEntityTypeBuilder.@NonNull Factory<T> factory,
		final @NonNull Block... block
	) {
		final BlockEntityType<T> entity =
			FabricBlockEntityTypeBuilder.create(factory, block).build();
		Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
			Global.modId(path), entity);
		return entity;
	}

	/**
	 * Create and register all blocks, items and block entities.
	 */
	public static void init() {
		// Blocks.
		CRUSHER_BLOCK_ID = createBlockId("crusher");
		CRUSHER_BLOCK_ITEMID = createBlockItemId("crusher");
		CRUSHER_BLOCK = registerBlock(CRUSHER_BLOCK_ITEMID.block(), CrusherBlock::new);
		COMPRESSOR_BLOCK_ID = createBlockId("compressor");
		COMPRESSOR_BLOCK = registerBlock(COMPRESSOR_BLOCK_ID, CompressorBlock::new);
		// Items.
		CRUSHER_ITEM = Items.registerBlock(CRUSHER_BLOCK_ITEMID, CRUSHER_BLOCK);
		COMPRESSOR_ITEM = Items.registerBlock(COMPRESSOR_BLOCK_ITEMID, COMPRESSOR_BLOCK);
		// Entities.
		CRUSHER_ENTITY = registerEntity("crusher",
			CrusherBlockEntity::new, ModBlocks.CRUSHER_BLOCK);
		COMPRESSOR_ENTITY = registerEntity("compressor",
			CompressorBlockEntity::new, ModBlocks.COMPRESSOR_BLOCK);
		// Creative tabs.
		CreativeModeTabEvents.modifyOutputEvent(
			CreativeModeTabs.FUNCTIONAL_BLOCKS).register(
			content -> content.insertAfter(
				Items.BLAST_FURNACE, CRUSHER_BLOCK, COMPRESSOR_BLOCK
			)
		);
	}
}
