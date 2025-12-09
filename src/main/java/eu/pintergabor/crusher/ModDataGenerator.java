package eu.pintergabor.crusher;

import eu.pintergabor.crusher.datagen.*;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;


public final class ModDataGenerator {

	public ModDataGenerator() {
		// Static class.
	}

	public static void listener(@NotNull GatherDataEvent.Client event) {
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
