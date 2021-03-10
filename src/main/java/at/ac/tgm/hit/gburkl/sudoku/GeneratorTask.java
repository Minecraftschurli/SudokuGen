package at.ac.tgm.hit.gburkl.sudoku;

/**
 * @author Georg Burkl <gburkl@student.tgm.ac.at>
 * @version 2021-03-03
 */
public class GeneratorTask implements Runnable {

    private final GameStore store;
    private final boolean small;

    public GeneratorTask(GameStore store) {
        this.store = store;
        this.small = false;
    }

    public GeneratorTask(GameStore store, boolean small) {
        this.store = store;
        this.small = small;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            int[][] sudoku = SudokuGenerator.generate(this.small);
            this.store.put(new SudokuSpiel(sudoku));
        }
    }
}
