package eu.pintergabor.crusher.blocks;

import eu.pintergabor.crusher.blocks.base.AbstractProcessingBlockEntity;
import eu.pintergabor.crusher.recipe.CompressorRecipe;
import eu.pintergabor.crusher.screen.CompressorMenu;
import org.jetbrains.annotations.NotNull;

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
	public CompressorBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlocks.COMPRESSOR_ENTITY, pos, state, CompressorRecipe.TYPE);
	}

	@Override
	protected @NotNull Component getDefaultName() {
		return Component.translatable("block.crusher.compressor");
	}

	@Override
	protected @NotNull AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
		return new CompressorMenu(containerId, playerInventory, this, propertyDelegate);
	}

	/**
	 * Create special effects when TNT is crafted.
	 */
	@Override
	protected void crafted() {
		// If the crafted result is TNT then create twice as large explosion as a TNT block.
		if (level != null && inventory.get(OUTPUT_SLOT_INDEX).is(Items.TNT)) {
			inventory.set(OUTPUT_SLOT_INDEX, ItemStack.EMPTY);
			level.explode(
				null,
				null,
				null,
				worldPosition.getX(),
				worldPosition.getY(),
				worldPosition.getZ(),
				8.0F,
				false,
				Level.ExplosionInteraction.TNT);
		}
	}
}
