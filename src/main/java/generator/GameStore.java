package generator;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Georg Burkl
 * @version 2021-03-03
 */
public class GameStore {
    private final ConcurrentLinkedQueue<SudokuSpiel> store = new ConcurrentLinkedQueue<>();

    public int size() {
        synchronized (this.store) {
            return this.store.size();
        }
    }

    public void put(SudokuSpiel spiel) {
        synchronized (this.store) {
            this.store.add(spiel);
        }
    }

    public SudokuSpiel get() {
        SudokuSpiel spiel;
        synchronized (this.store) {
            spiel = this.store.poll();
        }
        this.notifyAll();
        return spiel;
    }
}
