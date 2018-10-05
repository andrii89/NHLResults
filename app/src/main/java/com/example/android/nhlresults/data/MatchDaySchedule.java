package com.example.android.nhlresults.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class MatchDaySchedule {

    int totalItems;

    int totalEvents;
    int totalGames;
    int totalMatches;
    int wait;

    List<MatchDate> dates;
    List<ScheduleEvent> events;
    List<ScheduleMatch> matches;

    public MatchDaySchedule(){
        dates = new ArrayList<>();
        events = new ArrayList<>();
        matches = new ArrayList<>();
    }

    public static MatchDaySchedule parseJSON(String results) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(results, MatchDaySchedule.class);
    }

    public List<MatchDate> getDates() {
        return dates;
    }
}
