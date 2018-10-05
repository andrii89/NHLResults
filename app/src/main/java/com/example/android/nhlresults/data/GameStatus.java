package com.example.android.nhlresults.data;

public class GameStatus {

    String abstractGameState;
    String codedGameState;
    String detailedState;
    String statusCode;
    boolean startTimeTBD;

    public String getDetailedState() {
        return detailedState;
    }
}
