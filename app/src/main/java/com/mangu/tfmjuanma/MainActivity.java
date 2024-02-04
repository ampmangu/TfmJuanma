package com.mangu.tfmjuanma;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mangu.tfmjuanma.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
    }

    private void initializeView() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initializeButtons();
    }

    private void initializeButtons() {
        binding.btnRecap.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RecapActivity.class)));
        binding.btnReview.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ReviewActivity.class)));
    }
}