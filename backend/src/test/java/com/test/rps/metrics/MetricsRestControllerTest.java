package com.test.rps.metrics;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.rps.metrics.model.dto.MetricsDTO;

@WebMvcTest(MetricsRestController.class)
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
}
