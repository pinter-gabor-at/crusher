package eu.pintergabor.crusher.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.crusher.blocks.ModBlocks;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;


public final class ModBlockLootTableGenerator extends FabricBlockLootSubProvider {

	public ModBlockLootTableGenerator(
		final @NonNull FabricPackOutput dataOutput,
		final @NonNull CompletableFuture<HolderLookup.Provider> registriesFuture
	) {
		super(dataOutput, registriesFuture);
	}

	@Override
	public void generate() {
		// Drop themselves.
		dropSelf(ModBlocks.CRUSHER_BLOCK);
		dropSelf(ModBlocks.COMPRESSOR_BLOCK);
	}
}
