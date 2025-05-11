package eu.pintergabor.crusher.main;

import static net.minecraft.world.item.CreativeModeTab.TabVisibility;

import eu.pintergabor.crusher.blocks.ModBlocks;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.stream.IntStream;


public final class CreativeTabs {

	/**
	 * Add one or more items to creative tabs.
	 */
	private static void add(
		BuildCreativeModeTabContentsEvent event, ItemLike... items
	) {
		// Insert all items in the list after the blast furnace
		// in the same order as in the list.
		final ItemStack mark = new ItemStack(Items.BLAST_FURNACE);
		IntStream.rangeClosed(1, items.length)
			.mapToObj(i -> items[items.length-i])
			.forEach(item -> event.insertAfter(
				mark, new ItemStack(item), TabVisibility.PARENT_AND_SEARCH_TABS));
	}

	/**
	 * Add items to creative tabs.
	 */
	public static void listener(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
			add(event, ModBlocks.CRUSHER_ITEM, ModBlocks.COMPRESOR_ITEM);
		}
	}
}
