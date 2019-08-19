package com.measurement.service;

import com.measurement.data.transfer.MetricsDTO;
import com.measurement.exception.EntityNotFoundException;

public interface MetricService {

    /**
     * Calculate the metrics for the given sensor.
     *
     * @param   sensorName
     *
     * @return  data transfer object with the metrics
     *
     * @throws  EntityNotFoundException
     */
    MetricsDTO getSensorMetrics(String sensorName) throws EntityNotFoundException;
}
