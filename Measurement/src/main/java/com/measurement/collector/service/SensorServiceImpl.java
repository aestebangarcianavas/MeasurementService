package com.measurement.collector.service;

import com.measurement.collector.data.transfer.AlertDTO;
import com.measurement.collector.data.transfer.MetricsDTO;
import com.measurement.collector.persistence.entities.SensorDO;

import java.time.ZonedDateTime;

public class SensorServiceImpl implements SensorService {

    @Override
    public SensorDO getSensorStatus(Long sensorId) {
        return null;
    }

    @Override
    public MetricsDTO getSensorMetrics(Long sensorId) {
        return null;
    }

    @Override
    public AlertDTO getSensorAlerts(Long sensorId, ZonedDateTime periodStart, ZonedDateTime periodEnd) {
        return null;
    }
}
