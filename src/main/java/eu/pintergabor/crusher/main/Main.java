package eu.pintergabor.crusher.main;

import com.mojang.serialization.MapCodec;
import eu.pintergabor.crusher.Global;
import eu.pintergabor.crusher.ModCommon;
import eu.pintergabor.crusher.blocks.ModBlocks;
import eu.pintergabor.crusher.blocks.ModStats;
import eu.pintergabor.crusher.recipe.CompressorRecipe;
import eu.pintergabor.crusher.recipe.CrusherRecipe;
import eu.pintergabor.crusher.screen.ModScreenHandlers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;


public final class Main {
	// Registries.
	public static final DeferredRegister.Items ITEMS =
		DeferredRegister.createItems(Global.MODID);
	public static final DeferredRegister<MapCodec<? extends Block>> BLOCK_TYPES =
		DeferredRegister.create(Registries.BLOCK_TYPE, Global.MODID);
	public static final DeferredRegister.Blocks BLOCKS =
		DeferredRegister.createBlocks(Global.MODID);
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
		DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Global.MODID);
	public static final DeferredRegister<RecipeBookCategory> RECIPE_BOOK_CATEGORIES =
		DeferredRegister.create(Registries.RECIPE_BOOK_CATEGORY, Global.MODID);
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
		DeferredRegister.create(Registries.RECIPE_TYPE, Global.MODID);
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
		DeferredRegister.create(Registries.RECIPE_SERIALIZER, Global.MODID);
	public static final DeferredRegister<ResourceLocation> STATS =
		DeferredRegister.create(Registries.CUSTOM_STAT, Global.MODID);
	public static final DeferredRegister<MenuType<?>> MENU_TYPES =
		DeferredRegister.create(Registries.MENU, Global.MODID);

	/**
	 * Called from {@link ModCommon}.
	 */
	public static void init(IEventBus modEventBus) {
		// Items and blocks.
		ModBlocks.init();
		BLOCK_TYPES.register(modEventBus);
		BLOCKS.register(modEventBus);
		ITEMS.register(modEventBus);
		BLOCK_ENTITY_TYPES.register(modEventBus);
		// Recipes.
		CrusherRecipe.init();
		CompressorRecipe.init();
		RECIPE_BOOK_CATEGORIES.register(modEventBus);
		RECIPE_TYPES.register(modEventBus);
		RECIPE_SERIALIZERS.register(modEventBus);
		// Statistics.
		ModStats.init();
		STATS.register(modEventBus);
		// Menus.
		ModScreenHandlers.init(modEventBus);
		MENU_TYPES.register(modEventBus);
	}
}
