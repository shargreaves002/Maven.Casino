package io.zipcoder.casino;
import io.zipcoder.casino.utilities.*;

public class Casino {
    public static void main(String[] args) {
        Player sarah = new Player("Sarah");
        Player carlos = new Player("Carlos");
        Player malcolm = new Player("Malcolm");
        Craps craps = new Craps();
        craps.play(sarah, carlos, malcolm);
    }
}
