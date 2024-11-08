package com.test.rps.game;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.rps.game.model.dto.PlayerStrategyDTO;
import com.test.rps.game.model.dto.RoundOutcomeDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GameRestController {

    private final GameService gameService;

    @PostMapping(path = "/play")
    public RoundOutcomeDTO playRound(@Valid @RequestBody PlayerStrategyDTO strategy) {
        return gameService.processGame(strategy);
    }
}
