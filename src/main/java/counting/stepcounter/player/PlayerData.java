package counting.stepcounter.player;

import counting.stepcounter.question.Question;

public class PlayerData {

    private long steps;

    private Question activeQuestion;

    private long questionExpireTime;

    private String currentEvent = "";

    private long eventDisplayUntil = 0;

    private boolean receivedBook = false;

    public long getSteps() {
        return steps;
    }

    public void setSteps(long steps) {
        this.steps = steps;
    }

    public void addStep() {
        this.steps++;
    }

    public Question getActiveQuestion() {
        return activeQuestion;
    }

    public void setActiveQuestion(
            Question activeQuestion
    ) {
        this.activeQuestion = activeQuestion;
    }

    public boolean hasQuestion() {
        return activeQuestion != null;
    }

    public void clearQuestion() {
        activeQuestion = null;
    }

    public long getQuestionExpireTime() {
        return questionExpireTime;
    }

    public void setQuestionExpireTime(
            long questionExpireTime
    ) {
        this.questionExpireTime =
                questionExpireTime;
    }

    public String getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(
            String currentEvent
    ) {
        this.currentEvent = currentEvent;
    }

    public long getEventDisplayUntil() {
        return eventDisplayUntil;
    }

    public void setEventDisplayUntil(
            long eventDisplayUntil
    ) {
        this.eventDisplayUntil =
                eventDisplayUntil;
    }

    public boolean hasReceivedBook() {
        return receivedBook;
    }

    public void setReceivedBook(
            boolean receivedBook
    ) {
        this.receivedBook =
                receivedBook;
    }
}