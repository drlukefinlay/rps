package com.test.rps.metrics.model.dto;

import com.test.rps.game.model.Choice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MetricCountDTO {
    private Choice player;
    private Choice computer;
    private long count;
}
