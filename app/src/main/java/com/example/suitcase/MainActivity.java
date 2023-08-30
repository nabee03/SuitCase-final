package com.example.suitcase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.suitcase.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    ActivityMainBinding binding;


    public class mainActivity extends AppCompatActivity {
        ActivityMainBinding binding;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());


            binding.nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();
                    if (id == R.id.item_home) {
                        Intent intent = new Intent(getApplicationContext(), mainActivity.class);
                        startActivity(intent);
                        Toast.makeText(mainActivity.this, "Click to Home", Toast.LENGTH_SHORT).show();
                    }
                    if (id == R.id.item_about) {
                        Toast.makeText(mainActivity.this, "Click to about ", Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }
            });
            final DrawerLayout drawerLayout = findViewById(R.id.drawer);
            findViewById(R.id.nav_menu).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            });


            binding.fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Add_Items.class);
                    startActivity(intent);
                }
            });
        }
    }
}




