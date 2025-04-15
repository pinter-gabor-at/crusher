package eu.pintergabor.crusher.screen.base;

import java.util.List;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;


/**
 * Similar to {@link AbstractFurnaceScreen}.
 */
@OnlyIn(Dist.CLIENT)
public abstract class AbstractProcessingScreen<T extends AbstractProcessingMenu>
	extends AbstractRecipeBookScreen<T> {
	private final ResourceLocation background;
	private final ResourceLocation litProgressSprite;
	private final ResourceLocation burnProgressSprite;

	public AbstractProcessingScreen(
		T menu,
		Inventory playerInventory,
		Component title,
		Component recipeFilterName,
		ResourceLocation background,
		ResourceLocation litProgressSprite,
		ResourceLocation burnProgressSprite,
		List<RecipeBookComponent.TabInfo> recipeBookTabs
	) {
		super(
			menu,
			new AbstractProcessingRecipeBookWidget(
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
	protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
		guiGraphics.blit(
			RenderType::guiTextured,
			background,
			leftPos, topPos,
			0F, 0F,
			imageWidth, imageHeight,
			256, 256); // TODO: Why 256x256?
		if (menu.isLit()) {
			int h = Mth.ceil(menu.getLitProgress() * 13F) + 1;
			guiGraphics.blitSprite(
				RenderType::guiTextured,
				litProgressSprite,
				14, 14,
				0, 14 - h,
				leftPos + 56, topPos + 36 + 14 - h,
				14, h);
		}
		int w = Mth.ceil(menu.getBurnProgress() * 24F);
		guiGraphics.blitSprite(
			RenderType::guiTextured,
			burnProgressSprite,
			24, 16,
			0, 0,
			leftPos + 79, topPos + 34,
			w, 16);
	}
}
