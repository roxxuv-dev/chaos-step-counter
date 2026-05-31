package counting.stepcounter.client.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class QuestionScreen extends Screen {

    private final String question;

    private final String[] answers;

    public QuestionScreen(
            String question,
            String[] answers
    ) {
        super(Component.literal("Chaos Question"));

        this.question = question;
        this.answers = answers;
    }

    @Override
    protected void init() {

        int centerX = width / 2;

        int y = height / 2 - 20;

        for (int i = 0; i < answers.length; i++) {

            final int answerIndex = i;

            addRenderableWidget(
                    Button.builder(
                                    Component.literal(
                                            answers[i]
                                    ),
                                    button -> {

                                        // TODO:
                                        // send answer packet
                                        // close screen

                                        this.onClose();
                                    }
                            )
                            .bounds(
                                    centerX - 100,
                                    y + (i * 25),
                                    200,
                                    20
                            )
                            .build()
            );
        }
    }

    @Override
    public void render(
            GuiGraphics graphics,
            int mouseX,
            int mouseY,
            float delta
    ) {

        renderBackground(
                graphics,
                mouseX,
                mouseY,
                delta
        );

        graphics.drawCenteredString(
                font,
                question,
                width / 2,
                height / 2 - 60,
                0xFFFFFF
        );

        super.render(
                graphics,
                mouseX,
                mouseY,
                delta
        );
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }
}