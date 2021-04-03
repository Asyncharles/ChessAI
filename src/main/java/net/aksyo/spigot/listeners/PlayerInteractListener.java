package net.aksyo.spigot.listeners;

import com.github.bhlangonijr.chesslib.Square;
import net.aksyo.spigot.board.BoardManager;
import net.aksyo.spigot.board.MinecraftBoard;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class PlayerInteractListener implements @NotNull Listener {

    @EventHandler()
    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        ItemStack current = event.getItem();
        Action action = event.getAction();

        if (BoardManager.getInstance().hasCurrentGame(player.getUniqueId())) {
            if (action == Action.LEFT_CLICK_BLOCK) {
                if (player.getItemInHand().getType() == Material.BLAZE_ROD) {
                    Location location = event.getPlayer().getTargetBlockExact(5).getLocation();
                    Optional<MinecraftBoard> optionalMinecraftBoard = BoardManager.getInstance().getCurrentGameFromPlayer(player.getUniqueId());
                    if (optionalMinecraftBoard.isPresent()) {
                        MinecraftBoard minecraftBoard = optionalMinecraftBoard.get();
                        player.sendMessage(getSquareString(location, minecraftBoard));
                    }
                }
            }
        }

    }

    private String getSquareString(Location location, MinecraftBoard board) {

        return board.getSquareMap().keySet().stream().filter(l -> {
            return l.stream().anyMatch(b -> b.getBlock().getLocation().distance(location) < 1);
        }).findFirst().map(locations -> "§aSquare : §6§l" + board.getSquareMap().get(locations)).orElse("§cOutside of board");


    }

}
