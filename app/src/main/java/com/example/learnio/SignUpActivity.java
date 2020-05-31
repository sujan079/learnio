package com.example.learnio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private final Integer RC_SIGN_UP = 100;
    private GoogleSignInClient mGoogleSignInClient;

    private Button mSignUpBtn, mSignUpWithGoogleBtn;
    private EditText etUserName, etUserPassword, etUserEmail;

    private TextView tvErrorMsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();
        initGoogleClient();
        initButtons();

        mAuth = FirebaseAuth.getInstance();
    }

    //id are set here
    public void init() {

        tvErrorMsg = findViewById(R.id.tv_error_msg);

        mSignUpWithGoogleBtn = findViewById(R.id.btn_sign_up_google);
        mSignUpBtn = findViewById(R.id.btn_sign_up);

        etUserEmail = findViewById(R.id.etEmail);
        etUserName = findViewById(R.id.etUsername);
        etUserPassword = findViewById(R.id.etPassword);
    }

    private void initGoogleClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }


    private void initButtons() {
        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUserName.getText().toString();
                String password = etUserPassword.getText().toString();
                String email = etUserEmail.getText().toString();
                if (email.equals("") || password.equals(""))
                    Toast.makeText(getApplicationContext(), "Fill Information", Toast.LENGTH_SHORT).show();
                else
                    signUpWithEmailAndPassword(email, password);
            }
        });
        mSignUpWithGoogleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpWithGoogle();
            }
        });
    }

    private void signUpWithEmailAndPassword(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI();
                        } else {
                            displayErrorMsg(task.getException().getMessage());
                        }
                    }
                });

    }


    private void signUpWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_UP);
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI();
                        } else {

                            displayErrorMsg(task.getException().getMessage());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        displayErrorMsg(e.getMessage());
                    }
                });

    }


    private void displayErrorMsg(String error) {
        tvErrorMsg.setVisibility(View.VISIBLE);
        tvErrorMsg.setText(error);
    }

    private void updateUI() {

        Intent activityIntent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(activityIntent);
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_UP) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());

            } catch (ApiException e) {
                e.printStackTrace();
            }

        }
    }


}
