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
import com.mangu.tfmjuanma.databinding.ActivityFrequencyAdverbBinding;
import com.mangu.tfmjuanma.model.Adverb;
import com.mangu.tfmjuanma.service.FileService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AdverbActivity extends AppCompatActivity {

    @Inject
    public FileService fileService;

    private ActivityFrequencyAdverbBinding binding;

    private List<Adverb> adverbList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        addContentRows();
    }

    private String capitalize(String adjective) {
        return adjective.substring(0, 1).toUpperCase() + adjective.substring(1);
    }

    private void initializeView() {
        binding = ActivityFrequencyAdverbBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private void addContentRows() {
        String adverbType = getIntent().getStringExtra("adverbType");
        if (adverbType.equalsIgnoreCase("frequency")) {
            this.adverbList = this.fileService.getFrequencyAdverbs();
        } else {
            this.adverbList = this.fileService.getTimeAdverbs();
        }
        this.adverbList.forEach(this::addAdverbToLayout);
    }

    private void addAdverbToLayout(Adverb adverb) {
        final TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerow_two, null);
        TextView tv;

        tv = (TextView) tableRow.findViewById(R.id.tableCell1);
        tv.setText(capitalize(adverb.getAdverb()));

        tv = (TextView) tableRow.findViewById(R.id.tableCell2);
        tv.setText(capitalize(adverb.getTranslation()));

        binding.tableLayout.addView(tableRow);
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
                List<Adverb> adverbsFiltered = adverbList.stream().filter(adverb -> adverb.adverbInQuery(query)).collect(Collectors.toList());
                adverbsFiltered.forEach(adverb -> addAdverbToLayout(adverb));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    binding.tableLayout.removeAllViews();
                    binding.tableLayout.addView(binding.defaultRow);
                    adverbList.forEach(adverb -> addAdverbToLayout(adverb));
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}