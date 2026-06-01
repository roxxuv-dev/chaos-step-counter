package counting.stepcounter.client.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class QuestionScreen extends Screen {

    private final String question;

    private final String answer1;
    private final String answer2;
    private final String answer3;
    private final String answer4;

    private final int correctAnswer;

    private final long expireTime;

    public QuestionScreen(
            String question,
            String answer1,
            String answer2,
            String answer3,
            String answer4,
            int correctAnswer,
            long expireTime
    ) {

        super(Component.literal("Question"));

        this.question = question;

        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;

        this.correctAnswer = correctAnswer;

        this.expireTime = expireTime;
    }

    @Override
    protected void init() {

        int centerX = width / 2;
        int centerY = height / 2;

        addRenderableWidget(
                Button.builder(
                        Component.literal(answer1),
                        button -> answer(0)
                )
                .bounds(
                        centerX - 125,
                        centerY - 20,
                        250,
                        20
                )
                .build()
        );

        addRenderableWidget(
                Button.builder(
                        Component.literal(answer2),
                        button -> answer(1)
                )
                .bounds(
                        centerX - 125,
                        centerY + 10,
                        250,
                        20
                )
                .build()
        );

        addRenderableWidget(
                Button.builder(
                        Component.literal(answer3),
                        button -> answer(2)
                )
                .bounds(
                        centerX - 125,
                        centerY + 40,
                        250,
                        20
                )
                .build()
        );

        addRenderableWidget(
                Button.builder(
                        Component.literal(answer4),
                        button -> answer(3)
                )
                .bounds(
                        centerX - 125,
                        centerY + 70,
                        250,
                        20
                )
                .build()
        );
    }

    private void answer(
            int selected
    ) {

        Minecraft mc =
                Minecraft.getInstance();

        if (selected == correctAnswer) {

            if (mc != null) {
                mc.setScreen(null);
            }

            return;
        }

        if (mc != null &&
                mc.player != null) {

            mc.player.connection.sendCommand(
                    "answerfail"
            );

            mc.setScreen(null);
        }
    }

    @Override
    public void tick() {

        long remaining =
                expireTime
                        - System.currentTimeMillis();

        if (remaining <= 0) {

            Minecraft mc =
                    Minecraft.getInstance();

            if (mc != null &&
                    mc.player != null) {

                mc.player.connection.sendCommand(
                        "answerfail"
                );

                mc.setScreen(null);
            }
        }
    }

    @Override
    public void render(
            GuiGraphics graphics,
            int mouseX,
            int mouseY,
            float partialTick
    ) {

        renderTransparentBackground(
                graphics
        );

        super.render(
                graphics,
                mouseX,
                mouseY,
                partialTick
        );

        int centerX =
                width / 2;

        long secondsLeft =
                Math.max(
                        0,
                        (expireTime
                                - System.currentTimeMillis())
                                / 1000
                );

        graphics.drawCenteredString(
                font,
                "§4§lQUESTION TIME",
                centerX,
                40,
                0xFF0000
        );

        graphics.drawCenteredString(
                font,
                question,
                centerX,
                75,
                0xFFFFFF
        );

        graphics.drawCenteredString(
                font,
                "§cTime Remaining: "
                        + secondsLeft,
                centerX,
                105,
                0xFF5555
        );
    }

    @Override
    public boolean shouldCloseOnEsc() {

        return false;
    }

    @Override
    public void onClose() {

        // blocked
    }

    @Override
    public boolean isPauseScreen() {

        return false;
    }
}