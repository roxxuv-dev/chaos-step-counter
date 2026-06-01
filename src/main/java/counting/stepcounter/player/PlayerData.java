package counting.stepcounter.player;

import counting.stepcounter.question.Question;

public class PlayerData {

    private long steps = 0;

    private Question activeQuestion = null;

    private long questionExpireTime = 0;

    private boolean receivedTutorialBook = false;

    public long getSteps() {
        return steps;
    }

    public void addStep() {
        steps++;
    }

    public void addSteps(long amount) {
        steps += amount;
    }

    public void resetSteps() {
        steps = 0;
    }

    public Question getActiveQuestion() {
        return activeQuestion;
    }

    public void setActiveQuestion(Question activeQuestion) {
        this.activeQuestion = activeQuestion;
    }

    public boolean hasQuestion() {
        return activeQuestion != null;
    }

    public void clearQuestion() {
        activeQuestion = null;
        questionExpireTime = 0;
    }

    public long getQuestionExpireTime() {
        return questionExpireTime;
    }

    public void setQuestionExpireTime(long questionExpireTime) {
        this.questionExpireTime = questionExpireTime;
    }

    public boolean hasReceivedTutorialBook() {
        return receivedTutorialBook;
    }

    public void setReceivedTutorialBook(boolean receivedTutorialBook) {
        this.receivedTutorialBook = receivedTutorialBook;
    }

    public long getNextEventSteps() {

        long remainder = steps % 50;

        if (remainder == 0) {
            return 50;
        }

        return 50 - remainder;
    }
}