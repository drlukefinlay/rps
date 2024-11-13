package com.test.rps.metrics.model.dto;

import java.util.Map;

import com.test.rps.game.model.Choice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetricsDTO {
    private long totalCount;
    private Map<Choice, MetricCountDTO> playerCounts;
}
