package counting.stepcounter.network;

import net.minecraft.resources.ResourceLocation;

public class ModPackets {

    public static final ResourceLocation OPEN_QUESTION_SCREEN =
            ResourceLocation.fromNamespaceAndPath(
                    "step-counter",
                    "open_question_screen"
            );

    public static void register() {

        // Reserved for packet registration
        // Fabric 1.21.10 handles payload registration differently,
        // so we're keeping this simple for now.
    }
}