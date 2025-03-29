package eu.pintergabor.crusher.blocks;

import com.mojang.serialization.MapCodec;
import eu.pintergabor.crusher.blocks.base.AbstractProcessingBlock;
import eu.pintergabor.crusher.util.BlockUtil;
import org.jetbrains.annotations.Nullable;

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


/**
 * A machine, similar to a furnace, but for compressing.
 */
public class CompressorBlock extends AbstractProcessingBlock {
	public static final MapCodec<CompressorBlock> CODEC = createCodec(CompressorBlock::new);

	@Override
	public MapCodec<CompressorBlock> getCodec() {
		return CODEC;
	}

	public CompressorBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity instanceof CompressorBlockEntity) {
			player.openHandledScreen(((NamedScreenHandlerFactory) blockEntity));
			// Increment statistics.
			player.incrementStat(ModStats.COMPRESSOR_STAT);
		}
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new CompressorBlockEntity(pos, state);
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
		return validateModTicker(world, type, ModBlocks.COMPRESSOR_ENTITY);
	}
}
