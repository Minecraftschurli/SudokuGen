package generator;

/**
 * @author Georg Burkl <gburkl@student.tgm.ac.at>
 * @version 2021-03-03
 */
public class GeneratorRunner implements Runnable {

    private final GameStore store;
    private final boolean small;

    public GeneratorRunner(GameStore store) {
        this.store = store;
        this.small = false;
    }

    public GeneratorRunner(GameStore store, boolean small) {
        this.store = store;
        this.small = small;
    }

    @Override
    public void run() {
        try {
            if(this.store.size() >= 20) {
                while (this.store.size() > 15) {
                    this.store.wait();
                }
            }
            int[][] sudoku = SudokuGenerator.generate(this.small);
            this.store.put(new SudokuSpiel(sudoku));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
