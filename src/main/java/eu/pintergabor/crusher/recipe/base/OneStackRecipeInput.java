package eu.pintergabor.crusher.recipe.base;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import org.jetbrains.annotations.NotNull;


/**
 * Similar to {@link SingleRecipeInput}, but allows direct access
 * to the one and only {@link ItemStack}.
 */
public record OneStackRecipeInput(ItemStack itemStack) implements RecipeInput {

	/**
	 * @return The one and only {@link ItemStack}.
	 */
	public @NotNull ItemStack getItemStack() {
		return itemStack;
	}

	/**
	 * There is only one slot.
	 *
	 * @param slot is not used.
	 * @return The one and only {@link ItemStack}.
	 */
	@Override
	public @NotNull ItemStack getItem(int slot) {
		return itemStack;
	}

	/**
	 * @return 1, because there is only one {@link ItemStack} in the record.
	 */
	@Override
	public int size() {
		return 1;
	}
}
