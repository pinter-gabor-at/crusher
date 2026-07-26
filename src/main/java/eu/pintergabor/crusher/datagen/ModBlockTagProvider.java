package eu.pintergabor.crusher.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.crusher.Global;
import eu.pintergabor.crusher.blocks.ModBlocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;


public final class ModBlockTagProvider extends BlockTagsProvider {

	public ModBlockTagProvider(
		PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, lookupProvider, Global.MODID);
	}

	@Override
	protected void addTags(HolderLookup.@NonNull Provider wrapperLookup) {
		// Mineable with pickaxe.
		tag(BlockTags.MINEABLE_WITH_PICKAXE)
			.add(ModBlocks.CRUSHER_BLOCK.getKey())
			.add(ModBlocks.COMPRESSOR_BLOCK.getKey());
	}
}
