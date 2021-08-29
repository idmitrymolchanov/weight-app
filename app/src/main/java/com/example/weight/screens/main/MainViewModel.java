package com.example.weight.screens.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.weight.App;
import com.example.weight.model.Entity;

import java.util.List;

public class MainViewModel extends ViewModel {
    private LiveData<List<Entity>> noteLiveData = App.getInstance().getNoteDao().getAllLiveData();

    public LiveData<List<Entity>> getNoteLiveData() {
        return noteLiveData;
    }
}