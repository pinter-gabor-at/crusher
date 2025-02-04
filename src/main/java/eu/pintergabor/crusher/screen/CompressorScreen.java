package eu.pintergabor.crusher.screen;

import eu.pintergabor.crusher.Global;
import eu.pintergabor.crusher.recipe.CompressorRecipe;
import eu.pintergabor.crusher.screen.base.AbstractProcessingScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class CompressorScreen extends AbstractProcessingScreen<CompressorScreenHandler> {
    // The entire GUI (256x256)
    private static final Identifier TEXTURE = Global.ModIdentifier("textures/gui/compressor_gui.png");
    // The fire sprite in the middle (14x14) (textures/gui/sprites/...)
    private static final Identifier LIT_PROGRESS_TEXTURE = Global.ModIdentifier("container/compressor/lit_progress");
    // The arrow sprite on the right (24x16) (textures/gui/sprites/...)
    private static final Identifier BURN_PROGRESS_TEXTURE = Global.ModIdentifier("container/compressor/burn_progress");
    // The mouseover toggle text in the recipe book
    private static final Text TOGGLE_TEXT = Text.translatable("gui.recipebook.toggleRecipes.compressible");
    // Recipe book tabs
    private static final List<RecipeBookWidget.Tab> TABS = List.of(
            new RecipeBookWidget.Tab(Items.COMPASS, CompressorRecipe.CATEGORY)
    );

    public CompressorScreen(CompressorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(
                handler, inventory, title,
                TOGGLE_TEXT, TEXTURE, LIT_PROGRESS_TEXTURE, BURN_PROGRESS_TEXTURE, TABS);
    }
}