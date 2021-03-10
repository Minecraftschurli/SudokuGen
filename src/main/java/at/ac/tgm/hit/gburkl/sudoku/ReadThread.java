package at.ac.tgm.hit.gburkl.sudoku;


/**
 * @author Georg Burkl <gburkl@student.tgm.ac.at>
 * @version 2021-03-03
 */
public class ReadThread extends Thread {

    private final GameStore store;

    public ReadThread(GameStore store) {
        this.store = store;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            System.out.println(Thread.currentThread().getName());
            System.out.println(this.store.get().displayUnsolved());
        }
    }
}
