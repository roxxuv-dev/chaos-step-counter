package counting.stepcounter.event.events;

import counting.stepcounter.event.ChaosEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class SpeedBoostEvent
        implements ChaosEvent {

    @Override
    public String getName() {
        return "Speed Boost";
    }

    @Override
    public void execute(
            ServerPlayer player
    ) {

        player.addEffect(
                new MobEffectInstance(
                        MobEffects.SPEED,
                        20 * 20,
                        1
                )
        );
    }
}