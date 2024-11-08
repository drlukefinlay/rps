package com.test.rps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.rps.game.GameService;
import com.test.rps.game.model.Choice;
import com.test.rps.game.model.dto.PlayerStrategyDTO;
import com.test.rps.metrics.MetricsService;
import com.test.rps.metrics.model.dto.MetricCountDTO;
import com.test.rps.metrics.model.dto.MetricsDTO;

@SpringBootTest
class RpsApplicationTests {

    @Autowired
    private GameService gameService;

    @Autowired
    private MetricsService metricsService;

    @Test
    void testPlayAndTotalCount() {
        long startCount = metricsService.getMetrics().getPlayCount();
        gameService.processGame(new PlayerStrategyDTO(Choice.SCISSORS));
        assertEquals(startCount + 1, metricsService.getMetrics().getPlayCount());
    }

    @Test
    void testPlayAndCountMetrics() {
        int plays = 10000;
        for (int i = 0; i < plays; i++) {
            gameService.processGame(new PlayerStrategyDTO(Choice.ROCK));
        }

        MetricsDTO metrics = metricsService.getMetrics();
        assertTrue(metrics.getPlayCount() >= plays, plays + " plays not recorded");

        List<MetricCountDTO> playCounts = metrics.getCounts();
        long computerRock = 0;
        long computerPaper = 0;
        long computerScissors = 0;
        for (MetricCountDTO count : playCounts) {
            if (count.getPlayer() == Choice.ROCK) {
                switch (count.getComputer()) {
                case ROCK -> computerRock = count.getCount();
                case PAPER -> computerPaper = count.getCount();
                case SCISSORS -> computerScissors = count.getCount();
                }
            }
        }

        assertTrue(computerPaper + computerRock + computerScissors == plays,
                "Exactly " + plays + " ROCK plays not recorded");
    }
}
