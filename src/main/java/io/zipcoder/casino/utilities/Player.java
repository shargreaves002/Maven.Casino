package io.zipcoder.casino.utilities;

import java.util.ArrayList;

public class Player implements GamblingPlayer {
    private ArrayList<Card> hand = new ArrayList<>();
    private String name;
    private boolean isPlaying = true;
    private int score = 0;
    private ScoreSheet scoreSheet;

    public Player(String name){
        this.name = name;
        scoreSheet = new ScoreSheet();
    }

    ArrayList<Card> getHand(){
        return hand;
    }

    void addToHand(Card card){
        hand.add(card);
    }

    String getName(){
        return name;
    }

    void setPlaying(boolean isPlaying){
        this.isPlaying = isPlaying;
    }

    boolean isPlaying(){
        return isPlaying;
    }

    int getScore() {
        return score;
    }

    void setScore(int score) {
        this.score = score;
    }

    void addToScore(int score){
        this.score += score;
    }

    void subtractFromScore(int score){
        this.score -= score;
    }
    ScoreSheet getScoreSheet(){
        return scoreSheet;
    }
}