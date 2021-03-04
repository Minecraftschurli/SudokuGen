package generator;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.*;

/**
 * @author Georg Burkl
 * @version 2021-03-03
 */
public class GameStore {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private final Queue<SudokuSpiel> store = new ConcurrentLinkedQueue<>();

    public void awaitCondition() {
        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return this.store.size();
        } finally {
            lock.unlock();
        }
    }

    public void put(SudokuSpiel spiel) {
        lock.lock();
        try {
            this.store.add(spiel);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public SudokuSpiel get() {
        lock.lock();
        try {
            return this.store.poll();
        } finally {
            condition.signalAll();
            lock.unlock();
        }
    }
}
