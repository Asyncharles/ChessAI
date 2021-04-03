package net.aksyo.spigot.board;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.PieceType;
import com.github.bhlangonijr.chesslib.Square;
import net.aksyo.spigot.Main;
import net.aksyo.spigot.game.ChessGame;
import net.aksyo.spigot.game.MinecraftPiece;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.*;

public class MinecraftBoard {

    private final String name;
    private final int id;

    private final UUID uuid;
    private Player player;

    private final Location initialDelta;

    private final ChessGame chessGame;
    private Map<List<Location>, Square> squareMap = new HashMap<>();
    private final Map<Square, Location> centerMap = new HashMap<>();
    private Board board;

    public MinecraftBoard(String name, int id, Player player) {
        this.name = name;
        this.id = id;
        this.uuid = player.getUniqueId();
        this.player = player;
        this.initialDelta = player.getLocation();
        this.chessGame = new ChessGame();
        this.board = new Board();
    }

    public MinecraftBoard(String name, int id, Player player, Location deltaLocation) {
        this.name = name;
        this.id = id;
        this.uuid = player.getUniqueId();
        this.player = player;
        this.initialDelta = deltaLocation;
        this.chessGame = new ChessGame();
        board = new Board();
    }


    public void generateBoard() {

        boolean isBlack = true;
        int index = 0;

        for (int x = 0; x < 24; x+=3) {
            for (int z = 0; z < 24; z+=3) {
                Location delta = initialDelta.clone().add(x, 0, z);
                squareMap.put(generateSquare(delta, isBlack, Square.squareAt(index)), Square.squareAt(index));
                index++;
                if (z == 21) continue;
                isBlack = !isBlack;
            }
        }

    }

    public void generatePieces() {
        /*Optional<MinecraftPiece> minecraftPiece = MinecraftPiece.fromString(pieces[i]);
        if (minecraftPiece.isPresent()) {
            Square square = Square.squareAt(index + i);
            centerMap.get(square).clone().add(0, 1, 0).getBlock().setType(minecraftPiece.get().getType());
            System.out.println("Constructed : " + minecraftPiece.get().getPiece().name() + " of type : " + minecraftPiece.get().getType().name() + " index : " + i);
        }*/
        String[] fen = board.getFen().split("/");
        if (fen.length != 8) {
            throw new IllegalStateException("Fen is incorrect!");
        }


    }


    private List<Location> generateSquare(final Location deltaLocation, boolean isBlack, Square square) {

        final List<Location> locations = new ArrayList<>();

        for (int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {
                final Location location = deltaLocation.clone().add(x, 0, z);
                if (x == 1 && z == 1) {
                    location.getBlock().setType((isBlack ? Material.BLACK_CONCRETE : Material.WHITE_CONCRETE));
                    centerMap.put(square, location);
                } else {
                    location.getBlock().setType((isBlack ? Material.COAL_BLOCK : Material.IRON_BLOCK));
                }
                locations.add(location);
            }
        }

        return locations;

    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Player getPlayer() {
        return player;
    }

    public Location getInitialDelta() {
        return initialDelta;
    }

    public ChessGame getChessGame() {
        return chessGame;
    }

    public Map<List<Location>, Square> getSquareMap() {
        return squareMap;
    }
}
