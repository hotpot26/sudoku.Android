package com.example.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String ORIGINAL_MESSAGE = "com.example.sudoku.ORIGINAL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Original button */
    public void originalPuzzle (View view) {
        Intent intent = new Intent(this, OriginalPuzzleActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the New button */
    public void newPuzzle (View view) {
        Intent intent = new Intent(this, NewPuzzleActivity.class);
        startActivity(intent);
    }
}
