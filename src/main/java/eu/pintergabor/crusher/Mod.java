package eu.pintergabor.crusher;

import eu.pintergabor.crusher.main.Main;

import net.fabricmc.api.ModInitializer;


public final class Mod implements ModInitializer {

    @Override
    public void onInitialize() {
        Main.init();
    }
}
