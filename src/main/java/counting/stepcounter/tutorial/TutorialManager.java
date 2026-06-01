package counting.stepcounter.tutorial;

import counting.stepcounter.StepCounter;
import counting.stepcounter.player.PlayerData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Items;

public class TutorialManager {

    public static void handleJoin(
            ServerPlayer player
    ) {

        PlayerData data =
                StepCounter.getData(player);

        if (data.hasReceivedBook()) {
            return;
        }

        if (player.getInventory()
                .contains(
                        Items.WRITTEN_BOOK
                                .getDefaultInstance()
                )) {

            data.setReceivedBook(true);
            return;
        }

        TutorialBook.giveBook(player);

        data.setReceivedBook(true);
    }
}