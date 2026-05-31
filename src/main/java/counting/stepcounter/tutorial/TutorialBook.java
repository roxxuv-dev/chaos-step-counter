package counting.stepcounter.tutorial;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.WrittenBookContent;
import net.minecraft.network.chat.Component;

import java.util.List;

public class TutorialBook {

    public static void giveBook(ServerPlayer player) {

        ItemStack book =
                new ItemStack(Items.WRITTEN_BOOK);

        book.set(
                net.minecraft.core.component.DataComponents.WRITTEN_BOOK_CONTENT,
                new WrittenBookContent(
                        Component.literal("Chaos Guide"),
                        "Chaos Mod",
                        0,
                        List.of(
                                Component.literal("""
CHAOS STEP COUNTER

Every 50 steps:
A random event occurs.

Sometimes:
A question appears.

Wrong answer:
You die.

Answer with:
/answer <number>

Good luck.
""")
                        ),
                        true
                )
        );

        player.getInventory().setItem(4, book);
    }
}