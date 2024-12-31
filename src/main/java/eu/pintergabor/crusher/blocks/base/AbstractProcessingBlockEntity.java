package eu.pintergabor.crusher.blocks.base;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.Reference2IntMap.Entry;
import java.util.List;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeFinder;
import net.minecraft.recipe.RecipeInputProvider;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.RecipeUnlocker;
import net.minecraft.recipe.ServerRecipeManager;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractProcessingBlockEntity
        extends LockableContainerBlockEntity
        implements SidedInventory, RecipeUnlocker, RecipeInputProvider {
    protected static final int INPUT_SLOT_INDEX = 0;
    protected static final int FUEL_SLOT_INDEX = 1;
    protected static final int OUTPUT_SLOT_INDEX = 2;
    public static final int BURN_TIME_PROPERTY_INDEX = 0;
    private static final int[] TOP_SLOTS = new int[]{0};
    private static final int[] BOTTOM_SLOTS = new int[]{2, 1};
    private static final int[] SIDE_SLOTS = new int[]{1};
    public static final int FUEL_TIME_PROPERTY_INDEX = 1;
    public static final int COOK_TIME_PROPERTY_INDEX = 2;
    public static final int COOK_TIME_TOTAL_PROPERTY_INDEX = 3;
    public static final int PROPERTY_COUNT = 4;
    public static final int DEFAULT_COOK_TIME = 200;
    public static final int field_31295 = 2;
    protected DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    int litTimeRemaining;
    int litTotalTime;
    int cookingTimeSpent;
    int cookingTotalTime;
    protected final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> litTimeRemaining;
                case 1 -> litTotalTime;
                case 2 -> cookingTimeSpent;
                case 3 -> cookingTotalTime;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0:
                    litTimeRemaining = value;
                    break;
                case 1:
                    litTotalTime = value;
                    break;
                case 2:
                    cookingTimeSpent = value;
                    break;
                case 3:
                    cookingTotalTime = value;
            }
        }

        @Override
        public int size() {
            return 4;
        }
    };
    private final Reference2IntOpenHashMap<RegistryKey<Recipe<?>>> recipesUsed = new Reference2IntOpenHashMap<>();
    private final ServerRecipeManager.MatchGetter<SingleStackRecipeInput, ? extends AbstractCookingRecipe> matchGetter;

    protected AbstractProcessingBlockEntity(
            BlockEntityType<?> blockEntityType,
            BlockPos pos,
            BlockState state,
            RecipeType<? extends AbstractCookingRecipe> recipeType
    ) {
        super(blockEntityType, pos, state);
        this.matchGetter = ServerRecipeManager.createCachedMatchGetter(recipeType);
    }

    protected boolean isBurning() {
        return this.litTimeRemaining > 0;
    }

    @Override
    protected void readNbt(NbtCompound nbt, WrapperLookup registries) {
        super.readNbt(nbt, registries);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        Inventories.readNbt(nbt, this.inventory, registries);
        this.cookingTimeSpent = nbt.getShort("cooking_time_spent");
        this.cookingTotalTime = nbt.getShort("cooking_total_time");
        this.litTimeRemaining = nbt.getShort("lit_time_remaining");
        this.litTotalTime = nbt.getShort("lit_total_time");
        NbtCompound nbtCompound = nbt.getCompound("RecipesUsed");

        for (String string : nbtCompound.getKeys()) {
            this.recipesUsed.put(RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(string)), nbtCompound.getInt(string));
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, WrapperLookup registries) {
        super.writeNbt(nbt, registries);
        nbt.putShort("cooking_time_spent", (short)this.cookingTimeSpent);
        nbt.putShort("cooking_total_time", (short)this.cookingTotalTime);
        nbt.putShort("lit_time_remaining", (short)this.litTimeRemaining);
        nbt.putShort("lit_total_time", (short)this.litTotalTime);
        Inventories.writeNbt(nbt, this.inventory, registries);
        NbtCompound nbtCompound = new NbtCompound();
        this.recipesUsed.forEach((recipeKey, count) -> nbtCompound.putInt(recipeKey.getValue().toString(), count));
        nbt.put("RecipesUsed", nbtCompound);
    }

    public static void tick(ServerWorld world, BlockPos pos, BlockState state, AbstractProcessingBlockEntity blockEntity) {
        boolean bl = blockEntity.isBurning();
        boolean bl2 = false;
        if (blockEntity.isBurning()) {
            blockEntity.litTimeRemaining--;
        }

        ItemStack itemStack = blockEntity.inventory.get(1);
        ItemStack itemStack2 = blockEntity.inventory.get(INPUT_SLOT_INDEX);
        boolean bl3 = !itemStack2.isEmpty();
        boolean bl4 = !itemStack.isEmpty();
        if (blockEntity.isBurning() || bl4 && bl3) {
            SingleStackRecipeInput singleStackRecipeInput = new SingleStackRecipeInput(itemStack2);
            RecipeEntry<? extends AbstractCookingRecipe> recipeEntry;
            if (bl3) {
                recipeEntry = blockEntity.matchGetter.getFirstMatch(singleStackRecipeInput, world).orElse(null);
            } else {
                recipeEntry = null;
            }

            int i = blockEntity.getMaxCountPerStack();
            if (!blockEntity.isBurning() && canAcceptRecipeOutput(world.getRegistryManager(), recipeEntry, singleStackRecipeInput, blockEntity.inventory, i)) {
                blockEntity.litTimeRemaining = blockEntity.getFuelTime(world.getFuelRegistry(), itemStack);
                blockEntity.litTotalTime = blockEntity.litTimeRemaining;
                if (blockEntity.isBurning()) {
                    bl2 = true;
                    if (bl4) {
                        Item item = itemStack.getItem();
                        itemStack.decrement(1);
                        if (itemStack.isEmpty()) {
                            blockEntity.inventory.set(1, item.getRecipeRemainder());
                        }
                    }
                }
            }

            if (blockEntity.isBurning() && canAcceptRecipeOutput(world.getRegistryManager(), recipeEntry, singleStackRecipeInput, blockEntity.inventory, i)) {
                blockEntity.cookingTimeSpent++;
                if (blockEntity.cookingTimeSpent == blockEntity.cookingTotalTime) {
                    blockEntity.cookingTimeSpent = 0;
                    blockEntity.cookingTotalTime = getCookTime(world, blockEntity);
                    if (craftRecipe(world.getRegistryManager(), recipeEntry, singleStackRecipeInput, blockEntity.inventory, i)) {
                        blockEntity.setLastRecipe(recipeEntry);
                    }

                    bl2 = true;
                }
            } else {
                blockEntity.cookingTimeSpent = 0;
            }
        } else if (!blockEntity.isBurning() && blockEntity.cookingTimeSpent > 0) {
            blockEntity.cookingTimeSpent = MathHelper.clamp(blockEntity.cookingTimeSpent - 2, 0, blockEntity.cookingTotalTime);
        }

        if (bl != blockEntity.isBurning()) {
            bl2 = true;
            state = state.with(AbstractFurnaceBlock.LIT, blockEntity.isBurning());
            world.setBlockState(pos, state, Block.NOTIFY_ALL);
        }

        if (bl2) {
            markDirty(world, pos, state);
        }
    }

    private static boolean canAcceptRecipeOutput(
            DynamicRegistryManager dynamicRegistryManager,
            @Nullable RecipeEntry<? extends AbstractCookingRecipe> recipe,
            SingleStackRecipeInput input,
            DefaultedList<ItemStack> inventory,
            int maxCount
    ) {
        if (!inventory.get(INPUT_SLOT_INDEX).isEmpty() && recipe != null) {
            ItemStack itemStack = recipe.value().craft(input, dynamicRegistryManager);
            if (itemStack.isEmpty()) {
                return false;
            } else {
                ItemStack itemStack2 = inventory.get(OUTPUT_SLOT_INDEX);
                if (itemStack2.isEmpty()) {
                    return true;
                } else if (!ItemStack.areItemsAndComponentsEqual(itemStack2, itemStack)) {
                    return false;
                } else {
                    return (itemStack2.getCount() < maxCount && itemStack2.getCount() < itemStack2.getMaxCount()) ||
                            itemStack2.getCount() < itemStack.getMaxCount();
                }
            }
        } else {
            return false;
        }
    }

    private static boolean craftRecipe(
            DynamicRegistryManager dynamicRegistryManager,
            @Nullable RecipeEntry<? extends AbstractCookingRecipe> recipe,
            SingleStackRecipeInput input,
            DefaultedList<ItemStack> inventory,
            int maxCount
    ) {
        if (recipe != null && canAcceptRecipeOutput(dynamicRegistryManager, recipe, input, inventory, maxCount)) {
            ItemStack inputStack = inventory.get(INPUT_SLOT_INDEX);
            ItemStack recipeResult = recipe.value().craft(input, dynamicRegistryManager);
            ItemStack outputStack = inventory.get(OUTPUT_SLOT_INDEX);
            if (outputStack.isEmpty()) {
                inventory.set(2, recipeResult.copy());
            } else if (ItemStack.areItemsAndComponentsEqual(outputStack, recipeResult)) {
                outputStack.increment(1);
            }

            if (inputStack.isOf(Blocks.WET_SPONGE.asItem()) && !inventory.get(FUEL_SLOT_INDEX).isEmpty() && inventory.get(FUEL_SLOT_INDEX).isOf(Items.BUCKET)) {
                inventory.set(FUEL_SLOT_INDEX, new ItemStack(Items.WATER_BUCKET));
            }

            inputStack.decrement(1);
            return true;
        } else {
            return false;
        }
    }

    protected int getFuelTime(FuelRegistry fuelRegistry, ItemStack stack) {
        return fuelRegistry.getFuelTicks(stack);
    }

    private static int getCookTime(ServerWorld world, AbstractProcessingBlockEntity processor) {
        SingleStackRecipeInput singleStackRecipeInput =
                new SingleStackRecipeInput(processor.getStack(INPUT_SLOT_INDEX));
        return processor.matchGetter
                .getFirstMatch(singleStackRecipeInput, world)
                .map(recipe -> recipe.value().getCookingTime())
                .orElse(DEFAULT_COOK_TIME);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return switch (side) {
            case DOWN -> BOTTOM_SLOTS;
            case UP -> TOP_SLOTS;
            default -> SIDE_SLOTS;
        };
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return this.isValid(slot, stack);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return dir != Direction.DOWN || slot != FUEL_SLOT_INDEX || stack.isOf(Items.WATER_BUCKET) || stack.isOf(Items.BUCKET);
    }

    @Override
    public int size() {
        return inventory.size();
    }

    @Override
    protected DefaultedList<ItemStack> getHeldStacks() {
        return inventory;
    }

    @Override
    protected void setHeldStacks(DefaultedList<ItemStack> inventory) {
        this.inventory = inventory;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        ItemStack itemStack = inventory.get(slot);
        boolean bl = !stack.isEmpty() && ItemStack.areItemsAndComponentsEqual(itemStack, stack);
        inventory.set(slot, stack);
        stack.capCount(this.getMaxCount(stack));
        if (slot == INPUT_SLOT_INDEX && !bl && world instanceof ServerWorld serverWorld) {
            cookingTotalTime = getCookTime(serverWorld, this);
            cookingTimeSpent = 0;
            markDirty();
        }
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        if (slot == OUTPUT_SLOT_INDEX) {
            return false;
        } else if (slot != FUEL_SLOT_INDEX) {
            return true;
        } else {
            ItemStack fuelStack = inventory.get(FUEL_SLOT_INDEX);
            return world.getFuelRegistry().isFuel(stack) || stack.isOf(Items.BUCKET) && !fuelStack.isOf(Items.BUCKET);
        }
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
                dropExperience(world, pos, entry.getIntValue(), ((AbstractCookingRecipe)recipe.value()).getExperience());
            });
        }
        return list;
    }

    /**
     * Drop (multiplier*experience) experience orbs
     * @param world In this world
     * @param pos Here
     */
    private static void dropExperience(ServerWorld world, Vec3d pos, int multiplier, float experience) {
        // Calculate
        final float mulExp = (float)multiplier * experience;
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
