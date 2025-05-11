package eu.pintergabor.crusher.datagen;

import eu.pintergabor.crusher.blocks.ModBlocks;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.TexturedModel;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;


public final class ModModelProvider extends FabricModelProvider {

	public ModModelProvider(FabricDataOutput output) {
		super(output);
	}

	/**
	 * Generate block models and block states.
	 */
	@Override
	public void generateBlockStateModels(BlockModelGenerators blockModelGenerator) {
		blockModelGenerator.createFurnace(ModBlocks.CRUSHER_BLOCK, TexturedModel.ORIENTABLE_ONLY_TOP);
		blockModelGenerator.createFurnace(ModBlocks.COMPRESSOR_BLOCK, TexturedModel.ORIENTABLE_ONLY_TOP);
	}

	/**
	 * Generate item models.
	 */
	@Override
	public void generateItemModels(ItemModelGenerators itemModelGenerator) {
		// All item models come from the corresponding block model.
	}
}
