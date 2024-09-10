package com.mangu.tfmjuanma.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mangu.tfmjuanma.databinding.ActivityQuizBinding;
import com.mangu.tfmjuanma.model.Question;
import com.mangu.tfmjuanma.model.Quiz;
import com.mangu.tfmjuanma.service.FileService;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuizActivity extends AppCompatActivity {

    @Inject
    public FileService fileService;

    private ActivityQuizBinding binding;

    private Quiz quiz;

    private int index = 0;

    public int REQUEST_CODE = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeContent();
        initializeView();
    }

    private void initializeContent() {
        List<Question> questionList = fileService.getQuestions();
        this.quiz = new Quiz(questionList);
    }

    private void initializeView() {
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.btnNextQuestion.setOnClickListener(v -> {
            RadioButton viewById = findViewById(binding.radioGroup.getCheckedRadioButtonId());
            quiz.solve(viewById.getText().toString());
            displayNextQuestions();

        });
        displayData(quiz.getCurrentQuestion());
    }

    private void displayNextQuestions() {
        if (quiz.isFinished()) {
            Intent intent = new Intent(QuizActivity.this, QuizResultActivity.class);
            intent.putExtra("result", Parcels.wrap(quiz));
            startActivityForResult(intent, REQUEST_CODE);
        } else {
            setAnswersToRadioButton(quiz.getCurrentQuestion());
            binding.tvQuestion.setText("" + quiz.getCurrentQuestion().getTitle());
            binding.tvQuestionNumber.setText("Current Question: " + (quiz.getCurrentIndex() + 1));
        }
    }

    private void displayData(Question question) {
        binding.tvQuestion.setText(question.getTitle());
        binding.tvQuestionNumber.setText("Current Question: " + (quiz.getCurrentIndex() + 1));
        setAnswersToRadioButton(question);
    }

    private void setAnswersToRadioButton(Question question) {
        List<String> questions = question.getAnswers();
        binding.radioGroup.clearCheck();
        binding.radioButton1.setText("" + questions.get(0));
        binding.radioButton2.setText("" + questions.get(1));
        binding.radioButton3.setText("" + questions.get(2));
        binding.radioButton4.setText("" + questions.get(3));
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }
}