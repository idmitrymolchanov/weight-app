package com.example.weight.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.weight.model.Entity;

import java.util.List;

@Dao
public interface FirstDao {

    @Query("SELECT * FROM Entity")
    List<Entity> getAll();

    @Query("SELECT * FROM Entity")
    LiveData<List<Entity>> getAllLiveData();

    @Query("SELECT * FROM Entity WHERE uid IN (:noteIds)")
    List<Entity> loadAllByIds(int[] noteIds);

    @Query("SELECT * FROM Entity WHERE uid = :uid LIMIT 1")
    Entity findById(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Entity note);

    @Update
    void update(Entity note);

    @Delete
    void delete(Entity note);
}

