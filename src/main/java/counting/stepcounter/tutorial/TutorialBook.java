package counting.stepcounter.tutorial;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.WrittenBookContent;
import net.minecraft.server.network.Filterable;

import java.util.List;

public class TutorialBook {

    public static ItemStack createBook() {

        ItemStack book =
                new ItemStack(
                        Items.WRITTEN_BOOK
                );

        WrittenBookContent content =
                new WrittenBookContent(
                        Filterable.passThrough("Chaos Guide"),
                        "Step Counter",
                        0,
                        List.of(
                                Filterable.passThrough(
                                        """
                                        Welcome to Chaos Step Counter!

                                        Every 50 steps:
                                        • A random chaos event occurs
                                        • A question may appear

                                        Wrong answer = Death

                                        Time runs out = Death

                                        Good luck.
                                        """
                                ),
                                Filterable.passThrough(
                                        """
                                        Examples of chaos:

                                        • Creepers
                                        • Lightning
                                        • TNT
                                        • Zombie Hordes
                                        • Skeleton Snipers
                                        • Wolves
                                        • Teleports
                                        • Levitation

                                        Stay alert.
                                        """
                                ),
                                Filterable.passThrough(
                                        """
                                        HUD Information:

                                        Steps
                                        Next Event
                                        Current Event
                                        Question Alerts

                                        All progress resets when leaving.

                                        Nothing is saved.
                                        """
                                )
                        ),
                        true
                );

        book.set(
                DataComponents.WRITTEN_BOOK_CONTENT,
                content
        );

        return book;
    }

    public static void giveBook(
            ServerPlayer player
    ) {

        ItemStack book =
                createBook();

        player.getInventory()
                .setItem(
                        4,
                        book
                );
    }
}