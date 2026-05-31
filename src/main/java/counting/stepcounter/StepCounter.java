package counting.stepcounter;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StepCounter implements ModInitializer {
    public static final String MOD_ID = "step-counter";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // Shared step counts accessible from mixin
    public static final Map<UUID, Long> stepCounts = new HashMap<>();

    @Override
    public void onInitialize() {
        LOGGER.info("[StepCounter] Chaos Counter loaded! Watch your steps...");
    }

    /**
     * Called from the movement mixin every time a player walks a block.
     */
    public static void onPlayerStep(ServerPlayer player) {
        UUID id = player.getUUID();
        long steps = stepCounts.getOrDefault(id, 0L) + 1;
        stepCounts.put(id, steps);

        handleMilestone(player, steps);
    }

    private static void handleMilestone(ServerPlayer player, long steps) {
        Level level = player.level();

        // Every 10 steps: show a casual counter
        if (steps % 10 == 0) {
            player.sendSystemMessage(Component.literal(
                "§7[Steps: §f" + steps + "§7]"
            ));
        }

        // ── EVERY 50 STEPS ── You get tired
        if (steps % 50 == 0) {
            player.sendSystemMessage(Component.literal(
                "§eYou're feeling tired... §7(" + steps + " steps)"
            ));
            player.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                net.minecraft.world.effect.MobEffects.MOVEMENT_SLOWDOWN, 100, 1
            ));
            player.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                net.minecraft.world.effect.MobEffects.WEAKNESS, 100, 0
            ));
        }

        // ── EVERY 100 STEPS ── Random launch into the air
        if (steps % 100 == 0) {
            player.sendSystemMessage(Component.literal(
                "§b§lA mysterious force launches you skyward!"
            ));
            double randomX = (Math.random() * 2 - 1) * 0.5;
            double randomZ = (Math.random() * 2 - 1) * 0.5;
            player.setDeltaMovement(randomX, 1.8 + Math.random() * 1.2, randomZ);
            player.hurtMarked = true;
        }

        // ── EVERY 200 STEPS ── A creeper spawns right behind you
        if (steps % 200 == 0) {
            player.sendSystemMessage(Component.literal(
                "§c§lSomething is behind you... 😰"
            ));
            if (!level.isClientSide()) {
                Creeper creeper = EntityType.CREEPER.create(level);
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
        }

        // ── EVERY 300 STEPS ── Zombie horde (3 zombies)
        if (steps % 300 == 0) {
            player.sendSystemMessage(Component.literal(
                "§4§l☠ THE DEAD RISE! ☠"
            ));
            if (!level.isClientSide()) {
                for (int i = 0; i < 3; i++) {
                    Zombie zombie = EntityType.ZOMBIE.create(level);
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
        }

        // ── EVERY 400 STEPS ── Skeleton sniper spawns 10 blocks away
        if (steps % 400 == 0) {
            player.sendSystemMessage(Component.literal(
                "§6§l🏹 A skeletal sniper has found you!"
            ));
            if (!level.isClientSide()) {
                Skeleton skeleton = EntityType.SKELETON.create(level);
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
        }

        // ── EVERY 500 STEPS ── Lightning + survival chance
        if (steps % 500 == 0) {
            player.sendSystemMessage(Component.literal(
                "§e§l⚡ STEP 500! THE SKY IS ANGRY! ⚡"
            ));
            if (!level.isClientSide()) {
                net.minecraft.world.entity.LightningBolt bolt =
                    EntityType.LIGHTNING_BOLT.create(level);
                if (bolt != null) {
                    bolt.setPos(player.getX(), player.getY(), player.getZ());
                    bolt.setVisualOnly(true); // visual only — won't kill instantly
                    level.addFreshEntity(bolt);
                    player.hurt(level.damageSources().lightningBolt(), 4.0f);
                }
            }
        }

        // ── EVERY 1000 STEPS ── BIG CHAOS: Levitation + message + gift
        if (steps % 1000 == 0) {
            player.sendSystemMessage(Component.literal(
                "§d§l✨ 1000 STEPS! You ascend briefly as a reward... ✨"
            ));
            player.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                net.minecraft.world.effect.MobEffects.LEVITATION, 60, 3
            ));
            player.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                net.minecraft.world.effect.MobEffects.SLOW_FALLING, 200, 0
            ));
            // Drop a diamond as a gift
            if (!level.isClientSide()) {
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
}