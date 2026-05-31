package counting.stepcounter.event.events;

import counting.stepcounter.event.ChaosEvent;
import net.minecraft.server.level.ServerPlayer;

public class LaunchEvent implements ChaosEvent {

    @Override
    public String getName() {
        return "Sky Launch";
    }

    @Override
    public void execute(ServerPlayer player) {

        player.setDeltaMovement(
                0,
                2.2,
                0
        );

        player.hurtMarked = true;
    }
}