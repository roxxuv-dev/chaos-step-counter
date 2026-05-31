package counting.stepcounter.question;

import counting.stepcounter.StepCounter;
import counting.stepcounter.player.PlayerData;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class QuestionManager {

    public static void maybeAskQuestion(
            ServerPlayer player
    ) {

        if (Math.random() > 0.10) {
            return;
        }

        PlayerData data =
                StepCounter.getData(player);

        if (data.hasQuestion()) {
            return;
        }

        Question question =
                QuestionRegistry.getRandomQuestion();

        if (question == null) {
            return;
        }

        data.setActiveQuestion(question);

        data.setQuestionExpireTime(
                System.currentTimeMillis() + 15000
        );

        player.sendSystemMessage(
                Component.literal(
                        "§c§lQUESTION TIME!"
                )
        );

        player.sendSystemMessage(
                Component.literal(
                        "§f" + question.getQuestion()
                )
        );

        for (int i = 0;
             i < question.getAnswers().size();
             i++) {

            player.sendSystemMessage(
                    Component.literal(
                            "§7[" + i + "] §f"
                            + question.getAnswers().get(i)
                    )
            );
        }

        player.sendSystemMessage(
                Component.literal(
                        "§eAnswer using /answer <number>"
                )
        );
    }

    public static void answerQuestion(
            ServerPlayer player,
            int answer
    ) {

        PlayerData data =
                StepCounter.getData(player);

        if (!data.hasQuestion()) {
            return;
        }

        Question question =
                data.getActiveQuestion();

        if (answer ==
                question.getCorrectIndex()) {

            player.sendSystemMessage(
                    Component.literal(
                            "§aCorrect!"
                    )
            );

            data.clearQuestion();

        } else {

            player.kill();

            data.clearQuestion();
        }
    }

    public static void tickPlayer(
            ServerPlayer player
    ) {

        PlayerData data =
                StepCounter.getData(player);

        if (!data.hasQuestion()) {
            return;
        }

        if (System.currentTimeMillis() >
                data.getQuestionExpireTime()) {

            player.sendSystemMessage(
                    Component.literal(
                            "§4Time expired!"
                    )
            );

            player.kill();

            data.clearQuestion();
        }
    }
}