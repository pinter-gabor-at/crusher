package eu.pintergabor.crusher.blocks;

import eu.pintergabor.crusher.blocks.base.AbstractProcessingBlockEntity;
import eu.pintergabor.crusher.recipe.CrusherRecipe;
import eu.pintergabor.crusher.screen.CrusherScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

/**
 * See {@link CrusherBlock}
 */
public class CrusherBlockEntity extends AbstractProcessingBlockEntity {
    public CrusherBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.CRUSHER_ENTITY, pos, state, CrusherRecipe.CRUSHER_TYPE);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("block.crusher.crusher");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new CrusherScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }
}