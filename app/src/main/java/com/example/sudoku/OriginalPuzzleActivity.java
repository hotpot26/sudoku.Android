package com.example.sudoku;

import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.view.View;

public class OriginalPuzzleActivity extends PuzzleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_original_puzzle);

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
