package counting.stepcounter.mixin;

import counting.stepcounter.StepCounter;
import counting.stepcounter.event.EventManager;
import counting.stepcounter.hud.HudData;
import counting.stepcounter.player.PlayerData;
import counting.stepcounter.question.QuestionManager;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class PlayerMovementMixin {

    @Unique
    private double lastX = Double.MAX_VALUE;

    @Unique
    private double lastZ = Double.MAX_VALUE;

    @Unique
    private double distanceBuffer = 0.0D;

    @Inject(
            method = "tick",
            at = @At("TAIL")
    )
    private void chaosStepCounterTick(
            CallbackInfo ci
    ) {

        ServerPlayer player =
                (ServerPlayer)(Object)this;

        if (lastX == Double.MAX_VALUE) {

            lastX = player.getX();
            lastZ = player.getZ();

            return;
        }

        double dx =
                player.getX() - lastX;

        double dz =
                player.getZ() - lastZ;

        double distance =
                Math.sqrt(dx * dx + dz * dz);

        distanceBuffer += distance;

        if (distanceBuffer >= 1.0D) {

            long fullSteps =
                    (long)distanceBuffer;

            distanceBuffer -= fullSteps;

            PlayerData data =
                    StepCounter.getData(player);

            for (long i = 0;
                 i < fullSteps;
                 i++) {

                data.addStep();

                long steps =
                        data.getSteps();

                HudData.steps =
                        steps;

                HudData.nextEventAt =
                        ((steps / 50) + 1) * 50;

                if (steps % 50 == 0) {

                    EventManager.triggerRandomEvent(
                            player
                    );

                    QuestionManager.maybeAskQuestion(
                            player
                    );
                }
            }
        }

        QuestionManager.tickPlayer(
                player
        );

        lastX =
                player.getX();

        lastZ =
                player.getZ();
    }
}