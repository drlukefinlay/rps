package com.test.rps.game.model.dto;

import com.test.rps.game.model.Choice;
import com.test.rps.game.model.Outcome;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoundOutcomeDTO {
    private Choice playerChoice;
    private Choice computerChoice;
    private Outcome outcome;
}
