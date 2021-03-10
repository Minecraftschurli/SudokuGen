package at.ac.tgm.hit.gburkl.sudoku;

/**
 * @author Georg Burkl <gburkl@student.tgm.ac.at>
 * @version 2021-03-03
 */
public class ReadTask implements Runnable {

    private final GameStore store;

    public ReadTask(GameStore store) {
        this.store = store;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(Thread.currentThread().getName());
            System.out.println(this.store.get().displayUnsolved());
        }
    }
}
