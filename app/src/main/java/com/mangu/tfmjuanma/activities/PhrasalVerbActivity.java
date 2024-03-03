package com.mangu.tfmjuanma.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mangu.tfmjuanma.R;
import com.mangu.tfmjuanma.databinding.ActivityPhrasalVerbBinding;
import com.mangu.tfmjuanma.service.FileService;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PhrasalVerbActivity extends AppCompatActivity {

    private ActivityPhrasalVerbBinding binding;
    @Inject
    public FileService fileService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_phrasal_verb);
        initializeView();
        addContentRows();
    }

    private void addContentRows() {
        final TableLayout tableLayout = binding.tableLayout;
        this.fileService.getPhrasalVerbs().forEach(phrasalVerb -> {
            final TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerow, null);
            TextView tv;

            tv = (TextView) tableRow.findViewById(R.id.tableCell1);
            tv.setText(phrasalVerb.getName());

            tv = (TextView) tableRow.findViewById(R.id.tableCell2);
            tv.setText(phrasalVerb.getMeaning());

            tv = (TextView) tableRow.findViewById(R.id.tableCell3);
            tv.setText(phrasalVerb.getExample());

            tv = (TextView) tableRow.findViewById(R.id.tableCell4);
            tv.setText(phrasalVerb.getTranslation());
            tableRow.setPadding(5, 10, 5, 0);
            tableLayout.addView(tableRow);
        });
    }

    private void initializeView() {
        binding = ActivityPhrasalVerbBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}