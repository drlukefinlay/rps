package com.test.rps.metrics.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetricsDTO {
    private long playCount;
    private List<MetricCountDTO> counts;

}
