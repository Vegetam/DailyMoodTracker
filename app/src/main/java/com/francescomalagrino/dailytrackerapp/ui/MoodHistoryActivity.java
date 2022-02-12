package com.francescomalagrino.dailytrackerapp.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.francescomalagrino.dailytrackerapp.R;
import com.francescomalagrino.dailytrackerapp.adapter.MoodsAdapter;
import com.francescomalagrino.dailytrackerapp.data.Mood;
import com.francescomalagrino.dailytrackerapp.data.SharedPreferencesHelper;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MoodHistoryActivity extends AppCompatActivity {

    private static final String TAG = "MoodHistoryActivity";

    private RecyclerView moodsRecyclerView;

    private MoodsAdapter moodsAdapter;
    private SharedPreferences mPreferences;
    private ArrayList<Integer> moods = new ArrayList<>();
    private ArrayList<String> comments = new ArrayList<>();

    private ArrayList<Mood> mMood = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_history);
       // Log.d(TAG, "onCreate: MoodHistoryActivity");
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        moodsRecyclerView = findViewById(R.id.reycler_moods);
        moodsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        Gson gson = new Gson();
        for (int i=0; i<=7; i++) {
           if(mPreferences.contains(SharedPreferencesHelper.KEY_MOOD + i)) {
               Mood mood = gson.fromJson(mPreferences.getString(SharedPreferencesHelper.KEY_MOOD + i, ""), Mood.class);
               mMood.add(mood);
           }
        }



        moodsAdapter = new MoodsAdapter(this,mMood);
        moodsRecyclerView.setAdapter(moodsAdapter);
    }
}
