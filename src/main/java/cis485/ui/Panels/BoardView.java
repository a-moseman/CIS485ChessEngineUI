package cis485.ui.Panels;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class BoardView extends JPanel {
    private BufferedImage boardImage;
    private HashMap<String, BufferedImage> pieceImages;

    private int SQUARE_PIXEL_SIZE = 60;
    private Board board;

    public BoardView(Board board) {
        this.board = board;
        this.pieceImages = new HashMap<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(boardImage, 0, 0, this);
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                int i = x + y * 8;
                Square square = Square.squareAt(i);
                Piece piece = board.getPiece(square);
                if (piece != Piece.NONE) {
                    BufferedImage image = pieceImage(piece);
                    int px = x * SQUARE_PIXEL_SIZE;
                    int py = (Math.abs(y - 7)) * SQUARE_PIXEL_SIZE;
                    g.drawImage(image, px, py, this);
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
