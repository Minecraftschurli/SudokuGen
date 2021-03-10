package at.ac.tgm.hit.gburkl.sudoku;

/**
 * @author Georg Burkl <gburkl@student.tgm.ac.at>
 * @version 2021-03-03
 */
public class GeneratorTask implements Runnable {

    private final GameStore store;
    private final boolean small;

    /**
     * Create a new {@link GeneratorTask} generating large {@link SudokuSpiel sudokus}
     * with the {@link GameStore} used to save the new values
     * @param store the {@link GameStore} to store the generated values in
     */
    public static GeneratorTask createLarge(GameStore store) {
        return new GeneratorTask(store, false);
    }

    /**
     * Create a new {@link GeneratorTask} generating small {@link SudokuSpiel sudokus}
     * with the {@link GameStore} used to save the new values
     * @param store the {@link GameStore} to store the generated values in
     */
    public static GeneratorTask createSmall(GameStore store) {
        return new GeneratorTask(store, true);
    }

    private GeneratorTask(GameStore store, boolean small) {
        this.store = store;
        this.small = small;
    }

    /**
     * Generate new {@link SudokuSpiel} values until the thread is interrupted
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            int[][] sudoku = SudokuGenerator.generate(this.small);
            this.store.put(new SudokuSpiel(sudoku));
        }
    }
}
