package counting.stepcounter.question;

import counting.stepcounter.StepCounter;
import counting.stepcounter.player.PlayerData;
import net.minecraft.server.level.ServerPlayer;

public class QuestionManager {

    public static void maybeAskQuestion(
            ServerPlayer player
    ) {

        if (Math.random() > 0.10D) {
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

        data.setActiveQuestion(
                question
        );

        data.setQuestionExpireTime(
                System.currentTimeMillis()
                        + 15000L
        );

        /*
         * NETWORK OPEN SCREEN
         *
         * We'll connect this to packets
         * in the next file.
         */
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

            data.clearQuestion();

            return;
        }

        failQuestion(player);
    }

    public static void tickPlayer(
            ServerPlayer player
    ) {

        PlayerData data =
                StepCounter.getData(player);

        if (!data.hasQuestion()) {
            return;
        }

        if (System.currentTimeMillis()
                >= data.getQuestionExpireTime()) {

            failQuestion(player);
        }
    }

    public static void failQuestion(
            ServerPlayer player
    ) {

        player.hurt(
                player.damageSources()
                        .genericKill(),
                Float.MAX_VALUE
        );

        StepCounter.getData(player)
                .clearQuestion();
    }
}