package counting.stepcounter.mixin;

import counting.stepcounter.StepCounter;
import counting.stepcounter.event.EventManager;
import counting.stepcounter.player.PlayerData;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class PlayerMovementMixin {

    @Unique
    private double chaos_lastX = Double.MAX_VALUE;

    @Unique
    private double chaos_lastZ = Double.MAX_VALUE;

    @Unique
    private double chaos_accum = 0;

    @Inject(
            method = "tick",
            at = @At("TAIL")
    )
    private void chaosTick(CallbackInfo ci) {

        ServerPlayer player =
                (ServerPlayer)(Object)this;

        if (chaos_lastX == Double.MAX_VALUE) {

            chaos_lastX = player.getX();
            chaos_lastZ = player.getZ();

            return;
        }

        double dx =
                player.getX() - chaos_lastX;

        double dz =
                player.getZ() - chaos_lastZ;

        double distance =
                Math.sqrt(dx * dx + dz * dz);

        chaos_accum += distance;

        if (chaos_accum >= 1.0D) {

            long fullSteps =
                    (long) chaos_accum;

            chaos_accum -= fullSteps;

            PlayerData data =
                    StepCounter.getData(player);

            for (long i = 0; i < fullSteps; i++) {

                data.addStep();

                long steps =
                        data.getSteps();

                if (steps % 10 == 0) {

                    player.displayClientMessage(
                            net.minecraft.network.chat.Component.literal(
                                    "§7Steps: §f" + steps
                            ),
                            true
                    );
                }

                if (steps % 50 == 0) {

                    EventManager.triggerRandomEvent(player);
                }
            }
        }

        chaos_lastX = player.getX();
        chaos_lastZ = player.getZ();
    }
}