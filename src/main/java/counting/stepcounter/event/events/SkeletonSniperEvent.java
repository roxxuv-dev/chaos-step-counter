package counting.stepcounter.event.events;

import counting.stepcounter.event.ChaosEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class SkeletonSniperEvent
        implements ChaosEvent {

    @Override
    public String getName() {

        return "Skeleton Sniper";
    }

    @Override
    public void execute(
            ServerPlayer player
    ) {

        ServerLevel level =
                (ServerLevel) player.level();

        Skeleton skeleton =
                EntityType.SKELETON.create(
                        level,
                        EntitySpawnReason.MOB_SUMMONED
                );

        if (skeleton == null) {
            return;
        }

        skeleton.setPos(
                player.getX() + 10,
                player.getY() + 3,
                player.getZ() + 10
        );

        skeleton.setItemSlot(
                EquipmentSlot.MAINHAND,
                new ItemStack(
                        Items.BOW
                )
        );

        skeleton.setTarget(
                player
        );

        level.addFreshEntity(
                skeleton
        );
    }
}