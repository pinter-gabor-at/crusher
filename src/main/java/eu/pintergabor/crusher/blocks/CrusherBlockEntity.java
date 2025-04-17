package eu.pintergabor.crusher.blocks;

import eu.pintergabor.crusher.blocks.base.AbstractProcessingBlockEntity;
import eu.pintergabor.crusher.recipe.CrusherRecipe;
import eu.pintergabor.crusher.screen.CrusherMenu;
import org.jetbrains.annotations.NotNull;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;


/**
 * See {@link CrusherBlock}.
 */
public class CrusherBlockEntity extends AbstractProcessingBlockEntity {

	public CrusherBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlocks.CRUSHER_ENTITY, pos, state, CrusherRecipe.TYPE);
	}

	@Override
	protected @NotNull Component getDefaultName() {
		return Component.translatable("block.crusher.crusher");
	}

	@Override
	protected @NotNull AbstractContainerMenu createMenu(
		int containerId, @NotNull Inventory playerInventory
	) {
		return new CrusherMenu(containerId, playerInventory, this, dataAccess);
	}
}
