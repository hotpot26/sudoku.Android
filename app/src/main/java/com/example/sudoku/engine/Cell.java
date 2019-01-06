/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sudoku.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/**
 * Each instance of the Cell represents one space in the grid. The Cells are instantiated during
 * the SudokuEngine initialization stage. {@see SudokuEngine.initialization()}.
 * @author yiwen
 */


public class Cell {
    public enum CellValueType { UNKNOWN, GIVEN, DERIVED }

    private final static HashSet<Integer> UNIQUE_NUMBERS = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    private final static int ROW_INDEX = 0;
    private final static int COLUMN_INDEX = 1;
    private final static int SUBGRID_INDEX = 2;

    private int rowIndex;
    private int columnIndex;
    private int value;
    private List<LinkedList<Cell>> lines;
    private HashSet<Integer> possible;
    private CellValueType type;

    /**
     * Constructor
     * @param row the index of the row the cell is in. Index 0 is the first row.
     * @param column the index of the column the cell is in. Index 0 is the first column.
     * @param aValue the value of the cell has in the puzzle. Value 0 is an UNKNOWN cell.  <br>
     *               Only UNKNOWN cells will have the attribute, possible set, non-empty.
     */
    public Cell(int row, int column, int aValue){
        rowIndex = row;
        columnIndex = column;
        value = aValue;
        type = CellValueType.UNKNOWN;

        lines = new ArrayList<>();
        
        // initialize the possible values for the cell if its value is 0.
        if (value == 0) {
            possible = new HashSet<>(UNIQUE_NUMBERS);
        } else {
            possible = new HashSet<>();
            type = CellValueType.GIVEN;
        }
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getValue() { return value; }

    public void setRow(LinkedList<Cell> aRow) { lines.add(ROW_INDEX, aRow); }
    
    public void setColumn(LinkedList<Cell> aCol) {
        lines.add(COLUMN_INDEX, aCol);
    }
    
    public void setSubgrid(LinkedList<Cell> sub) {
        lines.add(SUBGRID_INDEX, sub);
    }

    public HashSet<Integer> getPossibleValues() {
        return possible;
    }
    
    public CellValueType getType() {
        return type;
    }

    public void setValue(int number) {
        value = number;
        type = CellValueType.DERIVED;
        possible.clear();
    }
    /**
     * Removes all the values in the input set from the possible set for this cell.
     * @param existing a set of known numbers.
     */
    public void removeSetFromPossible(Set<Integer> existing){
//        System.out.println("removeSetFromPossible(" + existing.toString() + ") in cell " + toString());
        possible.removeAll(existing);
        checkPossibleValue();
    }

    /**
     * if the attribute possible has only one data element, update the Cell value and remove <br>
     * this value from the associated row, column and subgrid.
     */
    private void checkPossibleValue() {
        // if only one value left in the possible set, then we have derived a value.
        if (possible.size() == 1) {
            for (Integer oneValue: possible) {
                foundValue(oneValue);
            }
        }
    }

    /**
     * This method updates the Cell with the found value.
     * It subsequently removes the found value from the associated row, column and subgrid.
     * @param aValue the found value for this cell.
     */
    public void foundValue(int aValue) {
//        System.out.println("foundValue(" + aValue + ") in cell: " + toString());
        setValue(aValue);

        // removes this value from the associating row, column and subgrid
        removeValueFromOtherCells(value);
    }

    /**
     * Removes the passed in value from possible values for all cells
     * that are in the same column, row and subgrid as this cell.
     * @param aValue a derived value.
     */
    //
    public void removeValueFromOtherCells(int aValue) {
        HashSet<Integer> set = new HashSet<>();
        set.add(aValue);
//        System.out.println(">> " + toString() + ".removeValueFromOtherCells " + set.toString() + ".");

        for (Cell cell: lines.get(ROW_INDEX)) {
            if (cell.value == 0) {
                cell.removeSetFromPossible(set);
            }
        }
        for (Cell cell: lines.get(COLUMN_INDEX)) {
            if (cell.value == 0) {
                cell.removeSetFromPossible(set);
            }
        }
        for (Cell cell: lines.get(SUBGRID_INDEX)) {
            if (cell.value == 0) {
                cell.removeSetFromPossible(set);
            }
        }
//        System.out.println(">> " + toString() + ".removeValueFromOtherCells " + set.toString() + ".");
    }

    /**
     * @return (rowIndex, columnIndex, value). If it is an UNKNOWN cell, the possible values <br>
     *     are also returned.
     */
    @Override
    public String toString() {
        String ret;
        
        ret = "(" + rowIndex + ", " + columnIndex + ", " + value + ")";
        if (value == 0) {
            ret += ". possible values are: " + possible.toString();
        }
        
        return ret;
    }
}
