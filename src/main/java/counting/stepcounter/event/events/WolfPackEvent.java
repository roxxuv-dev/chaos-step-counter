package counting.stepcounter.event.events;

import counting.stepcounter.event.ChaosEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;

public class WolfPackEvent implements ChaosEvent {

    @Override
    public String getName() {
        return "Zombie Pack";
    }

    @Override
    public void execute(ServerPlayer player) {

        ServerLevel level =
                (ServerLevel) player.level();

        for (int i = 0; i < 4; i++) {

            Zombie zombie =
                    EntityType.ZOMBIE.create(
                            level,
                            EntitySpawnReason.MOB_SUMMONED
                    );

            if (zombie == null) {
                continue;
            }

            zombie.setPos(
                    player.getX() + i,
                    player.getY(),
                    player.getZ() + i
            );

            zombie.setTarget(player);

            level.addFreshEntity(zombie);
        }
    }
}