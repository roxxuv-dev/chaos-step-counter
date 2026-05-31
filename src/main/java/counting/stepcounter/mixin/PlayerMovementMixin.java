package counting.stepcounter.mixin;

import counting.stepcounter.StepCounter;
import counting.stepcounter.event.EventManager;
import counting.stepcounter.player.PlayerData;
import counting.stepcounter.question.QuestionManager;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class PlayerMovementMixin {

    @Unique
    private double stepCounter_lastX = Double.MAX_VALUE;

    @Unique
    private double stepCounter_lastZ = Double.MAX_VALUE;

    @Unique
    private double stepCounter_distanceBuffer = 0.0;

    @Inject(
            method = "tick",
            at = @At("TAIL")
    )
    private void stepCounter_tick(CallbackInfo ci) {

        ServerPlayer player =
                (ServerPlayer) (Object) this;

        if (stepCounter_lastX == Double.MAX_VALUE) {

            stepCounter_lastX = player.getX();
            stepCounter_lastZ = player.getZ();

            return;
        }

        double dx =
                player.getX() - stepCounter_lastX;

        double dz =
                player.getZ() - stepCounter_lastZ;

        double distance =
                Math.sqrt(dx * dx + dz * dz);

        stepCounter_distanceBuffer += distance;

        if (stepCounter_distanceBuffer >= 1.0D) {

            long fullSteps =
                    (long) stepCounter_distanceBuffer;

            stepCounter_distanceBuffer -= fullSteps;

            PlayerData data =
                    StepCounter.getData(player);

            for (long i = 0; i < fullSteps; i++) {

                data.addStep();

                long steps =
                        data.getSteps();

                if (steps % 10 == 0) {

                    player.displayClientMessage(
                            Component.literal(
                                    "§7Steps: §f" + steps
                            ),
                            true
                    );
                }

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

        QuestionManager.tickPlayer(player);

        stepCounter_lastX =
                player.getX();

        stepCounter_lastZ =
                player.getZ();
    }
}