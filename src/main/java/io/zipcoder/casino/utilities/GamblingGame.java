package io.zipcoder.casino.utilities;

interface GamblingGame {
    //stores the player's current amount of money
    double balance = 0;

    //returns how much money they won off of a bet
    double wager(double bet);
}
