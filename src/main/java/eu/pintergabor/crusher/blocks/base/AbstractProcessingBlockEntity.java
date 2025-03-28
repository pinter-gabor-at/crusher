package eu.pintergabor.crusher.blocks.base;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import eu.pintergabor.crusher.recipe.base.AbstractProcessingRecipe;
import eu.pintergabor.crusher.recipe.base.OneStackRecipeInput;
import it.unimi.dsi.fastutil.objects.Reference2IntMap.Entry;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import org.jetbrains.annotations.Nullable;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeFinder;
import net.minecraft.recipe.RecipeInputProvider;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.RecipeUnlocker;
import net.minecraft.recipe.ServerRecipeManager;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;


/**
 * Similar to {@link AbstractFurnaceBlockEntity}.
 * <li>But allows multiple input and output counts.
 * <li>And adds a hook for special processing.
 * <li>And removes the special handling of buckets in the fuel slot.
 */
public abstract class AbstractProcessingBlockEntity
    extends LockableContainerBlockEntity
    implements SidedInventory, RecipeUnlocker, RecipeInputProvider {
    public static final int INPUT_SLOT_INDEX = 0;
    public static final int FUEL_SLOT_INDEX = 1;
    public static final int OUTPUT_SLOT_INDEX = 2;
    private static final int[] TOP_SLOTS = new int[]{INPUT_SLOT_INDEX};
    private static final int[] BOTTOM_SLOTS = new int[]{OUTPUT_SLOT_INDEX};
    private static final int[] SIDE_SLOTS = new int[]{FUEL_SLOT_INDEX};
    public static final int BURN_TIME_PROPERTY_INDEX = 0;
    public static final int FUEL_TIME_PROPERTY_INDEX = 1;
    public static final int COOK_TIME_PROPERTY_INDEX = 2;
    public static final int COOK_TIME_TOTAL_PROPERTY_INDEX = 3;
    public static final int PROPERTY_COUNT = 4;
    public static final int DEFAULT_COOK_TIME = 200;
    private static final Codec<Map<RegistryKey<Recipe<?>>, Integer>> CODEC =
        Codec.unboundedMap(Recipe.KEY_CODEC, Codec.INT);
    protected DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    protected int litTimeRemaining;
    protected int litTotalTime;
    protected int cookingTimeSpent;
    protected int cookingTotalTime;
    /**
     * Same as in {@link AbstractFurnaceBlockEntity}.
     */
    protected final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case BURN_TIME_PROPERTY_INDEX -> litTimeRemaining;
                case FUEL_TIME_PROPERTY_INDEX -> litTotalTime;
                case COOK_TIME_PROPERTY_INDEX -> cookingTimeSpent;
                case COOK_TIME_TOTAL_PROPERTY_INDEX -> cookingTotalTime;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case BURN_TIME_PROPERTY_INDEX -> litTimeRemaining = value;
                case FUEL_TIME_PROPERTY_INDEX -> litTotalTime = value;
                case COOK_TIME_PROPERTY_INDEX -> cookingTimeSpent = value;
                case COOK_TIME_TOTAL_PROPERTY_INDEX -> cookingTotalTime = value;
            }
        }

        @Override
        public int size() {
            return PROPERTY_COUNT;
        }
    };
    private final Reference2IntOpenHashMap<RegistryKey<Recipe<?>>> recipesUsed = new Reference2IntOpenHashMap<>();
    private final ServerRecipeManager.MatchGetter<OneStackRecipeInput, ? extends AbstractProcessingRecipe> matchGetter;

    /**
     * Same as in {@link AbstractFurnaceBlockEntity}.
     */
    protected AbstractProcessingBlockEntity(
        BlockEntityType<?> blockEntityType,
        BlockPos pos,
        BlockState state,
        RecipeType<? extends AbstractProcessingRecipe> recipeType
    ) {
        super(blockEntityType, pos, state);
        matchGetter = ServerRecipeManager.createCachedMatchGetter(recipeType);
    }

    /**
     * Same as in {@link AbstractFurnaceBlockEntity}.
     */
    protected boolean isBurning() {
        return 0 < litTimeRemaining;
    }

    /**
     * Same as in {@link AbstractFurnaceBlockEntity}.
     */
    @Override
    protected void readNbt(NbtCompound nbt, WrapperLookup registries) {
        super.readNbt(nbt, registries);
        inventory = DefaultedList.ofSize(size(), ItemStack.EMPTY);
        Inventories.readNbt(nbt, inventory, registries);
        cookingTimeSpent = nbt.getShort("cooking_time_spent", (short) 0);
        cookingTotalTime = nbt.getShort("cooking_total_time", (short) 0);
        litTimeRemaining = nbt.getShort("lit_time_remaining", (short) 0);
        litTotalTime = nbt.getShort("lit_total_time", (short) 0);
        recipesUsed.clear();
        recipesUsed.putAll(nbt.get("RecipesUsed", CODEC).orElse(Map.of()));
    }

    /**
     * Same as in {@link AbstractFurnaceBlockEntity}.
     */
    @Override
    protected void writeNbt(NbtCompound nbt, WrapperLookup registries) {
        super.writeNbt(nbt, registries);
        nbt.putShort("cooking_time_spent", (short) cookingTimeSpent);
        nbt.putShort("cooking_total_time", (short) cookingTotalTime);
        nbt.putShort("lit_time_remaining", (short) litTimeRemaining);
        nbt.putShort("lit_total_time", (short) litTotalTime);
        Inventories.writeNbt(nbt, inventory, registries);
        NbtCompound nbtCompound = new NbtCompound();
        recipesUsed.forEach((recipeKey, count) ->
            nbtCompound.putInt(
                recipeKey.getValue().toString(),
                count));
        nbt.put("RecipesUsed", nbtCompound);
    }

    /**
     * Similar to {@link AbstractFurnaceBlockEntity}, but allows multiple input and output counts.
     */
    public static void tick(
        ServerWorld world, BlockPos pos, BlockState state, AbstractProcessingBlockEntity blockEntity) {
        boolean burning = blockEntity.isBurning();
        boolean changed = false;
        // Count down burning time.
        if (burning) {
            blockEntity.litTimeRemaining--;
        }
        // Check if starting / continuing processing is possible.
        ItemStack inputStack = blockEntity.inventory.get(INPUT_SLOT_INDEX);
        ItemStack fuelStack = blockEntity.inventory.get(FUEL_SLOT_INDEX);
        boolean hasInput = !inputStack.isEmpty();
        boolean hasFuel = !fuelStack.isEmpty();
        if (blockEntity.isBurning() || (hasFuel && hasInput)) {
            // Get the recipe
            OneStackRecipeInput oneStackRecipeInput = new OneStackRecipeInput(inputStack);
            RecipeEntry<? extends AbstractProcessingRecipe> recipeEntry;
            if (hasInput) {
                recipeEntry = blockEntity.matchGetter.getFirstMatch(oneStackRecipeInput, world).orElse(null);
            } else {
                recipeEntry = null;
            }
            // Start processing a new input item.
            int maxCount = blockEntity.getMaxCountPerStack();
            if (!blockEntity.isBurning() &&
                canAcceptRecipeOutput(
                    world.getRegistryManager(),
                    recipeEntry,
                    oneStackRecipeInput,
                    blockEntity.inventory,
                    maxCount)) {
                blockEntity.litTimeRemaining = blockEntity.getFuelTime(world.getFuelRegistry(), fuelStack);
                blockEntity.litTotalTime = blockEntity.litTimeRemaining;
                // Need more fuel to continue.
                if (blockEntity.isBurning()) {
                    changed = true;
                    if (hasFuel) {
                        Item item = fuelStack.getItem();
                        fuelStack.decrement(1);
                        if (fuelStack.isEmpty()) {
                            blockEntity.inventory.set(FUEL_SLOT_INDEX, item.getRecipeRemainder());
                        }
                    }
                }
            }
            // End processing one input item and generate output.
            if (blockEntity.isBurning() &&
                canAcceptRecipeOutput(
                    world.getRegistryManager(),
                    recipeEntry,
                    oneStackRecipeInput,
                    blockEntity.inventory,
                    maxCount)) {
                blockEntity.cookingTimeSpent++;
                if (blockEntity.cookingTimeSpent == blockEntity.cookingTotalTime) {
                    blockEntity.cookingTimeSpent = 0;
                    blockEntity.cookingTotalTime = getCookTime(world, blockEntity);
                    if (craftRecipe(
                        world.getRegistryManager(),
                        recipeEntry,
                        oneStackRecipeInput,
                        blockEntity.inventory,
                        maxCount)) {
                        blockEntity.setLastRecipe(recipeEntry);
                        // Special action?
                        blockEntity.crafted();
                    }
                    changed = true;
                }
            } else {
                blockEntity.cookingTimeSpent = 0;
            }
        } else {
            // Continue processing the input item.
            if (!blockEntity.isBurning() && blockEntity.cookingTimeSpent > 0) {
                blockEntity.cookingTimeSpent = MathHelper.clamp(
                    blockEntity.cookingTimeSpent - 2, 0, blockEntity.cookingTotalTime);
            }
        }
        // Burning state changed.
        if (burning != blockEntity.isBurning()) {
            changed = true;
            state = state.with(AbstractFurnaceBlock.LIT, blockEntity.isBurning());
            world.setBlockState(pos, state, Block.NOTIFY_ALL);
        }
        // Something changed -> redraw.
        if (changed) {
            markDirty(world, pos, state);
        }
    }

    /**
     * Called when something has been crafted.
     */
    protected void crafted() {
        // Do nothing.
    }

    /**
     * Check if there are enough items in the input slot, and enough space in the output slot to craft new items.
     *
     * @param inputStack  {@link ItemStack} in the input slot
     * @param outputStack {@link ItemStack} in the ouput slot
     * @param inputCount  Number of items needed to craft resultStack
     * @param resultStack {@link ItemStack} that will be crafted
     * @param maxCount    Optional to further limit the max size of the new outputStack
     * @return true if nothing prevents crafting
     */
    private static boolean canCraft(
        ItemStack inputStack,
        ItemStack outputStack,
        int inputCount,
        ItemStack resultStack,
        int maxCount) {
        if (!inputStack.isEmpty() && inputCount <= inputStack.getCount()) {
            // If the input slot contains enough items for crafting.
            if (resultStack.isEmpty()) {
                // If there is no valid recipe, then there is no valid result.
                return false;
            } else {
                // The number of crafting result items.
                final int resultCount = resultStack.getCount();
                if (outputStack.isEmpty()) {
                    // If the output slot is empty then anything is craftable.
                    return true;
                } else if (!ItemStack.areItemsAndComponentsEqual(outputStack, resultStack)) {
                    // If the output slot contains incompatible items then the new item is not craftable.
                    return false;
                } else {
                    // If there is enough space for the new items, then they are craftable.
                    final int outputCount = outputStack.getCount() + resultCount;
                    return ((outputCount <= maxCount) && (outputCount <= outputStack.getMaxCount())) ||
                        (outputCount <= resultStack.getMaxCount());
                }
            }
        } else {
            return false;
        }
    }

    /**
     * Check if a recipe can be used to craft new items.
     * <p>
     * Similar to {@link AbstractFurnaceBlockEntity}, but allows multiple input counts.
     *
     * @param dynamicRegistryManager Lookup table
     * @param recipe                 Using this recipe
     * @param input                  To craft from this item
     * @param inventory              Inventory of the entity
     * @param maxCount               To further limit the craftable items in the output slot
     * @return true if the recipe is craftable,
     * and there are enough items in the input slot,
     * and there is enough space in the output slot for the new items.
     */
    private static boolean canAcceptRecipeOutput(
        DynamicRegistryManager dynamicRegistryManager,
        @Nullable RecipeEntry<? extends AbstractProcessingRecipe> recipe,
        OneStackRecipeInput input,
        DefaultedList<ItemStack> inventory,
        int maxCount) {
        if (recipe != null) {
            final ItemStack inputStack = inventory.get(INPUT_SLOT_INDEX);
            final ItemStack outputStack = inventory.get(OUTPUT_SLOT_INDEX);
            final int inputCount = recipe.value().ingredientCount();
            final ItemStack resultStack = recipe.value().craft(input, dynamicRegistryManager);
            return canCraft(inputStack, outputStack, inputCount, resultStack, maxCount);
        }
        return false;
    }

    /**
     * Craft new items if the recipe can be used to craft them.
     * <p>
     * Similar to {@link AbstractFurnaceBlockEntity}, but allows multiple input and output counts.
     *
     * @param dynamicRegistryManager Lookup table
     * @param recipe                 Using this recipe
     * @param input                  To craft from this item
     * @param inventory              Inventory of the entity
     * @param maxCount               To further limit the craftable items in the output slot
     * @return true if the new items are crafted.
     */
    private static boolean craftRecipe(
        DynamicRegistryManager dynamicRegistryManager,
        @Nullable RecipeEntry<? extends AbstractProcessingRecipe> recipe,
        OneStackRecipeInput input,
        DefaultedList<ItemStack> inventory,
        int maxCount) {
        if (recipe != null) {
            final ItemStack inputStack = inventory.get(INPUT_SLOT_INDEX);
            final ItemStack outputStack = inventory.get(OUTPUT_SLOT_INDEX);
            final int inputCount = recipe.value().ingredientCount();
            final ItemStack resultStack = recipe.value().craft(input, dynamicRegistryManager);
            if (canCraft(inputStack, outputStack, inputCount, resultStack, maxCount)) {
                // Craft.
                final int resultCount = resultStack.getCount();
                if (outputStack.isEmpty()) {
                    // If the output slot is empty then craft it.
                    inventory.set(OUTPUT_SLOT_INDEX, resultStack.copy());
                } else {
                    // Else increment the item count in the output slot.
                    outputStack.increment(resultCount);
                }
                // Use up the needed amount of input items.
                inputStack.decrement(inputCount);
                return true;
            }
        }
        return false;
    }

    /**
     * Same as in {@link AbstractFurnaceBlockEntity}.
     */
    protected int getFuelTime(FuelRegistry fuelRegistry, ItemStack stack) {
        return fuelRegistry.getFuelTicks(stack);
    }

    /**
     * Same as in {@link AbstractFurnaceBlockEntity}.
     */
    private static int getCookTime(ServerWorld world, AbstractProcessingBlockEntity processor) {
        OneStackRecipeInput oneStackRecipeInput =
            new OneStackRecipeInput(processor.getStack(INPUT_SLOT_INDEX));
        return processor.matchGetter
            .getFirstMatch(oneStackRecipeInput, world)
            .map(recipe -> recipe.value().getCookingTime())
            .orElse(DEFAULT_COOK_TIME);
    }

    /**
     * Same as in {@link AbstractFurnaceBlockEntity}.
     */
    @Override
    public int[] getAvailableSlots(Direction side) {
        return switch (side) {
            case DOWN -> BOTTOM_SLOTS;
            case UP -> TOP_SLOTS;
            default -> SIDE_SLOTS;
        };
    }

    /**
     * Same as in {@link AbstractFurnaceBlockEntity}.
     */
    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return isValid(slot, stack);
    }

    /**
     * Anything can be extracted from the output slot, and nothing from the fuel slot.
     */
    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return slot != FUEL_SLOT_INDEX;
    }

    /**
     * Same as in {@link AbstractFurnaceBlockEntity}.
     */
    @Override
    public int size() {
        return inventory.size();
    }

    /**
     * Same as in {@link AbstractFurnaceBlockEntity}.
     */
    @Override
    protected DefaultedList<ItemStack> getHeldStacks() {
        return inventory;
    }

    /**
     * Same as in {@link AbstractFurnaceBlockEntity}.
     */
    @Override
    protected void setHeldStacks(DefaultedList<ItemStack> inventory) {
        this.inventory = inventory;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        final ItemStack oldStack = inventory.get(slot);
        final boolean same = !stack.isEmpty() && ItemStack.areItemsAndComponentsEqual(oldStack, stack);
        inventory.set(slot, stack);
        stack.capCount(getMaxCount(stack));
        if (slot == INPUT_SLOT_INDEX && !same && world instanceof ServerWorld serverWorld) {
            cookingTotalTime = getCookTime(serverWorld, this);
            cookingTimeSpent = 0;
            markDirty();
        }
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        return switch (slot) {
            case OUTPUT_SLOT_INDEX -> false;
            case FUEL_SLOT_INDEX -> {
                ItemStack fuelStack = inventory.get(FUEL_SLOT_INDEX);
                yield ((world != null) && world.getFuelRegistry().isFuel(stack)) ||
                    (stack.isOf(Items.BUCKET) && !fuelStack.isOf(Items.BUCKET));
            }
            default -> true;
        };
    }

    @Override
    public void setLastRecipe(@Nullable RecipeEntry<?> recipe) {
        if (recipe != null) {
            RegistryKey<Recipe<?>> registryKey = recipe.id();
            recipesUsed.addTo(registryKey, 1);
        }
    }

    @Nullable
    @Override
    public RecipeEntry<?> getLastRecipe() {
        return null;
    }

    @Override
    public void unlockLastRecipe(PlayerEntity player, List<ItemStack> ingredients) {
    }

    public void dropExperienceForRecipesUsed(ServerPlayerEntity player) {
        List<RecipeEntry<?>> list = getRecipesUsedAndDropExperience(player.getServerWorld(), player.getPos());
        player.unlockRecipes(list);
        for (RecipeEntry<?> recipeEntry : list) {
            if (recipeEntry != null) {
                player.onRecipeCrafted(recipeEntry, inventory);
            }
        }
        recipesUsed.clear();
    }

    public List<RecipeEntry<?>> getRecipesUsedAndDropExperience(ServerWorld world, Vec3d pos) {
        List<RecipeEntry<?>> list = Lists.newArrayList();
        for (Entry<RegistryKey<Recipe<?>>> entry : recipesUsed.reference2IntEntrySet()) {
            world.getRecipeManager().get(entry.getKey()).ifPresent(recipe -> {
                list.add(recipe);
                dropExperience(world, pos, entry.getIntValue(),
                    ((AbstractProcessingRecipe) recipe.value()).getExperience());
            });
        }
        return list;
    }

    /**
     * Drop (multiplier*experience) experience orbs
     *
     * @param world In this world
     * @param pos   Here
     */
    private static void dropExperience(ServerWorld world, Vec3d pos, int multiplier, float experience) {
        // Calculate
        final float mulExp = (float) multiplier * experience;
        // Convert to int
        int intExp = MathHelper.floor(mulExp);
        final double fraction = MathHelper.fractionalPart(mulExp);
        if (Math.random() < fraction) {
            intExp++;
        }
        // Drop
        ExperienceOrbEntity.spawn(world, pos, intExp);
    }

    @Override
    public void provideRecipeInputs(RecipeFinder finder) {
        for (ItemStack itemStack : inventory) {
            finder.addInput(itemStack);
        }
    }
}
