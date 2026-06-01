package counting.stepcounter.event.events;

import counting.stepcounter.event.ChaosEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.IronGolem;

public class IronGolemEvent implements ChaosEvent {

    @Override
    public String getName() {
        return "Iron Golem";
    }

    @Override
    public void execute(ServerPlayer player) {

        ServerLevel level =
                (ServerLevel) player.level();

        IronGolem golem =
                EntityType.IRON_GOLEM.create(
                        level,
                        EntitySpawnReason.MOB_SUMMONED
                );

        if (golem == null) {
            return;
        }

        golem.setPos(
                player.getX() + 3,
                player.getY(),
                player.getZ()
        );

        level.addFreshEntity(golem);
    }
}