package counting.stepcounter.mixin;

import counting.stepcounter.tutorial.TutorialManager;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class PlayerJoinMixin {

    @Inject(
            method = "tick",
            at = @At("HEAD")
    )
    private void stepCounter_joinCheck(
            CallbackInfo ci
    ) {

        ServerPlayer player =
                (ServerPlayer)(Object)this;

        TutorialManager.handleJoin(
                player
        );
    }
}