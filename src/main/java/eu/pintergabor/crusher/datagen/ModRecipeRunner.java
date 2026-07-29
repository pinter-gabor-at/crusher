package eu.pintergabor.crusher.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.crusher.Global;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;


public final class ModRecipeRunner extends FabricRecipeProvider {

	public ModRecipeRunner(
		final @NonNull FabricPackOutput output,
		final @NonNull CompletableFuture<HolderLookup.Provider> completableFuture
	) {
		super(output, completableFuture);
	}

	@Override
	protected @NonNull RecipeProvider createRecipeProvider(
		final HolderLookup.@NonNull Provider registries,
		final @NonNull RecipeOutput output
	) {
		return new ModRecipeGenerator(registries, output);
	}

	@Override
	public @NonNull String getName() {
		return Global.MODID + " recipes";
	}
}
