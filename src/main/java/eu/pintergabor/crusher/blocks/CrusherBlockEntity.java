package eu.pintergabor.crusher.blocks;

import eu.pintergabor.crusher.blocks.base.AbstractProcessingBlockEntity;
import eu.pintergabor.crusher.recipe.CrusherRecipe;
import eu.pintergabor.crusher.screen.CrusherMenu;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;


/**
 * See {@link CrusherBlock}.
 */
public class CrusherBlockEntity extends AbstractProcessingBlockEntity {

	public CrusherBlockEntity(final @NonNull BlockPos pos, BlockState state) {
		super(ModBlocks.CRUSHER_ENTITY, pos, state, CrusherRecipe.TYPE);
	}

	@Override
	protected @NonNull Component getDefaultName() {
		return Component.translatable("block.crusher.crusher");
	}

	@Override
	protected @NonNull AbstractContainerMenu createMenu(
		final int containerId,
		final @NonNull Inventory playerInventory
	) {
		return new CrusherMenu(containerId, playerInventory, this, dataAccess);
	}
}
