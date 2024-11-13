package com.test.rps.metrics.model.dto;

import java.util.HashMap;
import java.util.Map;

import com.test.rps.game.model.Choice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetricCountDTO {
    private Map<Choice, Long> computerCounts = new HashMap<Choice, Long>();
}
