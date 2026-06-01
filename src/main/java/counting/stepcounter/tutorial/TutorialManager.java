package counting.stepcounter.tutorial;

import net.minecraft.server.level.ServerPlayer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TutorialManager {

    private static final Set<UUID> GIVEN_BOOK =
            new HashSet<>();

    public static void handleJoin(
            ServerPlayer player
    ) {

        if (GIVEN_BOOK.contains(
                player.getUUID()
        )) {
            return;
        }

        GIVEN_BOOK.add(
                player.getUUID()
        );

        TutorialBook.giveBook(
                player
        );
    }
}