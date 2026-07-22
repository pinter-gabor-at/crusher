package eu.pintergabor.crusher.blocks.base;

import eu.pintergabor.crusher.util.BlockUtil;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.BlastFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;


/**
 * Based on {@link AbstractFurnaceBlock} with a different entity.
 */
public abstract class AbstractProcessingBlock extends AbstractFurnaceBlock {

	protected AbstractProcessingBlock(Properties props) {
		super(props);
	}

	/**
	 * Almost the same as {@link AbstractFurnaceBlock#createFurnaceTicker}, but with a different entity.
	 */
	protected static @Nullable <T extends BlockEntity> BlockEntityTicker<T>
	createModTicker(
		final @NonNull Level level,
		final @NonNull BlockEntityType<T> givenType,
		final @NonNull BlockEntityType<? extends AbstractProcessingBlockEntity> expectedType
	) {
		return level instanceof ServerLevel serverLevel
			? createTickerHelper(givenType, expectedType,
			(_, pos, state,
			 blockEntity) ->
				StaticProcessingBlockEntity.serverTick(
					serverLevel, pos, state, blockEntity))
			: null;
	}

	/**
	 * Based on {@link BlastFurnaceBlock#animateTick(BlockState, Level, BlockPos, RandomSource)}.
	 */
	@Override
	public void animateTick(
		final @NonNull BlockState state,
		final @NonNull Level world,
		final @NonNull BlockPos pos,
		final @NonNull RandomSource random
	) {
		BlockUtil.randomBlockTick(state, world, pos, random);
	}
}
