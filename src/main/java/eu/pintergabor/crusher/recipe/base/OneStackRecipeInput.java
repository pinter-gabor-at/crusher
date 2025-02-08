package eu.pintergabor.crusher.recipe.base;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record OneStackRecipeInput(ItemStack itemStack) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot != 0) {
            throw new IllegalArgumentException("No item for index " + slot);
        } else {
            return this.itemStack;
        }
    }

    @Override
    public int size() {
        return itemStack.getCount();
    }
}
