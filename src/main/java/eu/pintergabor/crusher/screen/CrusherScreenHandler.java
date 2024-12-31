package eu.pintergabor.crusher.screen;

import eu.pintergabor.crusher.blocks.CrusherBlockEntity;
import eu.pintergabor.crusher.recipe.CrusherRecipe;
import eu.pintergabor.crusher.screen.base.AbstractProcessingScreenHandler;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipePropertySet;
import net.minecraft.recipe.book.RecipeBookType;
import net.minecraft.screen.PropertyDelegate;

public class CrusherScreenHandler extends AbstractProcessingScreenHandler {

    public CrusherScreenHandler(
            int syncId, PlayerInventory playerInventory) {
        super(
                ModScreenHandlers.CRUSHER_SCREEN_HANDLER,
                CrusherRecipe.CRUSHER_TYPE,
                RecipePropertySet.FURNACE_INPUT,
                RecipeBookType.FURNACE,
                syncId,
                playerInventory);
    }

    public CrusherScreenHandler(
            int syncId, PlayerInventory playerInventory,
            CrusherBlockEntity blockEntity, PropertyDelegate propertyDelegate) {

        super(
                ModScreenHandlers.CRUSHER_SCREEN_HANDLER,
                CrusherRecipe.CRUSHER_TYPE,
                RecipePropertySet.FURNACE_INPUT,
                RecipeBookType.FURNACE,
                syncId,
                playerInventory,
                blockEntity,
                propertyDelegate);
    }
}