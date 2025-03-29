package eu.pintergabor.crusher.blocks;

import eu.pintergabor.crusher.Global;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.Stat;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;


public final class ModStats {
	public static final Stat<Identifier> CRUSHER_STAT = register("crusher_interactions");
	public static final Stat<Identifier> COMPRESSOR_STAT = register("compressor_interactions");

	private ModStats() {
		// Static class.
	}

	/**
	 * Register statistics.
	 */
	private static Stat<Identifier> register(String path) {
		Identifier id = Global.modId(path);
		return Stats.CUSTOM.getOrCreateStat(
			Registry.register(Registries.CUSTOM_STAT, id, id),
			StatFormatter.DEFAULT);
	}

	public static void init() {
		// Everything has been done by static initializers.
	}
}
