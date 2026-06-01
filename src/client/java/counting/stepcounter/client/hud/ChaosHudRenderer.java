package counting.stepcounter.client.hud;

import counting.stepcounter.hud.HudData;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;

public class ChaosHudRenderer {

    public static void register() {

        HudRenderCallback.EVENT.register(
                (graphics, tickDelta) -> {

                    Minecraft mc =
                            Minecraft.getInstance();

                    if (mc.player == null) {
                        return;
                    }

                    graphics.drawString(
                            mc.font,
                            "Steps: " + HudData.steps,
                            10,
                            10,
                            0xFFFFFF,
                            true
                    );

                    graphics.drawString(
                            mc.font,
                            "Next Event: "
                                    + HudData.nextEventAt,
                            10,
                            25,
                            0xFF5555,
                            true
                    );

                    if (HudData.questionActive) {

                        graphics.drawString(
                                mc.font,
                                "QUESTION ACTIVE!",
                                10,
                                40,
                                0xFF0000,
                                true
                        );
                    }

                    if (!HudData.lastEvent.isEmpty()) {

                        graphics.drawString(
                                mc.font,
                                "EVENT:",
                                10,
                                60,
                                0xFFFF00,
                                true
                        );

                        graphics.drawString(
                                mc.font,
                                HudData.lastEvent,
                                10,
                                75,
                                0xFFFFFF,
                                true
                        );
                    }
                }
        );
    }
}