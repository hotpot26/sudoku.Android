package com.example.sudoku.engine;

import org.junit.Test;

/**
 * unit test for the BacktrackingAlgorithm with various puzzles
 *
 */
public class BacktrackingAlgorithmTest {

    @Test
    /* original qlik puzzle */
    public void originalPuzzleTest() {
        int [][] grid = {
                {8, 5, 6, 0, 1, 4, 7, 3, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 0},
                {2, 4, 0, 0, 0, 0, 1, 6, 0},
                {0, 6, 2, 0, 5, 9, 3, 0, 0},
                {0, 3, 1, 8, 0, 2, 4, 5, 0},
                {0, 0, 5, 3, 4, 0, 9, 2, 0},
                {0, 2, 4, 0, 0, 0, 0, 7, 3},
                {0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 1, 8, 6, 3, 0, 2, 9, 4}
        };

        BacktrackingAlgorithm engine = new BacktrackingAlgorithm(grid);

        boolean ret = engine.solve();

        System.out.println(engine.toString());
        org.junit.Assert.assertTrue(ret);
    }

    @Test
    /* continuation of the SudokuAlgorithmTest::originalPuzzleTest() output */
    public void originalPuzzle3Test() {
        int [][] grid = {
                {8, 5, 6, 0, 1, 4, 7, 3, 0},
                {1, 9, 0, 0, 0, 0, 0, 4, 0},
                {2, 4, 0, 0, 0, 0, 1, 6, 0},
                {4, 6, 2, 0, 5, 9, 3, 8, 0},
                {9, 3, 1, 8, 0, 2, 4, 5, 0},
                {7, 8, 5, 3, 4, 0, 9, 2, 0},
                {6, 2, 4, 0, 0, 0, 0, 7, 3},
                {3, 7, 9, 4, 2, 0, 6, 1, 0},
                {5, 1, 8, 6, 3, 7, 2, 9, 4}
        };

        BacktrackingAlgorithm engine = new BacktrackingAlgorithm(grid);

        boolean ret = engine.solve();

        System.out.println(engine.toString());
        org.junit.Assert.assertTrue(ret);
    }
}
