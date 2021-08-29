package com.example.weight;

import android.app.Application;

import androidx.room.Room;

import com.example.weight.data.AppDatabase;
import com.example.weight.data.FirstDao;

public class App extends Application {

    private AppDatabase database;
    private FirstDao firstDao;

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "app-db-name")
                .allowMainThreadQueries()
                .build();

        firstDao = database.firstDao();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    public FirstDao getNoteDao() {
        return firstDao;
    }

    public void setNoteDao(FirstDao noteDao) {
        this.firstDao = firstDao;
    }
}