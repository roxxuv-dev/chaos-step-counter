package counting.stepcounter.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionRegistry {

    private static final List<Question> QUESTIONS =
            new ArrayList<>();

    private static final Random RANDOM =
            new Random();

    public static void registerQuestions() {

        QUESTIONS.clear();

        QUESTIONS.add(
                new Question(
                        "What dimension contains the Ender Dragon?",
                        List.of(
                                "Overworld",
                                "Nether",
                                "End",
                                "Sky"
                        ),
                        2
                )
        );

        QUESTIONS.add(
                new Question(
                        "How many eyes does a spider have?",
                        List.of(
                                "2",
                                "4",
                                "6",
                                "8"
                        ),
                        3
                )
        );

        QUESTIONS.add(
                new Question(
                        "What block is needed for a Nether Portal?",
                        List.of(
                                "Stone",
                                "Obsidian",
                                "Diamond",
                                "Copper"
                        ),
                        1
                )
        );

        QUESTIONS.add(
                new Question(
                        "What mob explodes?",
                        List.of(
                                "Zombie",
                                "Creeper",
                                "Cow",
                                "Spider"
                        ),
                        1
                )
        );

        QUESTIONS.add(
                new Question(
                        "What ore drops diamonds?",
                        List.of(
                                "Diamond Ore",
                                "Coal Ore",
                                "Iron Ore",
                                "Redstone Ore"
                        ),
                        0
                )
        );
    }

    public static Question getRandomQuestion() {

        if (QUESTIONS.isEmpty()) {
            return null;
        }

        return QUESTIONS.get(
                RANDOM.nextInt(QUESTIONS.size())
        );
    }
}