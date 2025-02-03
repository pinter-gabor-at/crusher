package eu.pintergabor.crusher.blocks;

import com.mojang.serialization.MapCodec;
import eu.pintergabor.crusher.blocks.base.AbstractProcessingBlock;
import net.minecraft.block.BlastFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * A machine, similar to a furnace, but for compressing
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
            // TODO: Create and register stats
            // player.incrementStat(Stats.INTERACT_WITH_COMPRESSOR);
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
        if (state.get(LIT)) {
            // Sound comes from the center-bottom of the block.
            double x = (double) pos.getX() + 0.5;
            double y = pos.getY();
            double z = (double) pos.getZ() + 0.5;
            if (random.nextDouble() < 0.2) {
                world.playSound(
                        x, y, z,
                        SoundEvents.BLOCK_BLASTFURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS,
                        1.0f, 1.0f, false);
            }
            // Particles come from the front of the block
            Direction direction = state.get(FACING);
            Direction.Axis axis = direction.getAxis();
            double distance = 0.6;
            double spread = random.nextDouble() * 0.6 - 0.3;
            double offsetx = axis == Direction.Axis.X ?
                    (double) direction.getOffsetX() * distance : spread;
            double offsety = random.nextDouble() * 0.6;
            double offsetz = axis == Direction.Axis.Z ?
                    (double) direction.getOffsetZ() * distance : spread;
            world.addParticle(ParticleTypes.DUST_PLUME,
                    x + offsetx, y + offsety, z + offsetz,
                    0.0, 0.05, 0.0);
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(world, type, ModBlocks.COMPRESSOR_ENTITY);
    }
}