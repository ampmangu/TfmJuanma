package com.mangu.tfmjuanma.activities;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mangu.tfmjuanma.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MainActivity.this.startActivity(new Intent(MainActivity.this, RecapActivity.class));
        initializeView();
    }

    private void initializeView() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initializeButtons();
    }

    private void initializeButtons() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Burbank Big Condensed Bold.otf");
        binding.btnRecap.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RecapActivity.class)));
        binding.btnRecap.setTypeface(typeface);
        binding.btnHangman.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HangmanActivity.class)));
        binding.btnHangman.setTypeface(typeface);
    }
}