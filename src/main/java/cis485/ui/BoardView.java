package cis485.ui;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class BoardView extends JPanel {
    private BufferedImage boardImage;
    private HashMap<String, BufferedImage> pieceImages;

    private int SQUARE_PIXEL_SIZE = 10; // todo: determine
    private Board board;

    public BoardView(Board board) {
        this.board = board;
        this.pieceImages = new HashMap<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // todo: paint board
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = board.getPiece(Square.squareAt(x + y * 8));
                if (piece != Piece.NONE) {
                    BufferedImage image = pieceImage(piece);
                    g.drawImage(image, x * SQUARE_PIXEL_SIZE, y * SQUARE_PIXEL_SIZE, this);
                }
            }
        }
    }

    private BufferedImage pieceImage(Piece piece) {
        return pieceImages.get(piece.name()); // todo: check
    }

    public void setBoardImage(BufferedImage boardImage) {
        this.boardImage = boardImage;
    }

    public void addPieceImage(String name, BufferedImage image) {
        pieceImages.put(name, image);
    }
}