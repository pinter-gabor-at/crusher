package eu.pintergabor.crusher.screen;

import eu.pintergabor.crusher.recipe.CrusherRecipe;
import eu.pintergabor.crusher.screen.base.AbstractProcessingScreenHandler;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.RecipePropertySet;
import net.minecraft.recipe.book.RecipeBookType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.util.Identifier;

public class CrusherScreenHandler extends AbstractProcessingScreenHandler {
    public static final RegistryKey<? extends Registry<RecipePropertySet>> REGISTRY =
            RegistryKey.ofRegistry(Identifier.ofVanilla("recipe_property_set"));
    public static final RegistryKey<RecipePropertySet> PROCESSING_INPUT =
            register("processing_input");

    @SuppressWarnings("SameParameterValue")
    private static RegistryKey<RecipePropertySet> register(String id) {
        return RegistryKey.of(REGISTRY, Identifier.ofVanilla(id));
    }

    public CrusherScreenHandler(
            int syncId, PlayerInventory playerInventory) {
        super(
                ModScreenHandlers.CRUSHER_SCREEN_HANDLER,
                CrusherRecipe.CRUSHER_TYPE,
                PROCESSING_INPUT,
                RecipeBookType.FURNACE,
                syncId,
                playerInventory);
    }

    public CrusherScreenHandler(
            int syncId, PlayerInventory playerInventory,
            Inventory inventory, PropertyDelegate propertyDelegate) {

        super(
                ModScreenHandlers.CRUSHER_SCREEN_HANDLER,
                CrusherRecipe.CRUSHER_TYPE,
                PROCESSING_INPUT,
                RecipeBookType.FURNACE,
                syncId,
                playerInventory,
                inventory,
                propertyDelegate);
    }
}