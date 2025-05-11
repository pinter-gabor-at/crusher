package eu.pintergabor.crusher.datagen;

import eu.pintergabor.crusher.Global;
import eu.pintergabor.crusher.blocks.ModBlocks;
import org.jetbrains.annotations.NotNull;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.data.PackOutput;


public class ModModelProvider extends ModelProvider {

	public ModModelProvider(PackOutput output) {
		super(output, Global.MODID);
	}

	/**
	 * Generate blockstates, block and item models.
	 */
	@Override
	protected void registerModels(
		@NotNull BlockModelGenerators blockModels,
		@NotNull ItemModelGenerators itemModels
	) {
		blockModels.createFurnace(ModBlocks.CRUSHER_BLOCK.get(),
			TexturedModel.ORIENTABLE_ONLY_TOP);
		blockModels.createFurnace(ModBlocks.COMPRESSOR_BLOCK.get(),
			TexturedModel.ORIENTABLE_ONLY_TOP);
	}
}
