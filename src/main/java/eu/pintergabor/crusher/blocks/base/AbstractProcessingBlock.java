package eu.pintergabor.crusher.blocks.base;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;


/**
 * Same as {@link AbstractFurnaceBlock}, but with a different entity.
 */
public abstract class AbstractProcessingBlock extends AbstractFurnaceBlock {
	protected AbstractProcessingBlock(Settings settings) {
		super(settings);
	}

	/**
	 * Almost the same as {@link AbstractFurnaceBlock#validateTicker}.
	 */
	@Nullable
	protected static <T extends BlockEntity> BlockEntityTicker<T> validateModTicker(
		World world, BlockEntityType<T> givenType,
		BlockEntityType<? extends AbstractProcessingBlockEntity> expectedType
	) {
		return world instanceof ServerWorld serverWorld
			? validateTicker(givenType, expectedType,
			(worldx, pos, state,
			 blockEntity) ->
				AbstractProcessingBlockEntity.tick(
					serverWorld, pos, state, blockEntity))
			: null;
	}
}
