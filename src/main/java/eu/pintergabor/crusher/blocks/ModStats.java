package eu.pintergabor.crusher.blocks;

import eu.pintergabor.crusher.Global;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;


public final class ModStats {
	public static final Stat<ResourceLocation> CRUSHER_STAT = register("crusher_interactions");
	public static final Stat<ResourceLocation> COMPRESSOR_STAT = register("compressor_interactions");

	private ModStats() {
		// Static class.
	}

	/**
	 * Register statistics.
	 */
	private static Stat<ResourceLocation> register(String path) {
		ResourceLocation id = Global.modId(path);
		return Stats.CUSTOM.get(
			Registry.register(BuiltInRegistries.CUSTOM_STAT, id, id),
			StatFormatter.DEFAULT);
	}

	public static void init() {
		// Everything has been done by static initializers.
	}
}
