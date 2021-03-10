package at.ac.tgm.hit.gburkl.sudoku;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Georg Burkl
 * @version 2021-03-03
 */
public class GameStore {
    private final Queue<SudokuSpiel> store = new ConcurrentLinkedQueue<>();

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
