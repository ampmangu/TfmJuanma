package com.mangu.tfmjuanma.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mangu.tfmjuanma.databinding.ActivityRecapBinding;

public class RecapActivity extends AppCompatActivity {

    private ActivityRecapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
    }

    private void initializeView() {
        binding = ActivityRecapBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initializeButtons();
    }

    private void initializeButtons() {
        setTypefaces();
        binding.btnTenses.setOnClickListener(v -> startActivity(new Intent(RecapActivity.this, VerbTenseActivity.class)));
        binding.btnPhrasal.setOnClickListener(v -> startActivity(new Intent(RecapActivity.this, PhrasalVerbActivity.class)));
        binding.btnCollocations.setOnClickListener(v -> startActivity(new Intent(RecapActivity.this, CollocationsActivity.class)));
        binding.btnVocabulary.setOnClickListener(v -> startActivity(new Intent(RecapActivity.this, ReviewActivity.class)));
    }

    private void setTypefaces() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Burbank Big Condensed Bold.otf");
        binding.btnTenses.setTypeface(typeface);
        binding.btnCollocations.setTypeface(typeface);
        binding.btnPhrasal.setTypeface(typeface);
        binding.btnVocabulary.setTypeface(typeface);
    }
}