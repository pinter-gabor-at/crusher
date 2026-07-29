package eu.pintergabor.crusher.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.crusher.blocks.ModBlocks;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;


public final class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {

	public ModBlockTagProvider(
		final @NonNull FabricPackOutput output,
		final @NonNull CompletableFuture<HolderLookup.Provider> registriesFuture
	) {
		super(output, registriesFuture);
	}

	@Override
	protected void addTags(final HolderLookup.@NonNull Provider registries) {
		// Mineable with pickaxe.
		tag(BlockTags.MINEABLE_WITH_PICKAXE)
			.add(ModBlocks.CRUSHER_BLOCK_ID.block())
			.add(ModBlocks.COMPRESSOR_BLOCK_ID.block());
	}
}
