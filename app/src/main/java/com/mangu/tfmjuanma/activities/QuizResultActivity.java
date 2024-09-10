package com.mangu.tfmjuanma.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mangu.tfmjuanma.R;
import com.mangu.tfmjuanma.databinding.ActivityQuizResultBinding;
import com.mangu.tfmjuanma.model.Quiz;

import org.parceler.Parcels;

public class QuizResultActivity extends AppCompatActivity {

    private ActivityQuizResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        showResult();
    }

    private void showResult() {
        Quiz quiz = Parcels.unwrap(getIntent().getParcelableExtra("result"));
        if (quiz != null) {
            Long gotRight = quiz.getResult();
            Integer total = quiz.getMaxQuestions();
            String passed = gotRight > total / 2 ? getString(R.string.passed) : getString(R.string.no_passed);
            binding.results.setText(String.format(getString(R.string.results), gotRight, total, passed));
            quiz.getResults().forEach((index, pair) -> {
                final TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.quiz_result, null);
                TextView tv;
                tv = (TextView) tableRow.findViewById(R.id.tvAnswer);
                if (pair.second) {
                    tv.setTextColor(getResources().getColor(R.color.green));
                } else {
                    tv.setTextColor(getResources().getColor(R.color.red));
                }
                tv.setText(pair.first);
                tv.setPadding(5, 10, 5, 0);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f);
                binding.tableLayout.addView(tableRow);
            });

        }
    }

    private void initializeView() {
        binding = ActivityQuizResultBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        if (getParent() == null) {
            setResult(Activity.RESULT_OK, intent);
        } else {
            getParent().setResult(Activity.RESULT_OK, intent);
        }
        finish();
    }
}