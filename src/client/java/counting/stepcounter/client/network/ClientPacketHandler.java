package counting.stepcounter.client.network;

import counting.stepcounter.client.screen.QuestionScreen;
import net.minecraft.client.Minecraft;

public class ClientPacketHandler {

    public static void openQuestionScreen(
            String question,
            String a1,
            String a2,
            String a3,
            String a4,
            int correctAnswer,
            long expireTime
    ) {

        Minecraft client =
                Minecraft.getInstance();

        client.execute(() ->
                client.setScreen(
                        new QuestionScreen(
                                question,
                                a1,
                                a2,
                                a3,
                                a4,
                                correctAnswer,
                                expireTime
                        )
                )
        );
    }

    public static void register() {
    }
}