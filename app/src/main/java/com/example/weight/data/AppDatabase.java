package com.example.weight.data;

        import androidx.room.Database;
        import androidx.room.RoomDatabase;

import com.example.weight.model.Entity;

@Database(entities = {Entity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FirstDao firstDao();
}