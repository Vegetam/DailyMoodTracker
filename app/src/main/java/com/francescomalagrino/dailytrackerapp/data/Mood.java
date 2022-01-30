package com.francescomalagrino.dailytrackerapp.data;


public class Mood {

    int moodDate;
    int moodStatus;
    String comment;

    public int getMoodDate() {
        return moodDate;
    }

    public void setMoodDate(int moodDate) {
        this.moodDate = moodDate;
    }

    public int getMoodStatus() {
        return moodStatus;
    }

    public void setMoodStatus(int moodStatus) {
        this.moodStatus = moodStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
