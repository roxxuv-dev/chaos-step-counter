package counting.stepcounter.event.events;

import counting.stepcounter.event.ChaosEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class DiamondRainEvent implements ChaosEvent {

    @Override
    public String getName() {
        return "Diamond Reward";
    }

    @Override
    public void execute(ServerPlayer player) {

        ServerLevel level = player.serverLevel();

        for (int i = 0; i < 3; i++) {

            ItemEntity diamond =
                    new ItemEntity(
                            level,
                            player.getX(),
                            player.getY() + 5,
                            player.getZ(),
                            new ItemStack(
                                    Items.DIAMOND,
                                    1
                            )
                    );

            level.addFreshEntity(diamond);
        }
    }
}