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
import com.mangu.tfmjuanma.databinding.ActivityHobbiesBinding;
import com.mangu.tfmjuanma.model.Hobby;
import com.mangu.tfmjuanma.service.FileService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HobbiesActivity extends AppCompatActivity {

    private ActivityHobbiesBinding binding;
    @Inject
    public FileService fileService;
    
    private List<Hobby> hobbyList = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        addContentRows();
    }

    private void addContentRows() {
        this.hobbyList = this.fileService.getHobbies();
        this.hobbyList.forEach(hobby -> addHobbyToLayout(hobby));
    }

    private void addHobbyToLayout(Hobby hobby) {
        final TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerow_two, null);
        TextView tv;

        tv = (TextView) tableRow.findViewById(R.id.tableCell1);
        tv.setText(capitalize(hobby.getHobby()));

        tv = (TextView) tableRow.findViewById(R.id.tableCell2);
        tv.setText(capitalize(hobby.getTranslation()));

        binding.tableLayout.addView(tableRow);
    }

    private String capitalize(String hobby) {
        return hobby.substring(0, 1).toUpperCase() + hobby.substring(1);
    }

    private void initializeView() {
        binding = ActivityHobbiesBinding.inflate(getLayoutInflater());
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

                List<Hobby> filtered = hobbyList.stream().filter(hobby -> hobby.queryInHobby(query)).collect(Collectors.toList());
                filtered.forEach(hobby -> addHobbyToLayout(hobby));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    binding.tableLayout.removeAllViews();
                    binding.tableLayout.addView(binding.defaultRow);
                    hobbyList.forEach(hobby -> addHobbyToLayout(hobby));
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }
}