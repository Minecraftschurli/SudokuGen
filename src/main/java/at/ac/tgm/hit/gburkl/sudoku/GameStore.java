package at.ac.tgm.hit.gburkl.sudoku;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.*;

/**
 * @author Georg Burkl
 * @version 2021-03-03
 */
public class GameStore {
    private final Lock lock = new ReentrantLock();
    private final Condition full = lock.newCondition();
    private final Condition empty = lock.newCondition();
    private final Queue<SudokuSpiel> store = new ConcurrentLinkedQueue<>();

    public void put(SudokuSpiel spiel) {
        this.lock.lock();
        try {
            if(this.store.size() >= 20) {
                while (this.store.size() > 15) {
                    this.full.await();
                }
            }
            this.store.add(spiel);
            this.empty.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            this.lock.unlock();
        }
    }

    public SudokuSpiel get() {
        this.lock.lock();
        try {
            if (this.store.size() <= 0) {
                while (this.store.size() < 5) {
                    this.empty.await();
                }
            }
            return this.store.poll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            this.full.signalAll();
            this.lock.unlock();
        }
    }
}
