package com.test.rps.game.model.dto;

import com.test.rps.game.model.Choice;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerStrategyDTO {
    @NotNull(message = "Player must make a choice")
    private Choice playerChoice;
}
