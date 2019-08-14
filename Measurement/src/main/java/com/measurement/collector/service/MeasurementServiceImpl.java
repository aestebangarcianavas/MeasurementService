package com.measurement.collector.service;

import com.measurement.collector.exception.EntityNotFoundException;
import com.measurement.collector.persistence.entities.MeasurementDO;
import com.measurement.collector.persistence.repository.MeasurementRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

/**
 */
@Service
public class MeasurementServiceImpl implements MeasurementService {

    private static final Logger LOG = LoggerFactory.getLogger(MeasurementServiceImpl.class);

    private final MeasurementRepository measurementRepository;

    public MeasurementServiceImpl(final MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Override
    public MeasurementDO createMeasurement(MeasurementDO measurementDO) {
        return measurementRepository.save(measurementDO);
    }

    @Override
    public MeasurementDO getMeasurement(String sensorName, ZonedDateTime measurementDate)
        throws EntityNotFoundException {
        MeasurementDO measurementDO = measurementRepository.findBySensorNameAndMeasurementTime(sensorName,
                measurementDate);
        if (measurementDO == null) {
            new EntityNotFoundException("No measurement found for the sensor");
        }
        return measurementDO;
    }

    @Override
    public List<MeasurementDO> findMeasurementsBySensorFromDate(String sensorName, ZonedDateTime measurementDate)
        throws EntityNotFoundException {
        List<MeasurementDO> measurementDOList = measurementRepository.findAllBySensorNameAndMeasurementTimeAfter(
                sensorName, measurementDate);

        if (measurementDOList.isEmpty()) {
            throw new EntityNotFoundException("No measurement found!");
        }
        return measurementDOList;
    }
}
