package eu.pintergabor.crusher.screen;

import java.util.List;

import eu.pintergabor.crusher.Global;
import eu.pintergabor.crusher.recipe.CrusherRecipe;
import eu.pintergabor.crusher.screen.base.AbstractProcessingScreen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.FurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Items;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;


/**
 * Similar to {@link FurnaceScreen}, but with fewer tabs.
 * <p>
 * Texture and sprite sizes are defined in
 * {@link AbstractProcessingScreen#renderBg(GuiGraphics, float, int, int)}.
 */
@OnlyIn(Dist.CLIENT)
public class CrusherScreen extends AbstractProcessingScreen<CrusherMenu> {
	// The entire GUI (176x166).
	private static final ResourceLocation TEXTURE =
		Global.modId("textures/gui/crusher_gui.png");
	// The fire sprite in the middle (14x14) (textures/gui/sprites/...).
	private static final ResourceLocation LIT_PROGRESS_SPRITE =
		Global.modId("container/crusher/lit_progress");
	// The arrow sprite on the right (24x16) (textures/gui/sprites/...).
	private static final ResourceLocation BURN_PROGRESS_SPRITE =
		Global.modId("container/crusher/burn_progress");
	// The mouseover toggle text in the recipe book.
	private static final Component FILTER_NAME =
		Component.translatable("gui.recipebook.toggleRecipes.crushable");
	// Recipe book tabs.
	private static final List<RecipeBookComponent.TabInfo> TABS = List.of(
		new RecipeBookComponent.TabInfo(Items.COMPASS, CrusherRecipe.CATEGORY.get())
	);

	public CrusherScreen(
		CrusherMenu menu, Inventory playerInventory, Component title) {
		super(
			menu, playerInventory, title,
			FILTER_NAME, TEXTURE, LIT_PROGRESS_SPRITE, BURN_PROGRESS_SPRITE, TABS);
	}
}
