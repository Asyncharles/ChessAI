package net.aksyo.spigot.board;

import org.bukkit.entity.Player;

import java.util.*;

public class BoardManager {

    private static BoardManager instance;

    public static void init() {
        if (instance == null) {
            instance = new BoardManager();
        }
    }

    public static void shutdown() {
        if (instance != null) {
            instance = null;
        }
    }

    public static BoardManager getInstance() {
        return instance;
    }

    private final LinkedList<MinecraftBoard> boardsList;


    private BoardManager() {
        boardsList = new LinkedList<>();
    }

    public boolean createGame(String name, Player player) {
        MinecraftBoard minecraftBoard = new MinecraftBoard(name, boardsList.size() + 1, player);
        minecraftBoard.generateBoard();
        minecraftBoard.generatePieces();
        return boardsList.add(minecraftBoard);
    }

    public boolean hasCurrentGame(UUID uuid) {
        return boardsList.stream().anyMatch(b -> b.getUuid().compareTo(uuid) == 0);
    }

    public Optional<MinecraftBoard> getCurrentGameFromPlayer(UUID uuid) {
        Optional<MinecraftBoard> optional = boardsList.stream().filter(b -> b.getUuid().compareTo(uuid) == 0).findFirst();

        return optional;

    }


}
