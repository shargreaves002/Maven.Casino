package io.zipcoder.casino.utilities;

public class Yahtzee extends DiceGame {
    private int rollDice;
    private int reRollDice;
    private int[] diceRolled;
    public static int d1, d2, d3, d4, d5;


    private static Console console = new Console(System.in, System.out);

    public Yahtzee(int reRollDice) {

        this.reRollDice = reRollDice;

    }

    public void play(Player... players) {
        while (true) {
            for (Player w : players) {

                int d1 = (int) (Math.random() * 6 + 1);
                int d2 = (int) (Math.random() * 6 + 1);
                int d3 = (int) (Math.random() * 6 + 1);
                int d4 = (int) (Math.random() * 6 + 1);
                int d5 = (int) (Math.random() * 6 + 1);
                int sum = d1 + d2 + d3 + d4 + d5;

                if (sum == 20 || sum == 10 || sum == 3) {
                    System.out.println("you score is " + sum + "you lose");
                    break;
                } else if (sum == 30 || sum == 50 || sum == 60) {
                    System.out.println("your score is " + sum + "you win!");
                    break;
                }
                    console.println(w.getName(), player.getScore());

            }
        }
    }
}

