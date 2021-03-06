package at.ac.tgm.hit.gburkl.sudoku;

import javax.swing.*;
import java.awt.*;

/**
 * @author Georg Burkl <gburkl@student.tgm.ac.at>
 * @version 2021-03-06
 */
public class GameWindow extends JFrame {

    private final SudokuView sudoku;
    private final JPanel buttons;

    public GameWindow(GameStore store) {
        this.setLayout(new BorderLayout());
        sudoku = new SudokuView(store.get());
        this.add(sudoku, BorderLayout.CENTER);
        buttons = new JPanel();
        final JButton checkBtn = new JButton("CHECK");
        checkBtn.addActionListener(e -> {
            if (sudoku.checkSpiel()) {
                //displayCorrect();
                sudoku.lock();
            } else {
                //displayIncorrect();
            }
        });
        buttons.add(checkBtn);
        final JButton newBtn = new JButton("NEW");
        newBtn.addActionListener(e -> {
            sudoku.setSpiel(store.get());
            this.pack();
        });
        buttons.add(newBtn);
        this.add(buttons, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void pack() {
        super.pack();
        sudoku.setSize(sudoku.getHeight(), sudoku.getHeight());
        setSize(sudoku.getHeight(),sudoku.getHeight()+buttons.getHeight());
    }
}
