package counting.stepcounter.client.network;

import counting.stepcounter.client.screen.QuestionScreen;
import counting.stepcounter.network.OpenQuestionPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;

public class ClientPacketHandler {

    public static void register() {

        ClientPlayNetworking.registerGlobalReceiver(
                OpenQuestionPayload.TYPE,
                (payload, context) -> {

                    Minecraft client =
                            Minecraft.getInstance();

                    client.execute(() ->

                            client.setScreen(

                                    new QuestionScreen(

                                            payload.question(),

                                            payload.answer1(),
                                            payload.answer2(),
                                            payload.answer3(),
                                            payload.answer4(),

                                            payload.correctAnswer(),

                                            payload.expireTime()
                                    )
                            )
                    );
                }
        );
    }
}