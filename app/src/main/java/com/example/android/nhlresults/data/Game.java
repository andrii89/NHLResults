package com.example.android.nhlresults.data;

public class Game {

    private int gamePk;
    private String link;
    private String gameType;
    private String season;
    private String gameDate;
    private GameStatus status;
    private GameMatch teams;
    private Venue venue;
    private GameContent content;

    public int getGamePk() {
        return gamePk;
    }

    public GameStatus getStatus() {
        return status;
    }

    public GameMatch getTeams() {
        return teams;
    }

    public Venue getVenue() {
        return venue;
    }
}
