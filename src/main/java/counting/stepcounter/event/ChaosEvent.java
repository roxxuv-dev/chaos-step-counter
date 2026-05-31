package counting.stepcounter.event;

import net.minecraft.server.level.ServerPlayer;

public interface ChaosEvent {
    String getName();
    void execute(ServerPlayer player);
}