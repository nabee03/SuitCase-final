package com.example.suitcase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.suitcase.databinding.ActivityForgetPasswordPageBinding;

public class ForgetPassword_Page extends AppCompatActivity {
    ActivityForgetPasswordPageBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityForgetPasswordPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper=new DatabaseHelper(this);

        binding.getEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=binding.forgotEmail.getText().toString().trim();
                Boolean cheakUsers=databaseHelper.checkEmail(email);

                if (cheakUsers==true){
                    Intent intent=new Intent(getApplicationContext(),ResetPassword.class);
                    intent.putExtra("email",email);
                    startActivity(intent);
                }else {
                    Toast.makeText(ForgetPassword_Page.this, "Email does not exists", Toast.LENGTH_SHORT).show();
                }
                }
        });
    }
}