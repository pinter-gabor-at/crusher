package eu.pintergabor.crusher.screen;

import eu.pintergabor.crusher.Global;
import eu.pintergabor.crusher.recipe.CrusherRecipe;
import net.minecraft.client.gui.screen.ingame.AbstractFurnaceScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.client.recipebook.RecipeBookType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class CrusherScreen extends AbstractFurnaceScreen<CrusherScreenHandler> {
    private static final Identifier TEXTURE = Global.ModIdentifier("textures/gui/crusher_gui.png");
    private static final Identifier LIT_PROGRESS_TEXTURE = Global.ModIdentifier("container/crusher/lit_progress");
    private static final Identifier BURN_PROGRESS_TEXTURE = Global.ModIdentifier("container/crusher/burn_progress");
    private static final Text TOGGLE_TEXT = Text.translatable("gui.recipebook.toggleRecipes.crushable");
    private static final List<RecipeBookWidget.Tab> TABS = List.of(
            new RecipeBookWidget.Tab(RecipeBookType.FURNACE),
            new RecipeBookWidget.Tab(Items.SAND, CrusherRecipe.CRUSHER_CATEGORY)
    );

    public CrusherScreen(CrusherScreenHandler handler, PlayerInventory inventory, Text title) {
        super(
                handler,
                inventory,
                title,
                TOGGLE_TEXT, TEXTURE, LIT_PROGRESS_TEXTURE, BURN_PROGRESS_TEXTURE, TABS);
    }
}