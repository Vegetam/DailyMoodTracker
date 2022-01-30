package com.francescomalagrino.dailytrackerapp.ui;

import android.content.Intent;


import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MoodHistoryActivityTest {

    //test launch mood history activity
    @Rule
    public ActivityScenarioRule activityRule = new ActivityScenarioRule<>(MoodHistoryActivity.class);



    @Test
    public void intent() {
        Intent intent = new Intent();
        intent.putExtra("your_key", "your_value");

        activityRule.getScenario();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}
