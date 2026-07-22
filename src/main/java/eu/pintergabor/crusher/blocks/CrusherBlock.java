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
 * but for crushing.
 */
public class CrusherBlock extends AbstractProcessingBlock {
	public static final MapCodec<CrusherBlock> CODEC = simpleCodec(CrusherBlock::new);

	@Override
	public @NonNull MapCodec<CrusherBlock> codec() {
		return CODEC;
	}

	public CrusherBlock(Properties props) {
		super(props);
	}

	@Override
	public BlockEntity newBlockEntity(
		final @NonNull BlockPos pos,
		final @NonNull BlockState state
	) {
		return new CrusherBlockEntity(pos, state);
	}

	@Override
	public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(
		final @NonNull Level level,
		final @NonNull BlockState state,
		final @NonNull BlockEntityType<T> type
	) {
		return createModTicker(level, type, ModBlocks.CRUSHER_ENTITY);
	}

	@Override
	protected void openContainer(
		final @NonNull Level level,
		final @NonNull BlockPos pos,
		final @NonNull Player player
	) {
		final BlockEntity blockEntity = level.getBlockEntity(pos);
		if (blockEntity instanceof CrusherBlockEntity processor) {
			player.openMenu(processor);
			// Increment statistics.
			player.awardStat(ModStats.CRUSHER_STAT);
		}
	}
}
