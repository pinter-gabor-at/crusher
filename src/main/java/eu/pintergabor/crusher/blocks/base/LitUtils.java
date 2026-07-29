package eu.pintergabor.crusher.blocks.base;

import static eu.pintergabor.crusher.blocks.base.AbstractProcessingBlockEntity.SLOT_FUEL;

import org.jspecify.annotations.NonNull;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;


/**
 * Lit state related static methods of {@link AbstractProcessingBlockEntity}.
 */
public final class LitUtils {

	private LitUtils() {
		// Static class.
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	public static boolean isLit(final @NonNull AbstractProcessingBlockEntity processor) {
		return 0 < processor.litTimeRemaining;
	}

	/**
	 * Count down the lit timer.
	 * <p>
	 * Called from {@link AbstractProcessingBlockEntity#serverTick(ServerLevel, BlockPos, BlockState, AbstractProcessingBlockEntity)}
	 *
	 * @return the lit state <b>before</b> the tick.
	 */
	public static boolean tickLit(final @NonNull AbstractProcessingBlockEntity processor) {
		final boolean isLit = isLit(processor);
		if (isLit) {
			processor.litTimeRemaining--;
		}
		return isLit;
	}

	/**
	 * Refuel.
	 * <p>
	 * Consume one fuel item, reload the lit counter and
	 * set the remainder after the last fuel item is consumed.
	 *
	 * @param fuelValues {@code processor.level.fuelValues()}
	 * @param fuelStack  {@code processor.items.get(SLOT_FUEL)}
	 */
	private static void consumeFuel(
		final @NonNull AbstractProcessingBlockEntity processor,
		final @NonNull FuelValues fuelValues,
		final @NonNull ItemStack fuelStack
	) {
		// Reload the lit counter.
		// If the fuel is usable, this makes isLit() return true.
		processor.litTimeRemaining = processor.getBurnDuration(fuelValues, fuelStack);
		processor.litTotalTime = processor.litTimeRemaining;
		if (isLit(processor)) {
			// If the fuel is usable, consume one fuel item.
			fuelStack.shrink(1);
			if (fuelStack.isEmpty()) {
				// If the fuelstack has become empty after consuming
				// the last fuel item, set the remainder.
				final ItemStackTemplate remainderStackTemplate =
					fuelStack.getCraftingRemainder();
				final ItemStack remainderStack = remainderStackTemplate == null ?
					ItemStack.EMPTY :
					remainderStackTemplate.create();
				processor.items.set(SLOT_FUEL, remainderStack);
			}
		}
	}

	/**
	 * Refuel if needed.
	 * <p>
	 * If there is usable fuel in the fuel slot and
	 * the previous fuel item has completely burned down,
	 * consume one fuel item, reload the lit counter and
	 * set the remainder after the last fuel item is consumed.
	 *
	 * @return true if there was a change in the lit state.
	 */
	public static boolean refuel(
		final @NonNull ServerLevel level,
		final @NonNull AbstractProcessingBlockEntity processor
	) {
		final boolean isLit = isLit(processor);
		if (!isLit) {
			// If the previuos fuel item has completely burned down.
			ItemStack fuelStack = processor.items.get(SLOT_FUEL);
			if (!fuelStack.isEmpty()) {
				// Consume one fuel item, reload the lit counter and
				// set the remainder after the last fuel item is consumed.
				FuelValues fuelValues = level.fuelValues();
				consumeFuel(processor, fuelValues, fuelStack);
			}
		}
		// Return true if there was a change.
		return isLit != isLit(processor);
	}
}
