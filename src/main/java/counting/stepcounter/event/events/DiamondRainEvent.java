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
        return "Diamond Rain";
    }

    @Override
    public void execute(ServerPlayer player) {

        ServerLevel level =
                (ServerLevel) player.level();

        for (int i = 0; i < 5; i++) {

            ItemEntity diamond =
                    new ItemEntity(
                            level,
                            player.getX() + (Math.random() * 6 - 3),
                            player.getY() + 5,
                            player.getZ() + (Math.random() * 6 - 3),
                            new ItemStack(
                                    Items.DIAMOND
                            )
                    );

            level.addFreshEntity(diamond);
        }
    }
}