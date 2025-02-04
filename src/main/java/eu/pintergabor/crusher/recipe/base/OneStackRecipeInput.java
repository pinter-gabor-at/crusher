package eu.pintergabor.crusher.recipe.base;


import org.jetbrains.annotations.NotNull;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;


public record OneStackRecipeInput(ItemStack itemStack) implements RecipeInput {

	@Override
	public @NotNull ItemStack getItem(int slot) {
		if (slot != 0) {
			throw new IllegalArgumentException("No item for index " + slot);
		}
		return itemStack;
	}

	@Override
	public int size() {
		return itemStack.getCount();
	}
}
