package com.example.learnio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }


    public void onSignIn(View view) {
        Intent signInIntent = new Intent(WelcomeActivity.this, SignInActivity.class);
        startActivity(signInIntent);
    }

    public void onSignUp(View view) {
        Intent signUpIntent = new Intent(WelcomeActivity.this, SignUpActivity.class);
        startActivity(signUpIntent);
    }
}
