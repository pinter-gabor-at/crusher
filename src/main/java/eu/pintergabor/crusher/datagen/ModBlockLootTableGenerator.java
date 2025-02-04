package eu.pintergabor.crusher.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.crusher.blocks.ModBlocks;

import net.minecraft.core.HolderLookup;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;


public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {

	public ModBlockLootTableGenerator(
		FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generate() {
		// Drop themselves.
		dropSelf(ModBlocks.CRUSHER_BLOCK);
		dropSelf(ModBlocks.COMPRESSOR_BLOCK);
	}
}
