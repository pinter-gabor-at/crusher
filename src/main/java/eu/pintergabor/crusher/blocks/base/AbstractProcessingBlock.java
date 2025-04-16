package eu.pintergabor.crusher.blocks.base;

import eu.pintergabor.crusher.util.BlockUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.BlastFurnaceBlock;
import net.minecraft.world.level.block.state.BlockState;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;


/**
 * Same as {@link AbstractFurnaceBlock}, but with a different entity.
 */
public abstract class AbstractProcessingBlock extends AbstractFurnaceBlock {

	protected AbstractProcessingBlock(Properties props) {
		super(props);
	}

	/**
	 * Almost the same as {@link AbstractFurnaceBlock#createFurnaceTicker}.
	 */
	protected static @Nullable <T extends BlockEntity> BlockEntityTicker<T>
	createModTicker(
		Level level, BlockEntityType<T> givenType,
		BlockEntityType<? extends AbstractProcessingBlockEntity> expectedType
	) {
		return level instanceof ServerLevel serverLevel
			? createTickerHelper(givenType, expectedType,
			(worldx, pos, state,
			 blockEntity) ->
				AbstractProcessingBlockEntity.serverTick(
					serverLevel, pos, state, blockEntity))
			: null;
	}

	/**
	 * Based on {@link BlastFurnaceBlock#animateTick(BlockState, Level, BlockPos, RandomSource)}.
	 */
	@Override
	public void animateTick(
		@NotNull BlockState state,
		@NotNull Level world,
		@NotNull BlockPos pos,
		@NotNull RandomSource random) {
		BlockUtil.randomBlockTick(state, world, pos, random);
	}
}
