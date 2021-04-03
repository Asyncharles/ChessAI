package net.aksyo.spigot;

import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.PieceType;
import net.aksyo.spigot.board.BoardManager;
import net.aksyo.spigot.commands.BoardCommand;
import net.aksyo.spigot.listeners.BlockListener;
import net.aksyo.spigot.listeners.PlayerInteractListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    private PluginManager pluginManager;

    @Override
    public void onEnable() {
        instance = this;
        pluginManager = getServer().getPluginManager();
        BoardManager.init();
        getCommand("board").setExecutor(new BoardCommand());
        pluginManager.registerEvents(new PlayerInteractListener(), this);
        pluginManager.registerEvents(new BlockListener(), this);

    }

    @Override
    public void onDisable() {

    }


    public static final Main getInstance() {
        return instance;
    }

    public static void main(String[] args) {
    }

}
