package eu.pintergabor.crusher.screen.base;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;


/**
 * Similar to {@link AbstractFurnaceScreen}.
 */
public abstract class AbstractProcessingScreen<T extends AbstractProcessingMenu>
	extends AbstractRecipeBookScreen<T> {
	private final Identifier background;
	private final Identifier litProgressSprite;
	private final Identifier burnProgressSprite;

	public AbstractProcessingScreen(
		T menu,
		Inventory playerInventory,
		Component title,
		Component recipeFilterName,
		Identifier background,
		Identifier litProgressSprite,
		Identifier burnProgressSprite,
		List<RecipeBookComponent.TabInfo> recipeBookTabs
	) {
		super(
			menu,
			new AbstractProcessingRecipeBookComponent(
				menu,
				recipeFilterName,
				recipeBookTabs),
			playerInventory,
			title);
		this.background = background;
		this.litProgressSprite = litProgressSprite;
		this.burnProgressSprite = burnProgressSprite;
	}

	@Override
	public void init() {
		super.init();
		titleLabelX = (imageWidth - font.width(title)) / 2;
	}

	@Override
	protected @NotNull ScreenPosition getRecipeBookButtonPosition() {
		return new ScreenPosition(leftPos + 20, height / 2 - 49);
	}

	@Override
	protected void renderBg(
		@NotNull GuiGraphics guiGraphics,
		float partialTick, int mouseX, int mouseY
	) {
		// Full (176x166) size background.
		guiGraphics.blit(
			RenderPipelines.GUI_TEXTURED,
			background,
			leftPos, topPos,
			0F, 0F,
			imageWidth, imageHeight,
			imageWidth, imageHeight);
		if (menu.isLit()) {
			// Height of the fuel consumption sprite in the middle.
			final int h = Mth.ceil(menu.getLitProgress() * 13F) + 1;
			guiGraphics.blitSprite(
				RenderPipelines.GUI_TEXTURED,
				litProgressSprite,
				14, 14,
				0, 14 - h,
				leftPos + 56, topPos + 36 + 14 - h,
				14, h);
		}
		// Width of the progress sprite.
		final int w = Mth.ceil(menu.getBurnProgress() * 24F);
		guiGraphics.blitSprite(
			RenderPipelines.GUI_TEXTURED,
			burnProgressSprite,
			24, 16,
			0, 0,
			leftPos + 79, topPos + 34,
			w, 16);
	}
}
