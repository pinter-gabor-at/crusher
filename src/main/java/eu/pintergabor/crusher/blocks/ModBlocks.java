package eu.pintergabor.crusher.blocks;

import java.util.function.Function;

import eu.pintergabor.crusher.Global;
import org.jetbrains.annotations.NotNull;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;


/**
 * Mod blocks, items and entities.
 */
public class ModBlocks {
	public static Block CRUSHER_BLOCK;
	public static Block COMPRESSOR_BLOCK;
	public static Item CRUSHER_ITEM;
	public static Item COMPRESOR_ITEM;
	public static BlockEntityType<CrusherBlockEntity> CRUSHER_ENTITY;
	public static BlockEntityType<CompressorBlockEntity> COMPRESSOR_ENTITY;

	private static @NotNull Block registerBlock(
		String path, Function<BlockBehaviour.Properties, Block> factory) {
		return Blocks.register(
			ResourceKey.create(Registries.BLOCK, Global.modId(path)),
			factory,
			Block.Properties.of()
				.forceSolidOn()
				.strength(0.5F, 6.0F)
				.requiresCorrectToolForDrops());
	}

	public static void init() {
		// Blocks.
		CRUSHER_BLOCK = registerBlock("crusher", CrusherBlock::new);
		COMPRESSOR_BLOCK = registerBlock("compressor", CompressorBlock::new);
		// Items.
		CRUSHER_ITEM = Items.registerBlock(CRUSHER_BLOCK);
		COMPRESOR_ITEM = Items.registerBlock(COMPRESSOR_BLOCK);
		// Entities.
		CRUSHER_ENTITY = FabricBlockEntityTypeBuilder.create(
			CrusherBlockEntity::new, ModBlocks.CRUSHER_BLOCK).build();
		Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
			Global.modId("crusher"), CRUSHER_ENTITY);
		COMPRESSOR_ENTITY = FabricBlockEntityTypeBuilder.create(
			CompressorBlockEntity::new, ModBlocks.COMPRESSOR_BLOCK).build();
		Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
			Global.modId("compressor"), COMPRESSOR_ENTITY);
		// Creative tabs.
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(
			content -> content.addAfter(Items.BLAST_FURNACE,
				CRUSHER_BLOCK, COMPRESSOR_BLOCK));
	}
}
