package eu.pintergabor.crusher.datagen;

import eu.pintergabor.crusher.blocks.ModBlocks;

import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.TexturedModel;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;


public class ModModelProvider extends FabricModelProvider {
	public ModModelProvider(FabricDataOutput output) {
		super(output);
	}

	/**
	 * Generate block models and block states.
	 */
	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		blockStateModelGenerator.registerCooker(ModBlocks.CRUSHER_BLOCK, TexturedModel.ORIENTABLE);
		blockStateModelGenerator.registerCooker(ModBlocks.COMPRESSOR_BLOCK, TexturedModel.ORIENTABLE);
	}

	/**
	 * Generate item models.
	 */
	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		// All item models come from the corresponding block model.
	}
}
