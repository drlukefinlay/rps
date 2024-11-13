package com.test.rps.metrics;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.rps.game.model.Choice;
import com.test.rps.metrics.model.Play;
import com.test.rps.metrics.model.dto.MetricsDTO;
import com.test.rps.metrics.model.dto.PlayDTO;

@WebMvcTest(MetricsRestController.class)
@ActiveProfiles(profiles = "test")
class MetricsRestControllerTest {
    @MockBean
    private MetricsService metricsService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testBlankMetrics() throws Exception {
        MetricsDTO metrics = new MetricsDTO();
        metrics.setTotalCount(999);
        metrics.setPlayerCounts(Collections.emptyMap());
        
        when(metricsService.getMetrics()).thenReturn(metrics);

        mockMvc.perform(get("/api/metrics")).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(metrics)));
    }
    
    @Test
    void testPlayedMetrics() throws Exception {
        Play fakePlay = new Play(Choice.ROCK, Choice.SCISSORS);
        fakePlay.setId(245l);
        List<PlayDTO> plays = Collections.singletonList(new PlayDTO(fakePlay ));
        
        when(metricsService.getPlays(anyInt(), anyInt())).thenReturn(plays);

        mockMvc.perform(get("/api/plays")).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(plays)));
    }
}
