package counting.stepcounter.event.events;

import counting.stepcounter.event.ChaosEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class LevitationEvent implements ChaosEvent {

    @Override
    public String getName() {
        return "Levitation";
    }

    @Override
    public void execute(ServerPlayer player) {

        player.addEffect(
                new MobEffectInstance(
                        MobEffects.LEVITATION,
                        80,
                        2
                )
        );
    }
}