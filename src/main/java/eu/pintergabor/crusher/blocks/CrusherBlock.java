package eu.pintergabor.crusher.blocks;

import com.mojang.serialization.MapCodec;
import eu.pintergabor.crusher.blocks.base.AbstractProcessingBlock;
import eu.pintergabor.crusher.util.BlockUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BlastFurnaceBlock;
import net.minecraft.world.level.block.FurnaceBlock;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


/**
 * A machine, similar to a {@link FurnaceBlock} or a {@link BlastFurnaceBlock},
 * but for crushing.
 */
public class CrusherBlock extends AbstractProcessingBlock {
	public static final MapCodec<CrusherBlock> CODEC = simpleCodec(CrusherBlock::new);

	@Override
	public @NotNull MapCodec<CrusherBlock> codec() {
		return CODEC;
	}

	public CrusherBlock(Properties props) {
		super(props);
	}

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CrusherBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(
        Level world, BlockState state, BlockEntityType<T> type) {
        return createModTicker(world, type, ModBlocks.CRUSHER_ENTITY);
    }

	@Override
    protected void openContainer(Level world, BlockPos pos, Player player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity instanceof CrusherBlockEntity) {
            player.openMenu((MenuProvider)blockEntity);
            // Increment statistics.
            player.awardStat(ModStats.CRUSHER_STAT);
		}
	}

	/**
	 * Based on {@link BlastFurnaceBlock#animateTick(BlockState, Level, BlockPos, RandomSource)}.
	 */
    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        BlockUtil.randomBlockTick(state, world, pos, random);
    }
}
