package counting.stepcounter.event;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class EventManager {

    public static void triggerRandomEvent(ServerPlayer player) {

        ChaosEvent event = EventRegistry.getRandomEvent();

        if (event == null) {
            return;
        }

        player.sendSystemMessage(
                Component.literal("§c§lCHAOS EVENT §7» §f" + event.getName())
        );

        event.execute(player);
    }
}