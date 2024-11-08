package com.test.rps.metrics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.test.rps.game.GameLogger;
import com.test.rps.game.model.Choice;
import com.test.rps.metrics.model.Play;
import com.test.rps.metrics.model.PlayCount;
import com.test.rps.metrics.model.PlayRepository;
import com.test.rps.metrics.model.dto.MetricCountDTO;
import com.test.rps.metrics.model.dto.MetricsDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Metrics service.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MetricsService implements GameLogger {
    private final PlayRepository playRepository;

    public void logPlay(Choice playerChoice, Choice computerChoice) {
        Play play = new Play(playerChoice, computerChoice);
        playRepository.save(play);
    }

    public MetricsDTO getMetrics() {
        MetricsDTO metrics = new MetricsDTO();
        metrics.setPlayCount(playRepository.count());
        metrics.setCounts(calculateCounts());
        return metrics;
    }

    /*
     * Here we are effectively asking the database to count all 9 possibilities. If
     * there is a performance problem here because there are a lot of records, we
     * could keep a transient object that is updated per play and re-created from
     * the database on startup. Or persisted on changes.
     */
    private List<MetricCountDTO> calculateCounts() {
        List<MetricCountDTO> counts = new ArrayList<>(9);
        List<PlayCount> playCounts = playRepository.countTotalsByPlayerAndComputer();

        for (PlayCount playCount : playCounts) {
            counts.add(new MetricCountDTO(playCount.getPlayer(), playCount.getComputer(), playCount.getTotal()));
        }

        return counts;
    }
}
