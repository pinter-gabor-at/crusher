package eu.pintergabor.crusher.blocks;

import eu.pintergabor.crusher.Global;
import eu.pintergabor.crusher.main.Main;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.resources.ResourceLocation;


public final class ModStats {
	public static final DeferredHolder<ResourceLocation, ResourceLocation> CRUSHER_STAT =
		Main.STATS.register("crusher_interactions", () -> Global.modId("crusher"));
	public static final DeferredHolder<ResourceLocation, ResourceLocation> COMPRESSOR_STAT =
		Main.STATS.register("compressor_interactions", () -> Global.modId("compressor"));

	private ModStats() {
		// Static class.
	}

	public static void init() {
		// Everything has been done by static initializers.
	}
}
