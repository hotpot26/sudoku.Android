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
 *
 * @author yiwen
 */
public class SudokuEngine {
    private final int NUMBER_OF_CELLS = 9;
    private final int NUMBER_OF_CELLS_IN_SUBGRID = 3;

    private List<LinkedList<Cell>> rows = new ArrayList<>();
    private List<LinkedList<Cell>> columns = new ArrayList<>();
    private List<LinkedList<Cell>> subgrids = new ArrayList<>();
    
    private int derived = 0;
    private int unknown = 0;
    private int given = 0;

    /*
     * Initializes the Cell data structure using the grid
     */
    /**
     * @param grid the input puzzle.
     */
    public void initialize(int[][] grid) {
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

                // update the row and column for the cell in Cell for ease of reference
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

        // print the parsed in puzzle
        for (LinkedList<Cell> i : rows) {
            System.out.println(i.toString());
        }
    }

    /**
     * @return 0: solved successfully; -1: not solved; -2 not initialized.
     */
    public int solve() {
        int ret = 0;
        if (given == 0) {
            return -2;
        }

        // find possible numbers based on cells in row
        // should iterate until unknown is zero or unknown is unchanged at which
        // case there is no solution.
        int previousUnknown = 0;
        int iterations = 0;
        boolean solved = false;
        while (!solved && (previousUnknown != unknown)) {
            System.out.println("i = " + iterations);
            List<LinkedList<Cell>> orientation;
            for (int i = 0; i < 3; i++) {
                switch (i) {
                    case 0:
                        System.out.println("Find possible numbers based on cells in rows");
                        orientation = rows;
                        break;
                    case 1:
                        System.out.println("Find possible numbers based on cells in columns");
                        orientation = columns;
                        break;
                    default:
                        System.out.println("Find possible numbers based on cells in subgrids");
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
            solved = isSolved();
            printResult();
            iterations ++;
        }
        
        if ((!solved) && (previousUnknown == unknown)) {
            ret = -1;
            System.out.println("Unable to solve the puzzle");
        }

        return ret;
    }

    public List<LinkedList<Cell>> getRows() {
        return rows;
    }

    // fill all possible numbers within the linked list.
    private void fillAllPossibleNumbers(LinkedList<Cell> line) {
        // build all known numbers within the line
        Set<Integer> existingNumbers = new HashSet<>();
        for (Cell cell : line) {
            if (cell.value != 0) {
                existingNumbers.add(cell.value);
            }
        }
        System.out.println("Existing numbers: " + existingNumbers.toString());
        
        // for every cell with possible values, remove the known numbers 
        for (Cell cell : line) {
            if (cell.value == 0) {
                cell.removeMultipleValuesFromSet(existingNumbers);
            }
            System.out.println(cell.toString());
        }
    }

    // count the number of occurrances for all the possible values within the
    // input linked list of Cells.
    private int[] getPossibleValueCounts(LinkedList<Cell> line) {
        int[] possibleValueCount = new int[10];

        for (Cell cell : line) {
            if (cell.value == 0) {
                for (Integer value : cell.getPossibleValues()) {
                    possibleValueCount[value] ++;
                }
            }
        }
        
        return possibleValueCount;
    }
    
    // find unique number from all possible values within the linked list
    private void findUniquePossibleValues(LinkedList<Cell> line) {
        System.out.println("Find unique possible values");
        int[] possibleValueCount = getPossibleValueCounts(line);
        
        int index = 0;
        for (int count : possibleValueCount) {
            if (count == 1) {
                // if there is only one occurrance of one number from all of the possible values
                // find the cell that has the possible value
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

    // find preemptive set of 2 within the linked list
    private void findPreemptiveSet(LinkedList<Cell> line) {
        System.out.println("Handle preemptive set.");
        boolean foundPreemptiveSet = false;
        HashSet<Integer> previousPossibleValue = new HashSet<>();

        boolean firstTime = true;
        for (Cell cell : line) {
            if (cell.value == 0) {
                HashSet<Integer> possible = cell.getPossibleValues();
                if (possible.size() == 2) {
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
                if ((cell.value == 0) && (cell.getPossibleValues().equals(previousPossibleValue) == false)) {
                    cell.removeMultipleValuesFromSet(previousPossibleValue);
                }
            }
        }
    }

    private boolean isSolved() {
        boolean solved = true;
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
                        solved = false;
                        break;
                }
            }
        }
         
        return solved;
    }

    private void printResult() {
        System.out.println("Final Result:");
        for (LinkedList <Cell> row : rows) {
            for (Cell c : row ) {
                System.out.print(c.value + ", ");
            }
            System.out.println();
        }
        System.out.println("Given: " + given + ", derived: " + derived + ", unknown: " + unknown);
    }
}