package counting.stepcounter.network;

import counting.stepcounter.question.QuestionManager;
import net.minecraft.server.level.ServerPlayer;

public class AnswerQuestionPacket {

    public static void receive(
            ServerPlayer player,
            int answer
    ) {

        QuestionManager.answerQuestion(
                player,
                answer
        );
    }
}