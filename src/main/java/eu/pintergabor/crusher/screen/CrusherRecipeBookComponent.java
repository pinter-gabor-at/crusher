package eu.pintergabor.crusher.screen;

import net.minecraft.client.gui.screen.recipebook.AbstractFurnaceRecipeBookWidget;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.text.Text;

import java.util.List;

public class CrusherRecipeBookComponent extends AbstractFurnaceRecipeBookWidget {

    public CrusherRecipeBookComponent(AbstractFurnaceScreenHandler screenHandler, Text toggleCraftableButtonText, List<Tab> tabs) {
        super(screenHandler, toggleCraftableButtonText, tabs);
    }
}
