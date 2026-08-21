package eu.pintergabor.crusher.jei;

import eu.pintergabor.crusher.Global;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import org.jspecify.annotations.NonNull;

import net.minecraft.resources.Identifier;


@JeiPlugin
public class ModJeiPlugin implements IModPlugin {

	@Override
	public @NonNull Identifier getPluginUid() {
		return Global.modId("jei_plugin");
	}
}
