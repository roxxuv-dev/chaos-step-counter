package counting.stepcounter.util;

import java.util.List;
import java.util.Random;

public class RandomUtils {

    private static final Random RANDOM =
            new Random();

    public static int nextInt(
            int bound
    ) {
        return RANDOM.nextInt(bound);
    }

    public static double nextDouble() {
        return RANDOM.nextDouble();
    }

    public static boolean chance(
            double chance
    ) {
        return RANDOM.nextDouble() < chance;
    }

    public static <T> T random(
            List<T> list
    ) {

        if (list == null ||
                list.isEmpty()) {
            return null;
        }

        return list.get(
                RANDOM.nextInt(
                        list.size()
                )
        );
    }
}