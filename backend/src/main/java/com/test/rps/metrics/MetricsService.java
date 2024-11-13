package com.test.rps.metrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.test.rps.game.GameLogger;
import com.test.rps.game.model.Choice;
import com.test.rps.metrics.model.Play;
import com.test.rps.metrics.model.PlayCount;
import com.test.rps.metrics.model.PlayRepository;
import com.test.rps.metrics.model.dto.MetricCountDTO;
import com.test.rps.metrics.model.dto.MetricsDTO;
import com.test.rps.metrics.model.dto.PlayDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Metrics service.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MetricsService implements GameLogger {
    private final Choice[] CHOICES = Choice.values();
    private final PlayRepository playRepository;

    public void logPlay(Choice playerChoice, Choice computerChoice) {
        Play play = new Play(playerChoice, computerChoice);
        playRepository.save(play);
    }

    public MetricsDTO getMetrics() {
        MetricsDTO metrics = new MetricsDTO();
        metrics.setTotalCount(playRepository.count());
        metrics.setPlayerCounts(calculateCounts());
        return metrics;
    }

    public List<PlayDTO> getPlays(int pageNumber, int pageSize) {
        List<PlayDTO> plays = new ArrayList<>();
        Pageable pagedById = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        for(Play play: playRepository.findAll(pagedById)) {
            plays.add(new PlayDTO(play));
        }
        return plays;
    }
    
    /*
     * Here we are effectively asking the database to count all 9 possibilities. If
     * there is a performance problem here because there are a lot of records, we
     * could keep a transient object that is updated per play and re-created from
     * the database on startup. Or persisted on changes.
     */
    private Map<Choice, MetricCountDTO> calculateCounts() {
        HashMap<Choice, MetricCountDTO> playerCounts = new HashMap<>();
        for (Choice playerChoice: CHOICES) {
            playerCounts.put(playerChoice, new MetricCountDTO());
        }
        
        List<PlayCount> playCounts = playRepository.countTotalsByPlayerAndComputer();

        for (PlayCount playCount : playCounts) {
            playerCounts.get(playCount.getPlayer()).getComputerCounts().put(playCount.getComputer(), playCount.getTotal());
        }

        return playerCounts;
    }
}
