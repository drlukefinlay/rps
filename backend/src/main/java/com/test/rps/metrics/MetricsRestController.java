package com.test.rps.metrics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.rps.metrics.model.dto.MetricsDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MetricsRestController {

    private final MetricsService metricsService;

    @GetMapping(path = "/metrics")
    public MetricsDTO playRound() {
        return metricsService.getMetrics();
    }
}
