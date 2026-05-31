package counting.stepcounter.event;

import counting.stepcounter.event.events.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventRegistry {

    private static final List<ChaosEvent> EVENTS = new ArrayList<>();
    private static final Random RANDOM = new Random();

    public static void registerAll() {

        EVENTS.add(new LightningEvent());
        EVENTS.add(new CreeperEvent());
        EVENTS.add(new ZombieHordeEvent());
        EVENTS.add(new SkeletonSniperEvent());
        EVENTS.add(new LaunchEvent());
        EVENTS.add(new BlindnessEvent());
        EVENTS.add(new RandomTeleportEvent());
        EVENTS.add(new FreezeEvent());
        EVENTS.add(new DropItemEvent());
        EVENTS.add(new WolfPackEvent());
        EVENTS.add(new IronGolemEvent());
        EVENTS.add(new SpeedBoostEvent());
        EVENTS.add(new TntEvent());
        EVENTS.add(new LevitationEvent());
        EVENTS.add(new DiamondRainEvent());
    }

    public static ChaosEvent getRandomEvent() {

        if (EVENTS.isEmpty()) {
            return null;
        }

        return EVENTS.get(RANDOM.nextInt(EVENTS.size()));
    }
}