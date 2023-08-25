package com.example.suitcase;

import static android.os.SystemClock.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.suitcase.databinding.ActivitySplashScreenBinding;

public class Splash_Screen extends AppCompatActivity {
    ActivitySplashScreenBinding binding;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Set Animation in image and textView
        animation= AnimationUtils.loadAnimation(this,R.anim.anim);
        binding.logo.setAnimation(animation);
        binding.txtSplash.setAnimation(animation);


        //Splash Method
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(9000);
                    Intent intent = new Intent(getApplicationContext(), Login_Page.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }
}