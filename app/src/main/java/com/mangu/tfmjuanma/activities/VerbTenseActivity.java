package com.mangu.tfmjuanma.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mangu.tfmjuanma.R;
import com.mangu.tfmjuanma.databinding.ActivityVerbTenseBinding;
import com.mangu.tfmjuanma.model.Verb;
import com.mangu.tfmjuanma.service.FileService;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class VerbTenseActivity extends AppCompatActivity {

    private ActivityVerbTenseBinding binding;

    @Inject
    public FileService fileService;

    private List<Verb> verbList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        addContentRows();
    }

    private void addContentRows() {
        final TableLayout tableLayout = binding.tableVerbs;
        this.verbList = this.fileService.getVerbs();
        addVerbsToView(tableLayout);
    }

    private void addVerbsToView(TableLayout tableLayout) {
        if (!verbList.isEmpty()) {
            verbList.forEach(verb -> {
                final TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.verb_row, null);
                TextView tv;

                tv = (TextView) tableRow.findViewById(R.id.tableCellVerb);
                tv.setText(verb.getName());
                tv.setOnClickListener(v -> {
                    Intent intent = new Intent(VerbTenseActivity.this, VerbConjugationActivity.class);
                    intent.putExtra("verb", Parcels.wrap(verb));
                    startActivity(intent);
                });
                tableLayout.addView(tableRow);
            });
        }
    }

    private void initializeView() {
        binding = ActivityVerbTenseBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Burbank Big Condensed Bold.otf");
        binding.textView.setTypeface(typeface);
    }
}