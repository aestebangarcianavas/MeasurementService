package com.measurement.persistence.repository;

import com.measurement.datamodel.SensorStatus;
import com.measurement.persistence.entities.MeasurementDO;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Interface to access to the data contained within the measurement table.
 *
 * @author  Ana Esteban Garcia-Navas(<a href="mailto:ramoni.esteban@gmail.com">ramoni</a>)
 */
public interface MeasurementRepository extends CrudRepository<MeasurementDO, Long> {
    List<MeasurementDO> findBySensorName(String sensorName);
    MeasurementDO findBySensorNameAndMeasurementTime(String sensorName, LocalDateTime measurementDate);
    List<MeasurementDO> findAllBySensorNameAndMeasurementTimeAfter(String sensorName, LocalDateTime measurementDate);
    List<MeasurementDO> findBySensorNameOrderByMeasurementTimeDesc(String sensorName);
    List<MeasurementDO> findAllBySensorNameAndMeasurementTimeBetween(String sensorName, LocalDateTime startTime,
            LocalDateTime endTime);
    List<MeasurementDO> findAllBySensorStatusAndMeasurementTimeBetween(SensorStatus sensorStatus,
            LocalDateTime startTime, LocalDateTime endTime);
}
