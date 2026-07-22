package eu.pintergabor.crusher.recipe.base;

import org.jspecify.annotations.NonNull;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.SingleRecipeInput;


/**
 * Similar to {@link SingleRecipeInput}, but allows direct access
 * to the one and only {@link ItemStack}.
 */
public record OneStackRecipeInput(ItemStack itemStack) implements RecipeInput {

	/**
	 * @return The one and only {@link ItemStack}.
	 */
	public @NonNull ItemStack getItemStack() {
		return itemStack;
	}

	/**
	 * There is only one slot.
	 *
	 * @param slot is not used.
	 * @return The one and only {@link ItemStack}.
	 */
	@Override
	public @NonNull ItemStack getItem(final int slot) {
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
