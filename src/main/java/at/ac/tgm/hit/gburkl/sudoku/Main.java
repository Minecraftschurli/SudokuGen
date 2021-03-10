package at.ac.tgm.hit.gburkl.sudoku;

/**
 * @author Georg Burkl <gburkl@student.tgm.ac.at>
 * @version 2021-03-03
 */
public class Main {
    public static void main(String[] args) {
        GameStore store = new GameStore();
        Thread gt1 = new Thread(new GeneratorTask(store, true));
        Thread gt2 = new Thread(new GeneratorTask(store));
        Thread rt1 = new Thread(new ReadTask(store));
        Thread rt2 = new Thread(new ReadTask(store));

        gt1.setName("GeneratorThread1");
        gt2.setName("GeneratorThread2");
        rt1.setName("ReadThread1");
        rt2.setName("ReadThread2");

        gt1.start();
        gt2.start();
        rt1.start();
        rt2.start();
    }
}
