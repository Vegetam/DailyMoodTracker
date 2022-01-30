package com.francescomalagrino.dailytrackerapp.data;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

public class SharedPreferencesHelper {

    public static final String KEY_CURRENT_MOOD = "KEY_CURRENT_MOOD";

    public static final String KEY_CURRENT_COMMENT = "KEY_CURRENT_COMMENT";
    //for mood
    public static final String KEY_MOOD = "NEW_KEY_MOOD";



    //Save Mood Method
    public static void saveMood(SharedPreferences preferences, Mood mood)  {
        Gson gson = new Gson();
        String moodString = gson.toJson(mood);
        //Lo preferences.edit().putString(KEY_MOOD+mood.moodDate, moodString).apply();g.e("saveMood: ", moodString);
        preferences.edit().putString(KEY_MOOD+mood.moodDate, moodString).apply();
    }



}

