package counting.stepcounter;

import net.fabricmc.api.ModInitializer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StepCounter implements ModInitializer {
    public static final String MOD_ID = "step-counter";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Map<UUID, Long> stepCounts = new HashMap<>();

    @Override
    public void onInitialize() {
        LOGGER.info("[StepCounter] Chaos Counter loaded! Watch your steps...");
    }

    public static void onPlayerStep(ServerPlayer player) {
        UUID id = player.getUUID();
        long steps = stepCounts.getOrDefault(id, 0L) + 1;
        stepCounts.put(id, steps);
        handleMilestone(player, steps);
    }

    private static void handleMilestone(ServerPlayer player, long steps) {
        ServerLevel level = (ServerLevel) player.level();

        // Every 10 steps: show counter
        if (steps % 10 == 0) {
            player.sendSystemMessage(Component.literal(
                "§7[Steps: §f" + steps + "§7]"
            ));
        }

        // Every 50 steps: tired
        if (steps % 50 == 0) {
            player.sendSystemMessage(Component.literal(
                "§eYou're feeling tired... §7(" + steps + " steps)"
            ));
            player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 100, 1));
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0));
        }

        // Every 100 steps: random launch
        if (steps % 100 == 0) {
            player.sendSystemMessage(Component.literal(
                "§b§lA mysterious force launches you skyward!"
            ));
            double randomX = (Math.random() * 2 - 1) * 0.5;
            double randomZ = (Math.random() * 2 - 1) * 0.5;
            player.setDeltaMovement(randomX, 1.8 + Math.random() * 1.2, randomZ);
            player.hurtMarked = true;
        }

        // Every 200 steps: creeper spawns
        if (steps % 200 == 0) {
            player.sendSystemMessage(Component.literal(
                "§c§lSomething is behind you... \uD83D\uDE30"
            ));
            Creeper creeper = EntityType.CREEPER.create(level, EntitySpawnReason.MOB_SUMMONED);
            if (creeper != null) {
                double angle = Math.random() * Math.PI * 2;
                creeper.setPos(
                    player.getX() + Math.cos(angle) * 2,
                    player.getY(),
                    player.getZ() + Math.sin(angle) * 2
                );
                creeper.setTarget(player);
                level.addFreshEntity(creeper);
            }
        }

        // Every 300 steps: zombie horde
        if (steps % 300 == 0) {
            player.sendSystemMessage(Component.literal(
                "§4§l\u2620 THE DEAD RISE! \u2620"
            ));
            for (int i = 0; i < 3; i++) {
                Zombie zombie = EntityType.ZOMBIE.create(level, EntitySpawnReason.MOB_SUMMONED);
                if (zombie != null) {
                    double angle = (Math.PI * 2 / 3) * i;
                    zombie.setPos(
                        player.getX() + Math.cos(angle) * 4,
                        player.getY(),
                        player.getZ() + Math.sin(angle) * 4
                    );
                    zombie.setTarget(player);
                    level.addFreshEntity(zombie);
                }
            }
        }

        // Every 400 steps: skeleton sniper
        if (steps % 400 == 0) {
            player.sendSystemMessage(Component.literal(
                "§6§l\uD83C\uDFF9 A skeletal sniper has found you!"
            ));
            Skeleton skeleton = EntityType.SKELETON.create(level, EntitySpawnReason.MOB_SUMMONED);
            if (skeleton != null) {
                double angle = Math.random() * Math.PI * 2;
                skeleton.setPos(
                    player.getX() + Math.cos(angle) * 10,
                    player.getY() + 3,
                    player.getZ() + Math.sin(angle) * 10
                );
                skeleton.setTarget(player);
                level.addFreshEntity(skeleton);
            }
        }

        // Every 500 steps: lightning
        if (steps % 500 == 0) {
            player.sendSystemMessage(Component.literal(
                "§e§l\u26A1 STEP 500! THE SKY IS ANGRY! \u26A1"
            ));
            LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(level, EntitySpawnReason.MOB_SUMMONED);
            if (bolt != null) {
                bolt.setPos(player.getX(), player.getY(), player.getZ());
                bolt.setVisualOnly(true);
                level.addFreshEntity(bolt);
                player.hurt(level.damageSources().lightningBolt(), 4.0f);
            }
        }

        // Every 1000 steps: levitate + diamond
        if (steps % 1000 == 0) {
            player.sendSystemMessage(Component.literal(
                "§d§l\u2728 1000 STEPS! You ascend briefly as a reward... \u2728"
            ));
            player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60, 3));
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 200, 0));
            ItemEntity diamond = new ItemEntity(level,
                player.getX(), player.getY() + 1, player.getZ(),
                new ItemStack(Items.DIAMOND, 1)
            );
            level.addFreshEntity(diamond);
            player.sendSystemMessage(Component.literal(
                "§b§lA diamond falls from the sky for your dedication!"
            ));
        }
    }
}
