package counting.stepcounter.client;

import counting.stepcounter.client.hud.ChaosHudRenderer;
import counting.stepcounter.client.network.ClientPacketHandler;
import net.fabricmc.api.ClientModInitializer;

public class StepCounterClient
        implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ChaosHudRenderer.register();

        ClientPacketHandler.register();
    }
}