package io.zipcoder.casino.utilities;

interface Game {
    int score = 0;

    void play(Player... players);
}