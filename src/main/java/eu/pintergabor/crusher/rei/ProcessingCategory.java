package eu.pintergabor.crusher.rei;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import org.jspecify.annotations.NonNull;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;


/**
 * Static methods of {@link CrusherCategory} and {@link CompressorCategory}.
 */
public interface ProcessingCategory {

	/**
	 * Similar to {@code DefaultCookingCategory.setupDisplay}.
	 */
	static @NonNull List<Widget> setupDisplay(
		final @NonNull ProcessingDisplay display,
		final @NonNull Rectangle bounds,
		final @NonNull Identifier background,
		final @NonNull Identifier litProgressSprite
	) {
		final List<Widget> widgets = new LinkedList<>();
		final Point o = new Point(bounds.getCenterX() - 41, bounds.y + 10);
		final DecimalFormat df = new DecimalFormat("###.##");
		// Background.
		widgets.add(Widgets.createRecipeBase(bounds));
		widgets.add(Widgets.createResultSlotBackground(new Point(o.x + 61, o.y + 9)));
		// XP and time.
		widgets.add(Widgets.createLabel(new Point(bounds.x + bounds.width - 5, bounds.y + 5),
				Component.translatable("category.rei.cooking.time&xp",
					df.format(display.getExperience()),
					df.format(display.getProcessingTime() / 20F)))
			.noShadow()
			.rightAligned()
			.color(0xFF404040, 0xFFBBBBBB));
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

	static int getDisplayHeight() {
		return 49;
	}
}
