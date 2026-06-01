package counting.stepcounter.mixin;

import counting.stepcounter.tutorial.TutorialBook;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class PlayerJoinMixin {

    @Inject(
            method = "restoreFrom",
            at = @At("TAIL")
    )
    private void giveBook(
            ServerPlayer oldPlayer,
            boolean alive,
            CallbackInfo ci
    ) {

        ServerPlayer player =
                (ServerPlayer)(Object)this;

        TutorialBook.giveTutorialBook(
                player
        );
    }
}