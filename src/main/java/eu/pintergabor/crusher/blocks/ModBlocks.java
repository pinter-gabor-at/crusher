package eu.pintergabor.crusher.blocks;

import eu.pintergabor.crusher.Global;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class ModBlocks {
    public static Block CRUSHER_BLOCK;
    public static Block COMPRESSOR_BLOCK;
    public static Item CRUSHER_ITEM;
    public static Item COMPRESOR_ITEM;
    public static BlockEntityType<CrusherBlockEntity> CRUSHER_ENTITY;
    public static BlockEntityType<CompressorBlockEntity> COMPRESSOR_ENTITY;

    public static void register() {
        // Blocks
        CRUSHER_BLOCK = Blocks.register(
                RegistryKey.of(RegistryKeys.BLOCK, Global.ModIdentifier("crusher")),
                CrusherBlock::new,
                AbstractBlock.Settings.create()
                        .solid().strength(0.5f, 6.0f).requiresTool());
        COMPRESSOR_BLOCK = Blocks.register(
                RegistryKey.of(RegistryKeys.BLOCK, Global.ModIdentifier("compressor")),
                CompressorBlock::new,
                AbstractBlock.Settings.create()
                        .solid().strength(0.5f, 6.0f).requiresTool());
        // Items
        CRUSHER_ITEM = Items.register(CRUSHER_BLOCK);
        COMPRESOR_ITEM = Items.register(COMPRESSOR_BLOCK);
        // Entities
        CRUSHER_ENTITY =
                Registry.register(Registries.BLOCK_ENTITY_TYPE, Global.ModIdentifier("crusher"),
                        FabricBlockEntityTypeBuilder.create(CrusherBlockEntity::new,
                                ModBlocks.CRUSHER_BLOCK).build());
        COMPRESSOR_ENTITY =
                Registry.register(Registries.BLOCK_ENTITY_TYPE, Global.ModIdentifier("compressor"),
                        FabricBlockEntityTypeBuilder.create(CompressorBlockEntity::new,
                                ModBlocks.COMPRESSOR_BLOCK).build());
        // Creative tabs
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(
                content -> content.addAfter(Items.BLAST_FURNACE,
                        CRUSHER_BLOCK, COMPRESSOR_BLOCK));
    }
}
