package com.test.rps.game;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.test.rps.game.model.Choice;
import com.test.rps.game.model.Outcome;
import com.test.rps.game.model.dto.PlayerStrategyDTO;
import com.test.rps.game.model.dto.RoundOutcomeDTO;
import com.test.rps.metrics.MetricsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GameService {
    private final Choice[] CHOICES = Choice.values();
    /**
     * Random number generator for the computer choice. This could be a
     * ThreadLocalRandom if multi-threaded performance is a problem. However, I want
     * to be able to set the seed.
     */
    private final Random random;

    private final MetricsService metricsService;

    public GameService(@Value("${computer.seed}") long seed, MetricsService metricsService) {
        this.metricsService = metricsService;
        log.info("Game Service initialised with seed {}", seed);
        random = new Random(seed);
    }

    public RoundOutcomeDTO processGame(PlayerStrategyDTO playerStrategy) {
        Choice playerChoice = playerStrategy.getPlayerChoice();
        Choice computerChoice = nextComputerChoice();
        Outcome outcome = determineWinner(playerChoice, computerChoice);
        metricsService.logPlay(playerChoice, computerChoice);
        return new RoundOutcomeDTO(playerChoice, computerChoice, outcome);
    }

    void resetSeed(long seed) {
        log.info("Game seed reset to {}", seed);
        random.setSeed(seed);
    }

    private Choice nextComputerChoice() {
        return CHOICES[random.nextInt(CHOICES.length)];
    }

    /**
     * Main logic of the game.
     * 
     * <p>
     * This could be implemented a few different ways:
     * <ol>
     * <li>Comparing the ordinal value of the Choice enum (need to handle the edge
     * case) - a poor solution but generic in the nth case</li>
     * <li>With a matrix table lookup - a good solution if we expect the choices to
     * grow/future changes in game logic.</li>
     * <li>By moving the logic to the Choice enum with a beats(Choice other)
     * method</li>
     * <li>The way it is implemented here is simple and efficient although difficult
     * if there were more options such as SPOCK and LIZARD</li>
     * </ol>
     * </p>
     * 
     * @param player   the player's choice
     * @param computer the computer's choice
     * @return who wins outcome
     */
    Outcome determineWinner(Choice player, Choice computer) {
        if (player == computer) {
            return Outcome.DRAW;
        }
        if (player == Choice.ROCK && computer == Choice.SCISSORS || player == Choice.PAPER && computer == Choice.ROCK
                || player == Choice.SCISSORS && computer == Choice.PAPER) {
            return Outcome.PLAYER_WIN;
        }
        return Outcome.COMPUTER_WIN;
    }
}
