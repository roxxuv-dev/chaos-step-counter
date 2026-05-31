package counting.stepcounter.event.events;

import counting.stepcounter.event.ChaosEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class BlindnessEvent implements ChaosEvent {

    @Override
    public String getName() {
        return "Blindness";
    }

    @Override
    public void execute(ServerPlayer player) {

        player.addEffect(
                new MobEffectInstance(
                        MobEffects.BLINDNESS,
                        200,
                        0
                )
        );
    }
}