package eu.pintergabor.crusher.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.crusher.Global;
import eu.pintergabor.crusher.blocks.ModBlocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;

import org.jetbrains.annotations.NotNull;


public class ModBlockTagProvider extends BlockTagsProvider {

	public ModBlockTagProvider(
		PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, lookupProvider, Global.MODID);
	}

	@Override
	protected void addTags(@NotNull HolderLookup.Provider wrapperLookup) {
		// Mineable with pickaxe.
		tag(BlockTags.MINEABLE_WITH_PICKAXE)
			.add(ModBlocks.CRUSHER_BLOCK.get())
			.add(ModBlocks.COMPRESSOR_BLOCK.get());
	}
}
