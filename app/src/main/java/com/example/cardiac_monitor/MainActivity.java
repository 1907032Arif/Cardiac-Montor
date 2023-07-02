package com.example.cardiac_monitor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;


/**
 * The MainActivity class represents the main activity of the Cardiac Monitor application.
 * It extends the AppCompatActivity class and provides the user interface for the application.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Called when the activity is created. It sets the content view and initializes the user interface.
     *
     * @param savedInstanceState The saved instance state of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Called when the back button is pressed. It shows a confirmation dialog before exiting the application.
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> MainActivity.super.onBackPressed())
                .setNegativeButton("No", null)
                .show();
    }
}