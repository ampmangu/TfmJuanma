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
import com.mangu.tfmjuanma.databinding.ActivityCountriesBinding;
import com.mangu.tfmjuanma.model.Country;
import com.mangu.tfmjuanma.service.FileService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint

public class CountriesActivity extends AppCompatActivity {

    private ActivityCountriesBinding binding;

    @Inject
    public FileService fileService;

    private List<Country> countryList = new ArrayList<>();

    private List<TableRow> rows = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        addContentRows();
    }

    private void addContentRows() {
        this.countryList = this.fileService.getCountries();
        this.countryList.forEach(this::addCountryToLayout);
    }

    private void addCountryToLayout(Country country) {
        final TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerow_two, null);
        if (!country.sentence.isEmpty()) {
            TextView tv;

            tv = (TextView) tableRow.findViewById(R.id.tableCell1);
            tv.setText(country.getSentence());

            tv = (TextView) tableRow.findViewById(R.id.tableCell2);
            tv.setText(country.getExample());
            binding.tableLayout.addView(tableRow);
            rows.add(tableRow);
        } else {
            TableRow lastRow = rows.get(rows.size() - 1);
            lastRow.setPadding(0, 0, 0, 20);
        }

    }

    private void initializeView() {
        binding = ActivityCountriesBinding.inflate(getLayoutInflater());
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

                List<Country> countriesFiltered = countryList.stream().filter(country -> country.queryInCountry(query)).collect(Collectors.toList());
                countriesFiltered.forEach(country -> addCountryToLayout(country));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    binding.tableLayout.removeAllViews();
                    binding.tableLayout.addView(binding.defaultRow);
                    countryList.forEach(country -> addCountryToLayout(country));
                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}