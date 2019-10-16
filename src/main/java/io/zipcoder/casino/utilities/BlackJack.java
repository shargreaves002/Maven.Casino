package io.zipcoder.casino.utilities;

public class BlackJack extends CardGame implements GamblingGame {
    private Deck deck = new Deck() ;
    private boolean flag = true;
    private boolean flag2 = true;
    private Console console = new Console(System.in, System.out);
    
    public double wager(double bet) {
        return 0;
    }
    
   void play(Player... players){
        while (flag){
            while (flag2) {
                for (Player j : players) {
                    if (j.isPlaying()) {
                        int total = 0;
                        for (Card i : j.getHand()) {
                            total += Math.min(i.getCardNumber(), 10);
                        }
                        j.setScore(total);
                        if (j.getScore() < 22) {
                            console.println("The j.getScore() of the cards in your hand is " + j.getScore());
                            if (console.getStringInput(j.getName() + ", Would you like another card?").toLowerCase().equals("yes")) {
                                j.addToHand(deck.getCard());
                            } else {
                                console.println("You ended the round with " + j.getScore() + " points.");
                                j.setPlaying(false);
                            }
                        } else {
                            console.println("You lose! Your hand j.getScore() is " + j.getScore());
                            j.setPlaying(false);
                        }
                    }
                }

                for (Player player : players) {
                    if (player.isPlaying()) {
                        flag2 = true;
                        break;
                    } else {
                        flag2 = false;
                    }
                }
            }
            
            for (Player player : players){
                console.println(player.getName() + "'s score: " + player.getScore());
            }
            
            String result = console.getStringInput("Would you like to play again?", "Yes", "No");
            if (!result.toLowerCase().equals("yes")) flag = false;
        }
   }
}
