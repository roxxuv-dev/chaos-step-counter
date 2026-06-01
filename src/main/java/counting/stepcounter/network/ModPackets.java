package counting.stepcounter.network;

import counting.stepcounter.question.Question;
import net.minecraft.server.level.ServerPlayer;

public class ModPackets {

    public static void register() {

    }

    public static void sendQuestion(
            ServerPlayer player,
            Question question,
            long expireTime
    ) {

        // TODO:
        // Fabric networking implementation
        // will be added later.

    }
}