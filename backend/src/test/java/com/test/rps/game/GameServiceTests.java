package com.test.rps.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.rps.game.model.Choice;
import com.test.rps.game.model.Outcome;
import com.test.rps.game.model.dto.PlayerStrategyDTO;
import com.test.rps.metrics.MetricsService;

@ExtendWith(MockitoExtension.class)
class GameServiceTests {
    private static GameService gameService;

    @BeforeAll
    static void init(@Mock MetricsService metricsService) {
        gameService = new GameService(245l, metricsService);
    }

    @Test
    void testProcessGame() {
        // We currently don't need to reset the seed since this is the only test that
        // will poll the seed but just in case.
        gameService.resetSeed(245);
        var game = gameService.processGame(new PlayerStrategyDTO(Choice.ROCK));
        assertEquals(Outcome.DRAW, game.getOutcome());
        game = gameService.processGame(new PlayerStrategyDTO(Choice.ROCK));
        assertEquals(Outcome.COMPUTER_WIN, game.getOutcome());
        game = gameService.processGame(new PlayerStrategyDTO(Choice.ROCK));
        assertEquals(Outcome.PLAYER_WIN, game.getOutcome());
    }

    @Test
    void testDetermineWinnerDraws() {
        for (Choice choice : Choice.values()) {
            Outcome outcome = gameService.determineWinner(choice, choice);
            assertEquals(Outcome.DRAW, outcome);
        }
    }

    @Test
    void testDetermineWinnerPlayerWins() {
        assertEquals(Outcome.PLAYER_WIN, gameService.determineWinner(Choice.ROCK, Choice.SCISSORS));
        assertEquals(Outcome.PLAYER_WIN, gameService.determineWinner(Choice.PAPER, Choice.ROCK));
        assertEquals(Outcome.PLAYER_WIN, gameService.determineWinner(Choice.SCISSORS, Choice.PAPER));
    }

    @Test
    void testDetermineWinnerComputerWins() {
        assertEquals(Outcome.COMPUTER_WIN, gameService.determineWinner(Choice.ROCK, Choice.PAPER));
        assertEquals(Outcome.COMPUTER_WIN, gameService.determineWinner(Choice.PAPER, Choice.SCISSORS));
        assertEquals(Outcome.COMPUTER_WIN, gameService.determineWinner(Choice.SCISSORS, Choice.ROCK));
    }

}
