package com.measurement.collector.service;

import com.measurement.collector.data.transfer.AlertDTO;
import com.measurement.collector.data.transfer.MetricsDTO;
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
     * @param   sensorId
     *
     * @return  the sensor data object
     */
    SensorDO getSensorStatus(Long sensorId);

    /**
     * Returns the metrics calculated for the given sensor.
     *
     * @param   sensorId
     *
     * @return  Metrics data transfer object
     */
    MetricsDTO getSensorMetrics(Long sensorId);

    /**
     * Returns the alerts found for a given period of time.
     *
     * @param   sensorId
     * @param   periodStart
     * @param   periodEnd
     *
     * @return  alert data transfer object
     */
    AlertDTO getSensorAlerts(Long sensorId, ZonedDateTime periodStart, ZonedDateTime periodEnd);
}
