package at.ac.tgm.hit.gburkl.sudoku;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Georg Burkl <gburkl@student.tgm.ac.at>
 * @version 2021-03-03
 */
public class Main {
    public static void main(String[] args) {
        GameStore store = new GameStore();
        final ExecutorService threadPool = Executors.newFixedThreadPool(4);
        threadPool.submit(GeneratorTask.createSmall(store));
        threadPool.submit(GeneratorTask.createLarge(store));
        new GameWindow(store);
    }
}
