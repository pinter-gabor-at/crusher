package eu.pintergabor.crusher.screen.base;

import org.jetbrains.annotations.NotNull;

import net.minecraft.client.gui.screens.recipebook.GhostSlots;
import net.minecraft.client.gui.screens.recipebook.SlotSelectTime;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.crafting.display.SlotDisplay;


/**
 * Same as {@link GhostSlots},
 * but with access wideners.
 */
public class ProcessingGhostSlots extends GhostSlots {

	public ProcessingGhostSlots(SlotSelectTime slotSelectTime) {
		super(slotSelectTime);
	}

	@Override
	public void setInput(
		@NotNull Slot slot,
		@NotNull ContextMap context,
		@NotNull SlotDisplay display
	) {
		super.setInput(slot, context, display);
	}

	@Override
	public void setResult(
		@NotNull Slot slot,
		@NotNull ContextMap context,
		@NotNull SlotDisplay display
	) {
		super.setResult(slot, context, display);
	}
}
