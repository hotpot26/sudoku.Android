package com.example.sudoku.engine;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * unit test for the engine with various puzzles
 *
 */
    public class SudokuEngineTest {

        @Test
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

        SudokuEngine engine = new SudokuEngine();

        engine.initialize(grid);
        int ret = engine.solve();
        org.junit.Assert.assertEquals(0, ret);
    }
}