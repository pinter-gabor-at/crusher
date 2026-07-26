package eu.pintergabor.crusher.blocks;

import eu.pintergabor.crusher.blocks.base.AbstractProcessingBlockEntity;
import eu.pintergabor.crusher.recipe.CompressorRecipe;
import eu.pintergabor.crusher.screen.CompressorMenu;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;


/**
 * See {@link CompressorBlock}.
 */
public class CompressorBlockEntity extends AbstractProcessingBlockEntity {

	public CompressorBlockEntity(
		final @NonNull BlockPos pos,
		final @NonNull BlockState state
	) {
		super(ModBlocks.COMPRESSOR_ENTITY.get(), pos, state, CompressorRecipe.TYPE.get());
	}

	@Override
	protected @NonNull Component getDefaultName() {
		return Component.translatable("block.crusher.compressor");
	}

	@Override
	protected @NonNull AbstractContainerMenu createMenu(
		final int containerId,
		final @NonNull Inventory playerInventory
	) {
		return new CompressorMenu(containerId, playerInventory, this, dataAccess);
	}

	/**
	 * Create special effects when TNT is crafted.
	 */
	@Override
	protected void crafted() {
		if (level != null && items.get(SLOT_RESULT).is(Items.TNT)) {
			// If the crafted result is TNT then create twice as large explosion as a TNT block.
			items.set(SLOT_RESULT, ItemStack.EMPTY);
			level.explode(
				null,
				null,
				null,
				worldPosition.getX(),
				worldPosition.getY(),
				worldPosition.getZ(),
				8F,
				false,
				Level.ExplosionInteraction.TNT);
		}
	}
}
