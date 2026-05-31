package counting.stepcounter.event.events;

import counting.stepcounter.event.ChaosEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Skeleton;

public class SkeletonSniperEvent implements ChaosEvent {

    @Override
    public String getName() {
        return "Skeleton Sniper";
    }

    @Override
    public void execute(ServerPlayer player) {

        ServerLevel level = player.serverLevel();

        Skeleton skeleton =
                EntityType.SKELETON.create(
                        level,
                        EntitySpawnReason.MOB_SUMMONED
                );

        if (skeleton != null) {

            skeleton.setPos(
                    player.getX() + 12,
                    player.getY() + 3,
                    player.getZ()
            );

            skeleton.setTarget(player);

            level.addFreshEntity(skeleton);
        }
    }
}