package counting.stepcounter.event.events;

import counting.stepcounter.event.ChaosEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;

public class WolfPackEvent implements ChaosEvent {

    @Override
    public String getName() {
        return "Wolf Pack";
    }

    @Override
    public void execute(ServerPlayer player) {

        ServerLevel level =
                (ServerLevel) player.level();

        for (int i = 0; i < 4; i++) {

            var wolf =
                    EntityType.WOLF.create(
                            level,
                            EntitySpawnReason.MOB_SUMMONED
                    );

            if (wolf == null) {
                continue;
            }

            wolf.setPos(
                    player.getX() + (i * 1.5),
                    player.getY(),
                    player.getZ() + (i * 1.5)
            );

            wolf.setTarget(player);

            level.addFreshEntity(wolf);
        }
    }
}