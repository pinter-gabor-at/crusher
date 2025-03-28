package eu.pintergabor.crusher.screen.base;

import eu.pintergabor.crusher.blocks.base.AbstractProcessingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;

/**
 * Similar to {@link FurnaceOutputSlot}
 */
public class ProcessingOutputSlot extends Slot {
    private final PlayerEntity player;
    private int amount;

    public ProcessingOutputSlot(PlayerEntity player, Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.player = player;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack takeStack(int amount) {
        if (hasStack()) {
            amount += Math.min(amount, getStack().getCount());
        }
        return super.takeStack(amount);
    }

    @Override
    public void onTakeItem(PlayerEntity player, ItemStack stack) {
        onCrafted(stack);
        super.onTakeItem(player, stack);
    }

    @Override
    protected void onCrafted(ItemStack stack, int amount) {
        this.amount += amount;
        onCrafted(stack);
    }

    @Override
    protected void onCrafted(ItemStack stack) {
        stack.onCraftByPlayer(player, amount);
        if (player instanceof ServerPlayerEntity serverPlayerEntity &&
                inventory instanceof AbstractProcessingBlockEntity abstractProcessingBlockEntity) {
            abstractProcessingBlockEntity.dropExperienceForRecipesUsed(serverPlayerEntity);
        }
        amount = 0;
    }
}
