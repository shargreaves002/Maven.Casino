package io.zipcoder.casino.utilities;

public interface Game {
    int score = 0;

    void play(Player... players);
}