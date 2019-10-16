package io.zipcoder.casino.utilities;

public class Card {
    private int cardNumber;
    private String cardSuite;


    public Card(int cardNumber, int cardSuite) {
        this.cardNumber = cardNumber;
        switch (cardSuite){
            case 0 :
                this.cardSuite= "Spades";
                break;
            case 1:
                this.cardSuite= "Clubs";
                break;
            case 2:
                this.cardSuite= "Diamonds";
                break;
            case 3:
                this.cardSuite= "Hearts";
                break;
        }
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public String getCardSuite() {
        return cardSuite;
    }

}

