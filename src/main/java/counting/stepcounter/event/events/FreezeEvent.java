package counting.stepcounter.event.events;

import counting.stepcounter.event.ChaosEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class FreezeEvent
        implements ChaosEvent {

    @Override
    public String getName() {
        return "Freeze";
    }

    @Override
    public void execute(
            ServerPlayer player
    ) {

        player.addEffect(
                new MobEffectInstance(
                        MobEffects.SLOWNESS,
                        20 * 10,
                        4
                )
        );
    }
}