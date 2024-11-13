package com.test.rps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import com.test.rps.game.GameService;
import com.test.rps.game.model.Choice;
import com.test.rps.game.model.dto.PlayerStrategyDTO;
import com.test.rps.metrics.MetricsService;
import com.test.rps.metrics.model.dto.MetricCountDTO;
import com.test.rps.metrics.model.dto.MetricsDTO;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ActiveProfiles(profiles = "test")
class RpsApplicationTests {

    @Autowired
    private GameService gameService;

    @Autowired
    private MetricsService metricsService;

    @Test
    void testPlayAndTotalCount() {
        long startCount = metricsService.getMetrics().getTotalCount();
        gameService.processGame(new PlayerStrategyDTO(Choice.SCISSORS));
        assertEquals(startCount + 1, metricsService.getMetrics().getTotalCount());
    }

    @Test
    void testPlayAndCountMetrics() {
        int plays = 10000;
        for (int i = 0; i < plays; i++) {
            gameService.processGame(new PlayerStrategyDTO(Choice.ROCK));
        }

        MetricsDTO metrics = metricsService.getMetrics();
        assertTrue(metrics.getTotalCount() >= plays, plays + " plays not recorded");

        Map<Choice, MetricCountDTO> playCounts = metrics.getPlayerCounts();
        
        Map<Choice, Long> rockPlays = playCounts.get(Choice.ROCK).getComputerCounts();
        long computerRock = rockPlays.get(Choice.ROCK);
        long computerPaper = rockPlays.get(Choice.PAPER);
        long computerScissors = rockPlays.get(Choice.SCISSORS);
        
        assertTrue(computerPaper + computerRock + computerScissors == plays,
                "Exactly " + plays + " ROCK plays not recorded");
    }
}
