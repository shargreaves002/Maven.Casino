package io.zipcoder.casino.utilities;

import java.util.ArrayList;
import java.util.Collection;

public class Yahtzee extends DiceGame {
    private Die d1 = new Die();
    private Die d2 = new Die();
    private Die d3 = new Die();
    private Die d4 = new Die();
    private Die d5 = new Die();

    private ArrayList<Die> savedDice = new ArrayList<>();
    private boolean flag = true;

    private static Console console = new Console(System.in, System.out);

    public void play(Player... players) {
        while (flag) {
            for (Player w : players) {
                if (w.isPlaying()) {
                    savedDice.clear();
                    for (int x = 0; x < 3; x++) {
                        if (!savedDice.contains(d1)) d1.value = (int) (Math.random() * 6 + 1);
                        if (!savedDice.contains(d2)) d2.value = (int) (Math.random() * 6 + 1);
                        if (!savedDice.contains(d3)) d3.value = (int) (Math.random() * 6 + 1);
                        if (!savedDice.contains(d4)) d4.value = (int) (Math.random() * 6 + 1);
                        if (!savedDice.contains(d5)) d5.value = (int) (Math.random() * 6 + 1);
                        console.println(w.getName() + ", your dice rolls are: " + d1.value + ", " + d2.value + ", " + d3.value + ", " + d4.value + ", " + d5.value);
                        if (x < 2) {
                            String response = console.getStringInput("Which dice would you like to save?");
                            char[] diceToSave = response.toCharArray();
                            savedDice.clear();
                            savedDice.trimToSize();

                            for (char die : diceToSave) {
                                switch (die) {
                                    case '1':
                                        if (!savedDice.contains(d1))
                                            savedDice.add(d1);
                                        break;
                                    case '2':
                                        if (!savedDice.contains(d2))
                                            savedDice.add(d2);
                                        break;
                                    case '3':
                                        if (!savedDice.contains(d3))
                                            savedDice.add(d3);
                                        break;
                                    case '4':
                                        if (!savedDice.contains(d4))
                                            savedDice.add(d4);
                                        break;
                                    case '5':
                                        if (!savedDice.contains(d5))
                                            savedDice.add(d5);
                                        break;
                                }
                            }
                        } else {
                            if (!savedDice.contains(d1)) savedDice.add(d1);
                            if (!savedDice.contains(d2)) savedDice.add(d2);
                            if (!savedDice.contains(d3)) savedDice.add(d3);
                            if (!savedDice.contains(d4)) savedDice.add(d4);
                            if (!savedDice.contains(d5)) savedDice.add(d5);
                        }
                    }

                    ArrayList<String> eligibleChoices = new ArrayList<>();
                    if (w.getScoreSheet().getOnes() == null) eligibleChoices.add("ones");
                    if (w.getScoreSheet().getTwos() == null) eligibleChoices.add("twos");
                    if (w.getScoreSheet().getThrees() == null) eligibleChoices.add("threes");
                    if (w.getScoreSheet().getFours() == null) eligibleChoices.add("fours");
                    if (w.getScoreSheet().getFives() == null) eligibleChoices.add("fives");
                    if (w.getScoreSheet().getSixes() == null) eligibleChoices.add("sixes");
                    if (w.getScoreSheet().getThreeOfAKind() == null) eligibleChoices.add("3 of a kind");
                    if (w.getScoreSheet().getFourOfAKind() == null) eligibleChoices.add("4 of a kind");
                    if (w.getScoreSheet().getFullHouse() == null) eligibleChoices.add("full house");
                    if (w.getScoreSheet().getLowStraight() == null) eligibleChoices.add("low straight");
                    if (w.getScoreSheet().getHighStraight() == null) eligibleChoices.add("high straight");
                    if (w.getScoreSheet().getYahtzee() == null) eligibleChoices.add("yahtzee");
                    if (w.getScoreSheet().getChance() == null) eligibleChoices.add("chance");
                    if (w.getScoreSheet().getYahtzeeBonus() == null && w.getScoreSheet().getYahtzee() != null && w.getScoreSheet().getYahtzee() != 0)
                        eligibleChoices.add("yahtzee bonus");

                    eligibleChoices.add("quit");
                    console.println("Your eligible choices:");
                    for (String i : eligibleChoices) console.println(i);
                    String answer = getAnswer(eligibleChoices);
                    int j = 0;
                    int[] counts = new int[6];
                    for (Die die : savedDice) {
                        counts[die.value - 1]++;
                    }
                    boolean check2 = false;
                    boolean check3 = false;
                    boolean check4 = false;
                    boolean check5 = false;
                    for (int i : counts) {
                        check2 |= (i == 2);
                        check3 |= (i == 3);
                        check4 |= (i == 4);
                        check5 |= (i == 5);
                    }
                    switch (answer) {
                        case "ones":
                            j = counts[0];
                            w.getScoreSheet().ones(j);
                            console.println("Added " + j + " points to ones.");
                            break;
                        case "twos":
                            j = counts[1] * 2;
                            w.getScoreSheet().twos(j);
                            console.println("Added " + j + " points to twos.");
                            break;
                        case "threes":
                            j = counts[2] * 3;
                            w.getScoreSheet().threes(j);
                            console.println("Added " + j + " points to threes.");
                            break;
                        case "fours":
                            j = counts[3] * 4;
                            w.getScoreSheet().fours(j);
                            console.println("Added " + j + " points to fours.");
                            break;
                        case "fives":
                            j = counts[4] * 5;
                            w.getScoreSheet().fives(j);
                            console.println("Added " + j + " points to fives.");
                            break;
                        case "sixes":
                            j = counts[5] * 6;
                            w.getScoreSheet().sixes(j);
                            console.println("Added " + j + " points to sixes.");
                            break;
                        case "3 of a kind":
                            if (check3) {
                                for (Die i : savedDice) {
                                    j += i.value;
                                }
                                w.getScoreSheet().threeOfAKind(j);
                                console.println("Added " + j + " points to 3 of a kind.");
                            } else {
                                w.getScoreSheet().threeOfAKind(0);
                                console.println("Added 0 points to 3 of a kind.");
                            }
                            break;
                        case "4 of a kind":
                            if (check4) {
                                for (Die i : savedDice) {
                                    j += i.value;
                                }
                                w.getScoreSheet().fourOfAKind(j);
                                console.println("Added " + j + " points to 4 of a kind.");
                            } else {
                                w.getScoreSheet().fourOfAKind(0);
                                console.println("Added 0 points to 4 of a kind.");
                            }
                            break;
                        case "chance":
                            for (Die i : savedDice) {
                                j += i.value;
                            }
                            w.getScoreSheet().chance(j);
                            console.println("Added " + j + " points to chance.");
                            break;
                        case "full house":
                            if ((check2 && check3) || check5) {
                                w.getScoreSheet().fullHouse(25);
                                console.println("Added 25 points to full house.");
                            } else {
                                w.getScoreSheet().fullHouse(0);
                                console.println("Added 0 points to full house.");
                            }
                            break;
                        case "low straight":
                            if ((counts[0] >= 1 & counts[1] >= 1 & counts[2] >= 1 & counts[3] >= 1) ||
                                    (counts[1] >= 1 & counts[2] >= 1 & counts[3] >= 1 & counts[4] >= 1) ||
                                    (counts[2] >= 1 & counts[3] >= 1 & counts[4] >= 1 & counts[5] >= 1)) {
                                w.getScoreSheet().lowStraight(30);
                                console.println("Added 30 points to low straight.");
                            } else {
                                w.getScoreSheet().lowStraight(0);
                                console.println("Added 0 points to low straight.");
                            }
                            break;
                        case "high straight":
                            if ((counts[0] >= 1 & counts[1] >= 1 & counts[2] >= 1 & counts[3] >= 1 & counts[4] >= 1) ||
                                    (counts[1] >= 1 & counts[2] >= 1 & counts[3] >= 1 & counts[4] >= 1 & counts[5] >= 1)) {
                                w.getScoreSheet().highStraight(40);
                                console.println("Added 40 points to high straight. ");
                            } else {
                                w.getScoreSheet().highStraight(0);
                                console.println("Added 0 points to high straight.");
                            }
                            break;
                        case "yahtzee":
                            if (check5) {
                                w.getScoreSheet().yahtzee(50);
                                console.println("Added 50 points to yahtzee.");
                            } else {
                                w.getScoreSheet().yahtzee(0);
                                console.println("Added 0 points to yahtzee.");
                            }
                            break;
                        case "yahtzee bonus":
                            if (check5) {
                                w.getScoreSheet().yahtzeeBonus(100);
                                console.println("Added 100 points to yahtzee bonus.");
                            } else {
                                w.getScoreSheet().yahtzeeBonus(0);
                                console.println("Added 0 points to yahtzee bonus.");
                            }
                            break;
                        case "quit" :
                            w.setPlaying(false);
                            break;
                    }
                    int numOfScores = 0;
                    //set isPlaying to false if no more score options
                    for (Integer i : w.getScoreSheet().getScoreSheet().values()){
                        if (i != null) numOfScores++;
                    }
                    if (numOfScores == 13) w.setPlaying(false);
                }
                //break if no more players are playing
                boolean playing = false;
                for (Player i : players){
                    playing |= i.isPlaying();
                }
                if (!playing) break;

            }
            //exit while loop if no more players are playing
            flag = false;
            for (Player i : players){
                flag |= i.isPlaying();
            }
        }

        for (Player player : players){
            Collection<Integer> scores = player.getScoreSheet().getScoreSheet().values();
            int totalScore = 0;
            for (Integer i : scores){
                if(i != null) totalScore += i;
            }

            ArrayList<Integer> upperScores = player.getScoreSheet().upperSectionScore();
            int upperTotal = 0;
            for (Integer score : upperScores){
                if(score != null) upperTotal += score;
            }
            if (upperTotal >= 65) {
                totalScore += 35;
            }

            console.println(player.getName() + "\'s score: " + totalScore);
        }
    }

    private String getAnswer(ArrayList<String> eligibleChoices){
        String answer = console.getStringInput("Which category would you like to add to?").toLowerCase();
        if (!eligibleChoices.contains(answer)){
            console.println("Invalid input, try again.");
            return getAnswer(eligibleChoices);
        }

        return answer;
    }
}
