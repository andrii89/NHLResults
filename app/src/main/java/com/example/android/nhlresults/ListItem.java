package com.example.android.nhlresults;

import java.util.UUID;

public class ListItem {

    private UUID mId;
    private String mDate;
    private String mHometeam;
    private String mGuestteam;
    private String mResult;

    public ListItem () {
        mId = UUID.randomUUID();
    }

    public void setGameDayDate(String date) {
        mDate = date;
    }

    public void setHometeam(String hometeam) {
        mHometeam = hometeam;
    }

    public void setGuestteam(String guestteam) {
        mGuestteam = guestteam;
    }

    public void setMatchResult(String result) {
        mResult = result;
    }

    public String getGameDayDate() {
        return mDate;
    }

    public String getHometeam() {
        return mHometeam;
    }

    public String getGuestteam() {
        return mGuestteam;
    }

    public String getMatchResult() {
        return mResult;
    }
}
