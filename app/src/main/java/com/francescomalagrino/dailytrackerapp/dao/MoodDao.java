package com.francescomalagrino.dailytrackerapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.francescomalagrino.dailytrackerapp.model.Mood;

import java.util.List;

@Dao
public interface MoodDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Mood mood);

    @Query("DELETE FROM mood_table")
    void deleteAll();

    @Query("SELECT * FROM mood_table ORDER BY moodDate ASC")
    LiveData<List<Mood>> getMoods();

    @Query("SELECT * FROM mood_table ORDER BY moodDate ASC LIMIT 1")
    LiveData<Mood> getCurrentMood();
}
