package counting.stepcounter.client.screen;

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

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

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

        button1 =
                Button.builder(
                        Component.literal(answer1),
                        button -> answerClicked(0)
                )
                .bounds(
                        centerX - 100,
                        centerY - 20,
                        200,
                        20
                )
                .build();

        button2 =
                Button.builder(
                        Component.literal(answer2),
                        button -> answerClicked(1)
                )
                .bounds(
                        centerX - 100,
                        centerY + 5,
                        200,
                        20
                )
                .build();

        button3 =
                Button.builder(
                        Component.literal(answer3),
                        button -> answerClicked(2)
                )
                .bounds(
                        centerX - 100,
                        centerY + 30,
                        200,
                        20
                )
                .build();

        button4 =
                Button.builder(
                        Component.literal(answer4),
                        button -> answerClicked(3)
                )
                .bounds(
                        centerX - 100,
                        centerY + 55,
                        200,
                        20
                )
                .build();

        addRenderableWidget(button1);
        addRenderableWidget(button2);
        addRenderableWidget(button3);
        addRenderableWidget(button4);
    }

    private void answerClicked(
            int selectedAnswer
    ) {

        if (selectedAnswer == correctAnswer) {

            if (minecraft != null) {

                minecraft.setScreen(null);
            }

        } else {

            failQuestion();
        }
    }

    private void failQuestion() {

        if (minecraft != null &&
                minecraft.player != null) {

            minecraft.player.connection.sendCommand(
                    "kill"
            );
        }

        if (minecraft != null) {

            minecraft.setScreen(null);
        }
    }

    @Override
    public void tick() {

        super.tick();

        long remaining =
                expireTime -
                        System.currentTimeMillis();

        if (remaining <= 0) {

            failQuestion();
        }
    }

    @Override
    public boolean shouldCloseOnEsc() {

        return false;
    }

    @Override
    public void onClose() {

    }

    @Override
    public void render(
            GuiGraphics graphics,
            int mouseX,
            int mouseY,
            float partialTick
    ) {

        renderBackground(
                graphics,
                mouseX,
                mouseY,
                partialTick
        );

        super.render(
                graphics,
                mouseX,
                mouseY,
                partialTick
        );

        int centerX =
                width / 2;

        int centerY =
                height / 2;

        graphics.drawCenteredString(
                font,
                "QUESTION TIME",
                centerX,
                centerY - 80,
                0xFF5555
        );

        graphics.drawCenteredString(
                font,
                question,
                centerX,
                centerY - 55,
                0xFFFFFF
        );

        long secondsLeft =
                Math.max(
                        0,
                        (expireTime
                                - System.currentTimeMillis())
                                / 1000
                );

        graphics.drawCenteredString(
                font,
                "Time Left: "
                        + secondsLeft,
                centerX,
                centerY + 90,
                0xFFFF55
        );
    }

    @Override
    public boolean isPauseScreen() {

        return false;
    }
}