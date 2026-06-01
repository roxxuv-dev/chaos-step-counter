package counting.stepcounter.tutorial;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class TutorialBook {

    public static ItemStack createBook() {

        return new ItemStack(
                Items.BOOK
        );
    }

    public static void giveBook(
            ServerPlayer player
    ) {

        player.getInventory().setItem(
                4,
                createBook()
        );
    }
}