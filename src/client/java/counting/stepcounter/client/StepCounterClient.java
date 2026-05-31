package counting.stepcounter.client;

import counting.stepcounter.client.hud.ChaosHudRenderer;
import net.fabricmc.api.ClientModInitializer;

public class StepCounterClient
        implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ChaosHudRenderer.register();
    }
}