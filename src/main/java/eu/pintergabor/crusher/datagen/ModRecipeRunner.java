package eu.pintergabor.crusher.datagen;

import eu.pintergabor.crusher.Global;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;


public class ModRecipeRunner extends RecipeProvider.Runner {

	public ModRecipeRunner(
			PackOutput output,
			CompletableFuture<HolderLookup.Provider> completableFuture) {
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
