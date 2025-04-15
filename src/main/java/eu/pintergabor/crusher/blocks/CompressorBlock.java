package eu.pintergabor.crusher.blocks;

import com.mojang.serialization.MapCodec;
import eu.pintergabor.crusher.blocks.base.AbstractProcessingBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
	public @NotNull MapCodec<CompressorBlock> codec() {
		return CODEC;
	}

	public CompressorBlock(Properties props) {
		super(props);
	}

	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		return new CompressorBlockEntity(pos, state);
	}

	@Override
	public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(
		@NotNull Level world,
		@NotNull BlockState state,
		@NotNull BlockEntityType<T> type) {
		return createModTicker(world, type, ModBlocks.COMPRESSOR_ENTITY.get());
	}

	@Override
	protected void openContainer(
		@NotNull Level world,
		@NotNull BlockPos pos,
		@NotNull Player player) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity instanceof CompressorBlockEntity processor) {
			player.openMenu(processor);
			// Increment statistics.
			player.awardStat(ModStats.COMPRESSOR_STAT.get());
		}
	}
}
