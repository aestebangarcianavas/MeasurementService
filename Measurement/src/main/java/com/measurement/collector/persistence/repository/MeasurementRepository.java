package com.measurement.collector.persistence.repository;

import com.measurement.collector.datamodel.SensorStatus;
import com.measurement.collector.persistence.entities.MeasurementDO;

import org.springframework.data.repository.CrudRepository;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Interface to access to the data contained within the measurement table.
 *
 * @author  Ana Esteban Garcia-Navas(<a href="mailto:ramoni.esteban@gmail.com">ramoni</a>)
 */
public interface MeasurementRepository extends CrudRepository<MeasurementDO, Long> {
    List<MeasurementDO> findBySensorName(String sensorName);
    MeasurementDO findBySensorNameAndMeasurementTime(String sensorName, ZonedDateTime measurementDate);
    List<MeasurementDO> findAllBySensorNameAndMeasurementTimeAfter(String sensorName, ZonedDateTime measurementDate);
    MeasurementDO findOneBySensorNameOrderByMeasurementTimeDesc(String sensorName);
    List<MeasurementDO> findAllBySensorStatusAndMeasurementTimeBetween(SensorStatus sensorStatus, ZonedDateTime startTime, ZonedDateTime endTime);
}
