package com.measurement.collector.service;

import com.measurement.collector.data.transfer.AlertDTO;
import com.measurement.collector.data.transfer.MetricsDTO;
import com.measurement.collector.exception.EntityNotFoundException;
import com.measurement.collector.persistence.entities.SensorDO;

import java.time.ZonedDateTime;

/**
 * Service that provides the information aabout the sensors.
 *
 * @author  Ana Esteban Garcia-Navas(<a href="mailto:ramoni.esteban@gmail.com">ramoni</a>)
 */
public interface SensorService {

    /**
     * Return the current status of the sensor.
     *
     * @param   sensorName
     *
     * @return  the sensor data object
     *
     * @throws  EntityNotFoundException
     */
    SensorDO getCurrentSensorStatus(String sensorName) throws EntityNotFoundException;

    /**
     * Returns the metrics calculated for the given sensor.
     *
     * @param   sensorName
     *
     * @return  Metrics data transfer object
     *
     * @throws  EntityNotFoundException
     */
    MetricsDTO getSensorMetrics(String sensorName) throws EntityNotFoundException;

    /**
     * Returns the alerts found for a given period of time.
     *
     * @param   sensorName
     * @param   periodStart
     * @param   periodEnd
     *
     * @return  alert data transfer object
     */
    AlertDTO getSensorAlerts(String sensorName, ZonedDateTime periodStart, ZonedDateTime periodEnd);
}
