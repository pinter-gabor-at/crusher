package eu.pintergabor.crusher.screen;

import java.util.List;

import eu.pintergabor.crusher.Global;
import eu.pintergabor.crusher.recipe.CompressorRecipe;
import eu.pintergabor.crusher.screen.base.AbstractProcessingScreen;

import net.minecraft.client.gui.screens.inventory.FurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Items;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;


/**
 * Similar to {@link FurnaceScreen}, but with fewer tabs.
 */
@Environment(EnvType.CLIENT)
public class CompressorScreen extends AbstractProcessingScreen<CompressorMenu> {
	// The entire GUI (256x256). TODO: Why?
	private static final ResourceLocation TEXTURE =
		Global.modId("textures/gui/compressor_gui.png");
	// The fire sprite in the middle (14x14) (textures/gui/sprites/...).
	private static final ResourceLocation LIT_PROGRESS_SPRITE =
		Global.modId("container/compressor/lit_progress");
	// The arrow sprite on the right (24x16) (textures/gui/sprites/...).
	private static final ResourceLocation BURN_PROGRESS_SPRITE =
		Global.modId("container/compressor/burn_progress");
	// The mouseover toggle text in the recipe book.
	private static final Component FILTER_NAME =
		Component.translatable("gui.recipebook.toggleRecipes.compressible");
	// Recipe book tabs.
	private static final List<RecipeBookComponent.TabInfo> TABS = List.of(
		new RecipeBookComponent.TabInfo(Items.COMPASS, CompressorRecipe.CATEGORY)
	);

	public CompressorScreen(
		CompressorMenu menu, Inventory playerInventory, Component title) {
		super(
			menu, playerInventory, title,
			FILTER_NAME, TEXTURE, LIT_PROGRESS_SPRITE, BURN_PROGRESS_SPRITE, TABS);
	}
}
