package eu.pintergabor.crusher.screen.base;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.recipebook.FurnaceRecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.GhostSlots;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeCollection;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.crafting.display.FurnaceRecipeDisplay;
import net.minecraft.world.item.crafting.display.RecipeDisplay;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;


/**
 * Similar to {@link FurnaceRecipeBookComponent}.
 */
@Environment(EnvType.CLIENT)
public class AbstractProcessingRecipeBookWidget extends RecipeBookComponent<AbstractProcessingMenu> {
	private static final WidgetSprites FILTER_SPRITES = new WidgetSprites(
		ResourceLocation.withDefaultNamespace("recipe_book/furnace_filter_enabled"),
		ResourceLocation.withDefaultNamespace("recipe_book/furnace_filter_disabled"),
		ResourceLocation.withDefaultNamespace("recipe_book/furnace_filter_enabled_highlighted"),
		ResourceLocation.withDefaultNamespace("recipe_book/furnace_filter_disabled_highlighted")
	);
	private final Component recipeFilterName;

	public AbstractProcessingRecipeBookWidget(
		AbstractProcessingMenu menu, Component recipeFilterName, List<TabInfo> tabInfos) {
		super(menu, tabInfos);
		this.recipeFilterName = recipeFilterName;
	}

	@Override
	protected void initFilterButtonTextures() {
		this.filterButton.initTextureValues(FILTER_SPRITES);
	}

	@Override
	protected boolean isCraftingSlot(Slot slot) {
		return 0 <= slot.index && slot.index <= 2;
	}

	protected void fillGhostRecipe(GhostSlots ghostSlots, RecipeDisplay display, ContextMap context) {
		if (ghostSlots instanceof ProcessingGhostSlots processingGhostSlots) {
			processingGhostSlots.setResult(menu.getResultSlot(), context, display.result());
			if (display instanceof FurnaceRecipeDisplay furnaceRecipeDisplay) {
				processingGhostSlots.setInput(menu.slots.get(0),
					context, furnaceRecipeDisplay.ingredient());
				Slot slot = menu.slots.get(1);
				if (slot.getItem().isEmpty()) {
					processingGhostSlots.setInput(slot, context, furnaceRecipeDisplay.fuel());
				}
			}
		}
	}

	@Override
	protected @NotNull Component getRecipeFilterName() {
		return recipeFilterName;
	}

	@Override
	protected void selectMatchingRecipes(RecipeCollection possibleRecipes, StackedItemContents stackedItemContents) {
		possibleRecipes.selectRecipes(stackedItemContents, recipeDisplay ->
			recipeDisplay instanceof FurnaceRecipeDisplay);
	}
}
