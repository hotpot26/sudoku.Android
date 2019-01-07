package com.example.sudoku;

import android.content.Intent;
import android.support.design.widget.Snackbar;
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
            Snackbar.make(findViewById(R.id.myCoordinatorLayout), R.string.not_enough_numbers,
                    Snackbar.LENGTH_SHORT)
                    .show();
            return;
        }

        // initialize the engine and display the original grid
        engine.initialize(newPuzzle);
        displayGrid(convertListToString(engine.getRows()));
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
