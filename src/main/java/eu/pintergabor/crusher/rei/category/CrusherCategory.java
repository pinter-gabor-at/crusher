package eu.pintergabor.crusher.rei.category;

import java.util.List;

import eu.pintergabor.crusher.blocks.ModBlocks;
import eu.pintergabor.crusher.rei.ModReiCommon;
import eu.pintergabor.crusher.rei.base.ProcessingCategory;
import eu.pintergabor.crusher.rei.base.ProcessingDisplay;
import eu.pintergabor.crusher.screen.CrusherScreen;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import org.jspecify.annotations.NonNull;

import net.minecraft.network.chat.Component;


/**
 * See <a href="https://www.craft.do/s/qj8mHyTVd7qkOZ/b/7B02E98B-421E-41C2-958F-A9FD700BE6B6/Categories-(Client)">Wiki: Categories</a>
 */
public class CrusherCategory
	implements DisplayCategory<ProcessingDisplay>, ProcessingCategory {

	@Override
	public CategoryIdentifier<? extends ProcessingDisplay> getCategoryIdentifier() {
		return ModReiCommon.CRUSHER;
	}

	@Override
	public Component getTitle() {
		return Component.translatable("block.crusher.crusher");
	}

	@Override
	public Renderer getIcon() {
		return EntryStacks.of(ModBlocks.CRUSHER_ITEM.getDefaultInstance());
	}

	@Override
	public List<Widget> setupDisplay(
		final @NonNull ProcessingDisplay display,
		final @NonNull Rectangle bounds
	) {
		return ProcessingCategory.setupDisplay(
			display,
			bounds,
			CrusherScreen.TEXTURE,
			CrusherScreen.LIT_PROGRESS_SPRITE);
	}

	@Override
	public int getDisplayHeight() {
		return ProcessingCategory.getDisplayHeight();
	}
}
