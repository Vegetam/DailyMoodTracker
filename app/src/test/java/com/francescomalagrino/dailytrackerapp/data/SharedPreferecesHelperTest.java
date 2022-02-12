package com.francescomalagrino.dailytrackerapp.data;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(MockitoJUnitRunner.class)
public class SharedPreferecesHelperTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Mock
    SharedPreferences mSharedPreferences;
    @Mock SharedPreferences.Editor mEditor;

     Mood mood = new Mood();



    @Test
    public void saveMood() {
        Gson gson = new Gson();
        mood.setMoodDate(0);
        mood.setMoodStatus(0);
        mood.setComment("Comment");
        String moodString = gson.toJson(mood);
        Mockito.when(mSharedPreferences.getString(SharedPreferencesHelper.KEY_MOOD + 0, "")).thenReturn(moodString);
        Mockito.when(mSharedPreferences.edit()).thenReturn(mEditor);
        Mockito.when(mSharedPreferences.edit().putString(SharedPreferencesHelper.KEY_MOOD+mood.moodDate, moodString)).thenReturn(mEditor);
        SharedPreferencesHelper.saveMood(mSharedPreferences, mood);
        Mood mood2 = gson.fromJson(mSharedPreferences.getString(SharedPreferencesHelper.KEY_MOOD + 0, ""), Mood.class);
        assertEquals(mood.getMoodDate(), mood2.getMoodDate());
        assertEquals(mood.getComment(), mood2.getComment());
        assertEquals(mood.getMoodStatus(), mood2.getMoodStatus());
    }

}
