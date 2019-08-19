package com.measurement.service;

import com.measurement.data.transfer.AlertDTO;
import com.measurement.exception.EntityNotFoundException;

import java.time.LocalDateTime;

/**
 * Service to get the alerts of a sensor.
 *
 * @author  Ana Esteban Garcia-Navas(<a href="mailto:ramoni.esteban@gmail.com">ramoni</a>)
 */
public interface AlertService {

    /**
     * Get the alerts measured by the sensors.
     *
     * @param   periodStart
     * @param   periodEnd
     *
     * @return  alert data transfer object
     *
     * @throws  EntityNotFoundException  if no alert was found
     */
    AlertDTO getSensorAlerts(LocalDateTime periodStart, LocalDateTime periodEnd) throws EntityNotFoundException;
}
