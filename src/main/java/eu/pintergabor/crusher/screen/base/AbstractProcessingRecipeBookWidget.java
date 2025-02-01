package eu.pintergabor.crusher.screen.base;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ButtonTextures;
import net.minecraft.client.gui.screen.recipebook.GhostRecipe;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.client.gui.screen.recipebook.RecipeResultCollection;
import net.minecraft.recipe.RecipeFinder;
import net.minecraft.recipe.display.FurnaceRecipeDisplay;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.context.ContextParameterMap;

import java.util.List;

@Environment(EnvType.CLIENT)
public class AbstractProcessingRecipeBookWidget extends RecipeBookWidget<AbstractProcessingScreenHandler> {
    private static final ButtonTextures TEXTURES = new ButtonTextures(
            Identifier.ofVanilla("recipe_book/furnace_filter_enabled"),
            Identifier.ofVanilla("recipe_book/furnace_filter_disabled"),
            Identifier.ofVanilla("recipe_book/furnace_filter_enabled_highlighted"),
            Identifier.ofVanilla("recipe_book/furnace_filter_disabled_highlighted")
    );
    private final Text toggleCraftableButtonText;

    public AbstractProcessingRecipeBookWidget(
            AbstractProcessingScreenHandler screenHandler, Text toggleCraftableButtonText, List<Tab> tabs) {
        super(screenHandler, tabs);
        this.toggleCraftableButtonText = toggleCraftableButtonText;
    }

    @Override
    protected void setBookButtonTexture() {
        toggleCraftableButton.setTextures(TEXTURES);
    }

    @Override
    protected boolean isValid(Slot slot) {
        return 0 <= slot.id && slot.id <= 2;
    }

    @Override
    protected void showGhostRecipe(GhostRecipe ghostRecipe, RecipeDisplay display, ContextParameterMap context) {
        if (ghostRecipe instanceof ProcessingGhostRecipe processingGhostRecipe) {
            processingGhostRecipe.addResults(this.craftingScreenHandler.getOutputSlot(), context, display.result());
            if (display instanceof FurnaceRecipeDisplay furnaceRecipeDisplay) {
                processingGhostRecipe.addInputs(this.craftingScreenHandler.slots.get(0), context, furnaceRecipeDisplay.ingredient());
                Slot slot = this.craftingScreenHandler.slots.get(1);
                if (slot.getStack().isEmpty()) {
                    processingGhostRecipe.addInputs(slot, context, furnaceRecipeDisplay.fuel());
                }
            }
        }
    }

    @Override
    protected Text getToggleCraftableButtonText() {
        return toggleCraftableButtonText;
    }

    @Override
    protected void populateRecipes(RecipeResultCollection recipeResultCollection, RecipeFinder recipeFinder) {
        recipeResultCollection.populateRecipes(recipeFinder, display -> display instanceof FurnaceRecipeDisplay);
    }
}
