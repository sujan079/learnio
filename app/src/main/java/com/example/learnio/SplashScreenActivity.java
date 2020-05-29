package com.example.learnio;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreenActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private Integer delay_time = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null)
            start(MainActivity.class);
        else
            start(WelcomeActivity.class);
    }

    public void start(final Class desitnation) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent activity = new Intent(SplashScreenActivity.this, desitnation);
                startActivity(activity);
            }
        }, delay_time);
    }
}
