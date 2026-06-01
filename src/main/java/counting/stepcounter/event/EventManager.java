package counting.stepcounter.event;

import counting.stepcounter.hud.HudData;
import counting.stepcounter.player.PlayerData;
import counting.stepcounter.StepCounter;
import net.minecraft.server.level.ServerPlayer;

public class EventManager {

    public static void triggerRandomEvent(
            ServerPlayer player
    ) {

        ChaosEvent event =
                EventRegistry.getRandomEvent();

        if (event == null) {
            return;
        }

        event.execute(player);

        PlayerData data =
                StepCounter.getData(player);

        data.setCurrentEvent(
                event.getName()
        );

        data.setEventDisplayUntil(
                System.currentTimeMillis()
                        + 5000
        );

        HudData.lastEvent =
                event.getName();

        HudData.eventDisplayUntil =
                System.currentTimeMillis()
                        + 5000;
    }
}