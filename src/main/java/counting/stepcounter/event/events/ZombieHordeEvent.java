package counting.stepcounter.event.events;

import counting.stepcounter.event.ChaosEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;

public class ZombieHordeEvent implements ChaosEvent {

    @Override
    public String getName() {
        return "Zombie Horde";
    }

    @Override
    public void execute(ServerPlayer player) {

        ServerLevel level = player.serverLevel();

        for (int i = 0; i < 5; i++) {

            Zombie zombie =
                    EntityType.ZOMBIE.create(
                            level,
                            EntitySpawnReason.MOB_SUMMONED
                    );

            if (zombie != null) {

                zombie.setPos(
                        player.getX() + (Math.random() * 8 - 4),
                        player.getY(),
                        player.getZ() + (Math.random() * 8 - 4)
                );

                zombie.setTarget(player);

                level.addFreshEntity(zombie);
            }
        }
    }
}