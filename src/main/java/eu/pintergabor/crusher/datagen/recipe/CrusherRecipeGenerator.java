package eu.pintergabor.crusher.datagen.recipe;

import eu.pintergabor.crusher.datagen.ModItemTagProvider;
import eu.pintergabor.crusher.recipe.CrusherRecipe;
import eu.pintergabor.crusher.recipe.base.ProcessingRecipeJsonBuilder;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

/**
 * Generate crusher recipes
 */
public class CrusherRecipeGenerator extends RecipeGenerator {
    public float experience = 0.1f;
    public int cookingTime = 100;

    public CrusherRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
    }

    /**
     * Create crushing recipe from input item
     * @param input Input item
     * @param output Output item
     * @param count Number of output items
     */
    @SuppressWarnings({"unused", "SameParameterValue"})
    private void createRecipe(
            ItemConvertible input,
            ItemConvertible output,
            int count
    ) {
        ProcessingRecipeJsonBuilder.create(
                        Ingredient.ofItem(input),
                        RecipeCategory.MISC,
                        new ItemStack(output, count),
                        experience,
                        cookingTime,
                        CrusherRecipe::new)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter, getItemPath(output.asItem()) + "_from_crushing_" + getItemPath(input));
    }

    /**
     * Create crushing recipe from input item tag
     * @param tag Input item tag
     * @param output Output item
     * @param count Number of output items
     */
    @SuppressWarnings({"unused", "SameParameterValue"})
    private void createRecipe(
            TagKey<Item> tag,
            ItemConvertible output,
            int count
    ) {
        try {
            RegistryWrapper.Impl<Item> registryLookup = registries.getOrThrow(RegistryKeys.ITEM);
            Ingredient i = Ingredient.fromTag(registryLookup.getOrThrow(tag));
            ProcessingRecipeJsonBuilder.create(
                            i,
                            RecipeCategory.MISC,
                            new ItemStack(output, count),
                            experience,
                            cookingTime,
                            CrusherRecipe::new)
                    .criterion("has_" + tag.id().getPath(), conditionsFromTag(tag))
                    .offerTo(exporter, getItemPath(output.asItem()) + "_from_crushing_" + tag.id().getPath());
        } catch (IllegalStateException e) {
            // If tag does not exist, then do not generate the recipe.
        }
    }

    public void generate() {
        // Gravel from gravel sources.
        createRecipe(ModItemTagProvider.GRAVEL_SOURCES, Items.GRAVEL, 1);
        createRecipe(Items.STONE_AXE, Items.GRAVEL, 3);
        createRecipe(Items.STONE_PICKAXE, Items.GRAVEL, 3);
        createRecipe(Items.STONE_HOE, Items.GRAVEL, 2);
        createRecipe(Items.STONE_SHOVEL, Items.GRAVEL, 1);
        createRecipe(Items.STONE_SWORD, Items.GRAVEL, 2);
        // 8 cobblestone => 1 furnace => 9 gravel => 9 cobblestone
        // This allows the free generation of cobblestone.
        createRecipe(Items.FURNACE, Items.GRAVEL, 9);
        // Sand from sandstones and gravel.
        createRecipe(ModItemTagProvider.SAND_SOURCES, Items.SAND, 1);
        // Red sand from red sandstones and other redish blocks.
        createRecipe(ModItemTagProvider.RED_SAND_SOURCES, Items.RED_SAND, 1);
        // Blaze powder from blaze rod.
        createRecipe(Items.BLAZE_ROD, Items.BLAZE_POWDER, 2);
        // Bone meal from bone.
        createRecipe(Items.BONE, Items.BONE_MEAL, 6);
        createRecipe(Items.BONE_BLOCK, Items.BONE_MEAL, 20);
        // Sticks from planks and other wooden things.
        createRecipe(ItemTags.PLANKS, Items.STICK, 4);
        createRecipe(Items.STICK, Items.STICK, 1);
        createRecipe(Items.TORCH, Items.STICK, 1);
        createRecipe(ItemTags.SAPLINGS, Items.STICK, 2);
        createRecipe(Items.OAK_STAIRS, Items.STICK, 3);
        createRecipe(Items.SPRUCE_STAIRS, Items.STICK, 3);
        createRecipe(Items.BIRCH_STAIRS, Items.STICK, 3);
        createRecipe(Items.JUNGLE_STAIRS, Items.STICK, 3);
        createRecipe(Items.ACACIA_STAIRS, Items.STICK, 3);
        createRecipe(Items.DARK_OAK_STAIRS, Items.STICK, 3);
        createRecipe(Items.MANGROVE_STAIRS, Items.STICK, 3);
        createRecipe(Items.CHERRY_STAIRS, Items.STICK, 3);
        createRecipe(Items.PALE_OAK_STAIRS, Items.STICK, 3);
        createRecipe(Items.BAMBOO_STAIRS, Items.STICK, 3);
        createRecipe(Items.CRIMSON_STAIRS, Items.STICK, 3);
        createRecipe(Items.WARPED_STAIRS, Items.STICK, 3);
        createRecipe(Items.OAK_SLAB, Items.STICK, 1);
        createRecipe(Items.SPRUCE_SLAB, Items.STICK, 1);
        createRecipe(Items.BIRCH_SLAB, Items.STICK, 1);
        createRecipe(Items.JUNGLE_SLAB, Items.STICK, 1);
        createRecipe(Items.ACACIA_SLAB, Items.STICK, 1);
        createRecipe(Items.DARK_OAK_SLAB, Items.STICK, 1);
        createRecipe(Items.MANGROVE_SLAB, Items.STICK, 1);
        createRecipe(Items.CHERRY_SLAB, Items.STICK, 1);
        createRecipe(Items.PALE_OAK_SLAB, Items.STICK, 1);
        createRecipe(Items.BAMBOO_SLAB, Items.STICK, 1);
        createRecipe(Items.CRIMSON_SLAB, Items.STICK, 1);
        createRecipe(Items.WARPED_SLAB, Items.STICK, 1);
        createRecipe(Items.OAK_FENCE, Items.STICK, 3);
        createRecipe(Items.SPRUCE_FENCE, Items.STICK, 3);
        createRecipe(Items.BIRCH_FENCE, Items.STICK, 3);
        createRecipe(Items.JUNGLE_FENCE, Items.STICK, 3);
        createRecipe(Items.ACACIA_FENCE, Items.STICK, 3);
        createRecipe(Items.DARK_OAK_FENCE, Items.STICK, 3);
        createRecipe(Items.MANGROVE_FENCE, Items.STICK, 3);
        createRecipe(Items.CHERRY_FENCE, Items.STICK, 3);
        createRecipe(Items.PALE_OAK_FENCE, Items.STICK, 3);
        createRecipe(Items.BAMBOO_FENCE, Items.STICK, 3);
        createRecipe(Items.CRIMSON_FENCE, Items.STICK, 3);
        createRecipe(Items.WARPED_FENCE, Items.STICK, 3);
        createRecipe(Items.OAK_FENCE_GATE, Items.STICK, 8);
        createRecipe(Items.SPRUCE_FENCE_GATE, Items.STICK, 8);
        createRecipe(Items.BIRCH_FENCE_GATE, Items.STICK, 8);
        createRecipe(Items.JUNGLE_FENCE_GATE, Items.STICK, 8);
        createRecipe(Items.ACACIA_FENCE_GATE, Items.STICK, 8);
        createRecipe(Items.DARK_OAK_FENCE_GATE, Items.STICK, 8);
        createRecipe(Items.MANGROVE_FENCE_GATE, Items.STICK, 8);
        createRecipe(Items.CHERRY_FENCE_GATE, Items.STICK, 8);
        createRecipe(Items.PALE_OAK_FENCE_GATE, Items.STICK, 8);
        createRecipe(Items.BAMBOO_FENCE_GATE, Items.STICK, 8);
        createRecipe(Items.CRIMSON_FENCE_GATE, Items.STICK, 8);
        createRecipe(Items.WARPED_FENCE_GATE, Items.STICK, 8);
        createRecipe(Items.OAK_SIGN, Items.STICK, 4);
        createRecipe(Items.SPRUCE_SIGN, Items.STICK, 4);
        createRecipe(Items.BIRCH_SIGN, Items.STICK, 4);
        createRecipe(Items.JUNGLE_SIGN, Items.STICK, 4);
        createRecipe(Items.ACACIA_SIGN, Items.STICK, 4);
        createRecipe(Items.DARK_OAK_SIGN, Items.STICK, 4);
        createRecipe(Items.MANGROVE_SIGN, Items.STICK, 4);
        createRecipe(Items.CHERRY_SIGN, Items.STICK, 4);
        createRecipe(Items.PALE_OAK_SIGN, Items.STICK, 4);
        createRecipe(Items.BAMBOO_SIGN, Items.STICK, 4);
        createRecipe(Items.CRIMSON_SIGN, Items.STICK, 4);
        createRecipe(Items.WARPED_SIGN, Items.STICK, 4);
        createRecipe(Items.OAK_PRESSURE_PLATE, Items.STICK, 4);
        createRecipe(Items.SPRUCE_PRESSURE_PLATE, Items.STICK, 4);
        createRecipe(Items.BIRCH_PRESSURE_PLATE, Items.STICK, 4);
        createRecipe(Items.JUNGLE_PRESSURE_PLATE, Items.STICK, 4);
        createRecipe(Items.ACACIA_PRESSURE_PLATE, Items.STICK, 4);
        createRecipe(Items.DARK_OAK_PRESSURE_PLATE, Items.STICK, 4);
        createRecipe(Items.MANGROVE_PRESSURE_PLATE, Items.STICK, 4);
        createRecipe(Items.CHERRY_PRESSURE_PLATE, Items.STICK, 4);
        createRecipe(Items.PALE_OAK_PRESSURE_PLATE, Items.STICK, 4);
        createRecipe(Items.BAMBOO_PRESSURE_PLATE, Items.STICK, 4);
        createRecipe(Items.CRIMSON_PRESSURE_PLATE, Items.STICK, 4);
        createRecipe(Items.WARPED_PRESSURE_PLATE, Items.STICK, 4);
        // 7 sticks => 3 ladders => 9 sticks
        // This allows free generation of sticks, for those who discover it.
        createRecipe(Items.LADDER, Items.STICK, 3);
        // Planks from items made of planks.
        createRecipe(Items.OAK_DOOR, Items.OAK_PLANKS, 2);
        createRecipe(Items.SPRUCE_DOOR, Items.SPRUCE_PLANKS, 2);
        createRecipe(Items.BIRCH_DOOR, Items.BIRCH_PLANKS, 2);
        createRecipe(Items.JUNGLE_DOOR, Items.JUNGLE_PLANKS, 2);
        createRecipe(Items.ACACIA_DOOR, Items.ACACIA_PLANKS, 2);
        createRecipe(Items.DARK_OAK_DOOR, Items.DARK_OAK_PLANKS, 2);
        createRecipe(Items.MANGROVE_DOOR, Items.MANGROVE_PLANKS, 2);
        createRecipe(Items.CHERRY_DOOR, Items.CHERRY_PLANKS, 2);
        createRecipe(Items.PALE_OAK_DOOR, Items.PALE_OAK_PLANKS, 2);
        createRecipe(Items.BAMBOO_DOOR, Items.BAMBOO_PLANKS, 2);
        createRecipe(Items.CRIMSON_DOOR, Items.CRIMSON_PLANKS, 2);
        createRecipe(Items.WARPED_DOOR, Items.WARPED_PLANKS, 2);
        createRecipe(Items.OAK_TRAPDOOR, Items.OAK_PLANKS, 3);
        createRecipe(Items.SPRUCE_TRAPDOOR, Items.SPRUCE_PLANKS, 3);
        createRecipe(Items.BIRCH_TRAPDOOR, Items.BIRCH_PLANKS, 3);
        createRecipe(Items.JUNGLE_TRAPDOOR, Items.JUNGLE_PLANKS, 3);
        createRecipe(Items.ACACIA_TRAPDOOR, Items.ACACIA_PLANKS, 3);
        createRecipe(Items.DARK_OAK_TRAPDOOR, Items.DARK_OAK_PLANKS, 3);
        createRecipe(Items.MANGROVE_TRAPDOOR, Items.MANGROVE_PLANKS, 3);
        createRecipe(Items.CHERRY_TRAPDOOR, Items.CHERRY_PLANKS, 3);
        createRecipe(Items.PALE_OAK_TRAPDOOR, Items.PALE_OAK_PLANKS, 3);
        createRecipe(Items.BAMBOO_TRAPDOOR, Items.BAMBOO_PLANKS, 3);
        createRecipe(Items.CRIMSON_TRAPDOOR, Items.CRIMSON_PLANKS, 3);
        createRecipe(Items.WARPED_TRAPDOOR, Items.WARPED_PLANKS, 3);
        createRecipe(Items.OAK_BOAT, Items.OAK_PLANKS, 5);
        createRecipe(Items.SPRUCE_BOAT, Items.SPRUCE_PLANKS, 5);
        createRecipe(Items.BIRCH_BOAT, Items.BIRCH_PLANKS, 5);
        createRecipe(Items.JUNGLE_BOAT, Items.JUNGLE_PLANKS, 5);
        createRecipe(Items.ACACIA_BOAT, Items.ACACIA_PLANKS, 5);
        createRecipe(Items.DARK_OAK_BOAT, Items.DARK_OAK_PLANKS, 5);
        createRecipe(Items.MANGROVE_BOAT, Items.MANGROVE_PLANKS, 5);
        createRecipe(Items.CHERRY_BOAT, Items.CHERRY_PLANKS, 5);
        createRecipe(Items.PALE_OAK_BOAT, Items.PALE_OAK_PLANKS, 5);
        createRecipe(Items.BAMBOO_RAFT, Items.BAMBOO_PLANKS, 5);
        createRecipe(Items.CHEST, Items.OAK_PLANKS, 8);
        createRecipe(Items.CRAFTING_TABLE, Items.OAK_PLANKS, 4);
        createRecipe(Items.OAK_CHEST_BOAT, Items.OAK_PLANKS, 13);
        createRecipe(Items.SPRUCE_CHEST_BOAT, Items.SPRUCE_PLANKS, 13);
        createRecipe(Items.BIRCH_CHEST_BOAT, Items.BIRCH_PLANKS, 13);
        createRecipe(Items.JUNGLE_CHEST_BOAT, Items.JUNGLE_PLANKS, 13);
        createRecipe(Items.ACACIA_CHEST_BOAT, Items.ACACIA_PLANKS, 13);
        createRecipe(Items.DARK_OAK_CHEST_BOAT, Items.DARK_OAK_PLANKS, 13);
        createRecipe(Items.MANGROVE_CHEST_BOAT, Items.MANGROVE_PLANKS, 13);
        createRecipe(Items.CHERRY_CHEST_BOAT, Items.CHERRY_PLANKS, 13);
        createRecipe(Items.PALE_OAK_CHEST_BOAT, Items.PALE_OAK_PLANKS, 13);
        createRecipe(Items.BAMBOO_CHEST_RAFT, Items.BAMBOO_PLANKS, 13);
        createRecipe(Items.WOODEN_AXE, Items.OAK_PLANKS, 3);
        createRecipe(Items.WOODEN_PICKAXE, Items.OAK_PLANKS, 3);
        createRecipe(Items.WOODEN_HOE, Items.OAK_PLANKS, 2);
        createRecipe(Items.WOODEN_SHOVEL, Items.OAK_PLANKS, 1);
        createRecipe(Items.WOODEN_SWORD, Items.OAK_PLANKS, 2);
        // Coal from logs.
        createRecipe(ItemTags.LOGS, Items.CHARCOAL, 1);
        // Sugar, rotten flesh or dirt from food, except golden apple and golden carrot.
        createRecipe(ConventionalItemTags.BERRY_FOODS, Items.SUGAR, 1);
        createRecipe(ConventionalItemTags.CANDY_FOODS, Items.SUGAR, 1);
        createRecipe(ConventionalItemTags.COOKIE_FOODS, Items.SUGAR, 1);
        createRecipe(ConventionalItemTags.PIE_FOODS, Items.SUGAR, 1);
        createRecipe(ModItemTagProvider.NORMAL_FRUIT_FOODS, Items.SUGAR, 1);
        createRecipe(ConventionalItemTags.FOOD_POISONING_FOODS, Items.ROTTEN_FLESH, 1);
        createRecipe(ConventionalItemTags.RAW_FISH_FOODS, Items.ROTTEN_FLESH, 1);
        createRecipe(ConventionalItemTags.COOKED_FISH_FOODS, Items.ROTTEN_FLESH, 1);
        createRecipe(ConventionalItemTags.RAW_MEAT_FOODS, Items.ROTTEN_FLESH, 1);
        createRecipe(ConventionalItemTags.COOKED_MEAT_FOODS, Items.ROTTEN_FLESH, 1);
        createRecipe(ConventionalItemTags.BREAD_FOODS, Items.DIRT, 1);
        createRecipe(ConventionalItemTags.EDIBLE_WHEN_PLACED_FOODS, Items.DIRT, 1);
        createRecipe(ConventionalItemTags.SOUP_FOODS, Items.DIRT, 1);
        createRecipe(ModItemTagProvider.NORMAL_VEGETABLE_FOODS, Items.DIRT, 1);
        // 8 ingots + 1 apple => 1 golden apple => 9 ingots
        // This allows free generation of gold, for those who discover it.
        cookingTime = 200;
        createRecipe(Items.GOLDEN_APPLE, Items.RAW_GOLD, 9);
        cookingTime = 600;
        createRecipe(Items.ENCHANTED_GOLDEN_APPLE, Items.RAW_GOLD, 64);
        // 8 nugget + 1 carrot => 1 golden carrot => 1 ingots => 9 nuggets
        // This allows free generation of gold, for those who discover it.
        cookingTime = 100;
        createRecipe(Items.GOLDEN_CARROT, Items.RAW_GOLD, 1);
        // Snow from ice
        createRecipe(Items.ICE, Items.SNOW, 4);
        createRecipe(Items.BLUE_ICE, Items.SNOW, 4);
        createRecipe(Items.PACKED_ICE, Items.SNOW, 36);
        createRecipe(Items.SNOW, Items.SNOW, 1);
        createRecipe(Items.SNOW_BLOCK, Items.SNOW, 4);
        createRecipe(Items.SNOWBALL, Items.SNOW, 1);
        // String from soft things.
        createRecipe(ItemTags.WOOL, Items.STRING, 4);
        createRecipe(ItemTags.WOOL_CARPETS, Items.STRING, 1);
        createRecipe(Items.LEAD, Items.STRING, 2);
        // Wax from candles.
        createRecipe(ItemTags.CANDLES, Items.HONEYCOMB, 1);
        createRecipe(Items.HONEYCOMB_BLOCK, Items.HONEYCOMB, 4);
        // Glowstone.
        createRecipe(Items.GLOWSTONE, Items.GLOWSTONE_DUST, 4);
        // Sugar.
        createRecipe(Items.HONEY_BLOCK, Items.SUGAR, 12);
        createRecipe(Items.SUGAR_CANE, Items.SUGAR, 2);
        // Gunpowder from TNT.
        // 5 gunpowders + 4 sands => 1 TNT => 6 gunpowder.
        // This allows the generation of gunpowder from sand.
        createRecipe(Items.TNT, Items.GUNPOWDER, 6);
        // Sponge.
        createRecipe(Items.WET_SPONGE, Items.SPONGE, 1);
        // Dyes from colored items.
        createRecipe(Items.LILY_OF_THE_VALLEY, Items.WHITE_DYE, 2);
        createRecipe(Items.BONE_MEAL, Items.WHITE_DYE, 2);
        createRecipe(Items.ORANGE_TULIP, Items.ORANGE_DYE, 2);
        createRecipe(Items.TORCHFLOWER, Items.ORANGE_DYE, 2);
        createRecipe(Items.OPEN_EYEBLOSSOM, Items.ORANGE_DYE, 2);
        createRecipe(Items.ALLIUM, Items.MAGENTA_DYE, 2);
        createRecipe(Items.LILAC, Items.MAGENTA_DYE, 4);
        createRecipe(Items.BLUE_ORCHID, Items.LIGHT_BLUE_DYE, 2);
        createRecipe(Items.DANDELION, Items.YELLOW_DYE, 2);
        createRecipe(Items.SUNFLOWER, Items.YELLOW_DYE, 4);
        createRecipe(Items.PEONY, Items.PINK_DYE, 4);
        createRecipe(Items.PINK_PETALS, Items.PINK_DYE, 2);
        createRecipe(Items.PINK_TULIP, Items.PINK_DYE, 2);
        createRecipe(Items.CLOSED_EYEBLOSSOM, Items.GRAY_DYE, 2);
        createRecipe(Items.OXEYE_DAISY, Items.LIGHT_GRAY_DYE, 2);
        createRecipe(Items.WHITE_TULIP, Items.LIGHT_GRAY_DYE, 2);
        createRecipe(Items.AZURE_BLUET, Items.LIGHT_GRAY_DYE, 2);
        createRecipe(Items.PITCHER_PLANT, Items.CYAN_DYE, 4);
        createRecipe(Items.LAPIS_LAZULI, Items.BLUE_DYE, 3);
        createRecipe(Items.LAPIS_BLOCK, Items.BLUE_DYE, 32);
        createRecipe(Items.CORNFLOWER, Items.BLUE_DYE, 2);
        createRecipe(Items.COCOA_BEANS, Items.BROWN_DYE, 2);
        createRecipe(Items.CACTUS, Items.GREEN_DYE, 2);
        createRecipe(Items.ROSE_BUSH, Items.RED_DYE, 4);
        createRecipe(Items.POPPY, Items.RED_DYE, 2);
        createRecipe(Items.BEETROOT, Items.RED_DYE, 2);
        createRecipe(Items.RED_TULIP, Items.RED_DYE, 2);
        createRecipe(Items.REDSTONE, Items.RED_DYE, 3);
        createRecipe(Items.REDSTONE_BLOCK, Items.RED_DYE, 32);
        createRecipe(Items.INK_SAC, Items.BLACK_DYE, 2);
        createRecipe(Items.WITHER_ROSE, Items.BLACK_DYE, 2);
        createRecipe(Items.CHARCOAL, Items.BLACK_DYE, 4);
        createRecipe(Items.COAL, Items.BLACK_DYE, 3);
        createRecipe(Items.COAL_BLOCK, Items.BLACK_DYE, 32);
        // Concrete powder from concrete.
        createRecipe(Items.WHITE_CONCRETE, Items.WHITE_CONCRETE_POWDER, 1);
        createRecipe(Items.ORANGE_CONCRETE, Items.ORANGE_CONCRETE_POWDER, 1);
        createRecipe(Items.MAGENTA_CONCRETE, Items.MAGENTA_CONCRETE_POWDER, 1);
        createRecipe(Items.LIGHT_BLUE_CONCRETE, Items.LIGHT_BLUE_CONCRETE_POWDER, 1);
        createRecipe(Items.YELLOW_CONCRETE, Items.YELLOW_CONCRETE_POWDER, 1);
        createRecipe(Items.LIME_CONCRETE, Items.LIME_CONCRETE_POWDER, 1);
        createRecipe(Items.PINK_CONCRETE, Items.PINK_CONCRETE_POWDER, 1);
        createRecipe(Items.GRAY_CONCRETE, Items.GRAY_CONCRETE_POWDER, 1);
        createRecipe(Items.LIGHT_GRAY_CONCRETE, Items.LIGHT_GRAY_CONCRETE_POWDER, 1);
        createRecipe(Items.CYAN_CONCRETE, Items.CYAN_CONCRETE_POWDER, 1);
        createRecipe(Items.PURPLE_CONCRETE, Items.PURPLE_CONCRETE_POWDER, 1);
        createRecipe(Items.BLUE_CONCRETE, Items.BLUE_CONCRETE_POWDER, 1);
        createRecipe(Items.BROWN_CONCRETE, Items.BROWN_CONCRETE_POWDER, 1);
        createRecipe(Items.GREEN_CONCRETE, Items.GREEN_CONCRETE_POWDER, 1);
        createRecipe(Items.RED_CONCRETE, Items.RED_CONCRETE_POWDER, 1);
        createRecipe(Items.BLACK_CONCRETE, Items.BLACK_CONCRETE_POWDER, 1);
        // Copper from things made of copper.
        createRecipe(Items.COPPER_ORE, Items.RAW_COPPER, 4);
        createRecipe(Items.DEEPSLATE_COPPER_ORE, Items.RAW_COPPER, 4);
        createRecipe(Items.COPPER_DOOR, Items.RAW_COPPER, 2);
        createRecipe(Items.WAXED_COPPER_DOOR, Items.RAW_COPPER, 2);
        createRecipe(Items.EXPOSED_COPPER_DOOR, Items.RAW_COPPER, 2);
        createRecipe(Items.WAXED_EXPOSED_COPPER_DOOR, Items.RAW_COPPER, 2);
        createRecipe(Items.WEATHERED_COPPER_DOOR, Items.RAW_COPPER, 2);
        createRecipe(Items.WAXED_WEATHERED_COPPER_DOOR, Items.RAW_COPPER, 2);
        createRecipe(Items.OXIDIZED_COPPER_DOOR, Items.RAW_COPPER, 2);
        createRecipe(Items.WAXED_OXIDIZED_COPPER_DOOR, Items.RAW_COPPER, 2);
        createRecipe(Items.COPPER_TRAPDOOR, Items.RAW_COPPER, 4);
        createRecipe(Items.WAXED_COPPER_TRAPDOOR, Items.RAW_COPPER, 4);
        createRecipe(Items.EXPOSED_COPPER_TRAPDOOR, Items.RAW_COPPER, 4);
        createRecipe(Items.WAXED_EXPOSED_COPPER_TRAPDOOR, Items.RAW_COPPER, 4);
        createRecipe(Items.WEATHERED_COPPER_TRAPDOOR, Items.RAW_COPPER, 4);
        createRecipe(Items.WAXED_WEATHERED_COPPER_TRAPDOOR, Items.RAW_COPPER, 4);
        createRecipe(Items.OXIDIZED_COPPER_TRAPDOOR, Items.RAW_COPPER, 4);
        createRecipe(Items.WAXED_OXIDIZED_COPPER_TRAPDOOR, Items.RAW_COPPER, 4);
        createRecipe(Items.COPPER_BLOCK, Items.RAW_COPPER, 9);
        createRecipe(Items.RAW_COPPER_BLOCK, Items.RAW_COPPER, 9);
        createRecipe(Items.WAXED_COPPER_BLOCK, Items.RAW_COPPER, 9);
        createRecipe(Items.EXPOSED_COPPER, Items.RAW_COPPER, 9);
        createRecipe(Items.WAXED_EXPOSED_COPPER, Items.RAW_COPPER, 9);
        createRecipe(Items.WEATHERED_COPPER, Items.RAW_COPPER, 9);
        createRecipe(Items.WAXED_WEATHERED_COPPER, Items.RAW_COPPER, 9);
        createRecipe(Items.OXIDIZED_COPPER, Items.RAW_COPPER, 9);
        createRecipe(Items.WAXED_OXIDIZED_COPPER, Items.RAW_COPPER, 9);
        // Iron from things made of iron.
        createRecipe(Items.IRON_ORE, Items.RAW_IRON, 4);
        createRecipe(Items.DEEPSLATE_IRON_ORE, Items.RAW_IRON, 4);
        createRecipe(Items.BUCKET, Items.RAW_IRON, 3);
        createRecipe(Items.WATER_BUCKET, Items.RAW_IRON, 3);
        createRecipe(Items.LAVA_BUCKET, Items.RAW_IRON, 3);
        createRecipe(Items.MILK_BUCKET, Items.RAW_IRON, 3);
        createRecipe(Items.TROPICAL_FISH_BUCKET, Items.RAW_IRON, 3);
        createRecipe(Items.SALMON_BUCKET, Items.RAW_IRON, 3);
        createRecipe(Items.PUFFERFISH_BUCKET, Items.RAW_IRON, 3);
        createRecipe(Items.COD_BUCKET, Items.RAW_IRON, 3);
        createRecipe(Items.AXOLOTL_BUCKET, Items.RAW_IRON, 3);
        createRecipe(Items.TADPOLE_BUCKET, Items.RAW_IRON, 3);
        createRecipe(Items.POWDER_SNOW_BUCKET, Items.RAW_IRON, 3);
        createRecipe(Items.SHEARS, Items.RAW_IRON, 2);
        createRecipe(Items.IRON_DOOR, Items.RAW_IRON, 2);
        createRecipe(Items.IRON_TRAPDOOR, Items.RAW_IRON, 4);
        createRecipe(Items.CAULDRON, Items.RAW_IRON, 7);
        createRecipe(Items.IRON_AXE, Items.RAW_IRON, 3);
        createRecipe(Items.IRON_PICKAXE, Items.RAW_IRON, 3);
        createRecipe(Items.IRON_HOE, Items.RAW_IRON, 2);
        createRecipe(Items.IRON_SHOVEL, Items.RAW_IRON, 1);
        createRecipe(Items.IRON_SWORD, Items.RAW_IRON, 2);
        createRecipe(Items.IRON_BLOCK, Items.RAW_IRON, 9);
        createRecipe(Items.RAW_IRON_BLOCK, Items.RAW_IRON, 9);
        createRecipe(Items.IRON_HELMET, Items.RAW_IRON, 5);
        createRecipe(Items.IRON_CHESTPLATE, Items.RAW_IRON, 8);
        createRecipe(Items.IRON_LEGGINGS, Items.RAW_IRON, 7);
        createRecipe(Items.IRON_BOOTS, Items.RAW_IRON, 4);
        createRecipe(Items.CHAINMAIL_HELMET, Items.RAW_IRON, 5);
        createRecipe(Items.CHAINMAIL_CHESTPLATE, Items.RAW_IRON, 8);
        createRecipe(Items.CHAINMAIL_LEGGINGS, Items.RAW_IRON, 7);
        createRecipe(Items.CHAINMAIL_BOOTS, Items.RAW_IRON, 4);
        createRecipe(Items.IRON_HORSE_ARMOR, Items.RAW_IRON, 7);
        // Iron nuggets from iron things.
        // 11 nuggets => 2 nuggets + 1 ingot => 1 chain => 11 nuggets
        createRecipe(Items.CHAIN, Items.IRON_NUGGET, 11);
        // 54 nuggets => 6 ingots => 16 iron bars => 64 nuggets
        // This allows free generation of iron, for those who discover it.
        createRecipe(Items.IRON_BARS, Items.IRON_NUGGET, 4);
        // Gold from things made of gold.
        cookingTime = 150;
        createRecipe(Items.GOLD_ORE, Items.RAW_GOLD, 4);
        createRecipe(Items.DEEPSLATE_GOLD_ORE, Items.RAW_GOLD, 4);
        createRecipe(Items.NETHER_GOLD_ORE, Items.RAW_GOLD, 1);
        createRecipe(Items.GOLDEN_AXE, Items.RAW_GOLD, 3);
        createRecipe(Items.GOLDEN_PICKAXE, Items.RAW_GOLD, 3);
        createRecipe(Items.GOLDEN_HOE, Items.RAW_GOLD, 2);
        createRecipe(Items.GOLDEN_SHOVEL, Items.RAW_GOLD, 1);
        createRecipe(Items.GOLDEN_SWORD, Items.RAW_GOLD, 2);
        createRecipe(Items.GOLD_BLOCK, Items.RAW_GOLD, 9);
        createRecipe(Items.RAW_GOLD_BLOCK, Items.RAW_GOLD, 9);
        createRecipe(Items.GOLDEN_HELMET, Items.RAW_GOLD, 5);
        createRecipe(Items.GOLDEN_CHESTPLATE, Items.RAW_GOLD, 8);
        createRecipe(Items.GOLDEN_LEGGINGS, Items.RAW_GOLD, 7);
        createRecipe(Items.GOLDEN_BOOTS, Items.RAW_GOLD, 4);
        createRecipe(Items.GOLDEN_HORSE_ARMOR, Items.RAW_GOLD, 7);
        // Diamond from things made of diamonds.
        cookingTime = 200;
        createRecipe(Items.DIAMOND_ORE, Items.DIAMOND, 4);
        createRecipe(Items.DEEPSLATE_DIAMOND_ORE, Items.DIAMOND, 4);
        createRecipe(Items.DIAMOND_AXE, Items.DIAMOND, 3);
        createRecipe(Items.DIAMOND_PICKAXE, Items.DIAMOND, 3);
        createRecipe(Items.DIAMOND_HOE, Items.DIAMOND, 2);
        createRecipe(Items.DIAMOND_SHOVEL, Items.DIAMOND, 1);
        createRecipe(Items.DIAMOND_SWORD, Items.DIAMOND, 2);
        createRecipe(Items.DIAMOND_BLOCK, Items.DIAMOND, 9);
        createRecipe(Items.DIAMOND_HELMET, Items.DIAMOND, 5);
        createRecipe(Items.DIAMOND_CHESTPLATE, Items.DIAMOND, 8);
        createRecipe(Items.DIAMOND_LEGGINGS, Items.DIAMOND, 7);
        createRecipe(Items.DIAMOND_BOOTS, Items.DIAMOND, 4);
        createRecipe(Items.DIAMOND_HORSE_ARMOR, Items.DIAMOND, 7);
        // Sand from sand fast.
        experience = 1.0f;
        cookingTime = 10;
        createRecipe(Items.SAND, Items.SAND, 1);
        createRecipe(Items.RED_SAND, Items.RED_SAND, 1);
        // Diamond from diamond, even faster.
        experience = 1.5f;
        cookingTime = 8;
        createRecipe(Items.DIAMOND, Items.DIAMOND, 1);
    }

}
