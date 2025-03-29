package eu.pintergabor.crusher.util;

import static eu.pintergabor.crusher.blocks.base.AbstractProcessingBlock.FACING;
import static eu.pintergabor.crusher.blocks.base.AbstractProcessingBlock.LIT;

import eu.pintergabor.crusher.blocks.CompressorBlock;
import eu.pintergabor.crusher.blocks.CrusherBlock;

import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;


public class BlockUtil {

    private BlockUtil() {
        // Static class.
    }

    /**
     * Create sound and particles when the
     * {@link CrusherBlock} or the {@link CompressorBlock} is operating.
     */
    public static void randomBlockTick(
        BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIT, false)) {
            // Sound comes from the center-bottom of the block.
            double x = (double) pos.getX() + 0.5;
            double y = pos.getY();
            double z = (double) pos.getZ() + 0.5;
            if (random.nextDouble() < 0.2) {
                world.playSound(null,
                    x, y, z,
                    SoundEvents.BLOCK_BLASTFURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS,
                    1.0F, 1.0F);
            }
            // Particles come from the front of the block.
            Direction direction = state.get(FACING);
            Direction.Axis axis = direction.getAxis();
            double distance = 0.6;
            double spread = random.nextDouble() * 0.6 - 0.3;
            double offsetx = axis == Direction.Axis.X ?
                (double) direction.getOffsetX() * distance : spread;
            double offsety = random.nextDouble() * 0.6;
            double offsetz = axis == Direction.Axis.Z ?
                (double) direction.getOffsetZ() * distance : spread;
            world.addParticleClient(ParticleTypes.DUST_PLUME,
                x + offsetx, y + offsety, z + offsetz,
                0.0, 0.05, 0.0);
        }
    }
}
