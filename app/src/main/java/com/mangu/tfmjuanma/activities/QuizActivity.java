package com.mangu.tfmjuanma.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mangu.tfmjuanma.databinding.ActivityQuizBinding;
import com.mangu.tfmjuanma.model.Question;
import com.mangu.tfmjuanma.service.FileService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuizActivity extends AppCompatActivity {

    @Inject
    public FileService fileService;

    private ActivityQuizBinding binding;

    private List<Question> questionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeContent();
        initializeView();

    }

    private void initializeContent() {
        questionList = fileService.getQuestions();
    }

    private void initializeView() {

    }
}