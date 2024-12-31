package eu.pintergabor.crusher.blocks.base;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractProcessingBlock extends BlockWithEntity {
    public static final EnumProperty<Direction> FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty LIT = Properties.LIT;

    protected AbstractProcessingBlock(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(LIT, Boolean.FALSE));
    }

    @Override
    protected abstract MapCodec<? extends AbstractProcessingBlock> getCodec();

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            openScreen(world, pos, player);
        }
        return ActionResult.SUCCESS;
    }

    protected abstract void openScreen(World world, BlockPos pos, PlayerEntity player);

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            if (world.getBlockEntity(pos) instanceof AbstractProcessingBlockEntity blockEntity) {
                if (world instanceof ServerWorld serverWorld) {
                    ItemScatterer.spawn(serverWorld, pos, blockEntity);
                    blockEntity.getRecipesUsedAndDropExperience(serverWorld, Vec3d.ofCenter(pos));
                }
                super.onStateReplaced(state, world, pos, newState, moved);
                world.updateComparators(pos, this);
            } else {
                super.onStateReplaced(state, world, pos, newState, moved);
            }
        }
    }

    @Override
    protected boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> validateTicker(
            World world, BlockEntityType<T> givenType, BlockEntityType<? extends AbstractProcessingBlockEntity> expectedType
    ) {
        return world instanceof ServerWorld serverWorld
                ? validateTicker(givenType, expectedType,
                (worldx, pos, state, blockEntity) ->
                        AbstractProcessingBlockEntity.tick(serverWorld, pos, state, blockEntity))
                : null;
    }
}
