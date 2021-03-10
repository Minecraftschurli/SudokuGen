package at.ac.tgm.hit.gburkl.sudoku;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.*;
import java.util.function.Supplier;

/**
 * @author Georg Burkl
 * @version 2021-03-03
 */
public class GameStore implements Supplier<SudokuSpiel> {
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    private final Queue<SudokuSpiel> store = new ConcurrentLinkedQueue<>();

    /**
     * Add a {@link SudokuSpiel} to the {@link GameStore}
     * <br/>
     * This method is <b>thread safe</b> and will block as soon as there are 20 or more sudokus stored and
     * resume when there are 15 or less left
     *
     * @param spiel the {@link SudokuSpiel} to add to the {@link GameStore}
     */
    public void put(SudokuSpiel spiel) {
        this.lock.lock();
        try {
            if(this.store.size() >= 20) {
                while (this.store.size() > 15) {
                    this.notFull.await();
                }
            }
            this.store.add(spiel);
            this.notEmpty.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            this.lock.unlock();
        }
    }

    /**
     * Get a {@link SudokuSpiel} from the {@link GameStore}
     * <br/>
     * This method is <b>thread safe</b> and will block as soon as the store is empty and resume only when there are
     * 5 or more stored
     *
     * @return the oldest {@link SudokuSpiel} in the {@link GameStore}
     */
    public SudokuSpiel get() {
        this.lock.lock();
        try {
            if (this.store.size() <= 0) {
                while (this.store.size() < 5) {
                    this.notEmpty.await();
                }
            }
            return this.store.poll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            this.notFull.signalAll();
            this.lock.unlock();
        }
    }
}
