package counting.stepcounter.player;

public class PlayerData {

    private long steps;

    public long getSteps() {
        return steps;
    }

    public void addStep() {
        steps++;
    }

    public void addSteps(long amount) {
        steps += amount;
    }
}