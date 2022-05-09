package com.francescomalagrino.dailytrackerapp.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.francescomalagrino.dailytrackerapp.model.Mood;
import com.francescomalagrino.dailytrackerapp.repository.MoodRepository;

import java.util.List;

public class MoodViewModel extends AndroidViewModel{
    private MoodRepository mMoodRepository;

    private final LiveData<List<Mood>> allMoods;
    private final LiveData<Mood> mMood;

    public MoodViewModel (Application application) {
        super(application);
        mMoodRepository = new MoodRepository(application);
        allMoods = mMoodRepository.getAllMoods();
        mMood = mMoodRepository.getCurrentMood();
    }
    public LiveData<List<Mood>> getAllMoods() { return allMoods; }

    public void insert(Mood mood) { mMoodRepository.insert(mood); }

    public LiveData<Mood> getCurrentMood() { return mMood; }



}
