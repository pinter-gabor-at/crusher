package eu.pintergabor.crusher;

import eu.pintergabor.crusher.main.ClientMain;

import net.fabricmc.api.ClientModInitializer;


public final class ModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ClientMain.init();
	}
}
