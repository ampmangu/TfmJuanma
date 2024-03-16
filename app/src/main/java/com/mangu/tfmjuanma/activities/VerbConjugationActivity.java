package com.mangu.tfmjuanma.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mangu.tfmjuanma.R;
import com.mangu.tfmjuanma.databinding.ActivityVerbConjugationBinding;
import com.mangu.tfmjuanma.model.Tense;
import com.mangu.tfmjuanma.model.Verb;

import org.parceler.Parcels;

public class VerbConjugationActivity extends AppCompatActivity {

    private ActivityVerbConjugationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        showVerb();
    }

    private void showVerb() {
        Verb verb = Parcels.unwrap(getIntent().getParcelableExtra("verb"));
        setTitle(verb.getName());
        final TableLayout tableLayout = binding.tableLayout;
        verb.getTenses().forEach(tense -> {
            addTense(tableLayout, tense);
        });
    }

    private void addTense(TableLayout tableLayout, Tense tense) {
        final TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.verb_conjugation_row, null);
        TextView tv;

        tv = (TextView) tableRow.findViewById(R.id.verbForm);
        tv.setText(tense.getName());

        tv = (TextView) tableRow.findViewById(R.id.tc1);
        tv.setText(tense.getFirstsingular());
        tv = (TextView) tableRow.findViewById(R.id.tc2);
        tv.setText(tense.getSecondsingular());
        tv = (TextView) tableRow.findViewById(R.id.tc3);
        tv.setText(tense.getThirdsingular());
        tv = (TextView) tableRow.findViewById(R.id.tc4);
        tv.setText(tense.getFirstplural());
        tv = (TextView) tableRow.findViewById(R.id.tc5);
        tv.setText(tense.getSecondplural());
        tv = (TextView) tableRow.findViewById(R.id.tc6);
        tv.setText(tense.getThirdplural());
        tableRow.setPadding(5, 10, 5, 0);
        tableLayout.addView(tableRow);


    }

    private void initializeView() {
        binding = ActivityVerbConjugationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }
}