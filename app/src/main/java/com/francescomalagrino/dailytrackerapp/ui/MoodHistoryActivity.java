package com.francescomalagrino.dailytrackerapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.francescomalagrino.dailytrackerapp.R;

import java.util.ArrayList;

public class MoodHistoryActivity extends AppCompatActivity {
    private static final String TAG = "MoodHistoryActivity";

    private RecyclerView moodsRecyclerView;

    private MoodsAdapter moodsAdapter;
    private SharedPreferences mPreferences;
    private ArrayList<Integer> moods = new ArrayList<>();
    private ArrayList<String> comments = new ArrayList<>();
    private int currentDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_history);
    }
}