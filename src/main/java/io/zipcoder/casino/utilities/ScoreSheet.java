package io.zipcoder.casino.utilities;

import java.util.ArrayList;
import java.util.HashMap;

class ScoreSheet {
    private HashMap<String, Integer> scoreSheet = new HashMap<>();

    ScoreSheet(){
        scoreSheet.put("Ones", null);
        scoreSheet.put("Twos", null);
        scoreSheet.put("Threes", null);
        scoreSheet.put("Fours", null);
        scoreSheet.put("Fives", null);
        scoreSheet.put("Sixes", null);
        scoreSheet.put("3 of a kind", null);
        scoreSheet.put("4 of a kind", null);
        scoreSheet.put("Full house", null);
        scoreSheet.put("Low straight", null);
        scoreSheet.put("High straight", null);
        scoreSheet.put("Yahtzee", null);
        scoreSheet.put("Chance", null);
        scoreSheet.put("Yahtzee bonus", null);


    }
    ArrayList<Integer> upperSectionScore(){
        return (ArrayList<Integer>) scoreSheet.values();
    }

    void ones(int score){
        scoreSheet.replace("Ones", null, score);
    }
    void twos(int score){
        scoreSheet.replace("Twos", null, score);
    }
    void threes(int score){
        scoreSheet.replace("Threes", null, score);
    }
    void fours(int score){
        scoreSheet.replace("Fours", null, score);
    }
    void fives(int score){
        scoreSheet.replace("Fives", null, score);
    }
    void sixes(int score){
        scoreSheet.replace("Sixes", null, score);
    }
    void threeOfAKind(int score){
        scoreSheet.replace("3 of a kind", null, score);
    }
    void fourOfAKind(int score){
        scoreSheet.replace("4 of a kind", null, score);
    }
    void fullHouse(int score){
        scoreSheet.replace("Full house", null, score);
    }
    void lowStraight(int score){
        scoreSheet.replace("Low straight", null, score);
    }
    void highStraight(int score){
        scoreSheet.replace("High straight", null, score);
    }
    void yahtzee(int score){
        scoreSheet.replace("Yahtzee", null, score);
    }
    void chance(int score){
        scoreSheet.replace("Chance", null, score);
    }
    void yahtzeeBonus(int score){
        scoreSheet.replace("Yahtzee bonus", null, score);
    }

    Integer getOnes(){
        return scoreSheet.get("Ones");
    }
    Integer getTwos(){
        return scoreSheet.get("Twos");
    }
    Integer getThrees(){
        return scoreSheet.get("Threes");
    }
    Integer getFours(){
        return scoreSheet.get("Fours");
    }
    Integer getFives(){
        return scoreSheet.get("Fives");
    }
    Integer getSixes(){
        return scoreSheet.get("Sixes");
    }
    Integer getThreeOfAKind(){
        return scoreSheet.get("3 of a kind");
    }
    Integer getFourOfAKind(){
        return scoreSheet.get("4 of a kind");
    }
    Integer getFullHouse(){
        return scoreSheet.get("Full house");
    }
    Integer getLowStraight(){
        return scoreSheet.get("Low straight");
    }
    Integer getHighStraight(){
        return scoreSheet.get("High straight");
    }
    Integer getYahtzee(){
        return scoreSheet.get("Yahtzee");
    }
    Integer getChance(){
        return scoreSheet.get("Chance");
    }
    Integer getYahtzeeBonus(){
        return scoreSheet.get("Yahtzee bonus");
    }

    HashMap<String, Integer> getScoreSheet(){
        return scoreSheet;
    }
}
