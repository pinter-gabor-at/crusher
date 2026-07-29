package eu.pintergabor.crusher.blocks.base;

import static eu.pintergabor.crusher.blocks.base.LitUtils.isLit;
import static eu.pintergabor.crusher.blocks.base.LitUtils.tickLit;
import static eu.pintergabor.crusher.blocks.base.ProcessingUtils.tickProcessing;

import org.jspecify.annotations.NonNull;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;


/**
 * Static methods of {@link AbstractProcessingBlockEntity}.
 */
public abstract sealed class StaticProcessingBlockEntity
	extends BaseContainerBlockEntity
	permits AbstractProcessingBlockEntity {

	public static final int SLOT_INPUT = 0;
	public static final int SLOT_FUEL = 1;
	public static final int SLOT_RESULT = 2;
	public static final int DATA_LIT_TIME = 0;
	public static final int DATA_LIT_DURATION = 1;
	public static final int DATA_PROGRESS = 2;
	public static final int DATA_TOTAL_TIME = 3;
	public static final int NUM_DATA_VALUES = 4;
	public static final int DEFAULT_PROCESS_TIME = 200;

	protected StaticProcessingBlockEntity(
		final @NonNull BlockEntityType<?> blockEntityType,
		final @NonNull BlockPos blockPos,
		final @NonNull BlockState blockState
	) {
		super(blockEntityType, blockPos, blockState);
	}

	/**
	 * Similar to {@link AbstractFurnaceBlockEntity}, but allows multiple input and output counts.
	 */
	public static void serverTick(
		final @NonNull ServerLevel level,
		final @NonNull BlockPos pos,
		@NonNull BlockState state,
		final @NonNull AbstractProcessingBlockEntity processor
	) {
		// Tick the lit timer, and remember if the processor was lit before this tick.
		final boolean wasLit = tickLit(processor);
		// Set to true, if anything changes during this tick.
		boolean changed = tickProcessing(level, processor);
		// Burning state changed.
		final boolean isLit = isLit(processor);
		if (wasLit != isLit) {
			changed = true;
			state = state.setValue(AbstractFurnaceBlock.LIT, isLit);
			level.setBlock(pos, state, Block.UPDATE_ALL);
		}
		// Something changed -> redraw.
		if (changed) {
			setChanged(level, pos, state);
		}
	}
}
