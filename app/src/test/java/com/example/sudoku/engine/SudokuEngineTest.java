package com.example.sudoku.engine;

import org.junit.Test;

/**
 * unit test for the SudokuEngine with various puzzles
 *
 */
    public class SudokuEngineTest {

    @Test
    /* 25 unknown. multiple possible solutions */
    //856014730090000000240000160062059300031802450005340920024000073000000010018630294
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
        System.out.println(engine.toString());
        org.junit.Assert.assertEquals(0, ret);
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
        System.out.println(engine.toString());
        org.junit.Assert.assertEquals(0, ret);
    }

    @Test
    /* (1, 3) is assigned with 5 (one valid possible solutions) */
    public void originalPuzzle3Test() {
        int [][] grid = {
                {8, 5, 6, 0, 1, 4, 7, 3, 0},
                {0, 9, 3, 5, 0, 0, 0, 0, 0},
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
        System.out.println(engine.toString());
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
    public void superhard1Test() {
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
        System.out.println(engine.toString());
        org.junit.Assert.assertEquals(0, ret);
    }

    @Test
    public void hardest1Test() {
        String grid = "850002400720000009004000000000107002305000900040000000000080070017000000000036040";

        SudokuEngine engine = new SudokuEngine();

        engine.initialize(grid);
        int ret = engine.solve();
        System.out.println(engine.toString());
        org.junit.Assert.assertEquals(0, ret);
    }

}