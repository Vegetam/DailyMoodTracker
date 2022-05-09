package com.francescomalagrino.dailytrackerapp.controller;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.francescomalagrino.dailytrackerapp.R;
import com.francescomalagrino.dailytrackerapp.ui.MoodViewModel;
import com.francescomalagrino.dailytrackerapp.view.MoodsAdapter;

public class MoodHistoryActivity extends AppCompatActivity {

    private static final String TAG = "MoodHistoryActivity";

    private RecyclerView moodsRecyclerView;

    private MoodsAdapter moodsAdapter;

    private MoodViewModel mMoodsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_history);
        mMoodsViewModel = new ViewModelProvider(this).get(MoodViewModel.class);
        mMoodsViewModel.getAllMoods().observe(this, moods -> {
            moodsRecyclerView = findViewById(R.id.reycler_moods);
            moodsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
            moodsAdapter = new MoodsAdapter(this,moods);
            moodsRecyclerView.setAdapter(moodsAdapter);
        });

    }
}