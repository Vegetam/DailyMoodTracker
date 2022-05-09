package com.francescomalagrino.dailytrackerapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.francescomalagrino.dailytrackerapp.dao.MoodDao;
import com.francescomalagrino.dailytrackerapp.entity.MoodsDatabase;
import com.francescomalagrino.dailytrackerapp.model.Mood;

import java.util.List;

public class MoodRepository {
    private MoodDao moodDao;
    private LiveData<List<Mood>> allMoods;
    private LiveData<Mood> mMood;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public MoodRepository(Application application) {
        MoodsDatabase db = MoodsDatabase.getDatabase(application);
        moodDao = db.mooddDao();
        allMoods = moodDao.getMoods();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Mood>> getAllMoods() {
        return allMoods;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Mood mood) {
        MoodsDatabase.databaseWriteExecutor.execute(() -> {
            moodDao.insert(mood);
        });

    }

    public LiveData<Mood> getCurrentMood() {return mMood;}
}
