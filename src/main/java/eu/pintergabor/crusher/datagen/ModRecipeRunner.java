package eu.pintergabor.crusher.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.crusher.Global;
import org.jetbrains.annotations.NotNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;


public final class ModRecipeRunner extends RecipeProvider.Runner {

	public ModRecipeRunner(
		PackOutput output,
		CompletableFuture<HolderLookup.Provider> completableFuture
	) {
		super(output, completableFuture);
	}

	@Override
	protected @NotNull RecipeProvider createRecipeProvider(
		@NotNull HolderLookup.Provider registryLookup,
		@NotNull RecipeOutput output
	) {
		return new ModRecipeGenerator(registryLookup, output);
	}

	@Override
	public @NotNull String getName() {
		return Global.MODID + " recipes";
	}
}
