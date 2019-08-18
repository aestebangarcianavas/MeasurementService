package com.measurement.service;

import com.measurement.datamodel.SensorStatus;
import com.measurement.exception.EntityNotFoundException;
import com.measurement.persistence.entities.MeasurementDO;
import com.measurement.persistence.repository.MeasurementRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 */
@Service
public class MeasurementServiceImpl implements MeasurementService {

    private static final Logger LOG = LoggerFactory.getLogger(MeasurementServiceImpl.class);

    @Autowired
    MeasurementRepository measurementRepository;

    @Override
    public MeasurementDO createMeasurement(MeasurementDO measurementDO) {
        MeasurementDO measurementDOToSave = measurementDO;
        List<MeasurementDO> previousMeasurements = measurementRepository.findAllBySensorNameAndMeasurementTimeAfter(
                measurementDO.getSensorName(), measurementDO.getMeasurementTime().minusMinutes(3).withNano(0));
        LOG.info("Last three measurements found : {}", previousMeasurements);
        if (previousMeasurements.isEmpty()) {
            measurementDOToSave.setSensorStatus(SensorStatus.OK);
        } else {
            if ((measurementDO.getCo2() == 2000) || (measurementDO.getCo2() > 2000)) {
                measurementDOToSave.setSensorStatus(getSensorStatusForUpperMeasurement(previousMeasurements));
            } else {
                measurementDOToSave.setSensorStatus(getSensorStatusForLowerMeasurement(previousMeasurements));
            }
        }
        LOG.info("Save following entity: {} ", measurementDOToSave);
        return measurementRepository.save(measurementDOToSave);
    }

    private SensorStatus getSensorStatusForUpperMeasurement(List<MeasurementDO> previousMeasurements) {
        switch (previousMeasurements.get(0).getSensorStatus()) {
            case ALERT:
                return SensorStatus.ALERT;

            case OK:
                return SensorStatus.WARN;

            case WARN:
                if (previousMeasurements.stream().filter(measurementDO1 ->
                                ((measurementDO1.getCo2() > 2000) || (measurementDO1.getCo2() == 2000)))
                        .collect(Collectors.toList()).size() >= 2) {
                    return SensorStatus.ALERT;
                }
        }
        return SensorStatus.WARN;
    }

    private SensorStatus getSensorStatusForLowerMeasurement(List<MeasurementDO> previousMeasurements) {
        switch (previousMeasurements.get(0).getSensorStatus()) {
            case ALERT:
                return SensorStatus.WARN;

            case OK:
                return SensorStatus.OK;

            case WARN:
                if (previousMeasurements.stream().filter(measurementDO1 -> ((measurementDO1.getCo2() < 2000)))
                        .collect(Collectors.toList()).size() >= 3) {
                    return SensorStatus.OK;
                }
                return SensorStatus.WARN;
        }
        return SensorStatus.OK;
    }

    @Override
    public MeasurementDO getMeasurement(String sensorName, LocalDateTime measurementDate)
        throws EntityNotFoundException {
        MeasurementDO measurementDO = measurementRepository.findBySensorNameAndMeasurementTime(sensorName,
                measurementDate.withNano(0));
        if (measurementDO == null) {
            new EntityNotFoundException("No measurement found for the sensor");
        }
        return measurementDO;
    }

    @Override
    public List<MeasurementDO> findMeasurementsBySensorName(String sensorName) throws EntityNotFoundException {
        List<MeasurementDO> measurementDOList = measurementRepository.findBySensorName(sensorName);

        if (measurementDOList.isEmpty()) {
            throw new EntityNotFoundException("No measurement found!");
        }
        return measurementDOList;
    }

    @Override
    public MeasurementDO getCurrentSensorStatus(String sensorName) throws EntityNotFoundException {
        List<MeasurementDO> measurementDOList = measurementRepository.findBySensorNameOrderByMeasurementTimeDesc(
                sensorName);
        if (measurementDOList.isEmpty()) {
            throw new EntityNotFoundException("No status found");
        }
        return measurementDOList.get(0);
    }

    @Override
    public List<MeasurementDO> findMeasurementsBySensorStatusWithinPeriod(SensorStatus sensorStatus,
            LocalDateTime starDateTime, LocalDateTime endDateTime) throws EntityNotFoundException {
        List<MeasurementDO> measurementDOList = measurementRepository.findAllBySensorStatusAndMeasurementTimeBetween(
                sensorStatus, starDateTime, endDateTime);
        LOG.info("Measurements found {} ", measurementDOList);
        if (measurementDOList.isEmpty()) {
            throw new EntityNotFoundException("No measurement found!");
        }
        return measurementDOList;
    }

    @Override
    public List<MeasurementDO> findMeasurementBySensorNameWithinPeriod(String sensorName, LocalDateTime starDateTime,
            LocalDateTime endDateTime) throws EntityNotFoundException {
        List<MeasurementDO> measurementDOList = measurementRepository.findAllBySensorNameAndMeasurementTimeBetween(
                sensorName, starDateTime, endDateTime);
        if (measurementDOList.isEmpty()) {
            throw new EntityNotFoundException("No measurement found!");
        }
        return measurementDOList;
    }
}
