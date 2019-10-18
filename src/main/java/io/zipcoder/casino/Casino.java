package io.zipcoder.casino;
import io.zipcoder.casino.utilities.*;

public class Casino {
    private static Craps craps = new Craps();
    private static GoFishGame goFish = new GoFishGame();
    private static BlackJack blackJack = new BlackJack();
    private static Yahtzee yahtzee = new Yahtzee();
    public static void main(String[] args) {
        Player sarah = new Player("Sarah");
        Player shishonah = new Player("Shishonah");
        Player ryan = new Player("Ryan");

        blackJack.play(sarah, ryan, shishonah);
    }
}
