package com.francescomalagrino.dailytrackerapp.controller;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.lifecycle.ViewModelProvider;

import com.francescomalagrino.dailytrackerapp.R;
import com.francescomalagrino.dailytrackerapp.model.Mood;
import com.francescomalagrino.dailytrackerapp.ui.MoodViewModel;
import com.francescomalagrino.dailytrackerapp.util.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private static final String TAG = "MainActivity";
    // Class name for Log tag
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private ImageView moodImageView;
    private ImageButton moodHistoryButton;
    private ImageButton addCommentButton;
    private ImageButton shareAppButton;
    private GestureDetectorCompat mDetector;
    private RelativeLayout parentRelativeLayout;
    public int currentDay;
    private int currentMoodIndex;
    private String currentComment;
    private  FirebaseAnalytics mFirebaseAnalytics;
    private MoodViewModel mMoodsViewModel;
    private Mood mood = new Mood();


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: MainActivity");

        mMoodsViewModel = new ViewModelProvider(this).get(MoodViewModel.class);
        moodImageView = findViewById(R.id.my_mood);
        parentRelativeLayout = findViewById(R.id.parent_relative_layout);
        addCommentButton = findViewById(R.id.btn_add_comment);
        moodHistoryButton = findViewById(R.id.btn_mood_history);
        shareAppButton = findViewById(R.id.btn_share);

        mDetector = new GestureDetectorCompat(this, this);

        // int defaultDay = Calendar.getInstance().getTime().getDay();
        currentDay =  Calendar.getInstance().getTime().getDay();
        // Log.e("onCreate: ", currentDay + "");



     //  currentMoodIndex = mPreferences.getInt(SharedPreferencesHelper.KEY_CURRENT_MOOD, 3);
       mMoodsViewModel.getCurrentMood();
      //  currentComment = mPreferences.getString(SharedPreferencesHelper.KEY_CURRENT_COMMENT, "");


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        changeUiForMood(currentMoodIndex);
        mood.setMoodStatus(currentMoodIndex);
        mood.setMoodDate(currentDay);
        mMoodsViewModel.insert(mood);
        //SharedPreferencesHelper.saveMood(mPreferences, mood);


        //*****************************Add comment to the Mood********************************/

        addCommentButton.setOnClickListener(view -> {
            //  Log.d(LOG_TAG, "Button clicked!");
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            final EditText editText = new EditText(MainActivity.this);
            editText.setId(R.id.commentEditText);

            builder.setMessage("Comment\uD83E\uDD14 \uD83D\uDCDD").setView(editText)
                    .setPositiveButton("CONFIRM", (dialog, which) -> {
                        if (!editText.getText().toString().isEmpty()) {
                            mood.setComment(editText.getText().toString());
                           // SharedPreferencesHelper.saveMood(mPreferences,mood);
                            mMoodsViewModel.insert(mood);
                        }


                        dialog.dismiss();

                        Toast.makeText(MainActivity.this, "Comment Saved", Toast.LENGTH_SHORT).show();

                    });
            builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> {
                dialog.cancel();

                Toast.makeText(MainActivity.this, "Comment Canceled", Toast.LENGTH_SHORT).show();
            })
                    .create().show();


        });


        //* History Button to view Mood history screen*/
        moodHistoryButton.setOnClickListener(v -> {
            Log.d(LOG_TAG, "Button clicked!");

            Intent intent = new Intent(MainActivity.this, MoodHistoryActivity.class);
            startActivity(intent);
        });

        //*Share your mood Button*/
        shareAppButton.setOnClickListener(v -> {
            //   Log.d(LOG_TAG, "Button clicked!");
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello! I would like to share with you my mood of the day from MoodTracker App.Today my Mood is... ");
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_To)));
        });

    }

    @Override
    public boolean onDown(MotionEvent e) {
        // Not needed for this project
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        // Not needed for this project

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // Not needed for this project
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        // Not needed for this project
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // Not needed for this project

    }


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        // Swiping up
        if (e1.getY() - e2.getY() > 50) {
            if (currentMoodIndex < 4) {
                currentMoodIndex++;
                changeUiForMood(currentMoodIndex);
                mood.setMoodStatus(currentMoodIndex);
                mood.setMoodDate(currentDay);
                mMoodsViewModel.insert(mood);
              //  SharedPreferencesHelper.saveMood(mPreferences, mood);

            }

        }

        // Swiping down
        else if (e1.getY() - e2.getY() < 50) {
            if (currentMoodIndex > 0) {
                currentMoodIndex--;
                changeUiForMood(currentMoodIndex);
                mood.setMoodStatus(currentMoodIndex);
                mood.setMoodDate(currentDay);
                mMoodsViewModel.insert(mood);
             //   SharedPreferencesHelper.saveMood(mPreferences, mood);
            }
        }
        return true;
    }

    //************************* change mood methods***************************************/
    private void changeUiForMood(int currentMoodIndex) {
        moodImageView.setImageResource(Constants.moodImagesArray[currentMoodIndex]);
        parentRelativeLayout.setBackgroundResource(Constants.moodColorsArray[currentMoodIndex]);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, Constants.moodSoundsArray[currentMoodIndex]);
        mediaPlayer.start();
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

}