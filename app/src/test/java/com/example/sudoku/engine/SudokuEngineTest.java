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
    /* 25 unknown. multiple possible solutions */
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
        org.junit.Assert.assertEquals(1, ret);
    }

    @Test
    /* (2, 2) is now filled with 7 (a valid number).
     * 22 unknown. multiple possible solutions */
    public void originalPuzzle2Test() {
        int [][] grid = {
                {8, 5, 6, 0, 1, 4, 7, 3, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 0},
                {2, 4, 7, 0, 0, 0, 1, 6, 0},
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
        org.junit.Assert.assertEquals(1, ret);
    }

    @Test
    /* (1, 3) is assigned with 5 (one valid possible solutions) */
    public void originalPuzzle3Test() {
        int [][] grid = {
                {8, 5, 6, 0, 1, 4, 7, 3, 0},
                {0, 9, 0, 5, 0, 0, 0, 0, 0},
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

    @Test
    /* solved correctly. */
    public void medium1Test() {
        int [][] grid = {
                {6, 4, 0, 0, 3, 0, 0, 0, 7},
                {5, 0, 1, 0, 7, 0, 9, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 4, 9, 0, 8, 0, 6, 0},
                {0, 8, 0, 0, 0, 3, 0, 2, 0},
                {0, 0, 0, 4, 0, 0, 0, 0, 0},
                {4, 0, 0, 1, 5, 7, 0, 3, 0},
                {2, 0, 8, 3, 0, 0, 0, 4, 0},
                {7, 5, 0, 0, 0, 0, 0, 9, 6}
        };
        SudokuEngine engine = new SudokuEngine();

        engine.initialize(grid);
        int ret = engine.solve();
        org.junit.Assert.assertEquals(0, ret);
    }

    @Test
    /* 31 unknown */
    public void hard1Test() {
        int [][] grid = {
                {9, 0, 0, 8, 7, 1, 0, 4, 3},
                {0, 0, 0, 0, 0, 9, 0, 0, 0},
                {4, 0, 0, 0, 5, 0, 1, 0, 0},
                {7, 0, 0, 5, 0, 0, 0, 6, 1},
                {0, 0, 5, 0, 3, 2, 9, 0, 4},
                {0, 0, 0, 0, 0, 0, 0, 0, 5},
                {5, 0, 0, 4, 2, 0, 0, 0, 0},
                {0, 0, 7, 0, 8, 0, 0, 0, 2},
                {0, 0, 2, 0, 0, 0, 0, 8, 6}
        };
        SudokuEngine engine = new SudokuEngine();

        engine.initialize(grid);
        int ret = engine.solve();
        org.junit.Assert.assertEquals(0, ret);
    }

    @Test
    /* solved correctly. */
    public void hard2Test() {
        int [][] grid = {
                {9, 2, 0, 0, 0, 0, 3, 0, 0},
                {0, 5, 0, 0, 4, 0, 0, 2, 0},
                {0, 0, 0, 2, 0, 6, 0, 0, 0},
                {0, 0, 0, 4, 0, 0, 0, 1, 0},
                {3, 0, 0, 0, 0, 5, 6, 8, 0},
                {0, 0, 0, 0, 8, 3, 0, 0, 4},
                {6, 8, 0, 1, 3, 0, 0, 0, 0},
                {0, 0, 4, 5, 0, 0, 1, 0, 2},
                {0, 1, 9, 0, 0, 0, 5, 0, 0}
        };
        SudokuEngine engine = new SudokuEngine();

        engine.initialize(grid);
        int ret = engine.solve();
        org.junit.Assert.assertEquals(0, ret);
    }

    @Test
    public void superhardTest() {
        int [][] grid = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 0, 0, 0, 2, 0, 0, 0, 5},
                {0, 0, 0, 0, 0, 6, 2, 4, 0},
                {0, 3, 8, 0, 0, 7, 1, 0, 0},
                {2, 0, 4, 0, 0, 0, 3, 0, 9},
                {0, 0, 7, 4, 0, 0, 5, 2, 0},
                {0, 7, 2, 5, 0, 0, 0, 0, 0},
                {6, 0, 0, 0, 8, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        SudokuEngine engine = new SudokuEngine();

        engine.initialize(grid);
        int ret = engine.solve();
        org.junit.Assert.assertEquals(0, ret);
    }


}