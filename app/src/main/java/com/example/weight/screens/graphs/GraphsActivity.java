package com.example.weight.screens.graphs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.weight.App;
import com.example.weight.R;
import com.example.weight.model.Entity;
import com.example.weight.screens.main.MainActivity;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphsActivity extends AppCompatActivity {

    private static final String EXTRA_NOTE = "GraphsActivity.EXTRA_NOTE";

    public static void start(Activity caller, Entity note) {
        Intent intent = new Intent(caller, GraphsActivity.class);
        if (note != null) {
            intent.putExtra(EXTRA_NOTE, note);
        }
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphview);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setTitle("Period");

        final GraphView graph = (GraphView) findViewById(R.id.graph);
        //Button button = findViewById(R.id.addButton);
        graph.setVisibility(View.VISIBLE);
       // getData();
     //   button.setOnClickListener(view -> {
            try {
                LineGraphSeries<DataPoint> series = new LineGraphSeries< >(getData());
                graph.addSeries(series);
            } catch (IllegalArgumentException e) {
         //       Toast.makeText(MainActivity.this,
         //               e.getMessage(),
         //               Toast.LENGTH_LONG).show();
            }
       // });
    }

    private static DataPoint[] getData() {
        Entity entity;
        List<Entity> list = App.getInstance().getNoteDao().getAll();
        ArrayList<DataPoint> list1 = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            entity = list.get(i);
            list1.add(new DataPoint(entity.uid, entity.current_weight));
        }

        DataPoint[] dataPoints = list1.toArray(new DataPoint[0]);
        return dataPoints;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}

