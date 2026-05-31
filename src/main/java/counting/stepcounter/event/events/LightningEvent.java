package counting.stepcounter.event.events;

import counting.stepcounter.event.ChaosEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.EntitySpawnReason;

public class LightningEvent implements ChaosEvent {

    @Override
    public String getName() {
        return "Lightning Strike";
    }

    @Override
    public void execute(ServerPlayer player) {

        ServerLevel level = player.serverLevel();

        LightningBolt bolt =
                EntityType.LIGHTNING_BOLT.create(
                        level,
                        EntitySpawnReason.MOB_SUMMONED
                );

        if (bolt != null) {

            bolt.setPos(
                    player.getX(),
                    player.getY(),
                    player.getZ()
            );

            level.addFreshEntity(bolt);
        }
    }
}