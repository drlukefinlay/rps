package com.test.rps.metrics;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.rps.metrics.model.dto.MetricsDTO;
import com.test.rps.metrics.model.dto.PlayDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MetricsRestController {

    private final MetricsService metricsService;

    @GetMapping(path = "/metrics")
    @CrossOrigin
    public MetricsDTO playRound() {
        return metricsService.getMetrics();
    }
    
    @GetMapping(path = "/plays")
    @CrossOrigin
    public List<PlayDTO> getPlays(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int pageSize) {
        return metricsService.getPlays(page, pageSize);
    }
}
