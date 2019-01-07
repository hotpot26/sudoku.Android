/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sudoku.engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * This implements the backend algorithm to solve a sudoku puzzle provided when this class
 * is initialized.
 * @author yiwen
 */
public class SudokuEngine {
    private final int NUMBER_OF_CELLS = 9;
    private final int NUMBER_OF_CELLS_IN_SUBGRID = 3;
    private final int PREEMPTIVE_SET_SIZE = 2;

    private List<LinkedList<Cell>> rows = new ArrayList<>();
    private List<LinkedList<Cell>> columns = new ArrayList<>();
    private List<LinkedList<Cell>> subgrids = new ArrayList<>();

    private int derived = 0;
    private int unknown = 0;
    private int given = 0;

    private int [][] grid;

    public void initialize(String gridString) {
        grid = new int[NUMBER_OF_CELLS][NUMBER_OF_CELLS];
        int row, column;
        int index = 0;

        // read in the puzzle string into a grid.
        for (char c : gridString.toCharArray()) {
            row = index / NUMBER_OF_CELLS;
            column = index % NUMBER_OF_CELLS;

            // guaranteed to be a digit because of the constraint during the input.
            grid[row][column] = Character.getNumericValue(c);
            index++;
        }

        initialize(grid);
    }
    /**
     * Initializes the Cell data structure using the grid
     * @param puzzle the input puzzle.
     */
    public void initialize(int[][] puzzle) {
        grid = puzzle;

        // instantiate the rows that store the cells and columns that cross reference the cells
        for (int i = 0; i < NUMBER_OF_CELLS; i++) {
            rows.add(i, new LinkedList<Cell> ());
            columns.add(i, new LinkedList<Cell> ());
            subgrids.add(i, new LinkedList<Cell> ());
        }

        // initialize the cells within the grid.
        int subgridRow = 0;
        for (int i = 0; i < NUMBER_OF_CELLS; i++) {
            LinkedList<Cell> theRow = rows.get(i);
            if ((i/NUMBER_OF_CELLS_IN_SUBGRID > 0) && (i%NUMBER_OF_CELLS_IN_SUBGRID == 0)) {
                subgridRow ++;
            }
            for (int j = 0; j < NUMBER_OF_CELLS; j++) {
                Cell cell = new Cell (i, j, grid[i][j]);
                theRow.addLast(cell);

                LinkedList<Cell> theColumn = columns.get(j);
                theColumn.addLast(cell);

                int index = subgridRow*NUMBER_OF_CELLS_IN_SUBGRID + j/NUMBER_OF_CELLS_IN_SUBGRID;
                LinkedList<Cell> theSub = subgrids.get(index);
                theSub.addLast(cell);

                // update the row, column and subgrid references for the cell.
                cell.setRow(theRow);
                cell.setColumn(theColumn);
                cell.setSubgrid(theSub);

                if (grid[i][j] == 0) {
                    unknown++;
                } else {
                    given++;
                }
            }
        }
    }

    /**
     * This method attempts to solve the puzzle by eliminating possible numbers based on <br>
     * existing numbers in the row, column and subgrid that the Cell is in. It also eliminates <br>
     * any pre-emptive sets that is identified within the row, column and subgrid in hope of <br>
     * deriving Cells with only one possible number.
     * <p>
     * @return 0: solved successfully; <br>
     *         1: some cells are not filled; <br>
     *         2: incorrect solution <br>
     *         3. puzzle not initialized.
     *
     * TODO: refactor this method so findAllPossibleNumber is less extensive.
     */
    public int solve() {
        int ret;
        if (given == 0) {
            return 3;
        }

        fillGrid();

        if (isFilled()) {
            if (isSolved()) {
                return 0;
            }
        } else {
            // still unknown cells. prepare for backtracking algorithm.
            // create a list of unknown cells
            // update the grid with the Cell content
            updateGrid();
        }
        printResult();

        BacktrackingAlgorithm engine = new BacktrackingAlgorithm(grid);
        if (engine.solve()) {
            ret = 0;
            updateCell();
        } else {
            ret = 1;
        }

        return ret;
    }

    /*
     * Update the grid content with the updated values of the Cells.
     */
    private void updateGrid() {
        for (LinkedList<Cell> row: rows) {
            for (Cell cell: row) {
                grid[cell.getRowIndex()][cell.getColumnIndex()] = cell.getValue();
            }
        }
    }

    private void updateCell() {
        for (LinkedList<Cell> row: rows) {
            for (Cell cell : row) {
                if (cell.getValue() == 0) {
                    cell.setValue(grid[cell.getRowIndex()][cell.getColumnIndex()]);
                }
            }
        }
    }
    private void fillGrid() {
        // find possible numbers based on cells in row
        // should iterate until unknown is zero or unknown is unchanged at which
        // case there is no solution.
        int previousUnknown = 0;

        boolean filled = false;
        while (!filled && (previousUnknown != unknown)) {
            List<LinkedList<Cell>> orientation;
            for (int i = 0; i < 3; i++) {
                switch (i) {
                    case 0:
//                        System.out.println("Find possible numbers based on cells in rows");
                        orientation = rows;
                        break;
                    case 1:
//                        System.out.println("Find possible numbers based on cells in columns");
                        orientation = columns;
                        break;
                    default:
//                        System.out.println("Find possible numbers based on cells in subgrids");
                        orientation = subgrids;
                        break;
                }

                for (LinkedList<Cell> line : orientation) {
                    fillAllPossibleNumbers(line);
                    findPreemptiveSet(line);
                    findUniquePossibleValues(line);
                }
            }

            previousUnknown = unknown;
            filled = isFilled();
        }
    }

    /**
     * This method returns the puzzle including all derived cells.
     * Used by the two PuzzleActivity subclasses.
     * {@see OriginalPuzzleActivity::solvePuzzle()}
     * {@see NewPuzzleActivity::solvePuzzle()}
     * @return the grid in form of a List of LinkedList of Cells.
     */
    public List<LinkedList<Cell>> getRows() {
        return rows;
    }

    /**
     * Fill all possible numbers within the linked list.
     * @param line row, or column or subgrid which this operation applies to
     */
    private void fillAllPossibleNumbers(LinkedList<Cell> line) {
        // build all known numbers within the line
        Set<Integer> existingNumbers = new HashSet<>();
        for (Cell cell : line) {
            if (cell.getValue() != 0) {
                existingNumbers.add(cell.getValue());
            }
        }

        // for every cell with possible values, remove the known numbers
        for (Cell cell : line) {
            if (cell.getValue() == 0) {
                cell.removeSetFromPossible(existingNumbers);
            }
        }
    }

    /**
     * The index of the array is the numbers from 1 - 9. index 0 used in this array.\
     * The value of the array element is the number of occurrences for the number index.
     * @param line the row, column or subgrid that this operation applies to
     * @return an integer array that contains the count of occurrences for the numbers.
     */
    private int[] getPossibleValueCounts(LinkedList<Cell> line) {
        int[] possibleValueCount = new int[10];

        for (Cell cell : line) {
            if (cell.getValue() == 0) {
                for (Integer value : cell.getPossibleValues()) {
                    possibleValueCount[value] ++;
                }
            }
        }
        
        return possibleValueCount;
    }

    /**
     * This method attempts to find a unique number that exist in all of the possible value set <br>
     * within the Cells in line. If found, the unique value is assigned to the associated Cell. <br>
     * Possible values in other associated cells are removed as a result. See {@see Cell.foundValue()}.
     * @param line the row, column or subgrid that are processed.
     */
    // find unique number from all possible values within the linked list
    private void findUniquePossibleValues(LinkedList<Cell> line) {
//        System.out.println("Find unique possible values");
//
        int[] possibleValueCount = getPossibleValueCounts(line);
        
        int index = 0;
        for (int count : possibleValueCount) {
            if (count == 1) {
                // if there is only one occurrence of one number from all of the possible values
                // find the cell that has this unique value and handle it.
                for (Cell cell : line) {
                    HashSet<Integer> possible = cell.getPossibleValues();
                    if (possible.contains(index)) {
                        cell.foundValue(index);
                    }
                }
            }
            index++;
        }
    }

    /**
     * find preemptive set of PREEMPTIVE_SET_SIZE within the linked list
     * remove the preemptive set from the associated row, column and subgrid.
     * {@see Cell.removeSetFromPossible()}
     * @param line the row, column or subgrid that this operations applies to.
     */
    private void findPreemptiveSet(LinkedList<Cell> line) {
//        System.out.println("Handle preemptive set.");
        boolean foundPreemptiveSet = false;
        HashSet<Integer> previousPossibleValue = new HashSet<>();

        boolean firstTime = true;
        for (Cell cell : line) {
            if (cell.getValue() == 0) {
                HashSet<Integer> possible = cell.getPossibleValues();
                if (possible.size() == PREEMPTIVE_SET_SIZE) {
                    if (firstTime) {
                        previousPossibleValue = possible;
                        firstTime = false;
                    } else if (previousPossibleValue.equals(possible)) {
                        foundPreemptiveSet = true;
                        break;
                    }
                }
            }
        }

        // remove values in preemptive set in other cells within subgrid that has no values yet
        if (foundPreemptiveSet) {
            for (Cell cell : line) {
                if ((cell.getValue() == 0) && (cell.getPossibleValues().equals(previousPossibleValue) == false)) {
                    cell.removeSetFromPossible(previousPossibleValue);
                }
            }
        }
    }

    /**
     * This method counts the number of given, derived and unknown Cells in the grid.
     * The corresponding attributes in the instance is updated.
     * @return true if there are no unknown cells.
     */
    private boolean isFilled() {
        boolean filled = true;
        unknown = given = derived = 0;
        
        for (LinkedList <Cell> row : rows) {
            for (Cell c : row ) {
                switch (c.getType()) {
                    case DERIVED:
                        derived++;
                        break;
                    case GIVEN:
                        given++;
                        break;
                    default:
                        unknown++;
                        filled = false;
                        break;
                }
            }
        }
         
        return filled;
    }

    /**
     * This method checks that all the rows, columns and subgrid has the unique 9 numbers filled.
     * @return true if the condition is satisfied.
     */
    private boolean isSolved() {
        boolean solved = true;

        // check for rows, columns and subgrids
        List<LinkedList<Cell>> orientation;
        String orientationString;
        for (int i = 0; i < 3 && solved ; i++) {
            switch (i) {
                case 0:
                    orientation = rows;
                    orientationString = "row";
                    break;
                case 1:
                    orientation = columns;
                    orientationString = "column";
                    break;
                default:
                    orientation = subgrids;
                    orientationString = "subgrid";
                    break;
            }

            for (LinkedList<Cell> line : orientation) {
                HashSet<Integer> allValues = new HashSet<>();
                for (Cell cell: line) {
                    allValues.add(cell.getValue());
                }

                // if the line has less than 9 values there must be duplicate values
                // the puzzle is not solved
                if (allValues.size() != NUMBER_OF_CELLS ) {
                    solved = false;
                    System.out.println("Incorrect " + orientationString);
                    break;
                }
            }
        }

        return solved;
    }

    /**
     * print the filled puzzle to stdout.
     */
    private void printResult() {
        System.out.println("Final Result:");
        for (LinkedList <Cell> row : rows) {
            for (Cell c : row ) {
                System.out.print(c.getValue() + ", ");
            }
            System.out.println();
        }
        System.out.println("Given: " + given + ", derived: " + derived + ", unknown: " + unknown);
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
