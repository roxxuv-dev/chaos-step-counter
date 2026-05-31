package counting.stepcounter.event.events;

import counting.stepcounter.event.ChaosEvent;
import net.minecraft.server.level.ServerPlayer;

public class RandomTeleportEvent implements ChaosEvent {

    @Override
    public String getName() {
        return "Random Teleport";
    }

    @Override
    public void execute(ServerPlayer player) {

        double x =
                player.getX() + (Math.random() * 40 - 20);

        double z =
                player.getZ() + (Math.random() * 40 - 20);

        player.teleportTo(
                x,
                player.getY(),
                z
        );
    }
}