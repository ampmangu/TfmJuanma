package com.mangu.tfmjuanma.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mangu.tfmjuanma.databinding.ActivityReviewBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ReviewActivity extends AppCompatActivity {

    private ActivityReviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();

    }

    private void initializeView() {
        binding = ActivityReviewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initializeButtons();
    }

    private void initializeButtons() {
        binding.btnAdjectives.setOnClickListener(v -> startActivity(new Intent(ReviewActivity.this, AdjectivesActivity.class)));
        binding.btnHobbies.setOnClickListener(v -> startActivity(new Intent(ReviewActivity.this, HobbiesActivity.class)));
    }
}