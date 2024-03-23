package com.mangu.tfmjuanma.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mangu.tfmjuanma.R;
import com.mangu.tfmjuanma.databinding.ActivityPhrasalVerbBinding;
import com.mangu.tfmjuanma.model.PhrasalVerb;
import com.mangu.tfmjuanma.service.FileService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PhrasalVerbActivity extends AppCompatActivity {

    private ActivityPhrasalVerbBinding binding;
    @Inject
    public FileService fileService;

    private List<PhrasalVerb> phrasalVerbs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        addContentRows();
    }

    private void initializeView() {
        binding = ActivityPhrasalVerbBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private void addContentRows() {
        final TableLayout tableLayout = binding.tableLayout;
        this.phrasalVerbs = this.fileService.getPhrasalVerbs();
        this.phrasalVerbs.forEach(phrasalVerb -> addPhrasalVerbToLayout(tableLayout, phrasalVerb));
    }

    private void addPhrasalVerbToLayout(TableLayout tableLayout, PhrasalVerb phrasalVerb) {
        final TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerow_four, null);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                binding.tableLayout.removeAllViews();
                binding.tableLayout.addView(binding.defaultRow);
                List<PhrasalVerb> filtered = phrasalVerbs.stream().filter(verb -> verb.queryInVerb(query)).collect(Collectors.toList());

                filtered.forEach(phrasalVerb -> addPhrasalVerbToLayout(binding.tableLayout, phrasalVerb));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    binding.tableLayout.removeAllViews();
                    binding.tableLayout.addView(binding.defaultRow);
                    phrasalVerbs.forEach(phrasalVerb -> addPhrasalVerbToLayout(binding.tableLayout, phrasalVerb));
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}