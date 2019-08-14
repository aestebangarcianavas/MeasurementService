package com.measurement.collector.service;

import com.measurement.collector.exception.EntityNotFoundException;
import com.measurement.collector.persistence.entities.MeasurementDO;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Service that provides the information of the measurement.
 *
 * @author  Ana Esteban Garcia-Navas(<a href="mailto:ramoni.esteban@gmail.com">ramoni</a>)
 */
public interface MeasurementService {

    /**
     * Creates a new measurement and stores it within the persistence.
     *
     * @param   measurementDO
     *
     * @return  the measurement data object
     */
    MeasurementDO createMeasurement(MeasurementDO measurementDO);

    /**
     * Get the measurement for a given sensor and the time of the measurement.
     *
     * @param   sensor
     * @param   measurementDate
     *
     * @return  the measurement data object
     *
     * @throws  EntityNotFoundException
     */
    MeasurementDO getMeasurement(String sensor, ZonedDateTime measurementDate) throws EntityNotFoundException;

    /**
     * Get all the measurements available for the given sensor and up to measurementDate.
     *
     * @param   sensor
     * @param   measurementDate
     *
     * @return  List of measurements
     *
     * @throws  EntityNotFoundException
     */
    List<MeasurementDO> findMeasurementsBySensorFromDate(String sensor, ZonedDateTime measurementDate)
        throws EntityNotFoundException;
}
