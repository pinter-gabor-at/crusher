package eu.pintergabor.crusher.screen.base;

import java.util.List;

import org.jspecify.annotations.NonNull;

import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.recipebook.FurnaceRecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.GhostSlots;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeCollection;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.crafting.display.FurnaceRecipeDisplay;
import net.minecraft.world.item.crafting.display.RecipeDisplay;

import static eu.pintergabor.crusher.blocks.base.StaticProcessingBlockEntity.SLOT_FUEL;
import static eu.pintergabor.crusher.blocks.base.StaticProcessingBlockEntity.SLOT_INPUT;


/**
 * Similar to {@link FurnaceRecipeBookComponent}.
 */
public class AbstractProcessingRecipeBookComponent extends RecipeBookComponent<@NonNull AbstractProcessingMenu> {
	private static final WidgetSprites FILTER_SPRITES = new WidgetSprites(
		Identifier.withDefaultNamespace("recipe_book/furnace_filter_enabled"),
		Identifier.withDefaultNamespace("recipe_book/furnace_filter_disabled"),
		Identifier.withDefaultNamespace("recipe_book/furnace_filter_enabled_highlighted"),
		Identifier.withDefaultNamespace("recipe_book/furnace_filter_disabled_highlighted")
	);
	private final Component recipeFilterName;

	public AbstractProcessingRecipeBookComponent(
		final @NonNull AbstractProcessingMenu menu,
		final @NonNull Component recipeFilterName,
		final @NonNull List<TabInfo> tabInfos
	) {
		super(menu, tabInfos);
		this.recipeFilterName = recipeFilterName;
	}

	@Override
	protected @NonNull WidgetSprites getFilterButtonTextures() {
		return FILTER_SPRITES;
	}

	@Override
	protected boolean isCraftingSlot(final @NonNull Slot slot) {
		return 0 <= slot.index && slot.index <= 2;
	}

	protected void fillGhostRecipe(
		final @NonNull GhostSlots ghostSlots,
		final @NonNull RecipeDisplay display,
		final @NonNull ContextMap context
	) {
		if (ghostSlots instanceof ProcessingGhostSlots processingGhostSlots) {
			final Slot resultSlot = menu.getResultSlot();
			processingGhostSlots.setResult(resultSlot,
				context, display.result());
			if (display instanceof FurnaceRecipeDisplay furnaceRecipeDisplay) {
				final Slot inputSlot = menu.slots.get(SLOT_INPUT);
				processingGhostSlots.setInput(inputSlot,
					context, furnaceRecipeDisplay.ingredient());
				final Slot fuelSlot = menu.slots.get(SLOT_FUEL);
				if (fuelSlot.getItem().isEmpty()) {
					processingGhostSlots.setInput(fuelSlot,
						context, furnaceRecipeDisplay.fuel());
				}
			}
		}
	}

	@Override
	protected @NonNull Component getRecipeFilterName() {
		return recipeFilterName;
	}

	@Override
	protected void selectMatchingRecipes(
		final @NonNull RecipeCollection possibleRecipes,
		final @NonNull StackedItemContents contents
	) {
		possibleRecipes.selectRecipes(contents, recipeDisplay ->
			recipeDisplay instanceof FurnaceRecipeDisplay);
	}
}
