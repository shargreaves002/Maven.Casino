package io.zipcoder.casino.utilities;

import java.util.Random;

public class Craps extends DiceGame implements GamblingGame {
    private Console console = new Console(System.in, System.out);

    public void play(Player... players){
        //make sure there's at least one player
        if (players.length >= 1) {
            //make it so all players are playing, initialize some required local variables
            for (Player player : players) player.setPlaying(true);
            Player shooter = players[0];
            int tracker = 0;
            boolean flag2 = true;

            //loops while there's at least one player still playing
            while (flag2 && shooter.isPlaying()) {
                boolean shootAgain = false;

                //allows a player to leave if they want
                String result = console.getStringInput("Would "+ shooter.getName() + " like to continue playing?", "Yes", "No");
                if (result.toLowerCase().equals("no")) shooter.setPlaying(false);

                //and if they don't leave:
                if (shooter.isPlaying()) {
                    console.println("The current shooter is " + shooter.getName());
                    int comeOut = rollDice(2);

                    // handle the comeout roll
                    // 7 or 11 wins pass line bets
                    // 2, 3, or 12 loses pass line bets
                    //anything else sets the Point and causes the shooter to keep rolling
                    if (comeOut == 7 || comeOut == 11) {
                        console.println("Your come out roll was " + comeOut + ", you win!");
                        shooter.addToScore(1);
                        shootAgain = true;
                    } else if (comeOut == 2 || comeOut == 3 || comeOut == 12) {
                        console.println("Your come out roll was " + comeOut + ", you lost!");
                    } else {
                        console.println("The point is " + comeOut + ".");
                        int roll;
                        //if their roll was the comeout roll, pass line bets win
                        //if their roll was 7, pass line bets lost
                        //else, reroll
                        do {
                            roll = rollDice(2);
                            if (roll == comeOut) {
                                console.println("You rolled " + roll + ", you win!");
                                shooter.addToScore(1);
                                shootAgain = true;
                            } else if (roll == 7) {
                                console.println("You rolled 7, you lost!");
                            } else {
                                console.println("You rolled " + roll + ", roll again!");
                            }
                        } while (roll != comeOut && roll != 7);
                    }
                }

                //handles the possibility of shooting again
                //goes to the next playing player if you don't shoot again
                if (!shootAgain){
                    tracker = getNextShooter(tracker, players);
                    shooter = players[tracker];
                } else {
                    console.println("You get to shoot again!");
                }

                //checks to see if there are any players who are playing
                //If no players are playing, end the while loop and display the final score
                for (Player player : players) {
                    if (player.isPlaying()) {
                        flag2 = true;
                        break;
                    } else {
                        flag2 = false;
                    }
                }
            }
        }

        for (Player player : players){
            console.println(player.getName() + "'s score: " + player.getScore());
        }
    }

    //hah, we don't go there
    public double wager(double bet) {
        return 0;
    }
    //returns the total result of x dice rolled
    private int rollDice(int dice){
        int answer = 0;
        Random r = new Random();
        for (int x = 0; x < dice; x++){
            answer =+ r.nextInt(6) + 1;
        }
        return answer;
    }

    //finds the index position of the next player in line who isPlaying.
    //Uses recursive calls to move to the next index position in the event
    //the player it found isn't playing.
    private int getNextShooter(int currentShooter, Player[] players){
        //first, make sure there is a player playing before we start recursive calls
        //if not, just return 0
        boolean anyPlayersPlaying = true;
        for (Player player : players){
            if (player.isPlaying()) {
                anyPlayersPlaying = true;
                break;
            } else {
                anyPlayersPlaying = false;
            }
        }
        if (!anyPlayersPlaying || players.length <= 1) return 0;

        //now we do the real work
        //if the next player exists
        if (currentShooter < players.length - 1){
            //and is playing
            if (players[currentShooter + 1].isPlaying()){
                //they shoot
                return currentShooter + 1;
            } else {
                //if they aren't playing, try the next player
                return getNextShooter(currentShooter + 1, players);
            }
        } else {
            //if the next player doesn't exist, go back to the beginning of the array and start again
            return getNextShooter(-1, players);
        }
    }
}
