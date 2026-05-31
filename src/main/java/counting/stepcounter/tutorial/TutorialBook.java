package counting.stepcounter.tutorial;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.WrittenBookContent;

import java.util.List;

public class TutorialBook {

    public static void give(ServerPlayer player) {

        ItemStack book = new ItemStack(
                Items.WRITTEN_BOOK
        );

        player.getInventory().setItem(
                4,
                book
        );
    }
}