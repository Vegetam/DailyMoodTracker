package com.francescomalagrino.dailytrackerapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mood_table")
public class Mood {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "moodDate")
    private Integer moodDate;

    @NonNull
    @ColumnInfo(name = "moodStatus")
    int moodStatus;

    @ColumnInfo(name = "comment")
    String comment;

    public Mood() { };
    public Mood(@NonNull Integer moodStatus, String comment, Integer moodDate) {
        this.moodStatus = moodStatus;
        this.comment = comment;
        this.moodDate = moodDate;
    }

    public void setMoodDate(@NonNull Integer moodDate) {
        this.moodDate = moodDate;
    }

    public void setMoodStatus(int moodStatus) {
        this.moodStatus = moodStatus;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @NonNull
    public Integer getMoodDate() {
        return moodDate;
    }

    public int getMoodStatus() {
        return moodStatus;
    }

    public String getComment() {
        return comment;
    }

}