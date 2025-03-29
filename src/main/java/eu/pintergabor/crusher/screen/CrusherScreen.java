package eu.pintergabor.crusher.screen;

import java.util.List;

import eu.pintergabor.crusher.Global;
import eu.pintergabor.crusher.recipe.CrusherRecipe;
import eu.pintergabor.crusher.screen.base.AbstractProcessingScreen;

import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class CrusherScreen extends AbstractProcessingScreen<CrusherScreenHandler> {
	// The entire GUI (256x256).
	private static final Identifier TEXTURE =
		Global.modId("textures/gui/crusher_gui.png");
	// The fire sprite in the middle (14x14) (textures/gui/sprites/...).
	private static final Identifier LIT_PROGRESS_TEXTURE =
		Global.modId("container/crusher/lit_progress");
	// The arrow sprite on the right (24x16) (textures/gui/sprites/...).
	private static final Identifier BURN_PROGRESS_TEXTURE =
		Global.modId("container/crusher/burn_progress");
	// The mouseover toggle text in the recipe book.
	private static final Text TOGGLE_TEXT =
		Text.translatable("gui.recipebook.toggleRecipes.crushable");
	// Recipe book tabs.
	private static final List<RecipeBookWidget.Tab> TABS = List.of(
		new RecipeBookWidget.Tab(Items.COMPASS, CrusherRecipe.CATEGORY)
	);

	public CrusherScreen(
		CrusherScreenHandler handler, PlayerInventory inventory, Text title) {
		super(
			handler, inventory, title,
			TOGGLE_TEXT, TEXTURE, LIT_PROGRESS_TEXTURE, BURN_PROGRESS_TEXTURE, TABS);
	}
}
