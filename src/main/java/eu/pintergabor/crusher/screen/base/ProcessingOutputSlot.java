package eu.pintergabor.crusher.screen.base;

import eu.pintergabor.crusher.blocks.base.AbstractProcessingBlockEntity;
import org.jetbrains.annotations.NotNull;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;


/**
 * Similar to {@link FurnaceResultSlot}.
 */
public class ProcessingOutputSlot extends Slot {
	private final Player player;
	private int removeCount;

	public ProcessingOutputSlot(
		Player player, Container container,
		int slot, int x, int y) {
		super(container, slot, x, y);
		this.player = player;
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return false;
	}

	@Override
	public @NotNull ItemStack remove(int amount) {
		if (hasItem()) {
			amount += Math.min(amount, getItem().getCount());
		}
		return super.remove(amount);
	}

	@Override
	public void onTake(Player player, ItemStack stack) {
		checkTakeAchievements(stack);
		super.onTake(player, stack);
	}

	protected void onQuickCraft(ItemStack stack, int amount) {
		removeCount += amount;
		checkTakeAchievements(stack);
	}

	@Override
	protected void checkTakeAchievements(ItemStack stack) {
		stack.onCraftedBy(player, removeCount);
		if (player instanceof ServerPlayer serverPlayerEntity &&
			container instanceof AbstractProcessingBlockEntity abstractProcessingBlockEntity) {
			abstractProcessingBlockEntity.dropExperienceForRecipesUsed(serverPlayerEntity);
		}
		removeCount = 0;
	}
}
