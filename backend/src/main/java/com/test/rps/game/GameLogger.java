package com.test.rps.game;

import com.test.rps.game.model.Choice;

/**
 * Interface for a game logger. MetricsService is the only implementation in this case.
 */
public interface GameLogger {
    void logPlay(Choice playerChoice, Choice computerChoice);
}
