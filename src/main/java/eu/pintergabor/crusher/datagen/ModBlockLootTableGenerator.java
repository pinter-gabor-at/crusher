package eu.pintergabor.crusher.datagen;

import java.util.Set;

import eu.pintergabor.crusher.blocks.ModBlocks;

import eu.pintergabor.crusher.main.Main;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import org.jetbrains.annotations.NotNull;


public class ModBlockLootTableGenerator extends BlockLootSubProvider {

	public ModBlockLootTableGenerator(HolderLookup.Provider lookupProvider) {
		super(Set.of(), FeatureFlags.DEFAULT_FLAGS, lookupProvider);
	}

	@Override
	@NotNull
	protected Iterable<Block> getKnownBlocks() {
		return Main.BLOCKS.getEntries()
			.stream()
			.map(e -> (Block) e.get())
			.toList();
	}

	@Override
	public void generate() {
		// Drop themselves.
		dropSelf(ModBlocks.CRUSHER_BLOCK.get());
		dropSelf(ModBlocks.COMPRESSOR_BLOCK.get());
	}
}
