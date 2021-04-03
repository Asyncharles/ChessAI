package net.aksyo.spigot.game;

import com.github.bhlangonijr.chesslib.Piece;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.Optional;

public enum MinecraftPiece {

    BLACK_PAWN(Piece.BLACK_PAWN, Material.COBBLESTONE, 'P'),
    BLACK_KNIGHT(Piece.BLACK_KNIGHT, Material.STONE, 'N'),
    BLACK_BISHOP(Piece.BLACK_BISHOP, Material.NETHER_BRICK_FENCE, 'B'),
    BLACK_ROOK(Piece.BLACK_ROOK, Material.NETHERRACK, 'R'),
    BLACK_QUEEN(Piece.BLACK_QUEEN, Material.BEACON, 'Q'),
    BLACK_KING(Piece.BLACK_KING, Material.EMERALD_BLOCK, 'K'),
    WHITE_PAWN(Piece.WHITE_PAWN, Material.OAK_PLANKS, 'p'),
    WHITE_KNIGHT(Piece.WHITE_KNIGHT, Material.STONE_BRICK_WALL, 'n'),
    WHITE_BISHOP(Piece.WHITE_BISHOP, Material.RED_NETHER_BRICK_WALL, 'b'),
    WHITE_ROOK(Piece.WHITE_ROOK, Material.RED_NETHER_BRICKS, 'r'),
    WHITE_QUEEN(Piece.WHITE_QUEEN, Material.BEACON, 'q'),
    WHITE_KING(Piece.WHITE_KING, Material.DIAMOND_BLOCK, 'k');

    private final Piece piece;
    private final Material type;
    private final char charValue;

    MinecraftPiece(final Piece piece, final Material type, final char charValue) {
        this.piece = piece;
        this.type = type;
        this.charValue = charValue;
    }

    public static Optional<MinecraftPiece> fromString(char value) {
        return Arrays.stream(MinecraftPiece.values()).filter(p -> p.getCharValue() == value).findFirst();
    }

    public Piece getPiece() {
        return piece;
    }

    public Material getType() {
        return type;
    }

    public char getCharValue() {
        return charValue;
    }
}
