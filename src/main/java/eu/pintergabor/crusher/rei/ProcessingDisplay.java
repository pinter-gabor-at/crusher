package eu.pintergabor.crusher.rei;

import eu.pintergabor.crusher.recipe.base.AbstractProcessingRecipe;
import me.shedaniel.rei.api.common.display.SimpleGridMenuDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import org.jspecify.annotations.NonNull;


public interface ProcessingDisplay extends SimpleGridMenuDisplay {

	@Override
	default int getWidth() {
		return 1;
	}

	@Override
	default int getHeight() {
		return 1;
	}

	default double getExperience() {
		return 0.1F;
	}

	default int getProcessingTime() {
		return 100;
	}

	static EntryIngredient getEntryOutput(final @NonNull AbstractProcessingRecipe recipe) {
		final ItemStack output = recipe.result().create();
		return EntryIngredients.of(output);
	}

	static EntryIngredient getEntryInput(final @NonNull AbstractProcessingRecipe recipe) {
		final Ingredient input = recipe.input();
		final int inputCount = recipe.inputCount();
		final EntryIngredient entryInput = EntryIngredients.ofIngredient(input);
		if (1 < inputCount) {
			entryInput.map(entryStack -> {
				if (entryStack.getValue() instanceof ItemStack itemStack) {
					itemStack.setCount(inputCount);
				}
				return entryStack;
			});
		}
		return entryInput;
	}


}
