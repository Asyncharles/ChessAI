package net.aksyo.spigot.listeners;

import net.aksyo.spigot.board.BoardManager;
import net.aksyo.spigot.board.MinecraftBoard;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Optional;

public class BlockListener implements Listener {

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {

        if (event.getPlayer() != null) {

            Optional<MinecraftBoard> minecraftBoard = BoardManager.getInstance().getCurrentGameFromPlayer(event.getPlayer().getUniqueId());

            if (minecraftBoard.isPresent()) {
                if (isBlockInGame(event.getBlock().getLocation(), minecraftBoard.get())) {
                    event.setCancelled(true);
                }
            }

        }

    }

    private boolean isBlockInGame(Location location, MinecraftBoard board) {

        return board.getSquareMap().keySet().stream().anyMatch(l -> {
            return l.stream().anyMatch(b -> b.getBlock().getLocation().distance(location) < 1);
        });


    }
}
