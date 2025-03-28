package eu.pintergabor.crusher.blocks;

import com.mojang.serialization.MapCodec;
import eu.pintergabor.crusher.blocks.base.AbstractProcessingBlock;
import eu.pintergabor.crusher.util.BlockUtil;

import net.minecraft.block.BlastFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * A machine, similar to a furnace, but for crushing.
 */
public class CrusherBlock extends AbstractProcessingBlock {
    public static final MapCodec<CrusherBlock> CODEC = createCodec(CrusherBlock::new);

    @Override
    public MapCodec<CrusherBlock> getCodec() {
        return CODEC;
    }

    public CrusherBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof CrusherBlockEntity) {
            player.openHandledScreen(((NamedScreenHandlerFactory) blockEntity));
            // TODO: Create and register stats
            // player.incrementStat(Stats.INTERACT_WITH_CRUSHER);
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CrusherBlockEntity(pos, state);
    }

    /**
     * Based on {@link BlastFurnaceBlock#randomDisplayTick(BlockState, World, BlockPos, Random)}
     */
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        BlockUtil.randomBlockTick(state, world, pos, random);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            World world, BlockState state, BlockEntityType<T> type) {
        return validateModTicker(world, type, ModBlocks.CRUSHER_ENTITY);
    }
}
