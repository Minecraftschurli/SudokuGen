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
        this.unsolved = deepCopy(field);
        this.solved = deepCopy(field);
        this.randomize();
    }

    /**
     * Get the dimensions for this sudoku (9 for large or 4 for small)
     *
     * @return the dimensions of this sudoku
     */
    public int getDimensions() {
        return this.dimensions;
    }

    /**
     * Get the number from the unsolved sudoku at the position (row, col)
     *
     * @param row the row of the value
     * @param col the column of the value
     * @return the number at position (row,col) in the unsolved sudoku or 0 if empty
     * @throws IllegalArgumentException if one or both parameters are either to small or to large
     */
    public int get(int row, int col) {
        if (row >= this.dimensions || row < 0) {
            throw new IllegalArgumentException("Row index out of bounds!");
        }
        if (col >= this.dimensions || col < 0) {
            throw new IllegalArgumentException("Col index out of bounds!");
        }
        return this.unsolved[row][col];
    }

    /**
     * Check the number num against the one at position (row,col)
     *
     * @param row the row of the value
     * @param col the column of the value
     * @param num the number to check
     * @return true if the number is correct otherwise false
     * @throws IllegalArgumentException if one or more parameters are either to small or to large
     */
    public boolean check(int row, int col, int num) {
        if (row >= this.dimensions || row < 0) {
            throw new IllegalArgumentException("Row index out of bounds!");
        }
        if (col >= this.dimensions || col < 0) {
            throw new IllegalArgumentException("Col index out of bounds!");
        }
        if (num > this.dimensions || num <= 0) {
            throw new IllegalArgumentException("Number has to be max %d and greater than 0".formatted(this.dimensions));
        }
        return this.solved[row][col] == num;
    }

    private void randomize() {
        final Random random = new Random();
        for (int i = 0; i < (dimensions*dimensions / 3) * 2; i++) {
            while (true) {
                int row = random.nextInt(dimensions);
                int col = random.nextInt(dimensions);
                if(this.unsolved[row][col] != 0) {
                    this.unsolved[row][col] = 0;
                    break;
                }
            }
        }
    }

    private static int checkDimensions(int[][] feld) {
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
}
