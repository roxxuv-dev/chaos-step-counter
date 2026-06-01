package counting.stepcounter.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;

public class AnswerFailCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack>
                    dispatcher
    ) {

        dispatcher.register(

                Commands.literal(
                        "answerfail"
                )

                .executes(ctx -> {

                    if (ctx.getSource()
                            .getEntity()
                            instanceof ServerPlayer player) {

                        player.hurt(
                                player.damageSources()
                                        .genericKill(),
                                Float.MAX_VALUE
                        );
                    }

                    return 1;
                })
        );
    }
}