package com.example.sudoku.engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BacktrackingAlgorithm{
    private static final int NUMBER_OF_CELLS = 9;
    private final int NUMBER_OF_CELLS_IN_SUBGRID = 3;

    private int[][] grid;

    /**
     * To be used only by the JUnit test for ease of input
     * @param puzzleGrid the puzzle grid
     */
    public BacktrackingAlgorithm(int[][] puzzleGrid) {
        grid = puzzleGrid;

        // initialize the rows
        List<LinkedList<Cell>> rows = new ArrayList<>();

    }

    // Solve method. We will use a recursive BackTracking algorithm.
    // we will see better approaches in next video :)
    public boolean solve() {
        for (int row = 0; row < NUMBER_OF_CELLS; row++) {
            for (int column = 0; column < NUMBER_OF_CELLS; column++) {
                if (grid[row][column] == 0) {
                    for (int number = 1; number <= NUMBER_OF_CELLS; number++) {
                        if (isValid(row, column, number)) {
                            grid[row][column] = number;
                            if (solve()) {
                                return true;
                            } else {
                                grid[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }

    // check if a possible number to a row,column position is valid
    private boolean isValid(int row, int column, int number) {
        return !isInRow(row, number) &&
                !isInColumn(column, number) &&
                !isInSubgrid(row, column, number);
    }

    /**
     * @return true if the number is already in the Cell's row
     */
    private boolean isInRow(int row, int number) {
        for (int columnIndex = 0; columnIndex < NUMBER_OF_CELLS; columnIndex++)
            if (grid[row][columnIndex] == number)
                return true;

        return false;
    }

    /**
     * @return true if the number is already in the Cell's column
     */
    private boolean isInColumn(int column, int number) {
        for (int rowIndex = 0; rowIndex < NUMBER_OF_CELLS; rowIndex++)
            if (grid[rowIndex][column] == number)
                return true;

        return false;
    }

    /**
     * @return true if the number is already in the Cell's subgrid
     */
    private boolean isInSubgrid(int row, int column, int number) {
        int subgridRowIndexStart = row - row % NUMBER_OF_CELLS_IN_SUBGRID;
        int subgridColumnIndexStart = column - column % NUMBER_OF_CELLS_IN_SUBGRID;

        for (int i = subgridRowIndexStart; i < subgridRowIndexStart + NUMBER_OF_CELLS_IN_SUBGRID; i++)
            for (int j = subgridColumnIndexStart; j < subgridColumnIndexStart + NUMBER_OF_CELLS_IN_SUBGRID; j++)
                if (grid[i][j] == number)
                    return true;

        return false;
    }

    @Override
    public String toString() {
        String returnString = "";

        for (int[] row : grid) {
            for (int number : row) {
                returnString += " " + number;
            }
            returnString += "\n";
        }
        return returnString;
    }
}