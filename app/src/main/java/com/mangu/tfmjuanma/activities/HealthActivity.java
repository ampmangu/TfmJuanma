package com.mangu.tfmjuanma.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mangu.tfmjuanma.R;
import com.mangu.tfmjuanma.databinding.ActivityHealthBinding;
import com.mangu.tfmjuanma.model.Health;
import com.mangu.tfmjuanma.model.Method;
import com.mangu.tfmjuanma.service.FileService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HealthActivity extends AppCompatActivity {

    private ActivityHealthBinding binding;
    @Inject
    public FileService fileService;

    private List<Health> healthList = new ArrayList<>();

    private List<Method> methodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        initializeView();
        addContentRows();
    }

    private void addContentRows() {
        this.healthList = this.fileService.getHealthElements();
        this.healthList.forEach(this::addHealthToLayout);
        this.methodList = this.fileService.getMethods();
        this.methodList.forEach(this::addMethodsToLayout);
    }

    private void addMethodsToLayout(Method method) {
        final TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerow_two, null);
        TextView tv;

        tv = (TextView) tableRow.findViewById(R.id.tableCell1);
        tv.setText(method.getMethod());

        tv = (TextView) tableRow.findViewById(R.id.tableCell2);
        tv.setText(method.getMeaning());

        binding.tableLayout2.addView(tableRow);
    }

    private void addHealthToLayout(Health health) {
        final TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerow_four, null);
        TextView tv;

        tv = (TextView) tableRow.findViewById(R.id.tableCell1);
        tv.setText(health.getWord());

        tv = (TextView) tableRow.findViewById(R.id.tableCell2);
        tv.setText(health.getType());

        tv = (TextView) tableRow.findViewById(R.id.tableCell3);
        tv.setText(health.getMeaning());

        tv = (TextView) tableRow.findViewById(R.id.tableCell4);
        tv.setText(health.getExample());

//        tableRow.setPadding(5, 10, 5, 0);
        binding.tableLayout.addView(tableRow);
    }

    private void initializeView() {
        binding = ActivityHealthBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}