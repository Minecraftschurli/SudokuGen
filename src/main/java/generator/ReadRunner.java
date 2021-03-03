package generator;

/**
 * @author Georg Burkl <gburkl@student.tgm.ac.at>
 * @version 2021-03-03
 */
public class ReadRunner implements Runnable {

    private final GameStore store;

    public ReadRunner(GameStore store) {
        this.store = store;
    }

    @Override
    public void run() {
        try {
            if (this.store.size() <= 0) {
                while (this.store.size() < 5) {
                    this.store.wait();
                }
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println(this.store.get().displayUnsolved());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
