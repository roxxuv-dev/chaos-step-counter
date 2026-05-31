package counting.stepcounter.event;

import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventManager {

    private static final Random RANDOM = new Random();

    private static final List<ChaosEvent> EVENTS = new ArrayList<>();

    public static void register(ChaosEvent event) {
        EVENTS.add(event);
    }

    public static void triggerRandom(ServerPlayer player) {

        if (EVENTS.isEmpty()) {
            return;
        }

        ChaosEvent event = EVENTS.get(
                RANDOM.nextInt(EVENTS.size())
        );

        event.execute(player);
    }
}