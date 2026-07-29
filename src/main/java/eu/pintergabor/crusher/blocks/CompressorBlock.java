package eu.pintergabor.crusher.blocks;

import com.mojang.serialization.MapCodec;
import eu.pintergabor.crusher.blocks.base.AbstractProcessingBlock;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BlastFurnaceBlock;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;


/**
 * A machine, similar to a {@link FurnaceBlock} or a {@link BlastFurnaceBlock},
 * but for compressing.
 */
public class CompressorBlock extends AbstractProcessingBlock {
	public static final MapCodec<CompressorBlock> CODEC = simpleCodec(CompressorBlock::new);

	@Override
	public @NonNull MapCodec<CompressorBlock> codec() {
		return CODEC;
	}

	public CompressorBlock(Properties props) {
		super(props);
	}

	@Override
	public BlockEntity newBlockEntity(
		final @NonNull BlockPos pos,
		final @NonNull BlockState state
	) {
		return new CompressorBlockEntity(pos, state);
	}

	@Override
	public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(
		final @NonNull Level level,
		final @NonNull BlockState state,
		final @NonNull BlockEntityType<T> type
	) {
		return createModTicker(level, type, ModBlocks.COMPRESSOR_ENTITY);
	}

	@Override
	protected void openContainer(
		final @NonNull Level level,
		final @NonNull BlockPos pos,
		final @NonNull Player player
	) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (blockEntity instanceof CompressorBlockEntity processor) {
			player.openMenu(processor);
			// Increment statistics.
			player.awardStat(ModStats.COMPRESSOR_STAT);
		}
	}
}
