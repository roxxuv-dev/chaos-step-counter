package counting.stepcounter.question;

import java.util.List;

public class Question {

    private final String question;

    private final List<String> answers;

    private final int correctIndex;

    public Question(
            String question,
            List<String> answers,
            int correctIndex
    ) {
        this.question = question;
        this.answers = answers;
        this.correctIndex = correctIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }
}