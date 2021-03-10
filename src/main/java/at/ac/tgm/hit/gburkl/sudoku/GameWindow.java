package at.ac.tgm.hit.gburkl.sudoku;

import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

/**
 * @author Georg Burkl <gburkl@student.tgm.ac.at>
 * @version 2021-03-06
 */
public class GameWindow extends JFrame {

    private final SudokuView sudoku;
    private final JPanel buttons;

    /**
     * Create a new {@link GameWindow} with the given {@link Supplier} to get new {@link SudokuSpiel} values
     * @param store the {@link Supplier} of {@link SudokuSpiel} values
     */
    public GameWindow(Supplier<SudokuSpiel> store) {
        this.setLayout(new BorderLayout());
        sudoku = new SudokuView(store.get());
        this.add(sudoku, BorderLayout.CENTER);
        buttons = new JPanel();
        final JButton checkBtn = new JButton("CHECK");
        checkBtn.addActionListener(e -> {
            if (sudoku.checkSpiel()) {
                sudoku.lock();
                JOptionPane.showMessageDialog(this, "Correct!", "Correct!", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect!\nTry again.", "Incorrect!", JOptionPane.ERROR_MESSAGE);
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
        // make the sudoku square
        sudoku.setSize(sudoku.getHeight(), sudoku.getHeight());
        setSize(sudoku.getHeight(),sudoku.getHeight()+buttons.getHeight());
    }
}
