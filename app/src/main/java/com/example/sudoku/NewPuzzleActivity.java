package com.example.sudoku;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewPuzzleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_puzzle);
    }

    /** Called when the user taps the Solve button */
    public void solvePuzzle(View view) {
        Snackbar.make(findViewById(R.id.myCoordinatorLayout), R.string.no_solution,
                Snackbar.LENGTH_SHORT)
                .show();
    }

}
