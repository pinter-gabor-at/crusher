package eu.pintergabor.crusher;

import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Global {

	// Used for logging and registration
	public static final String MODID = "crusher";

	// This logger is used to write text to the console and the log file.
	@SuppressWarnings("unused")
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	/**
	 * Create a mod specific identifier
	 * @param path Name, without the "modid:" prefix
	 */
	public static Identifier ModIdentifier(String path) {
		return Identifier.of(MODID, path);
	}
}
