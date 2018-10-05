package com.example.android.nhlresults.data;

import java.util.ArrayList;
import java.util.List;

public class MatchDate {

    String date;
    int totalItems;
    int totalEvents;
    int totalGames;
    int totalMatches;

    List<Game> games;

    public MatchDate(){
        games = new ArrayList<>();
    }

    public List<Game> getGames() {
        return games;
    }
}
