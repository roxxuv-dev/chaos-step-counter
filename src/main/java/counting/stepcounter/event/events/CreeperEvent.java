package counting.stepcounter.event.events;

import counting.stepcounter.event.ChaosEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;

public class CreeperEvent implements ChaosEvent {

    @Override
    public String getName() {
        return "Creeper Behind You";
    }

    @Override
    public void execute(ServerPlayer player) {

        ServerLevel level =
                (ServerLevel) player.level();

        Creeper creeper =
                EntityType.CREEPER.create(
                        level,
                        EntitySpawnReason.MOB_SUMMONED
                );

        if (creeper == null) {
            return;
        }

        creeper.setPos(
                player.getX() + 2,
                player.getY(),
                player.getZ()
        );

        creeper.setTarget(player);

        level.addFreshEntity(creeper);
    }
}