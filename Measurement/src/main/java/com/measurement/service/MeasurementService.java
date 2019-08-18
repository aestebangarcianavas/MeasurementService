package com.measurement.service;

import com.measurement.datamodel.SensorStatus;
import com.measurement.exception.EntityNotFoundException;
import com.measurement.persistence.entities.MeasurementDO;

import java.time.LocalDateTime;
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
     * @param   sensorName
     * @param   measurementDate
     *
     * @return  the measurement data object
     *
     * @throws  EntityNotFoundException
     */
    MeasurementDO getMeasurement(String sensorName, LocalDateTime measurementDate) throws EntityNotFoundException;

    /**
     * Get all the measurements available for the given sensor and up to measurementDate.
     *
     * @param   sensor
     *
     * @return  List of measurements
     *
     * @throws  EntityNotFoundException
     */
    List<MeasurementDO> findMeasurementsBySensorName(String sensor) throws EntityNotFoundException;

    /**
     * Get the status and the measurement of the given sensor.
     *
     * @param   sensorName
     *
     * @return
     *
     * @throws  EntityNotFoundException
     */
    MeasurementDO getCurrentSensorStatus(String sensorName) throws EntityNotFoundException;

    /**
     * Returns all the measurements for the given period.
     *
     * @param   sensorName
     * @param   starDateTime
     * @param   endDateTime
     *
     * @return
     */
    List<MeasurementDO> findMeasurementBySensorNameWithinPeriod(String sensorName, LocalDateTime starDateTime,
            LocalDateTime endDateTime) throws EntityNotFoundException;

    /**
     * Returns all the sensor measurements with the given sensor status.
     *
     * @param   sensorStatus
     * @param   starDateTime
     * @param   endDateTime
     *
     * @return
     *
     * @throws  EntityNotFoundException
     */
    List<MeasurementDO> findMeasurementsBySensorStatusWithinPeriod(SensorStatus sensorStatus,
            LocalDateTime starDateTime, LocalDateTime endDateTime) throws EntityNotFoundException;
}
