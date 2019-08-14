package com.measurement.collector.mapper;

import com.measurement.collector.data.transfer.MetricsDTO;

import java.time.ZonedDateTime;

public class MetricsMapper {

    public static MetricsDTO createMetricsMapper(String sensorName, ZonedDateTime startTime, ZonedDateTime endTime, Long maxValue, Double average){
        MetricsDTO metricsDTO = new MetricsDTO();
        metricsDTO.setAverageValue(average);
        metricsDTO.setMaxValue(maxValue);
        metricsDTO.setSensorName(sensorName);
        metricsDTO.setEndTime(endTime);
        metricsDTO.setStartTime(startTime);
        return metricsDTO;
    }
}
