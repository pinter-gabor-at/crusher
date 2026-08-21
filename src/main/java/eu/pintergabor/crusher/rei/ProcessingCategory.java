package eu.pintergabor.crusher.rei;

import eu.pintergabor.crusher.screen.CrusherScreen;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;

import net.minecraft.resources.Identifier;

import org.jspecify.annotations.NonNull;

import java.util.LinkedList;
import java.util.List;


/**
 * Static methods of {@link CrusherCategory} and
 */
public final class ProcessingCategory {

	private ProcessingCategory(){
		// Static class
	}

	public static @NonNull List<Widget> setupDisplay(
		final @NonNull ProcessingDisplay display,
		final @NonNull Rectangle bounds,
		final @NonNull Identifier background,
		final @NonNull Identifier litProgressSprite
	) {
		List<Widget> widgets = new LinkedList<>();
		Point o = new Point(bounds.getCenterX() - 41, bounds.y + 10);
		// Background.
		widgets.add(Widgets.createRecipeBase(bounds));
		widgets.add(Widgets.createResultSlotBackground(new Point(o.x + 61, o.y + 9)));
		// The animated processing action.
		widgets.add(new ProgressWidget(
			new Rectangle(o.x + 2, o.y + 20, 14, 14),
			background,
			litProgressSprite));
		// The animated arrow.
		widgets.add(Widgets.createArrow(new Point(o.x + 24, o.y + 8))
			.animationDurationTicks(display.getProcessingTime()));
		// Input.
		widgets.add(Widgets.createSlot(new Point(o.x + 1, o.y + 1))
			.entries(display.getInputEntries().getFirst())
			.markInput());
		// Output.
		widgets.add(Widgets.createSlot(new Point(o.x + 61, o.y + 9))
			.entries(display.getOutputEntries().getFirst())
			.disableBackground()
			.markOutput());
		return widgets;
	}

	public static int getDisplayHeight() {
		return 49;
	}
}
