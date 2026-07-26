package eu.pintergabor.crusher.blocks.base;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import eu.pintergabor.crusher.recipe.base.AbstractProcessingRecipe;
import eu.pintergabor.crusher.recipe.base.OneStackRecipeInput;
import it.unimi.dsi.fastutil.objects.Reference2IntMap.Entry;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;

import net.neoforged.neoforge.transfer.transaction.SnapshotJournal;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
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
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;


/**
 * Similar to {@link AbstractFurnaceBlockEntity}.
 * <li>But allows multiple input and output counts.
 * <li>And adds a hook for special processing.
 * <li>And removes the special handling of buckets in the fuel slot.
 */
public abstract non-sealed class AbstractProcessingBlockEntity
	extends StaticProcessingBlockEntity
	implements WorldlyContainer, RecipeCraftingHolder, StackedContentsCompatible {

	private static final int[] SLOTS_FOR_UP = new int[]{SLOT_INPUT};
	private static final int[] SLOTS_FOR_DOWN = new int[]{SLOT_RESULT};
	private static final int[] SLOTS_FOR_SIDES = new int[]{SLOT_FUEL};

	private static final Codec<Map<ResourceKey<Recipe<?>>, Integer>> RECIPES_USED_CODEC =
		Codec.unboundedMap(Recipe.KEY_CODEC, Codec.INT);;
	protected NonNullList<ItemStack> items;
	protected int litTimeRemaining;
	protected int litTotalTime;
	protected int processingTimer;
	protected int processingTotalTime;
	protected final ContainerData dataAccess;
	private final Reference2IntOpenHashMap<ResourceKey<Recipe<?>>> recipesUsed;
	public final RecipeManager.CachedCheck<OneStackRecipeInput,
		? extends AbstractProcessingRecipe> quickCheck;
	private final RecipeType<? extends AbstractProcessingRecipe> recipeType;
	private boolean needsReset;
	private final SnapshotJournal<Boolean> cookingResetJournal;

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	protected AbstractProcessingBlockEntity(
		final @NonNull BlockEntityType<?> blockEntityType,
		final @NonNull BlockPos pos,
		final @NonNull BlockState state,
		final @NonNull RecipeType<? extends AbstractProcessingRecipe> recipeType
	) {
		super(blockEntityType, pos, state);
		items = NonNullList.withSize(3, ItemStack.EMPTY);
		dataAccess = new ContainerData() {
			@Override
			public int get(int index) {
				return switch (index) {
					case DATA_LIT_TIME -> 32767 < litTotalTime ?
						Mth.floor(litTimeRemaining / (double) litTotalTime * 32767.0F) :
						litTimeRemaining;
					case DATA_LIT_DURATION -> Math.min(litTotalTime, 32767);
					case DATA_PROGRESS -> processingTimer;
					case DATA_TOTAL_TIME -> processingTotalTime;
					default -> 0;
				};
			}

			@Override
			public void set(int index, int value) {
				switch (index) {
					case DATA_LIT_TIME -> litTimeRemaining = value;
					case DATA_LIT_DURATION -> litTotalTime = value;
					case DATA_PROGRESS -> processingTimer = value;
					case DATA_TOTAL_TIME -> processingTotalTime = value;
				}
			}

			@Override
			public int getCount() {
				return NUM_DATA_VALUES;
			}
		};
		recipesUsed = new Reference2IntOpenHashMap<>();
		needsReset = false;
		cookingResetJournal = new SnapshotJournal<>() {
			protected Boolean createSnapshot() {
				return needsReset;
			}

			protected void revertToSnapshot(Boolean snapshot) {
				needsReset = snapshot;
			}

			protected void onRootCommit(Boolean originalState) {
				if (needsReset) {
					if (level instanceof ServerLevel serverLevel) {
						processingTotalTime = getTotalProcessingTime(
							serverLevel, AbstractProcessingBlockEntity.this);
						processingTimer = 0;
					}
					needsReset = false;
				}
			}
		};
		this.quickCheck = RecipeManager.createCheck(recipeType);
		this.recipeType = recipeType;
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	protected boolean isLit() {
		return 0 < litTimeRemaining;
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	protected void loadAdditional(final @NonNull ValueInput input) {
		super.loadAdditional(input);
		items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(input, items);
		processingTimer = input.getShortOr("processing_time_spent", (short) 0);
		processingTotalTime = input.getShortOr("processing_total_time", (short) 0);
		litTimeRemaining = input.getShortOr("lit_time_remaining", (short) 0);
		litTotalTime = input.getShortOr("lit_total_time", (short) 0);
		recipesUsed.clear();
		recipesUsed.putAll(input.read("RecipesUsed", RECIPES_USED_CODEC).orElse(Map.of()));
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	protected void saveAdditional(final @NonNull ValueOutput output) {
		super.saveAdditional(output);
		output.putShort("processing_time_spent", (short) processingTimer);
		output.putShort("processing_total_time", (short) processingTotalTime);
		output.putShort("lit_time_remaining", (short) litTimeRemaining);
		output.putShort("lit_total_time", (short) litTotalTime);
		ContainerHelper.saveAllItems(output, items);
		output.store("RecipesUsed", RECIPES_USED_CODEC, recipesUsed);
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
	protected int getBurnDuration(
		final @NonNull FuelValues fuelValues,
		final @NonNull ItemStack stack
	) {
		return stack.getBurnTime(recipeType, fuelValues);
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	public int @NonNull [] getSlotsForFace(final @NonNull Direction side) {
		return switch (side) {
			case DOWN -> SLOTS_FOR_DOWN;
			case UP -> SLOTS_FOR_UP;
			default -> SLOTS_FOR_SIDES;
		};
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	public boolean canPlaceItemThroughFace(
		final int slot,
		final @NonNull ItemStack stack,
		final @Nullable Direction dir
	) {
		return canPlaceItem(slot, stack);
	}

	/**
	 * Anything can be extracted from the output slot, and nothing from the fuel slot.
	 */
	@Override
	public boolean canTakeItemThroughFace(
		final int slot,
		final @NonNull ItemStack stack,
		final @NonNull Direction dir
	) {
		return slot != SLOT_FUEL;
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	public int getContainerSize() {
		return items.size();
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	protected @NonNull NonNullList<ItemStack> getItems() {
		return items;
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	protected void setItems(@NonNull NonNullList<ItemStack> inventory) {
		this.items = inventory;
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	public void setItem(
		final int slot,
		final @NonNull ItemStack stack,
		final boolean insideTransaction
	) {
		final ItemStack oldStack = items.get(slot);
		final boolean same = !stack.isEmpty() &&
			ItemStack.isSameItemSameComponents(oldStack, stack);
		items.set(slot, stack);
		stack.limitSize(getMaxStackSize(stack));
		if (slot == SLOT_INPUT && !same &&
			level instanceof ServerLevel serverLevel && !insideTransaction) {
			processingTotalTime = getProcessingTime(serverLevel, this);
			processingTimer = 0;
			setChanged();
		}
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	public void setItem(final int slot, final @NonNull ItemStack stack) {
		setItem(slot, stack, false);
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	public void onTransfer(
		final int slot, final int amountChange,
		final @NonNull TransactionContext transaction
	) {
		if (slot == SLOT_INPUT) {
			int currentAmount = getItem(slot).getCount();
			if (currentAmount == 0 || currentAmount == amountChange) {
				cookingResetJournal.updateSnapshots(transaction);
				needsReset = true;
			}
		}
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	public boolean canPlaceItem(final int slot, final @NonNull ItemStack stack) {
		return switch (slot) {
			case SLOT_RESULT -> false;
			case SLOT_FUEL -> {
				ItemStack fuelStack = items.get(SLOT_FUEL);
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
	public void setRecipeUsed(final @Nullable RecipeHolder<?> recipe) {
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
	public void awardUsedRecipes(
		final @NonNull Player player,
		final @NonNull List<ItemStack> ingredients
	) {
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	public void awardUsedRecipesAndPopExperience(final @NonNull ServerPlayer player) {
		final List<RecipeHolder<?>> list = getRecipesToAwardAndPopExperience(
			player.level(), player.position());
		player.awardRecipes(list);
		list.stream()
			.filter(Objects::nonNull)
			.forEach(recipeHolder ->
				player.triggerRecipeCrafted(recipeHolder, items));
		recipesUsed.clear();
	}

	/**
	 * Drop (multiplier*experience) experience orbs.
	 *
	 * @param level In this world.
	 * @param pos   Here.
	 */
	private static void createExperience(
		final @NonNull ServerLevel level,
		final @NonNull Vec3 pos,
		final int multiplier, final float experience
	) {
		// Calculate.
		final float mulExp = (float) multiplier * experience;
		// Convert it to int.
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
	public List<RecipeHolder<?>> getRecipesToAwardAndPopExperience(
		final @NonNull ServerLevel level,
		final @NonNull Vec3 pos
	) {
		final List<RecipeHolder<?>> list = Lists.newArrayList();
		for (Entry<ResourceKey<Recipe<?>>> entry : recipesUsed.reference2IntEntrySet()) {
			level.recipeAccess().byKey(entry.getKey()).ifPresent(recipe -> {
				list.add(recipe);
				createExperience(level, pos, entry.getIntValue(),
					((AbstractProcessingRecipe) recipe.value()).experience());
			});
		}
		return list;
	}

	/**
	 * Same as in {@link AbstractFurnaceBlockEntity}.
	 */
	@Override
	public void fillStackedContents(final @NonNull StackedItemContents stackedItemContents) {
		items.forEach(stackedItemContents::accountStack);
	}
}
