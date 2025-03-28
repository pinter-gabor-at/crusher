package eu.pintergabor.crusher.screen.base;

import static eu.pintergabor.crusher.blocks.base.AbstractProcessingBlockEntity.*;

import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.InputSlotFiller;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeFinder;
import net.minecraft.recipe.RecipeInputProvider;
import net.minecraft.recipe.book.RecipeBookType;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;


public class AbstractProcessingScreenHandler extends AbstractRecipeScreenHandler {
    final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    protected final World world;

    protected AbstractProcessingScreenHandler(
        ScreenHandlerType<?> type,
        int syncId,
        PlayerInventory playerInventory
    ) {
        this(
            type,
            syncId,
            playerInventory,
            new SimpleInventory(3),
            new ArrayPropertyDelegate(PROPERTY_COUNT));
    }

    protected AbstractProcessingScreenHandler(
        ScreenHandlerType<?> type,
        int syncId,
        PlayerInventory playerInventory,
        Inventory inventory,
        PropertyDelegate propertyDelegate
    ) {
        super(type, syncId);
        checkSize(inventory, 3);
        checkDataCount(propertyDelegate, PROPERTY_COUNT);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.world = playerInventory.player.getWorld();
        this.addSlot(new Slot(inventory,
            INPUT_SLOT_INDEX, 56, 17));
        this.addSlot(new ProcessingFuelSlot(this, inventory,
            FUEL_SLOT_INDEX, 56, 53));
        this.addSlot(new ProcessingOutputSlot(playerInventory.player, inventory,
            OUTPUT_SLOT_INDEX, 116, 35));
        this.addPlayerSlots(playerInventory, 8, 84);
        this.addProperties(propertyDelegate);
    }

    @Override
    public void populateRecipeFinder(RecipeFinder finder) {
        if (inventory instanceof RecipeInputProvider inputProvider) {
            inputProvider.provideRecipeInputs(finder);
        }
    }

    public Slot getOutputSlot() {
        return slots.get(OUTPUT_SLOT_INDEX);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        final Slot clickSlot = slots.get(slot);
        if (clickSlot.hasStack()) {
            final ItemStack clickItemStack = clickSlot.getStack();
            final ItemStack returnItemStack = clickItemStack.copy();
            if (slot == OUTPUT_SLOT_INDEX) {
                // From output slot to inventory
                if (!this.insertItem(clickItemStack,
                    3, 39, true)) {
                    return ItemStack.EMPTY;
                }
                clickSlot.onQuickTransfer(clickItemStack, returnItemStack);
            } else if (slot == FUEL_SLOT_INDEX || slot == INPUT_SLOT_INDEX) {
                // From fuel, or input slot to inventory
                if (!this.insertItem(clickItemStack,
                    3, 39, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // From elsewhere, if it is fuel, to the fuel slot
                if (this.isFuel(clickItemStack)) {
                    if (!this.insertItem(clickItemStack,
                        FUEL_SLOT_INDEX, FUEL_SLOT_INDEX + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    // From elsewhere to the input slot
                    if (!this.insertItem(clickItemStack,
                        INPUT_SLOT_INDEX, INPUT_SLOT_INDEX + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }
            if (clickItemStack.isEmpty()) {
                clickSlot.setStack(ItemStack.EMPTY);
            } else {
                clickSlot.markDirty();
            }
            if (clickItemStack.getCount() == returnItemStack.getCount()) {
                return ItemStack.EMPTY;
            }
            clickSlot.onTakeItem(player, clickItemStack);
            return returnItemStack;
        }
        return ItemStack.EMPTY;
    }

    protected boolean isFuel(ItemStack item) {
        return world.getFuelRegistry().isFuel(item);
    }

    /**
     * Calculate cooking progress
     *
     * @return Progress (0.0 ... 1.0)
     */
    public float getCookProgress() {
        int progress = propertyDelegate.get(COOK_TIME_PROPERTY_INDEX);
        int total = propertyDelegate.get(COOK_TIME_TOTAL_PROPERTY_INDEX);
        return total != 0 ?
            MathHelper.clamp((float) progress / (float) total, 0.0f, 1.0f) :
            0.0f;
    }

    /**
     * Calculate fuel consumption progress
     *
     * @return Progress (0.0 ... 1.0)
     */
    public float getFuelProgress() {
        int total = propertyDelegate.get(FUEL_TIME_PROPERTY_INDEX);
        if (total == 0) {
            total = DEFAULT_COOK_TIME;
        }
        final int progress = propertyDelegate.get(BURN_TIME_PROPERTY_INDEX);
        return MathHelper.clamp((float) progress / (float) total, 0.0f, 1.0f);
    }

    public boolean isBurning() {
        return 0 < propertyDelegate.get(BURN_TIME_PROPERTY_INDEX);
    }

    @Override
    public RecipeBookType getCategory() {
        return RecipeBookType.FURNACE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PostFillAction fillInputSlots(
        boolean craftAll,
        boolean creative,
        RecipeEntry<?> recipe,
        ServerWorld world,
        PlayerInventory inventory) {
        final List<Slot> list = List.of(getSlot(INPUT_SLOT_INDEX), getSlot(OUTPUT_SLOT_INDEX));
        AbstractProcessingScreenHandler parent = this;
        return InputSlotFiller.fill(
            new InputSlotFiller.Handler<>() {
                @Override
                public void populateRecipeFinder(RecipeFinder finder) {
                    parent.populateRecipeFinder(finder);
                }

                @Override
                public void clear() {
                    list.forEach(slot -> slot.setStackNoCallbacks(ItemStack.EMPTY));
                }

                @Override
                public boolean matches(RecipeEntry<AbstractCookingRecipe> entry) {
                    return entry.value().matches(
                        new SingleStackRecipeInput(parent.inventory.getStack(INPUT_SLOT_INDEX)), world);
                }
            },
            1,
            1,
            List.of(getSlot(INPUT_SLOT_INDEX)),
            list,
            inventory,
            (RecipeEntry<AbstractCookingRecipe>) recipe,
            craftAll,
            creative);
    }
}
