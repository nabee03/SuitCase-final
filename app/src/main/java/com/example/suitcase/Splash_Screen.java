package com.example.suitcase;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.suitcase.databinding.ActivitySplashScreenBinding;

public class Splash_Screen extends AppCompatActivity {
    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ImageView imageView = binding.sImg;

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim);
        imageView.startAnimation(animation);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Screen.this, signup.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
