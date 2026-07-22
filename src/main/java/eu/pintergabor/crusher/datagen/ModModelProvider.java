package eu.pintergabor.crusher.datagen;

import eu.pintergabor.crusher.blocks.ModBlocks;
import org.jspecify.annotations.NonNull;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.TexturedModel;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;


public final class ModModelProvider extends FabricModelProvider {

	public ModModelProvider(final @NonNull FabricPackOutput output) {
		super(output);
	}


	/**
	 * Generate block models and block states.
	 */
	@Override
	public void generateBlockStateModels(final @NonNull BlockModelGenerators generators) {
		generators.createFurnace(ModBlocks.CRUSHER_BLOCK, TexturedModel.ORIENTABLE_ONLY_TOP);
		generators.createFurnace(ModBlocks.COMPRESSOR_BLOCK, TexturedModel.ORIENTABLE_ONLY_TOP);
	}

	/**
	 * Generate item models.
	 */
	@Override
	public void generateItemModels(final @NonNull ItemModelGenerators generators) {
		// All item models come from the corresponding block model.
	}
}
