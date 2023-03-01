package cis485.ui.Panels;

import cis485.ui.EngineClient;
import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public class SouthPanel extends JPanel {
    public SouthPanel(JFrame frame, Board board, EngineClient engineClient) {
        TextField input = new TextField();
        input.setColumns(4);
        add(input);
        JButton button = new JButton("Do");
        button.addActionListener(e -> {
            String move = input.getText();
            List<Move> legalMoves = board.legalMoves();
            boolean legal = false;
            for (Move legalMove : legalMoves) {
                if (legalMove.toString().equals(move)) {
                    legal = true;
                }
            }
            if (legal) {
                input.setText("");
                board.doMove(move);
                frame.repaint();
            }
            else {
                input.setText("ERR");
            }
        });
        add(button);
        JButton doEngineMove = new JButton("Run Engine");
        doEngineMove.addActionListener(e -> {
            // do engine move
            try {
                engineClient.command("position fen " + board.getFen(), Function.identity(), s -> s.startsWith("readyok"), 2000l);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            } catch (ExecutionException ex) {
                throw new RuntimeException(ex);
            } catch (TimeoutException ex) {
                throw new RuntimeException(ex);
            }
            String bestMove = null;
            try {
                bestMove = engineClient.command(
                                "go movetime 5",
                                lines -> lines.stream().filter(s->s.startsWith("bestmove")).findFirst().get(),
                                line -> line.startsWith("bestmove"),
                                20000L)
                        .split(" ")[1];
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            } catch (ExecutionException ex) {
                throw new RuntimeException(ex);
            } catch (TimeoutException ex) {
                throw new RuntimeException(ex);
            }
            board.doMove(bestMove);
            frame.repaint();
        });
        add(doEngineMove);
    }
}
