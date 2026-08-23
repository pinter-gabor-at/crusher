package eu.pintergabor.crusher.rei.base;

import eu.pintergabor.crusher.recipe.base.AbstractProcessingRecipe;
import eu.pintergabor.crusher.rei.display.CompressorDisplay;
import eu.pintergabor.crusher.rei.display.CrusherDisplay;
import me.shedaniel.rei.api.common.display.SimpleGridMenuDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import org.jspecify.annotations.NonNull;


/**
 * Main properties and common utilities of
 * {@link CrusherDisplay} and {@link CompressorDisplay}.
 */
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

	/**
	 * Create input {@link EntryIngredient} from the
	 * {@link AbstractProcessingRecipe#input()} and the
	 * {@link AbstractProcessingRecipe#inputCount()} of a recipe.
	 */
	static EntryIngredient getEntryInput(final @NonNull AbstractProcessingRecipe recipe) {
		final Ingredient input = recipe.input();
		final int inputCount = recipe.inputCount();
		// If input is a tag, this creates
		// a list of ItemStacks of all tagged items.
		// If input is an ItemStack, this creates
		// a list of 1 ItemStacks, containing only the ItemStack.
		// In both case all ItemStack has counts of 1.
		final EntryIngredient entryInput = EntryIngredients.ofIngredient(input);
		// If the desired count is not 1, change it for every ItemStack.
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

	/**
	 * Create output {@link EntryIngredient} from the
	 * {@link AbstractProcessingRecipe#result()} of a recipe.
	 */
	static EntryIngredient getEntryOutput(final @NonNull AbstractProcessingRecipe recipe) {
		final ItemStack output = recipe.result().create();
		return EntryIngredients.of(output);
	}
}
