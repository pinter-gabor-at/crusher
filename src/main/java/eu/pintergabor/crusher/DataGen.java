package eu.pintergabor.crusher;

import eu.pintergabor.crusher.datagen.ModBlockLootTableGenerator;
import eu.pintergabor.crusher.datagen.ModBlockTagProvider;
import eu.pintergabor.crusher.datagen.ModItemTagProvider;
import eu.pintergabor.crusher.datagen.ModModelProvider;
import eu.pintergabor.crusher.datagen.ModRecipeRunner;

import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;


@OnlyIn(Dist.CLIENT)
public final class DataGen {

	public DataGen() {
		// Static class.
	}

	public static void listener(GatherDataEvent.Client event) {
		// Create blocks and their models first.
		event.createProvider(ModModelProvider::new);
		// Tag them next.
		event.createBlockAndItemTags(ModBlockTagProvider::new, ModItemTagProvider::new);
		// Must come after the tags, because some recipes reference tags.
		event.createProvider(ModRecipeRunner::new);
		// Create loot tables.
		event.createProvider((output, lookupProvider) ->
			new LootTableProvider(output, Set.of(), List.of(
				new LootTableProvider.SubProviderEntry(
					ModBlockLootTableGenerator::new,
					LootContextParamSets.BLOCK)), lookupProvider));
	}
}
