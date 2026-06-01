package counting.stepcounter.tutorial;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class TutorialBook {

    public static void giveBook(
            ServerPlayer player
    ) {

        ItemStack book =
                new ItemStack(
                        Items.WRITABLE_BOOK
                );

        player.getInventory()
                .setItem(4, book);
    }
}