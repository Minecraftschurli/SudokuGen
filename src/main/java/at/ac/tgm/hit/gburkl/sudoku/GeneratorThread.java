package at.ac.tgm.hit.gburkl.sudoku;

/**
 * @author Georg Burkl <gburkl@student.tgm.ac.at>
 * @version 2021-03-03
 */
public class GeneratorThread extends Thread {

    private final GameStore store;
    private final boolean small;

    public GeneratorThread(GameStore store) {
        this.store = store;
        this.small = false;
    }

    public GeneratorThread(GameStore store, boolean small) {
        this.store = store;
        this.small = small;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            if(this.store.size() >= 20) {
                while (this.store.size() > 15) {
                    this.store.awaitCondition();
                }
            }
            int[][] sudoku = SudokuGenerator.generate(this.small);
            this.store.put(new SudokuSpiel(sudoku));
        }
    }
}
