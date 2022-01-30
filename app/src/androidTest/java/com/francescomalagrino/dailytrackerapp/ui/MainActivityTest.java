package com.francescomalagrino.dailytrackerapp.ui;

import android.content.Context;


import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.francescomalagrino.dailytrackerapp.R;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public ActivityScenarioRule activityRule = new ActivityScenarioRule<>(MainActivity.class);

    private MainActivity mActivity = null;


    @Before// setUp methods call every time before an execute test case
    public void setUp() throws Exception {
        activityRule.getScenario();
    }


    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.francescomalagrino.dailytrackerapp",
                appContext.getPackageName());
    }

    @Test
    public void testLaunchCommentDialog() {


        // test launch comment

        Espresso.onView(withId(R.id.btn_add_comment)).perform(click());

    }

    @Test
    public void testViewBtnMoodHistory() {

        Espresso.onView(withId(R.id.btn_mood_history)).perform(click());

    }


    @Test
    public void testShareMood() {

        Espresso.onView(withId(R.id.btn_share)).perform(click());//test Share Mood

    }

}
