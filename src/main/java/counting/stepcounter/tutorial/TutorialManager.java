package counting.stepcounter.tutorial;

import counting.stepcounter.StepCounter;
import counting.stepcounter.player.PlayerData;
import net.minecraft.server.level.ServerPlayer;

public class TutorialManager {

    public static void handleJoin(
            ServerPlayer player
    ) {

        PlayerData data =
                StepCounter.getData(player);

        if (data.hasReceivedBook()) {
            return;
        }

        TutorialBook.giveBook(player);

        data.setReceivedBook(true);
    }
}