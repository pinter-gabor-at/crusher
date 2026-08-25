package eu.pintergabor.crusher.screen.base;

import eu.pintergabor.crusher.blocks.base.AbstractProcessingBlockEntity;
import org.jspecify.annotations.NonNull;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;


/**
 * Similar to {@link FurnaceResultSlot}.
 */
public class ProcessingResultSlot extends Slot {
	private final Player player;
	private int removeCount;

	public ProcessingResultSlot(
		final @NonNull Player player,
		final @NonNull Container container,
		final int slot, final int x, final int y
	) {
		super(container, slot, x, y);
		this.player = player;
	}

	@Override
	public boolean mayPlace(final @NonNull ItemStack stack) {
		return false;
	}

	@Override
	public @NonNull ItemStack remove(int amount) {
		if (hasItem()) {
			amount += Math.min(amount, getItem().getCount());
		}
		return super.remove(amount);
	}

	@Override
	public void onTake(final @NonNull Player player, final @NonNull ItemStack stack) {
		checkTakeAchievements(stack);
		super.onTake(player, stack);
	}

	protected void onQuickCraft(final @NonNull ItemStack stack, final int amount) {
		removeCount += amount;
		checkTakeAchievements(stack);
	}

	@Override
	protected void checkTakeAchievements(final @NonNull ItemStack stack) {
		stack.onCraftedBy(player, removeCount);
		if (player instanceof ServerPlayer serverPlayer &&
			container instanceof AbstractProcessingBlockEntity processor) {
			processor.awardUsedRecipesAndPopExperience(serverPlayer);
		}
		removeCount = 0;
	}
}
