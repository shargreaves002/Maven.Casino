package io.zipcoder.casino.utilities;

import java.util.ArrayList;

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
                                        savedDice.add(d1);
                                        break;
                                    case '2':
                                        savedDice.add(d2);
                                        break;
                                    case '3':
                                        savedDice.add(d3);
                                        break;
                                    case '4':
                                        savedDice.add(d4);
                                        break;
                                    case '5':
                                        savedDice.add(d5);
                                        break;
                                }
                            }

                            // add an easy way to add all the dice, instead of typing "12345"
                            if (response.toLowerCase().equals("all")){
                                savedDice.add(d1);
                                savedDice.add(d2);
                                savedDice.add(d3);
                                savedDice.add(d4);
                                savedDice.add(d5);
                            }
                            // if they saved all their dice (via either method), skip to scoring
                            if (savedDice.size() == 5) break;
                        } else {
                            //on the third roll, save all their dice and go to scoring
                            if (!savedDice.contains(d1)) savedDice.add(d1);
                            if (!savedDice.contains(d2)) savedDice.add(d2);
                            if (!savedDice.contains(d3)) savedDice.add(d3);
                            if (!savedDice.contains(d4)) savedDice.add(d4);
                            if (!savedDice.contains(d5)) savedDice.add(d5);
                        }
                    }

                    // make an array list of all the choices they can make
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

                    // print it
                    console.println("Your eligible choices:");
                    for (String i : eligibleChoices) console.println(i);

                    // get which score they want to add
                    String answer = getAnswer(eligibleChoices);

                    // this next bit counts how many dice of each value we have
                    // used for determining scoring later
                    int j = 0;
                    int[] counts = new int[6];
                    for (Die die : savedDice) {
                        counts[die.value - 1]++;
                    }

                    //check to see if we have 2 dice of the same value
                    boolean check2 = false;
                    // 3 of the same value
                    boolean check3 = false;
                    // 4 of the same value
                    boolean check4 = false;
                    // 5 of the same value
                    boolean check5 = false;
                    for (int i : counts) {
                        check2 |= (i == 2);
                        check3 |= (i == 3);
                        check4 |= (i == 4);
                        check5 |= (i == 5);
                    }
                    switch (answer) {
                        // ones - sixes adds x times the number of those dice they have
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
                        // add all dice together if you have a 3 of a kind, else put 0.
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
                        // similar to 3 of a kind, but for 4.
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
                        // just add all the dice together
                        case "chance":
                            for (Die i : savedDice) {
                                j += i.value;
                            }
                            w.getScoreSheet().chance(j);
                            console.println("Added " + j + " points to chance.");
                            break;
                        // if you have a full house, 25. Else, 0.
                        case "full house":
                            if ((check2 && check3) || check5) {
                                w.getScoreSheet().fullHouse(25);
                                console.println("Added 25 points to full house.");
                            } else {
                                w.getScoreSheet().fullHouse(0);
                                console.println("Added 0 points to full house.");
                            }
                            break;
                        // if you have at least one of each of four values in a row, you have a low straight
                        // and get 30 points. Otherwise, you get 0 points.
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
                        // similar to low straight, but five values and 40 points.
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
                        // five of a kind gets 50 points, else 0.
                        case "yahtzee":
                            if (check5) {
                                w.getScoreSheet().yahtzee(50);
                                console.println("Added 50 points to yahtzee.");
                            } else {
                                w.getScoreSheet().yahtzee(0);
                                console.println("Added 0 points to yahtzee.");

                                //also set yahtzee bonus to 0 so it's not counted as null
                                w.getScoreSheet().yahtzeeBonus(0);
                            }
                            break;
                        // another yatzhee nets you 100 points
                        // only available if you got the first yahtzee (not zero, not null)
                        case "yahtzee bonus":
                            if (check5) {
                                w.getScoreSheet().yahtzeeBonus(100);
                                console.println("Added 100 points to yahtzee bonus.");
                            } else {
                                w.getScoreSheet().yahtzeeBonus(0);
                                console.println("Added 0 points to yahtzee bonus.");
                            }
                            break;
                        // allows you to quit
                        case "quit" :
                            w.setPlaying(false);
                            break;
                    }

                    boolean flag = false;
                    //set isPlaying to false if no more score options
                    for (Integer i : w.getScoreSheet().getScoreSheet().values()){
                        flag |= (i == null);
                    }
                    if (w.isPlaying()) w.setPlaying(flag);
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

        //prints out each players score
        for (Player player : players){
            printScoreSheet(player);
            console.println("\n");
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

    private void printScoreSheet (Player player) {
        console.println(player.getName() + "\'s score sheet:\n");
        player.getScoreSheet().getScoreSheet().forEach((k, v) -> System.out.println(k + ": " + v));
        int totalScore = 0;
        for (Integer i : player.getScoreSheet().getScoreSheet().values()){
            if(i != null) totalScore += i;
        }

        int upperTotal = 0;
        for (Integer score : player.getScoreSheet().upperSectionScore()){
            if(score != null) upperTotal += score;
        }

        //you get a 35 point bonus if the upper section was greater than 65.
        int upperBonus = 0;
        if (upperTotal >= 65) {
            upperBonus = 35;
        }
        totalScore += upperBonus;

        console.println("Upper section bonus: " + upperBonus);
        console.println("Total score: " + totalScore);
    }
}
