package com.example.weight.screens.main;

import android.os.Bundle;

import com.example.weight.R;
import com.example.weight.screens.details.NoteDetailsActivity;
import com.example.weight.screens.graphs.GraphsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        final Adapter adapter = new Adapter();
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> NoteDetailsActivity.start(MainActivity.this, null));

        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(view -> GraphsActivity.start(MainActivity.this, null));

        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getNoteLiveData().observe(this, adapter::setItems);
    }
}
