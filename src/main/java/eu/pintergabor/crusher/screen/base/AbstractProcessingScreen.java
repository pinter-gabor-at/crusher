package eu.pintergabor.crusher.screen.base;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.ScreenPos;
import net.minecraft.client.gui.screen.ingame.RecipeBookScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public abstract class AbstractProcessingScreen<T extends AbstractProcessingScreenHandler> extends RecipeBookScreen<T> {
    private final Identifier background;
    private final Identifier litProgressTexture;
    private final Identifier burnProgressTexture;

    public AbstractProcessingScreen(
            T handler,
            PlayerInventory playerInventory,
            Text title,
            Text toggleCraftableButtonText,
            Identifier background,
            Identifier litProgressTexture,
            Identifier burnProgressTexture,
            List<RecipeBookWidget.Tab> recipeBookTabs
    ) {
        super(handler, new AbstractProcessingRecipeBookWidget(handler, toggleCraftableButtonText, recipeBookTabs), playerInventory, title);
        this.background = background;
        this.litProgressTexture = litProgressTexture;
        this.burnProgressTexture = burnProgressTexture;
    }

    @Override
    public void init() {
        super.init();
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
    }

    @Override
    protected ScreenPos getRecipeBookButtonPos() {
        return new ScreenPos(this.x + 20, this.height / 2 - 49);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = this.x;
        int y = this.y;
        context.drawTexture(RenderLayer::getGuiTextured, this.background, x, y, 0.0F, 0.0F, this.backgroundWidth, this.backgroundHeight, 256, 256);
        if (this.handler.isBurning()) {
            int h = MathHelper.ceil(this.handler.getFuelProgress() * 13.0f) + 1;
            context.drawGuiTexture(RenderLayer::getGuiTextured,
                    this.litProgressTexture, 14, 14,
                    0, 14 - h,
                    x + 56, y + 36 + 14 - h, 14, h);
        }
        int w = MathHelper.ceil(this.handler.getCookProgress() * 24.0f);
        context.drawGuiTexture(RenderLayer::getGuiTextured,
                this.burnProgressTexture, 24, 16,
                0, 0,
                x + 79, y + 34, w, 16);
    }
}
