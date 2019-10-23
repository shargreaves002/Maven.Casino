package io.zipcoder.casino.utilities;

public class BlackJack extends CardGame implements GamblingGame {
    private Deck deck = new Deck();
    private boolean flag = true;
    private boolean flag2 = true;
    private Console console = new Console(System.in, System.out);
    
    public double wager(double bet) {
        return 0;
    }
    
    public void play(Player... players){
        while (flag){
            deck.fillDeck();
            for (Player j : players){
                j.addToHand(deck.deleteAnyCard());
                j.addToHand(deck.deleteAnyCard());
            }
            while (flag2) {
                for (Player j : players) {
                    if (j.isPlaying()) {

                        // prints the cards you're holding and the total points of all those cards
                        console.println(j.getName() + ", you are holding:");
                        for (Card i : j.getHand()){
                            console.println(i.toString());
                        }

                        // asks if you'd like another card
                        // if you don't want another card, you're out of the round
                        // I don't know if that's correct or not
                        if (console.getStringInput(j.getName() + ", Would you like another card?").toLowerCase().equals("yes")) {
                            j.addToHand(deck.deleteAnyCard());

                            // set your score according to the cards in your hand

                            //if that score is over 21, you busted
                            if (getScore(j) > 21) {
                                console.println("You busted! The total of the cards in your hand is " + getScore(j));
                                j.setPlaying(false);
                            }
                        } else {
                            console.println("You ended the round with " + getScore(j) + " points.");
                            j.setPlaying(false);
                        }
                    }
                }

                // quits this while loop if there are no more players playing
                flag2 = false;
                for (Player j : players){
                    flag2 |= j.isPlaying();
                }
            }

            // print out each player's score
            for (Player player : players) console.println(player.getName() + "'s score: " + getScore(player));


            // asks for a replay
            String result = console.getStringInput("Would you like to play again?", "Yes", "No");
            if (result.toLowerCase().equals("yes")) {
                //if we're playing again, reset some variables.
                for (Player j : players){
                    j.setPlaying(true);
                    j.getHand().clear();
                }
            } else {
                flag = false;
            }
        }
    }

    private int getScore(Player player){
        int numOfAces = 0;
        int total = 0;
        for (Card i : player.getHand()) {
            if (i.getValue() == 1){
                numOfAces++;
                continue;
            }
            total += Math.min(i.getValue(), 10);
        }

        // aces are 11, unless adding 11 would make you bust
        // in that case, ace is 1.
        for (int x=0; x < numOfAces; x++){
            if (player.getScore() + 11 <= 21) total += 11;
            else total++;
        }

        return total;
    }
}
