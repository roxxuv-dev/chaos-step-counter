package counting.stepcounter.question;

import counting.stepcounter.StepCounter;
import counting.stepcounter.network.ModPackets;
import counting.stepcounter.player.PlayerData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;

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

        long expireTime =
                System.currentTimeMillis()
                        + 15000L;

        data.setActiveQuestion(
                question
        );

        data.setQuestionExpireTime(
                expireTime
        );

        ModPackets.sendQuestion(
                player,
                question,
                expireTime
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

        ServerLevel level =
                (ServerLevel) player.level();

        PrimedTnt tnt =
                EntityType.TNT.create(
                        level,
                        null,
                        player.blockPosition(),
                        net.minecraft.world.entity.EntitySpawnReason.EVENT,
                        true,
                        true
                );

        if (tnt != null) {

            tnt.setPos(
                    player.getX(),
                    player.getY(),
                    player.getZ()
            );

            tnt.setFuse(20);

            level.addFreshEntity(
                    tnt
            );
        }

        player.kill(level);

        StepCounter.getData(player)
                .clearQuestion();
    }
}