package counting.stepcounter.event.events;

import counting.stepcounter.event.ChaosEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;

public class TntEvent implements ChaosEvent {

    @Override
    public String getName() {
        return "TNT";
    }

    @Override
    public void execute(ServerPlayer player) {

        ServerLevel level = player.serverLevel();

        PrimedTnt tnt =
                EntityType.TNT.create(level);

        if (tnt != null) {

            tnt.setPos(
                    player.getX() + 4,
                    player.getY(),
                    player.getZ()
            );

            level.addFreshEntity(tnt);
        }
    }
}