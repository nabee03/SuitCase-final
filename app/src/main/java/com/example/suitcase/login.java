package com.example.suitcase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    Button login;
    TextView signup;
    EditText Email,Password;
    TextView forgotPassword;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        firebaseAuth=FirebaseAuth.getInstance();
        init();

    }
    public void init(){
        signup=findViewById(R.id.txt_signup);
        login=findViewById(R.id.login_btn);
        Email=findViewById(R.id.txt_login_email);
        Password=findViewById(R.id.txt_login_password);
        forgotPassword=findViewById(R.id.txt_forgotPassword);

        forgotPassword.setOnClickListener(this::startPasswordRecoveryProcess);
        signup.setOnClickListener(this::startSignUpActivity);
        login.setOnClickListener(this::login);
    }

    private void byPass(View view) {
        startActivity( new Intent(getApplicationContext(), MainActivity.class));
    }

    private void login(View view) {
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();
        if (email.isEmpty()) {
            Toast.makeText(login.this, "Enter Email", Toast.LENGTH_SHORT).show();
            Email.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(login.this, "Enter Password", Toast.LENGTH_SHORT).show();
            Password.requestFocus();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void startPasswordRecoveryProcess(View view) {
        Intent intent = new Intent(getApplicationContext(),forgot_password.class);
        startActivity(intent);
    }

    private void startSignUpActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), signup.class);
        startActivity(intent);
    }
}

