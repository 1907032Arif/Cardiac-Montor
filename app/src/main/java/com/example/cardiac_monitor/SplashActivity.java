package com.example.cardiac_monitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * This activity is displayed as a splash screen when the app is launched.
 * It shows a splash screen for a certain duration and then navigates to the HomeActivity.
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * Called when the activity is starting. Sets the content view and initiates the splash screen timeout.
     *
     * @param savedInstanceState The saved instance state Bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        int SPLASH_TIME_OUT = 10000;
        new Handler().postDelayed(() -> {
            // This method will be executed once the timer is over
            // Start your app main activity
            Intent i = new Intent(SplashActivity.this, HomeActivity.class);
            startActivity(i);

            // close this activity
            finish();
        }, SPLASH_TIME_OUT);




    }

}