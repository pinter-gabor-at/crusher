package eu.pintergabor.crusher;

import eu.pintergabor.crusher.main.CreativeTabs;
import eu.pintergabor.crusher.screen.ModScreenHandlers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;


/**
 * Client side startup.
 */
@Mod(value = Global.MODID, dist = Dist.CLIENT)
public final class ModClient {

	@SuppressWarnings("unused")
	public ModClient(IEventBus modEventBus, ModContainer modContainer) {
		// GUI screens.
		//modEventBus.addListener(ModScreenHandlers::initMenu);
		//modEventBus.addListener(ModScreenHandlers::initScreen);
		// Creative tabs.
		modEventBus.addListener(CreativeTabs::init);
		// Data generator.
		modEventBus.addListener(DataGen::init);
	}
}
