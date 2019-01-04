package com.example.sudoku;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sudoku.engine.Cell;
import com.example.sudoku.engine.SudokuEngine;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NewPuzzleActivity extends PuzzleActivity {
    SudokuEngine engine = new SudokuEngine();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_puzzle);

        // read in the grid, eventually from external file
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

        // initialize the engine
        engine.initialize(grid);

        // display the int grid onto the displayed puzzle
        displayGrid(convertGridToString(grid));
    }

    /** Called when the user taps the Solve button */
    public void solvePuzzle(View view) {
        int ret = engine.solve();
        if (ret != 0) {
            Snackbar.make(findViewById(R.id.myCoordinatorLayout), R.string.message_no_solution,
                    Snackbar.LENGTH_SHORT)
                    .show();
        } else {
            Snackbar.make(findViewById(R.id.myCoordinatorLayout), R.string.message_solved,
                    Snackbar.LENGTH_SHORT)
                    .show();
        }

        // display the array list onto the displayed puzzle
        displayGrid(convertListToString(engine.getRows()));
    }

}
