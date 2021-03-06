package at.ac.tgm.hit.gburkl.sudoku;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Georg Burkl
 * @version 2021-03-03
 */
public class GameStore {
    private final Queue<SudokuSpiel> store = new ConcurrentLinkedQueue<>();

    public synchronized int size() {
        return this.store.size();
    }

    public synchronized void put(SudokuSpiel spiel) {
        this.store.add(spiel);
        this.notifyAll();
    }

    public synchronized SudokuSpiel get() {
        SudokuSpiel spiel;
        spiel = this.store.poll();
        this.notifyAll();
        return spiel;
    }
}
