package com.example.weight.screens.details;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.weight.App;
import com.example.weight.R;
import com.example.weight.model.Entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class NoteDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_NOTE = "NoteDetailsActivity.EXTRA_NOTE";

    private Entity note;

    private EditText editText;

    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static void start(Activity caller, Entity note) {
        Intent intent = new Intent(caller, NoteDetailsActivity.class);
        if (note != null) {
            intent.putExtra(EXTRA_NOTE, note);
        }
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle("");

        editText = findViewById(R.id.text);

        if (getIntent().hasExtra(EXTRA_NOTE)) {
            note = getIntent().getParcelableExtra(EXTRA_NOTE);
            editText.setText(note.current_date);
        } else {
            note = new Entity();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_save:
                if (editText.getText().length() > 0) {
                    note.current_weight = Double.parseDouble(editText.getText().toString());
                    note.current_date = getData();
                    if (getIntent().hasExtra(EXTRA_NOTE)) {
                        App.getInstance().getNoteDao().update(note);
                    } else {
                        App.getInstance().getNoteDao().insert(note);
                    }
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private static String getData() {

        return dateFormat.format(new Date());
    }
}