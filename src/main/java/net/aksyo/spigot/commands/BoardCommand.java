package net.aksyo.spigot.commands;

import net.aksyo.spigot.board.BoardManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BoardCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;
            if (BoardManager.getInstance().createGame(args[0], player)) {
                player.sendMessage("§aYou have created a game!");
            } else {
                player.sendMessage("§cGame creation failed");
            }

        }

        return false;
    }
}
