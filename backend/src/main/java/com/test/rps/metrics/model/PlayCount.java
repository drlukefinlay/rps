package com.test.rps.metrics.model;

import com.test.rps.game.model.Choice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PlayCount {
    private Choice player;
    private Choice computer;
    private Long total;
}
