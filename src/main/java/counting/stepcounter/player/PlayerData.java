package counting.stepcounter.player;

public class PlayerData {

    private long steps;
    private long nextQuestionAt;

    public PlayerData() {
        this.steps = 0;
        this.nextQuestionAt = 300;
    }

    public long getSteps() {
        return steps;
    }

    public void addStep() {
        steps++;
    }

    public long getNextQuestionAt() {
        return nextQuestionAt;
    }

    public void setNextQuestionAt(long nextQuestionAt) {
        this.nextQuestionAt = nextQuestionAt;
    }
}