package eu.pintergabor.crusher.blocks;

import eu.pintergabor.crusher.blocks.base.AbstractProcessingBlockEntity;
import eu.pintergabor.crusher.recipe.CompressorRecipe;
import eu.pintergabor.crusher.screen.CompressorScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

/**
 * See {@link CompressorBlock}
 */
public class CompressorBlockEntity extends AbstractProcessingBlockEntity {
    public CompressorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.COMPRESSOR_ENTITY, pos, state, CompressorRecipe.TYPE);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("block.crusher.compressor");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new CompressorScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }
}