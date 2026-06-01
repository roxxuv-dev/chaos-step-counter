package counting.stepcounter.network;

import counting.stepcounter.question.Question;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class ModPackets {

    public static final ResourceLocation OPEN_QUESTION_SCREEN =
            ResourceLocation.fromNamespaceAndPath(
                    "step-counter",
                    "open_question_screen"
            );

    public static void register() {

    }

    public static void sendQuestion(
            ServerPlayer player,
            Question question,
            long expireTime
    ) {

        /*
         * Fabric 1.21.10 custom payload implementation
         * goes here later.
         *
         * For now we're keeping the API ready
         * so the rest of the mod compiles.
         */
    }
}