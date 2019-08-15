package com.measurement.collector.service;

import com.measurement.collector.data.transfer.AlertDTO;
import com.measurement.collector.data.transfer.MetricsDTO;
import com.measurement.collector.datamodel.SensorStatus;
import com.measurement.collector.exception.EntityNotFoundException;
import com.measurement.collector.mapper.MeasurementMapper;
import com.measurement.collector.mapper.MetricsMapper;
import com.measurement.collector.persistence.entities.MeasurementDO;
import com.measurement.collector.persistence.repository.MeasurementRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
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
        if (measurementDO.getCo2() >= 2000) {
            MeasurementDO lastMeasurementDO = measurementRepository.findOneBySensorNameOrderByMeasurementTimeDesc(
                    measurementDO.getSensorName());
            if (SensorStatus.WARN.equals(lastMeasurementDO.getSensorStatus())
                    || SensorStatus.ALERT.equals(lastMeasurementDO.getSensorStatus())) {
                measurementDOToSave.setSensorStatus(SensorStatus.ALERT);
            } else {
                measurementDOToSave.setSensorStatus(SensorStatus.WARN);
            }
        } else {
            List<MeasurementDO> previousMeasurements = measurementRepository.findAllBySensorNameAndMeasurementTimeAfter(
                    measurementDO.getSensorName(), measurementDO.getMeasurementTime().minusMinutes(3));
            if(previousMeasurements.isEmpty()){
                measurementDOToSave.setSensorStatus(SensorStatus.OK);
            }
            else if (previousMeasurements.stream().filter(measurementDO1 -> measurementDO1.getCo2() < 2000).collect(
                        Collectors.toList()).size() == 3) {
                measurementDOToSave.setSensorStatus(SensorStatus.OK);
            }else {
                measurementDOToSave.setSensorStatus(measurementDO.getSensorStatus());
            }
        }
        return measurementRepository.save(measurementDOToSave);
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

    @Override
    public MeasurementDO getCurrentSensorStatus(String sensorName) throws EntityNotFoundException {
        MeasurementDO measurementDO = measurementRepository.findOneBySensorNameOrderByMeasurementTimeDesc(sensorName);
        if (measurementDO == null) {
            throw new EntityNotFoundException("No status found");
        }
        return measurementDO;
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
    public AlertDTO getSensorAlerts(ZonedDateTime periodStart, ZonedDateTime periodEnd) {
        List<MeasurementDO> measurementDOList = measurementRepository.findAllBySensorStatusAndMeasurementTimeBetween(
                SensorStatus.ALERT, periodStart, periodEnd);
        AlertDTO alertDTO = new AlertDTO();
        alertDTO.setStartPeriod(periodStart);
        alertDTO.setEndPeriod(periodEnd);
        alertDTO.setAlertMeasurements(MeasurementMapper.createMeasurementDTOListfromMeasurementDOList(
                measurementDOList));
        return alertDTO;
    }
}
