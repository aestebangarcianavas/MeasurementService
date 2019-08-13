package com.measurement.collector.persistence.repository;

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
    List<MeasurementDO> findBySensorId(Long sensorId);
    MeasurementDO findBySensorIdAndMeasurementTime(Long sensorId, ZonedDateTime measurementDate);
}
