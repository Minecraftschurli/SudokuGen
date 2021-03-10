package at.ac.tgm.hit.gburkl.sudoku;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Georg Burkl
 * @version 2021-03-03
 */
public class GameStore {
    private final Queue<SudokuSpiel> store = new ConcurrentLinkedQueue<>();

    /**
     * Add a {@link SudokuSpiel} to the {@link GameStore}
     * <br/>
     * This method is <b>thread safe</b> and will block as soon as there are 20 or more sudokus stored and
     * resume when there are 15 or less left
     *
     * @param spiel the {@link SudokuSpiel} to add to the {@link GameStore}
     */
    public synchronized void put(SudokuSpiel spiel) {
        if (this.store.size() >= 20) {
            while (this.store.size() > 15) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        this.store.add(spiel);
        this.notifyAll();
    }

    /**
     * Get a {@link SudokuSpiel} from the {@link GameStore}
     * <br/>
     * This method is <b>thread safe</b> and will block as soon as the store is empty and resume only when there are
     * 5 or more stored
     *
     * @return the oldest {@link SudokuSpiel} in the {@link GameStore}
     */
    public synchronized SudokuSpiel get() {
        if (this.store.size() <= 0) {
            while (this.store.size() < 5) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        SudokuSpiel spiel;
        spiel = this.store.poll();
        this.notifyAll();
        return spiel;
    }
}
