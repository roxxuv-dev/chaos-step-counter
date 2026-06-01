package counting.stepcounter.network;

import counting.stepcounter.question.Question;
import counting.stepcounter.question.QuestionManager;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;

public class ModPackets {

    public static void register() {

        PayloadTypeRegistry.playS2C().register(
                OpenQuestionPayload.TYPE,
                OpenQuestionPayload.CODEC
        );

        PayloadTypeRegistry.playC2S().register(
                AnswerQuestionPayload.TYPE,
                AnswerQuestionPayload.CODEC
        );

        ServerPlayNetworking.registerGlobalReceiver(
                AnswerQuestionPayload.TYPE,
                (payload, context) -> {

                    ServerPlayer player =
                            context.player();

                    context.server().execute(() ->

                            QuestionManager.answerQuestion(
                                    player,
                                    payload.answer()
                            )
                    );
                }
        );
    }

    public static void sendQuestion(
            ServerPlayer player,
            Question question,
            long expireTime
    ) {

        ServerPlayNetworking.send(
                player,

                new OpenQuestionPayload(
                        question.getQuestion(),

                        question.getAnswers().get(0),
                        question.getAnswers().get(1),
                        question.getAnswers().get(2),
                        question.getAnswers().get(3),

                        question.getCorrectIndex(),

                        expireTime
                )
        );
    }
}