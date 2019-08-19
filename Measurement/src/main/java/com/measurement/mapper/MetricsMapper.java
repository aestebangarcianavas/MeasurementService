package com.measurement.mapper;

import com.measurement.data.transfer.MetricsDTO;

import java.time.LocalDateTime;

public class MetricsMapper {

    public static MetricsDTO createMetricsMapper(String sensorName, LocalDateTime startTime, LocalDateTime endTime,
            Long maxValue, Double average) {
        MetricsDTO metricsDTO = new MetricsDTO();
        metricsDTO.setAverageValue(average);
        metricsDTO.setMaxValue(maxValue);
        metricsDTO.setSensorName(sensorName);
        metricsDTO.setEndTime(endTime);
        metricsDTO.setStartTime(startTime);
        return metricsDTO;
    }
}
