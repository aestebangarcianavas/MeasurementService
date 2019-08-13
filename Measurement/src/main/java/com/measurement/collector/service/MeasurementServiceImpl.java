package com.measurement.collector.service;

import com.measurement.collector.exception.ConstraintsViolationException;
import com.measurement.collector.persistence.entities.MeasurementDO;
import com.measurement.collector.persistence.repository.MeasurementRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.dao.DataIntegrityViolationException;
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
    public MeasurementDO createMeasurement(MeasurementDO measurementDO) throws ConstraintsViolationException {
        MeasurementDO measurement;
        try {
            measurement = measurementRepository.save(measurementDO);
        } catch (DataIntegrityViolationException exception) {
            LOG.error("Constraint violated. See", exception);
            throw new ConstraintsViolationException(exception.getMessage());
        }
        return measurement;
    }

    @Override
    public MeasurementDO getMeasurement(String sensor, ZonedDateTime measurementDate) {
        return null;
    }

    @Override
    public List<MeasurementDO> findMeasurementsBySensorFromDate(String sensor, ZonedDateTime measurementDate) {
        return null;
    }
}
