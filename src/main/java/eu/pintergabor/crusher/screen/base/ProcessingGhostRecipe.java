package eu.pintergabor.crusher.screen.base;

import net.minecraft.client.gui.screen.recipebook.CurrentIndexProvider;
import net.minecraft.client.gui.screen.recipebook.GhostRecipe;
import net.minecraft.recipe.display.SlotDisplay;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.context.ContextParameterMap;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;


/**
 * Same as {@link GhostRecipe},
 * but with access wideners.
 */
@Environment(EnvType.CLIENT)
public class ProcessingGhostRecipe extends GhostRecipe {
	public ProcessingGhostRecipe(CurrentIndexProvider currentIndexProvider) {
		super(currentIndexProvider);
	}

	@Override
	public void addInputs(Slot slot, ContextParameterMap context, SlotDisplay display) {
		super.addInputs(slot, context, display);
	}

	@Override
	public void addResults(Slot slot, ContextParameterMap context, SlotDisplay display) {
		super.addResults(slot, context, display);
	}
}
