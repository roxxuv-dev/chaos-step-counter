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

                    int x = 10;
                    int y = 10;

                    graphics.drawString(
                            mc.font,
                            "Steps: " + HudData.steps,
                            x,
                            y,
                            0xFFFFFF,
                            true
                    );

                    y += 15;

                    graphics.drawString(
                            mc.font,
                            "Next Event: "
                                    + HudData.nextEventAt,
                            x,
                            y,
                            0xFFAA00,
                            true
                    );

                    y += 15;

                    if (!HudData.lastEvent.isEmpty()) {

                        graphics.drawString(
                                mc.font,
                                "Last Event: "
                                        + HudData.lastEvent,
                                x,
                                y,
                                0xFF5555,
                                true
                        );

                        y += 15;
                    }

                    if (HudData.questionActive) {

                        graphics.drawString(
                                mc.font,
                                "QUESTION ACTIVE!",
                                x,
                                y,
                                0xFF0000,
                                true
                        );
                    }

                    if (System.currentTimeMillis()
                            < HudData.eventDisplayUntil) {

                        String banner =
                                "CHAOS EVENT: "
                                + HudData.lastEvent;

                        int width =
                                mc.font.width(
                                        banner
                                );

                        int centerX =
                                graphics.guiWidth() / 2
                                - width / 2;

                        graphics.drawString(
                                mc.font,
                                banner,
                                centerX,
                                40,
                                0xFF0000,
                                true
                        );
                    }
                }
        );
    }
}