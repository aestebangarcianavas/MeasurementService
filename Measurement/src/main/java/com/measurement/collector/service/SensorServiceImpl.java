package com.measurement.collector.service;

import com.measurement.collector.data.transfer.AlertDTO;
import com.measurement.collector.data.transfer.MetricsDTO;
import com.measurement.collector.exception.EntityNotFoundException;
import com.measurement.collector.mapper.MetricsMapper;
import com.measurement.collector.persistence.entities.MeasurementDO;
import com.measurement.collector.persistence.entities.SensorDO;
import com.measurement.collector.persistence.repository.MeasurementRepository;
import com.measurement.collector.persistence.repository.SensorRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.List;

public class SensorServiceImpl implements SensorService {

    private static final Logger LOG = LoggerFactory.getLogger(SensorServiceImpl.class);

    private final SensorRepository sensorRepository;
    private final MeasurementRepository measurementRepository;

    public SensorServiceImpl(SensorRepository sensorRepository, MeasurementRepository measurementRepository) {
        this.sensorRepository = sensorRepository;
        this.measurementRepository = measurementRepository;
    }

    @Override
    public SensorDO getCurrentSensorStatus(String sensorName) throws EntityNotFoundException {
        SensorDO sensorDO = sensorRepository.findOneBySensorNameOrderByLastModificationDateDesc(sensorName);
        if (sensorDO == null) {
            throw new EntityNotFoundException("No status found");
        }
        return sensorDO;
    }

    @Override
    public MetricsDTO getSensorMetrics(String sensorName) throws EntityNotFoundException {
        ZonedDateTime startTime = ZonedDateTime.now().minusDays(30);
        ZonedDateTime endTime = ZonedDateTime.now();
        List<MeasurementDO> measurementDOList = measurementRepository.findAllBySensorNameAndMeasurementTimeAfter(
                sensorName, startTime);
        if (measurementDOList.isEmpty()) {
            throw new EntityNotFoundException("No measurements found for the sensor " + sensorName);
        }
        Long maxValue = measurementDOList.stream().mapToLong(measurementDO -> measurementDO.getCo2()).max().getAsLong();
        Double avgValue = measurementDOList.stream()
                .mapToLong(measurementDO -> measurementDO.getCo2())
                .average()
                .getAsDouble();
        return MetricsMapper.createMetricsMapper(sensorName, startTime, endTime, maxValue, avgValue);
    }

    @Override
    public AlertDTO getSensorAlerts(String sensorName, ZonedDateTime periodStart, ZonedDateTime periodEnd) {
        return null;
    }
}
