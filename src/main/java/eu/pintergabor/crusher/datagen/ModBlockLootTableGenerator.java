package eu.pintergabor.crusher.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.crusher.blocks.ModBlocks;

import net.minecraft.registry.RegistryWrapper;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;


public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {
	public ModBlockLootTableGenerator(
		FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generate() {
		// Drops itself.
		addDrop(ModBlocks.CRUSHER_BLOCK);
		addDrop(ModBlocks.COMPRESSOR_BLOCK);
	}
}
