package com.example.suitcase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.suitcase.databinding.ActivityForgetPasswordPageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword_Page extends AppCompatActivity {
    ActivityForgetPasswordPageBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetPasswordPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        binding.getEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.forgotEmail.getText().toString().trim();

                // Check if the email exists and send a password reset email
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(ForgetPassword_Page.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Password reset email sent successfully
                                    Toast.makeText(ForgetPassword_Page.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), ResetPassword.class);
                                    intent.putExtra("email", email);
                                    startActivity(intent);
                                } else {
                                    // Email does not exist or there was an error
                                    Toast.makeText(ForgetPassword_Page.this, "Email does not exist or an error occurred", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
