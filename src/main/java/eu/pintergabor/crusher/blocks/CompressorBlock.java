package eu.pintergabor.crusher.blocks;

import com.mojang.serialization.MapCodec;
import eu.pintergabor.crusher.blocks.base.AbstractProcessingBlock;
import eu.pintergabor.crusher.util.BlockUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new CompressorBlockEntity(pos, state);
	}

	@Override
	public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(
		Level world, BlockState state, BlockEntityType<T> type) {
		return createModTicker(world, type, ModBlocks.COMPRESSOR_ENTITY);
	}

	@Override
	protected void openContainer(Level world, BlockPos pos, Player player) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity instanceof CompressorBlockEntity processor) {
			player.openMenu(processor);
			// Increment statistics.
			player.awardStat(ModStats.COMPRESSOR_STAT);
		}
	}
}
