package counting.stepcounter;

import counting.stepcounter.event.EventRegistry;
import counting.stepcounter.network.ModPackets;
import counting.stepcounter.player.PlayerData;
import counting.stepcounter.question.QuestionRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.level.ServerPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StepCounter
        implements ModInitializer {

    public static final String MOD_ID =
            "step-counter";

    public static final Logger LOGGER =
            LoggerFactory.getLogger(
                    MOD_ID
            );

    private static final Map<UUID, PlayerData>
            PLAYER_DATA =
            new HashMap<>();

    @Override
    public void onInitialize() {

        LOGGER.info(
                "Chaos Step Counter Loading..."
        );

        EventRegistry.registerAll();

        QuestionRegistry.registerQuestions();

        ModPackets.register();

        LOGGER.info(
                "Chaos Step Counter Ready"
        );
    }

    public static PlayerData getData(
            ServerPlayer player
    ) {

        return PLAYER_DATA.computeIfAbsent(
                player.getUUID(),
                uuid -> new PlayerData()
        );
    }

    public static void removePlayer(
            ServerPlayer player
    ) {

        PLAYER_DATA.remove(
                player.getUUID()
        );
    }
}