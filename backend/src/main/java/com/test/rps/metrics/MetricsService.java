package com.test.rps.metrics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.test.rps.game.model.Choice;
import com.test.rps.metrics.model.Play;
import com.test.rps.metrics.model.PlayCount;
import com.test.rps.metrics.model.PlayRepository;
import com.test.rps.metrics.model.dto.MetricCountDTO;
import com.test.rps.metrics.model.dto.MetricsDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MetricsService {
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

    private List<MetricCountDTO> calculateCounts() {
        List<MetricCountDTO> counts = new ArrayList<>(9);
        List<PlayCount> playCounts = playRepository.countTotalsByPlayerAndComputer();

        for (PlayCount playCount : playCounts) {
            counts.add(new MetricCountDTO(playCount.getPlayer(), playCount.getComputer(), playCount.getTotal()));
        }

        return counts;
    }
}
