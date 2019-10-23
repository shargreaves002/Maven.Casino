package io.zipcoder.casino.utilities;
import java.util.Random;

public class Deck {
    private int _count;
    private Card[] _hand;
    private static final int Storage = 52;
    private static Random random = new Random();

    Deck() {
        _hand = new Card[Storage];
        _count = 0;
    }

    void fillDeck() {
        Card card;
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 13; j++) {
                card = new Card(j,i);
                this.insertCard(card);
            }
        }
    }

    void insertCard(Card card) {
        if(_count == _hand.length) {
            Card[] _temp = new Card[_count + Storage];
            System.arraycopy(_hand, 0, _temp, 0, _hand.length);
            _hand = _temp;
        }
        _hand[_count] = card;
        _count++;
    }

    Card deleteValue(int value) {
        Card temp;
        Card deleted;
        for(int i = 0; i<_count; i++) {
            temp = _hand[i];
            if(temp.getValue() == value) {
                deleted = temp;
                _hand[i] = _hand[_count-1];
                _count--;
                return deleted;
            }
        }
        return null;
    }
    Card deleteAnyCard() {
        if(_count == 0) {
            return null;
        } else {
            int randoIx = random.nextInt(_count);
            Card temp = _hand[randoIx];
            if(randoIx != _count-1) {
                _hand[randoIx] = _hand[_count-1];
                _count--;
            } else {
                _hand[_count-1] = null;
                _count--;
            }
            return temp;
        }
    }
    int checkBookBegginingDeal() {
        for(int i = 0; i<4 ; i++) {
            if(getCount(_hand[i].getValue()) == 4) {
                return _hand[i].getValue();
            }
        }
        return 0;
    }
    int getCount(int value) {
        int occurences = 0;
        for(int i = 0; i<_count;i++) {
            if(_hand[i].getValue() == value) {
                occurences++;
            }
        }
        return occurences;
    }
    int getSize()
    {
        return _count;
    }
    public String toString()
    {
        if(_count != 0)
        {
            StringBuilder result = new StringBuilder("User Hand: \n");
            for(int i = 0; i<_count; i++)
            {
                result.append(_hand[i]).append("\n");
            }
            return result.toString();
        }
        return "Empty Hand";
    }
}
