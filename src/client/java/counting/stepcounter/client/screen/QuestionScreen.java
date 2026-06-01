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

    public QuestionScreen(
            String question,
            String answer1,
            String answer2,
            String answer3,
            String answer4,
            int correctAnswer,
            long expireTime
    ) {

        super(
                Component.literal(
                        "Question"
                )
        );

        this.question = question;

        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;

        this.correctAnswer =
                correctAnswer;

        this.expireTime =
                expireTime;
    }

    @Override
    protected void init() {

        int centerX =
                width / 2;

        int centerY =
                height / 2;

        addRenderableWidget(
                Button.builder(
                                Component.literal(
                                        answer1
                                ),
                                button ->
                                        answerClicked(0)
                        )
                        .bounds(
                                centerX - 100,
                                centerY - 20,
                                200,
                                20
                        )
                        .build()
        );

        addRenderableWidget(
                Button.builder(
                                Component.literal(
                                        answer2
                                ),
                                button ->
                                        answerClicked(1)
                        )
                        .bounds(
                                centerX - 100,
                                centerY + 5,
                                200,
                                20
                        )
                        .build()
        );

        addRenderableWidget(
                Button.builder(
                                Component.literal(
                                        answer3
                                ),
                                button ->
                                        answerClicked(2)
                        )
                        .bounds(
                                centerX - 100,
                                centerY + 30,
                                200,
                                20
                        )
                        .build()
        );

        addRenderableWidget(
                Button.builder(
                                Component.literal(
                                        answer4
                                ),
                                button ->
                                        answerClicked(3)
                        )
                        .bounds(
                                centerX - 100,
                                centerY + 55,
                                200,
                                20
                        )
                        .build()
        );
    }

    private void answerClicked(
            int selectedAnswer
    ) {

        if (minecraft == null) {
            return;
        }

        if (selectedAnswer ==
                correctAnswer) {

            minecraft.setScreen(
                    null
            );

        } else {

            failQuestion();
        }
    }

    private void failQuestion() {

        if (minecraft == null) {
            return;
        }

        if (minecraft.player != null) {

            minecraft.player.setHealth(
                    0.0F
            );
        }

        minecraft.setScreen(
                null
        );
    }

    @Override
    public void tick() {

        super.tick();

        if (System.currentTimeMillis()
                >= expireTime) {

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
    public boolean isPauseScreen() {

        return false;
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
                0xFF4444
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
                        (expireTime -
                                System.currentTimeMillis())
                                / 1000
                );

        graphics.drawCenteredString(
                font,
                "Time Left: "
                        + secondsLeft,
                centerX,
                centerY + 95,
                0xFFFF55
        );
    }
}