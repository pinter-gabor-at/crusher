package eu.pintergabor.crusher.util;

import static eu.pintergabor.crusher.blocks.base.AbstractProcessingBlock.FACING;
import static eu.pintergabor.crusher.blocks.base.AbstractProcessingBlock.LIT;

import eu.pintergabor.crusher.blocks.CompressorBlock;
import eu.pintergabor.crusher.blocks.CrusherBlock;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BlastFurnaceBlock;
import net.minecraft.world.level.block.state.BlockState;


public final class BlockUtil {

	private BlockUtil() {
		// Static class.
	}

	/**
	 * Create sound and particles when the
	 * {@link CrusherBlock} or the {@link CompressorBlock} is operating.
	 * <p>
	 * Based on {@link BlastFurnaceBlock#animateTick(BlockState, Level, BlockPos, RandomSource)}.
	 */
	public static void randomBlockTick(
		final @NonNull BlockState state,
		final @NonNull Level level,
		final @NonNull BlockPos pos,
		final @NonNull RandomSource random
	) {
		if (state.getValueOrElse(LIT, false)) {
			// Sound comes from the center-bottom of the block.
			final double x = (double) pos.getX() + 0.5;
			final double y = pos.getY();
			final double z = (double) pos.getZ() + 0.5;
			if (random.nextDouble() < 0.2) {
				level.playSound(null,
					x, y, z,
					SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.BLOCKS,
					1F, 1F
				);
			}
			// Particles come from the front of the block.
			final Direction direction = state.getValue(FACING);
			final Direction.Axis axis = direction.getAxis();
			final double distance = 0.6;
			final double spread = random.nextDouble() * 0.6 - 0.3;
			final double offsetx = axis == Direction.Axis.X ?
				(double) direction.getStepX() * distance : spread;
			final double offsety = random.nextDouble() * 0.6;
			final double offsetz = axis == Direction.Axis.Z ?
				(double) direction.getStepZ() * distance : spread;
			level.addParticle(ParticleTypes.DUST_PLUME,
				x + offsetx, y + offsety, z + offsetz,
				0.0, 0.05, 0.0
			);
		}
	}
}
