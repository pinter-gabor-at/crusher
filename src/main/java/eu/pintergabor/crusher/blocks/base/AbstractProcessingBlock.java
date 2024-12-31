package eu.pintergabor.crusher.blocks.base;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractProcessingBlock extends AbstractFurnaceBlock {
    protected AbstractProcessingBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> validateTicker(
            World world, BlockEntityType<T> givenType, BlockEntityType<? extends AbstractFurnaceBlockEntity> expectedType
    ) {
        return world instanceof ServerWorld serverWorld
                ? validateTicker(givenType, expectedType,
                (worldx, pos, state, blockEntity) ->
                        AbstractProcessingBlockEntity.tick(serverWorld, pos, state, blockEntity))
                : null;
    }
}
