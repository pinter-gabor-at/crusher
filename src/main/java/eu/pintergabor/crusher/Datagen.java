package eu.pintergabor.crusher;

import eu.pintergabor.crusher.datagen.ModBlockLootTableGenerator;
import eu.pintergabor.crusher.datagen.ModBlockTagProvider;
import eu.pintergabor.crusher.datagen.ModItemTagProvider;
import eu.pintergabor.crusher.datagen.ModModelProvider;
import eu.pintergabor.crusher.datagen.ModRecipeProvider;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public final class Datagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		// Create blocks and their models first.
		pack.addProvider(ModModelProvider::new);
		// Tag them next.
		pack.addProvider(ModBlockTagProvider::new);
		// Must come after ModBlockTagProvider, because some item tags are generated from block tags.
		pack.addProvider(ModItemTagProvider::new);
		// Must come after the tags, because some recipes reference tags.
		pack.addProvider(ModRecipeProvider::new);
		// And loot is the last.
		pack.addProvider(ModBlockLootTableGenerator::new);
	}
}
