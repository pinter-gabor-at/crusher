package eu.pintergabor.crusher.blocks;

import eu.pintergabor.crusher.blocks.base.AbstractProcessingBlockEntity;
import eu.pintergabor.crusher.recipe.CompressorRecipe;
import eu.pintergabor.crusher.screen.CompressorScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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

    /**
     * Create special effect when TNT is crafted.
     */
    @Override
    protected void crafted() {
        // If the crafted result is TNT then create twice as large explosion as a TNT block.
        if (world != null && inventory.get(OUTPUT_SLOT_INDEX).isOf(Items.TNT)) {
            inventory.set(OUTPUT_SLOT_INDEX, ItemStack.EMPTY);
            world.createExplosion(
                    null,
                    null,
                    null,
                    pos.getX(),
                    pos.getY(),
                    pos.getZ(),
                    8.0f,
                    false,
                    World.ExplosionSourceType.TNT);
        }
    }
}