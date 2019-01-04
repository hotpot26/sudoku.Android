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
 *
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
    public int value;
    private List<LinkedList<Cell>> lines;
    private HashSet<Integer> possible;
    private CellValueType type;

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

    public LinkedList<Cell> getRow() {
        return lines.get(ROW_INDEX);
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setRow(LinkedList<Cell> aRow) {
        lines.add(ROW_INDEX, aRow);
    }
    
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
    
    // set the value for the cell and clear the possible values
    // removes this value from the associating row, column and subgrid
    public void foundValue(int aValue) {
        System.out.println("foundValue(" + aValue + ") in cell: " + toString());
        value = aValue;
        type = CellValueType.DERIVED;
        possible.clear();

        // removes this value from the associating row, column and subgrid
        removeValue(value);
    }
    
    // removes all the values in the input set from the possible set for this cell.
    public void removeMultipleValuesFromSet(Set<Integer> existing){
        System.out.println("removeMultipleValuesFromSet(" + existing.toString() + ") in cell " + toString());
        possible.removeAll(existing);

        // if only one value left in the possible set, then we have derived a value.
        if (possible.size() == 1) {
            for (Integer aValue : possible) {
                foundValue(aValue);
            }
        }
    }

    // remove the single value in the input parameter from the possible set for this cell.
    public void removeOneValueFromSet(int aValue){
        System.out.println("removeOneValueFromSet(" + aValue + ") in cell " + toString());
        possible.remove(aValue);
        
        if (possible.size() == 1) {
            for (Integer i : possible) {
                foundValue(i);
            }
        }
    }
    
    // removes the passed in value from possible values for all cells 
    // that are in the same column, row and subgrid as this cell.
    public void removeValue(int aValue) {
        System.out.println(">> RemoveValue(" + aValue + ") from " + toString());
        for (Cell cell: lines.get(ROW_INDEX)) {
            if (cell.value == 0) {
                cell.removeOneValueFromSet(aValue);
            }
        }
        for (Cell cell: lines.get(COLUMN_INDEX)) {
            if (cell.value == 0) {
                cell.removeOneValueFromSet(aValue);
            }
        }
        for (Cell cell: lines.get(SUBGRID_INDEX)) {
            if (cell.value == 0) {
                cell.removeOneValueFromSet(aValue);
            }
        }
        System.out.println("<< RemoveValue(" + aValue + ") from " + toString());
    }
    
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
