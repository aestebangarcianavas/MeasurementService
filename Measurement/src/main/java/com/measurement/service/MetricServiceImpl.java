package com.measurement.service;

import com.measurement.data.transfer.MetricsDTO;
import com.measurement.exception.EntityNotFoundException;
import com.measurement.mapper.MetricsMapper;
import com.measurement.persistence.entities.MeasurementDO;
import com.measurement.persistence.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class MetricServiceImpl implements MetricService{


    @Autowired
    MeasurementService measurementService;

    /**
     * Calculate the metrics for the given sensor.
     *
     * @param   sensorName
     *
     * @return  data transfer object with the metrics
     *
     * @throws EntityNotFoundException
     */
    @Override
    public MetricsDTO getSensorMetrics(String sensorName) throws EntityNotFoundException {
        LocalDateTime startTime = LocalDateTime.now().minusDays(30);
        LocalDateTime endTime = LocalDateTime.now();
        List<MeasurementDO> measurementDOList = measurementService.findMeasurementBySensorNameWithinPeriod(sensorName, startTime, endTime);
        Long maxValue = measurementDOList.stream().mapToLong(measurementDO -> measurementDO.getCo2()).max().getAsLong();
        Double avgValue = measurementDOList.stream()
                .mapToLong(measurementDO -> measurementDO.getCo2())
                .average()
                .getAsDouble();
        return MetricsMapper.createMetricsMapper(sensorName, startTime, endTime, maxValue, avgValue);
    }
}
