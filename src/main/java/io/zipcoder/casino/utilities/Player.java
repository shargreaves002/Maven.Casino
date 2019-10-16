package io.zipcoder.casino.utilities;

import java.util.ArrayList;

public class Player implements GamblingPlayer {
    private ArrayList<Card> hand = new ArrayList<Card>();
    private String name;
    private boolean isPlaying = true;
    private int score = 0;

    public Player(String name){
        this.name = name;
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

    public void setScore(int score) {
        this.score = score;
    }
}