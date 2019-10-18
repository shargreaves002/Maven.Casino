package io.zipcoder.casino.utilities;

import java.util.Collections;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Deck {
private LinkedHashSet<Card> deck;

    public Deck() {
        deck= new LinkedHashSet<Card>();
        for(int x = 1; x < 5; x++){
            for (int y = 1; y < 14; y++){
                deck.add(new Card(y, x));
            }
        }
        shuffle();
    }

    void shuffle(){
        ArrayList<Card> tempDeck = new ArrayList<Card>(deck);
        Collections.shuffle(tempDeck);
        deck.clear();
        deck.addAll(tempDeck);
    }

    Card getCard(){
        Card card = (Card) deck.toArray()[0];
        deck.remove(card);
        return card;
    }

    void reset (){
        deck.clear();
        for(int x = 1; x < 5; x++){
            for (int y = 1; y < 14; y++){
                deck.add(new Card(y, x));
            }
        }
        shuffle();
    }

    public void fillDeck() {
    }

}
