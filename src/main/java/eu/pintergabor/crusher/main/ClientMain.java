package eu.pintergabor.crusher.main;

import eu.pintergabor.crusher.ModClient;
import eu.pintergabor.crusher.screen.CompressorScreen;
import eu.pintergabor.crusher.screen.CrusherScreen;
import eu.pintergabor.crusher.screen.ModScreenHandlers;

import net.minecraft.client.gui.screen.ingame.HandledScreens;


public final class ClientMain {

    /**
     * Called from {@link ModClient#onInitializeClient()}.
     */
    public static void init() {
        HandledScreens.register(ModScreenHandlers.CRUSHER_SCREEN_HANDLER, CrusherScreen::new);
        HandledScreens.register(ModScreenHandlers.COMPRESSOR_SCREEN_HANDLER, CompressorScreen::new);
    }
}
