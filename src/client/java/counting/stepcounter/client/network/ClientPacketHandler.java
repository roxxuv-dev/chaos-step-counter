package counting.stepcounter.client.network;

import counting.stepcounter.client.screen.QuestionScreen;
import net.minecraft.client.Minecraft;

public class ClientPacketHandler {

    public static void openQuestionScreen(
            String question,
            String answer1,
            String answer2,
            String answer3,
            String answer4,
            int correctAnswer,
            long expireTime
    ) {

        Minecraft client =
                Minecraft.getInstance();

        client.execute(() ->
                client.setScreen(
                        new QuestionScreen(
                                question,
                                answer1,
                                answer2,
                                answer3,
                                answer4,
                                correctAnswer,
                                expireTime
                        )
                )
        );
    }

    public static void register() {

        // Future networking hooks
    }
}