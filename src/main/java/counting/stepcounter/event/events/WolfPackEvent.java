package counting.stepcounter.event.events;

import counting.stepcounter.event.ChaosEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Wolf;

public class WolfPackEvent implements ChaosEvent {

    @Override
    public String getName() {
        return "Wolf Pack";
    }

    @Override
    public void execute(ServerPlayer player) {

        ServerLevel level = player.serverLevel();

        for (int i = 0; i < 4; i++) {

            Wolf wolf =
                    EntityType.WOLF.create(
                            level,
                            EntitySpawnReason.MOB_SUMMONED
                    );

            if (wolf != null) {

                wolf.setPos(
                        player.getX() + i,
                        player.getY(),
                        player.getZ() + i
                );

                level.addFreshEntity(wolf);
            }
        }
    }
}