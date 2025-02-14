package eu.pintergabor.crusher.rei;

import eu.pintergabor.crusher.Global;
import eu.pintergabor.crusher.blocks.ModBlocks;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class CrusherCategory implements DisplayCategory<BasicDisplay> {
    public static final Identifier TEXTURE =
            Global.ModIdentifier("textures/gui/crusher_small_gui_for_rei.png");
    public static final CategoryIdentifier<CrusherDisplay> CRUSHER =
            CategoryIdentifier.of(Global.ModIdentifier("crusher"));

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return CRUSHER;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("block.crusher.crusher");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.CRUSHER_ITEM.getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 45);
        List<Widget> widgets = new LinkedList<>();
        widgets.add(Widgets.createTexturedWidget(TEXTURE,
                new Rectangle(startPoint.x, startPoint.y, 175, 82)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 56, startPoint.y + 17))
                .entries(display.getInputEntries().getFirst()));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 116, startPoint.y + 26))
                .markOutput().entries(display.getOutputEntries().getFirst()));

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }
}