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
import com.mangu.tfmjuanma.databinding.ActivityCollocationsBinding;
import com.mangu.tfmjuanma.model.Collocation;
import com.mangu.tfmjuanma.service.FileService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CollocationsActivity extends AppCompatActivity {
    private ActivityCollocationsBinding binding;
    @Inject
    public FileService fileService;

    private List<Collocation> collocationList = new ArrayList<>();

    private String previousPrefix = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        addContentRows();
    }

    private void addContentRows() {
        this.collocationList = this.fileService.getCollocations();
        this.collocationList.stream().filter(collocation -> !collocation.getCollocation().isEmpty()).forEach(collocation -> addCollocationToLayout(collocation));
    }

    private void addCollocationToLayout(Collocation collocation) {
        final TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerow_two, null);
        TextView tv;

        tv = (TextView) tableRow.findViewById(R.id.tableCell1);
        tv.setText(collocation.getCollocation());

        tv = (TextView) tableRow.findViewById(R.id.tableCell2);
        tv.setText(collocation.getMeaning());
        if (shouldAddPadding(collocation)) {
            tableRow.setPadding(5, 40, 5, 0);
        } else {
            tableRow.setPadding(5, 10, 5, 0);
        }
        binding.tableLayout.addView(tableRow);
    }

    private boolean shouldAddPadding(Collocation collocation) {
        String value = collocation.getCollocation();
        String prefix = value.split(" ")[0];
        boolean shouldAddPadding = !prefix.equalsIgnoreCase(previousPrefix);
        this.previousPrefix = prefix;
        return shouldAddPadding;
    }

    private void initializeView() {
        binding = ActivityCollocationsBinding.inflate(getLayoutInflater());
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
                List<Collocation> filtered = collocationList.stream().filter(collocation -> collocation.queryInCollocation(query)).collect(Collectors.toList());

                filtered.forEach(phrasalVerb -> addCollocationToLayout(phrasalVerb));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    binding.tableLayout.removeAllViews();
                    binding.tableLayout.addView(binding.defaultRow);
                    collocationList.forEach(collocation -> addCollocationToLayout(collocation));
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}