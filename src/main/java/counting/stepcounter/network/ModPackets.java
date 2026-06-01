package counting.stepcounter.network;

import net.minecraft.resources.ResourceLocation;

public class ModPackets {

    public static final ResourceLocation OPEN_QUESTION_SCREEN =
            ResourceLocation.fromNamespaceAndPath(
                    "step-counter",
                    "open_question_screen"
            );

    public static final ResourceLocation ANSWER_QUESTION =
            ResourceLocation.fromNamespaceAndPath(
                    "step-counter",
                    "answer_question"
            );

    public static void register() {
    }
}