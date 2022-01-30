package com.francescomalagrino.dailytrackerapp.ui;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.contrib.ViewPagerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.francescomalagrino.dailytrackerapp.ui.MainActivity;
import com.francescomalagrino.dailytrackerapp.R;
import com.francescomalagrino.dailytrackerapp.utils.DeleteViewAction;
import com.francescomalagrino.dailytrackerapp.utils.ToastMatcher;
import com.google.android.gms.common.data.DataBuffer;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasBackground;
import static androidx.test.espresso.matcher.ViewMatchers.hasTextColor;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static com.google.android.gms.common.data.DataBufferUtils.hasData;
import static com.google.android.material.internal.ContextUtils.getActivity;
import static java.util.EnumSet.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class IntroActivityTest {



    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);



    private ActivityScenario<MainActivity> mActivity = null;
    private View decorView;

    @Before// setUp methods call every time before an execute test case
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getScenario();
        mActivityTestRule.getScenario().onActivity(new ActivityScenario.ActivityAction<MainActivity>() {
            @Override
            public void perform(MainActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });


    }


    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.francescomalagrino.dailytrackerapp",
                appContext.getPackageName());
    }

    @Test
    public void swipeUpDownLeftRight()  {
    onView(withId(R.id.my_mood)).perform(swipeUp());
      //  onView(withId(R.id.my_mood)).check(matches((Color.parseColor("#F8EC50"))))
        //  //create a custom background color check;
       // onView(withId(R.id.my_view)).check(matches(withBackgroundColour(R.color.color_to_check)))
    onView(withId(R.id.my_mood)).perform(swipeDown());
    onView(withId(R.id.my_mood)).perform(swipeLeft());
    onView(withId(R.id.my_mood)).perform(swipeRight());
    }

    @Test
    public void testLaunchCommentDialog() {

        // test launch comment
        onView(withId(R.id.btn_add_comment)).perform(click());

        //test the add commet.
        //first step is to click add button (already done)
        //second step is to write text on popup (the popup of the comment)
        onView(withId(R.id.commentEditText)).perform(typeText("Comment"));

        // third step is to press confirm
        onView(withText("CONFIRM")).perform(click());

        mActivity.onActivity(activity -> {
            Log.e("Date", ((MainActivity) activity).currentDay + "current day");
        });
        onView(withId(R.id.btn_mood_history)).perform(click());

        // forth step is to open the hystory screen, press the comment button
        onView(ViewMatchers.withId(R.id.reycler_moods)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));

     /*   try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mActivity.onActivity(activity -> {
                       onView(withText("Message")).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        });
    */
        // onData(allOf(is(instanceOf(String.class)))).atPosition(0).onChildView(withId(R.id.btn_show_comment)).perform(click());
     //
        //check the toast seems is not working with any of this test !
        //  onView(withText("Comment")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
        //  onView(withText("Comment")).inRoot(new ToastMatcher()).check(matches(withText("Comment")));
       // onView(withText("Comment"))
      //         .inRoot(withDecorView(not(decorView)))// Here we use decorView
        //        .check(matches(isDisplayed()));

        //
    }


    @Test
    public void shouldShowToast() {
        onView(withId(R.id.btn_mood_history)).perform(click());
        mActivity.onActivity(activity -> {
            onView(withText("Message")).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        });
    }


    @Test
    public void testLaunchShare() {
        onView(withId(R.id.btn_share)).perform(click());
        onView(withText("Hello! I would like to share with you my mood of the day from MoodTracker App.Today my Mood is...")).check(matches(isDisplayed()));
    }


    @After// tearDown methods that will be called every time after executing the test
    public void tearDown() throws Exception {
        mActivity = null;
    }


}