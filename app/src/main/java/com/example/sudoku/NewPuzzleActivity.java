package com.example.sudoku;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.sudoku.engine.SudokuEngine;

public class NewPuzzleActivity extends PuzzleActivity {
    SudokuEngine engine = new SudokuEngine();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_puzzle);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String newPuzzle = intent.getStringExtra(MainActivity.NEW_MESSAGE);

        // if the length of the puzzle is less than 81, return.
        if (newPuzzle.length() != NUMBER_OF_CELLS * NUMBER_OF_CELLS) {
            Snackbar.make(findViewById(R.id.myCoordinatorLayout), R.string.invalid_puzzle,
                    Snackbar.LENGTH_SHORT)
                    .show();
            return;
        }

//        newPuzzle = "920000300050040020000206000000400010300005680000083004680130000004500102019000500";
//        int [][] grid = {
//                {9, 2, 0, 0, 0, 0, 3, 0, 0},
//                {0, 5, 0, 0, 4, 0, 0, 2, 0},
//                {0, 0, 0, 2, 0, 6, 0, 0, 0},
//                {0, 0, 0, 4, 0, 0, 0, 1, 0},
//                {3, 0, 0, 0, 0, 5, 6, 8, 0},
//                {0, 0, 0, 0, 8, 3, 0, 0, 4},
//                {6, 8, 0, 1, 3, 0, 0, 0, 0},
//                {0, 0, 4, 5, 0, 0, 1, 0, 2},
//                {0, 1, 9, 0, 0, 0, 5, 0, 0}
//        };
        int [][] grid = new int[NUMBER_OF_CELLS][NUMBER_OF_CELLS];
        int row, column;
        int index = 0;

        // read in the puzzle string into a grid.
        for (char c : newPuzzle.toCharArray()) {
            row = index / NUMBER_OF_CELLS;
            column = index % NUMBER_OF_CELLS;

            // isDigit may not be required as the resource for the PlainText is set to be Number.
            if (Character.isDigit(c)) {
                grid[row][column] = Character.getNumericValue(c);
            } else {
                // invalid value within the grid. message and then return.
                Snackbar.make(findViewById(R.id.myCoordinatorLayout), R.string.invalid_puzzle,
                        Snackbar.LENGTH_SHORT)
                        .show();
                return;
            }
            index++;
        }

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
