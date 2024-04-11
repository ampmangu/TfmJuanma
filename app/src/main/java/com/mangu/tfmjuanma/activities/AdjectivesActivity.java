package com.mangu.tfmjuanma.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mangu.tfmjuanma.R;
import com.mangu.tfmjuanma.databinding.ActivityAdjectivesBinding;
import com.mangu.tfmjuanma.model.Adjective;
import com.mangu.tfmjuanma.service.FileService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AdjectivesActivity extends AppCompatActivity {

    private ActivityAdjectivesBinding binding;

    @Inject
    public FileService fileService;

    private List<Adjective> adjectiveList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        addContentRows();
    }

    private void addContentRows() {
        this.adjectiveList = this.fileService.getAdjectives();
        this.adjectiveList.forEach(adjective -> addAdjectiveToLayout(adjective));
    }

    private void addAdjectiveToLayout(Adjective adjective) {
        final TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerow_two, null);
        TextView tv;

        tv = (TextView) tableRow.findViewById(R.id.tableCell1);
        tv.setText(capitalize(adjective.getAdjective()));

        tv = (TextView) tableRow.findViewById(R.id.tableCell2);
        tv.setText(capitalize(adjective.getTranslation()));

        binding.tableLayout.addView(tableRow);
    }

    private String capitalize(String adjective) {
        return adjective.substring(0, 1).toUpperCase() + adjective.substring(1);
    }

    private void initializeView() {
        binding = ActivityAdjectivesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
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

                List<Adjective> filtered = adjectiveList.stream().filter(adjective -> adjective.queryInAdjective(query)).collect(Collectors.toList());
                filtered.forEach(adjective -> addAdjectiveToLayout(adjective));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    binding.tableLayout.removeAllViews();
                    binding.tableLayout.addView(binding.defaultRow);
                    adjectiveList.forEach(adjective -> addAdjectiveToLayout(adjective));
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}