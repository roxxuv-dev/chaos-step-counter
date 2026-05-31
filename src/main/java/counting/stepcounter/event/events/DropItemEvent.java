package counting.stepcounter.event.events;

import counting.stepcounter.event.ChaosEvent;
import net.minecraft.server.level.ServerPlayer;

public class DropItemEvent implements ChaosEvent {

    @Override
    public String getName() {
        return "Drop Item";
    }

    @Override
    public void execute(ServerPlayer player) {

        player.drop(
                player.getMainHandItem(),
                true
        );
    }
}