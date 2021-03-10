package at.ac.tgm.hit.gburkl.sudoku;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Georg Burkl <gburkl@student.tgm.ac.at>
 * @version 2021-03-03
 */
public class SudokuSpiel {
    private final int[][] unsolved;
    private final int[][] solved;
    private final int dimensions;
    private final int sub;

    /**
     * Create a new {@link SudokuSpiel} from a 2d array of ints
     *
     * @param field the 2d int array of values for this sudoku
     * @throws IllegalArgumentException if the field parameter is invalid (either not the right dimensions or null)
     */
    public SudokuSpiel(int[][] field) {
        if (field == null) {
            throw new IllegalArgumentException("Feld must not be null!");
        }
        this.dimensions = checkDimensions(field);
        this.sub = (int) Math.sqrt(this.dimensions);
        this.unsolved = deepCopy(field);
        this.solved = deepCopy(field);
        this.randomize();
    }

    /**
     * Format the unsolved sudoku as string and return it
     * @return the formatted unsolved sudoku
     */
    public synchronized String displayUnsolved() {
        return display(this.unsolved, this.sub);
    }

    /**
     * Format the solved sudoku as string and return it
     * @return the formatted solved sudoku
     */
    public synchronized String displaySolved() {
        return display(this.solved, this.sub);
    }

    private void randomize() {
        final Random random = new Random();
        for (int i = 0; i < (dimensions*dimensions / 3) * 2; i++) {
            int row;
            int col;
            do {
                row = random.nextInt(dimensions);
                col = random.nextInt(dimensions);
            } while (this.unsolved[row][col] == 0);
            this.unsolved[row][col] = 0;
        }
    }

    private int checkDimensions(int[][] feld) {
        if (feld.length != 9 && feld.length != 4) {
            throw new IllegalArgumentException("Feld must be of size 4x4 or 9x9!");
        }
        for (int[] ints : feld) {
            if (ints.length != 9 && ints.length != 4) {
                throw new IllegalArgumentException("Feld must be of size 4x4 or 9x9!");
            }
        }
        return feld.length;
    }

    private static int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }
        final int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }

    private static String display(int[][] arr, int sub) {
        String delimRow =("+"+("-".repeat(sub))).repeat(sub)+"+\n";
        StringBuilder result = new StringBuilder(delimRow);
        for (int i = 0; i < sub; i++) { // row block
            for (int j = 0; j < sub; j++) { // row
                for (int k = 0; k < sub; k++) { // col block
                    result.append("|");
                    for (int l = 0; l < sub; l++) { // col
                        int x = arr[i*sub+j][k*sub+l];
                        if(x==0) {
                            result.append(' ');
                        } else {
                            result.append(x);
                        }
                    }
                }
                result.append("|\n");
            }
            result.append(delimRow);
        }
        return result.toString();
    }
}
