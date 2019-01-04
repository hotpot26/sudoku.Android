package com.example.sudoku;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.sudoku.engine.Cell;
import com.example.sudoku.engine.SudokuEngine;

import java.util.LinkedList;
import java.util.List;

public class PuzzleActivity extends AppCompatActivity {
    SudokuEngine engine = new SudokuEngine();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid);
    }

    // show the grid onto the display
    protected String[][] convertGridToString(int[][] grid) {
        String [][] gridInString = new String[9][9];

        for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
            for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
                if (grid[rowIndex][columnIndex] == 0) {
                    gridInString[rowIndex][columnIndex] = "";
                } else {
                    gridInString[rowIndex][columnIndex] = String.valueOf(grid[rowIndex][columnIndex]);
                }
            }
        }

        return gridInString;
    }

    protected String[][] convertListToString(List<LinkedList<Cell>> rows) {
        String [][] gridInString = new String[9][9];

        for (LinkedList<Cell> row : rows) {
            for (Cell cell : row) {
                if (cell.value == 0) {
                    gridInString[cell.getRowIndex()][cell.getColumnIndex()] = "";
                } else {
                    gridInString[cell.getRowIndex()][cell.getColumnIndex()] = String.valueOf(cell.value);
                }
            }
        }

        return gridInString;
    }

    protected void displayGrid(String[][] grid) {
        TextView textView;

        // row 0
        textView = findViewById(R.id.textView_00);
        textView.setText(grid[0][0]);
        textView = findViewById(R.id.textView_01);
        textView.setText(grid[0][1]);
        textView = findViewById(R.id.textView_02);
        textView.setText(grid[0][2]);
        textView = findViewById(R.id.textView_03);
        textView.setText(grid[0][3]);
        textView = findViewById(R.id.textView_04);
        textView.setText(grid[0][4]);
        textView = findViewById(R.id.textView_05);
        textView.setText(grid[0][5]);
        textView = findViewById(R.id.textView_06);
        textView.setText(grid[0][6]);
        textView = findViewById(R.id.textView_07);
        textView.setText(grid[0][7]);
        textView = findViewById(R.id.textView_08);
        textView.setText(grid[0][8]);

        // row 1
        textView = findViewById(R.id.textView_10);
        textView.setText(grid[1][0]);
        textView = findViewById(R.id.textView_11);
        textView.setText(grid[1][1]);
        textView = findViewById(R.id.textView_12);
        textView.setText(grid[1][2]);
        textView = findViewById(R.id.textView_13);
        textView.setText(grid[1][3]);
        textView = findViewById(R.id.textView_14);
        textView.setText(grid[1][4]);
        textView = findViewById(R.id.textView_15);
        textView.setText(grid[1][5]);
        textView = findViewById(R.id.textView_16);
        textView.setText(grid[1][6]);
        textView = findViewById(R.id.textView_17);
        textView.setText(grid[1][7]);
        textView = findViewById(R.id.textView_18);
        textView.setText(grid[1][8]);

        // row 2
        textView = findViewById(R.id.textView_20);
        textView.setText(grid[2][0]);
        textView = findViewById(R.id.textView_21);
        textView.setText(grid[2][1]);
        textView = findViewById(R.id.textView_22);
        textView.setText(grid[2][2]);
        textView = findViewById(R.id.textView_23);
        textView.setText(grid[2][3]);
        textView = findViewById(R.id.textView_24);
        textView.setText(grid[2][4]);
        textView = findViewById(R.id.textView_25);
        textView.setText(grid[2][5]);
        textView = findViewById(R.id.textView_26);
        textView.setText(grid[2][6]);
        textView = findViewById(R.id.textView_27);
        textView.setText(grid[2][7]);
        textView = findViewById(R.id.textView_28);
        textView.setText(grid[2][8]);

        // row 3
        textView = findViewById(R.id.textView_30);
        textView.setText(grid[3][0]);
        textView = findViewById(R.id.textView_31);
        textView.setText(grid[3][1]);
        textView = findViewById(R.id.textView_32);
        textView.setText(grid[3][2]);
        textView = findViewById(R.id.textView_33);
        textView.setText(grid[3][3]);
        textView = findViewById(R.id.textView_34);
        textView.setText(grid[3][4]);
        textView = findViewById(R.id.textView_35);
        textView.setText(grid[3][5]);
        textView = findViewById(R.id.textView_36);
        textView.setText(grid[3][6]);
        textView = findViewById(R.id.textView_37);
        textView.setText(grid[3][7]);
        textView = findViewById(R.id.textView_38);
        textView.setText(grid[3][8]);

        // row 4
        textView = findViewById(R.id.textView_40);
        textView.setText(grid[4][0]);
        textView = findViewById(R.id.textView_41);
        textView.setText(grid[4][1]);
        textView = findViewById(R.id.textView_42);
        textView.setText(grid[4][2]);
        textView = findViewById(R.id.textView_43);
        textView.setText(grid[4][3]);
        textView = findViewById(R.id.textView_44);
        textView.setText(grid[4][4]);
        textView = findViewById(R.id.textView_45);
        textView.setText(grid[4][5]);
        textView = findViewById(R.id.textView_46);
        textView.setText(grid[4][6]);
        textView = findViewById(R.id.textView_47);
        textView.setText(grid[4][7]);
        textView = findViewById(R.id.textView_48);
        textView.setText(grid[4][8]);

        // row 5
        textView = findViewById(R.id.textView_50);
        textView.setText(grid[5][0]);
        textView = findViewById(R.id.textView_51);
        textView.setText(grid[5][1]);
        textView = findViewById(R.id.textView_52);
        textView.setText(grid[5][2]);
        textView = findViewById(R.id.textView_53);
        textView.setText(grid[5][3]);
        textView = findViewById(R.id.textView_54);
        textView.setText(grid[5][4]);
        textView = findViewById(R.id.textView_55);
        textView.setText(grid[5][5]);
        textView = findViewById(R.id.textView_56);
        textView.setText(grid[5][6]);
        textView = findViewById(R.id.textView_57);
        textView.setText(grid[5][7]);
        textView = findViewById(R.id.textView_58);
        textView.setText(grid[5][8]);

        // row 6
        textView = findViewById(R.id.textView_60);
        textView.setText(grid[6][0]);
        textView = findViewById(R.id.textView_61);
        textView.setText(grid[6][1]);
        textView = findViewById(R.id.textView_62);
        textView.setText(grid[6][2]);
        textView = findViewById(R.id.textView_63);
        textView.setText(grid[6][3]);
        textView = findViewById(R.id.textView_64);
        textView.setText(grid[6][4]);
        textView = findViewById(R.id.textView_65);
        textView.setText(grid[6][5]);
        textView = findViewById(R.id.textView_66);
        textView.setText(grid[6][6]);
        textView = findViewById(R.id.textView_67);
        textView.setText(grid[6][7]);
        textView = findViewById(R.id.textView_68);
        textView.setText(grid[6][8]);

        // row 7
        textView = findViewById(R.id.textView_70);
        textView.setText(grid[7][0]);
        textView = findViewById(R.id.textView_71);
        textView.setText(grid[7][1]);
        textView = findViewById(R.id.textView_72);
        textView.setText(grid[7][2]);
        textView = findViewById(R.id.textView_73);
        textView.setText(grid[7][3]);
        textView = findViewById(R.id.textView_74);
        textView.setText(grid[7][4]);
        textView = findViewById(R.id.textView_75);
        textView.setText(grid[7][5]);
        textView = findViewById(R.id.textView_76);
        textView.setText(grid[7][6]);
        textView = findViewById(R.id.textView_77);
        textView.setText(grid[7][7]);
        textView = findViewById(R.id.textView_78);
        textView.setText(grid[7][8]);

        // row 8
        textView = findViewById(R.id.textView_80);
        textView.setText(grid[8][0]);
        textView = findViewById(R.id.textView_81);
        textView.setText(grid[8][1]);
        textView = findViewById(R.id.textView_82);
        textView.setText(grid[8][2]);
        textView = findViewById(R.id.textView_83);
        textView.setText(grid[8][3]);
        textView = findViewById(R.id.textView_84);
        textView.setText(grid[8][4]);
        textView = findViewById(R.id.textView_85);
        textView.setText(grid[8][5]);
        textView = findViewById(R.id.textView_86);
        textView.setText(grid[8][6]);
        textView = findViewById(R.id.textView_87);
        textView.setText(grid[8][7]);
        textView = findViewById(R.id.textView_88);
        textView.setText(grid[8][8]);
    }
}
