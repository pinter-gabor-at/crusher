package eu.pintergabor.crusher.blocks.base;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import eu.pintergabor.crusher.recipe.base.AbstractProcessingRecipe;
import eu.pintergabor.crusher.recipe.base.OneStackRecipeInput;
import it.unimi.dsi.fastutil.objects.Reference2IntMap.Entry;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;


/**
 * Similar to {@link AbstractFurnaceBlockEntity}.
 * <li>But allows multiple input and output counts.
 * <li>And adds a hook for special processing.
 * <li>And removes the special handling of buckets in the fuel slot.
 */
public abstract class AbstractProcessingBlockEntity
	extends BaseContainerBlockEntity
	implements WorldlyContainer, RecipeCraftingHolder, StackedContentsCompatible {
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
	private static final Codec<Map<ResourceKey<Recipe<?>>, Integer>> CODEC =
		Codec.unboundedMap(Recipe.KEY_CODEC, Codec.INT);
	protected NonNullList<ItemStack> inventory = NonNullList.withSize(3, ItemStack.EMPTY);
	protected int litTimeRemaining;
	protected int litTotalTime;
	protected int cookingTimer;
	protected int cookingTotalTime;
	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	protected final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int index) {
			return switch (index) {
				case BURN_TIME_PROPERTY_INDEX -> litTimeRemaining;
				case FUEL_TIME_PROPERTY_INDEX -> litTotalTime;
				case COOK_TIME_PROPERTY_INDEX -> cookingTimer;
				case COOK_TIME_TOTAL_PROPERTY_INDEX -> cookingTotalTime;
				default -> 0;
			};
		}

		@Override
		public void set(int index, int value) {
			switch (index) {
				case BURN_TIME_PROPERTY_INDEX -> litTimeRemaining = value;
				case FUEL_TIME_PROPERTY_INDEX -> litTotalTime = value;
				case COOK_TIME_PROPERTY_INDEX -> cookingTimer = value;
				case COOK_TIME_TOTAL_PROPERTY_INDEX -> cookingTotalTime = value;
			}
		}

		@Override
		public int getCount() {
			return PROPERTY_COUNT;
		}
	};
	final Reference2IntOpenHashMap<ResourceKey<Recipe<?>>> recipesUsed =
		new Reference2IntOpenHashMap<>();
	final RecipeManager.CachedCheck<OneStackRecipeInput,
		? extends AbstractProcessingRecipe> matchGetter;

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
		matchGetter = RecipeManager.createCheck(recipeType);
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
	protected void loadAdditional(
		@NotNull CompoundTag tag,
		@NotNull HolderLookup.Provider registries
	) {
		super.loadAdditional(tag, registries);
		inventory = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(tag, inventory, registries);
		cookingTimer = tag.getShortOr("cooking_time_spent", (short) 0);
		cookingTotalTime = tag.getShortOr("cooking_total_time", (short) 0);
		litTimeRemaining = tag.getShortOr("lit_time_remaining", (short) 0);
		litTotalTime = tag.getShortOr("lit_total_time", (short) 0);
		recipesUsed.clear();
		recipesUsed.putAll(tag.read("RecipesUsed", CODEC).orElse(Map.of()));
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	protected void saveAdditional(
		@NotNull CompoundTag tag, @NotNull HolderLookup.Provider registries
	) {
		super.saveAdditional(tag, registries);
		tag.putShort("cooking_time_spent", (short) cookingTimer);
		tag.putShort("cooking_total_time", (short) cookingTotalTime);
		tag.putShort("lit_time_remaining", (short) litTimeRemaining);
		tag.putShort("lit_total_time", (short) litTotalTime);
		ContainerHelper.saveAllItems(tag, inventory, registries);
		CompoundTag nbtCompound = new CompoundTag();
		recipesUsed.forEach((recipeKey, count) ->
			nbtCompound.putInt(
				recipeKey.location().toString(),
				count));
		tag.put("RecipesUsed", nbtCompound);
	}

	/**
	 * Called when something has been crafted.
	 */
	protected void crafted() {
		// Do nothing.
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	protected int getFuelTime(FuelValues fuelValues, ItemStack stack) {
		return fuelValues.burnDuration(stack);
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	public int @NotNull [] getSlotsForFace(Direction side) {
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
	public boolean canPlaceItemThroughFace(
		int slot, @NotNull ItemStack stack, @Nullable Direction dir
	) {
		return canPlaceItem(slot, stack);
	}

	/**
	 * Anything can be extracted from the output slot, and nothing from the fuel slot.
	 */
	@Override
	public boolean canTakeItemThroughFace(
		int slot, @NotNull ItemStack stack, @NotNull Direction dir
	) {
		return slot != FUEL_SLOT_INDEX;
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	public int getContainerSize() {
		return inventory.size();
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	protected @NotNull NonNullList<ItemStack> getItems() {
		return inventory;
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	protected void setItems(@NotNull NonNullList<ItemStack> inventory) {
		this.inventory = inventory;
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	public void setItem(int slot, ItemStack stack) {
		final ItemStack oldStack = inventory.get(slot);
		final boolean same = !stack.isEmpty() && ItemStack.isSameItemSameComponents(oldStack, stack);
		inventory.set(slot, stack);
		stack.limitSize(getMaxStackSize(stack));
		if (slot == INPUT_SLOT_INDEX && !same && level instanceof ServerLevel serverLevel) {
			cookingTotalTime = StaticProcessingBlock.getCookTime(serverLevel, this);
			cookingTimer = 0;
			setChanged();
		}
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	public boolean canPlaceItem(int slot, @NotNull ItemStack stack) {
		return switch (slot) {
			case OUTPUT_SLOT_INDEX -> false;
			case FUEL_SLOT_INDEX -> {
				ItemStack fuelStack = inventory.get(FUEL_SLOT_INDEX);
				yield ((level != null) && level.fuelValues().isFuel(stack)) ||
					(stack.is(Items.BUCKET) && !fuelStack.is(Items.BUCKET));
			}
			default -> true;
		};
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	public void setRecipeUsed(@Nullable RecipeHolder<?> recipe) {
		if (recipe != null) {
			ResourceKey<Recipe<?>> ResourceKey = recipe.id();
			recipesUsed.addTo(ResourceKey, 1);
		}
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	public @Nullable RecipeHolder<?> getRecipeUsed() {
		return null;
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	public void awardUsedRecipes(@NotNull Player player, @NotNull List<ItemStack> ingredients) {
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	public void dropExperienceForRecipesUsed(ServerPlayer player) {
		List<RecipeHolder<?>> list = getRecipesToAwardAndPopExperience(
			player.serverLevel(), player.position());
		player.awardRecipes(list);
		for (RecipeHolder<?> recipeEntry : list) {
			if (recipeEntry != null) {
				player.triggerRecipeCrafted(recipeEntry, inventory);
			}
		}
		recipesUsed.clear();
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	public List<RecipeHolder<?>> getRecipesToAwardAndPopExperience(ServerLevel level, Vec3 pos) {
		List<RecipeHolder<?>> list = Lists.newArrayList();
		for (Entry<ResourceKey<Recipe<?>>> entry : recipesUsed.reference2IntEntrySet()) {
			level.recipeAccess().byKey(entry.getKey()).ifPresent(recipe -> {
				list.add(recipe);
				dropExperience(level, pos, entry.getIntValue(),
					((AbstractProcessingRecipe) recipe.value()).experience());
			});
		}
		return list;
	}

	/**
	 * Drop (multiplier*experience) experience orbs.
	 *
	 * @param level In this world.
	 * @param pos   Here.
	 */
	private static void dropExperience(
		ServerLevel level, Vec3 pos, int multiplier, float experience
	) {
		// Calculate.
		final float mulExp = (float) multiplier * experience;
		// Convert to int.
		int intExp = Mth.floor(mulExp);
		final double fraction = Mth.frac(mulExp);
		if (Math.random() < fraction) {
			intExp++;
		}
		// Drop.
		ExperienceOrb.award(level, pos, intExp);
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	public void fillStackedContents(@NotNull StackedItemContents finder) {
		for (ItemStack itemStack : inventory) {
			finder.accountStack(itemStack);
		}
	}
}
