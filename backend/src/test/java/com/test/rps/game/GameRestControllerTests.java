package com.test.rps.game;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.StringContains.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.rps.game.model.Choice;
import com.test.rps.game.model.Outcome;
import com.test.rps.game.model.dto.PlayerStrategyDTO;
import com.test.rps.game.model.dto.RoundOutcomeDTO;
import com.test.rps.metrics.MetricsService;

@WebMvcTest
class GameRestControllerTests {
    @MockBean
    private GameService gameService;
    @MockBean
    private MetricsService metricsService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testPlayRoundNormal() throws Exception {
        PlayerStrategyDTO play = new PlayerStrategyDTO(Choice.ROCK);
        RoundOutcomeDTO result = new RoundOutcomeDTO(Choice.ROCK, Choice.PAPER, Outcome.COMPUTER_WIN);
        when(gameService.processGame(play)).thenReturn(result);
        mockMvc.perform(post("/api/play").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(play))).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(result)));
    }

    @Test
    void testNullChoice() throws Exception {
        PlayerStrategyDTO play = new PlayerStrategyDTO(null);
        mockMvc.perform(post("/api/play").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(play))).andExpect(status().isBadRequest())
                .andExpect(content().json("{\"playerChoice\":\"Player must make a choice\"}"));
    }

    @Test
    void testInvalidChoice() throws Exception {
        mockMvc.perform(post("/api/play").contentType(MediaType.APPLICATION_JSON).content("{\"playerChoice\":1.5}"))
                .andExpect(status().isBadRequest()).andExpect(content().string(containsString("\"error\"")));
    }
}