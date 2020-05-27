package com.example.learnio;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private Integer delay_time = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        start();
    }

    public void start() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent activity = new Intent(SplashScreenActivity.this, WelcomeActivity.class);
                startActivity(activity);
            }
        }, delay_time);
    }
}
