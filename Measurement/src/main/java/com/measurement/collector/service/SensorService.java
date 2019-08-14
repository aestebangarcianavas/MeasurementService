package com.measurement.collector.service;

import com.measurement.collector.data.transfer.MeasurementDTO;
import com.measurement.collector.data.transfer.MetricsDTO;
import com.measurement.collector.persistence.entities.SensorDO;

import java.util.List;

public interface SensorService {
    SensorDO getSensorStatus(Long sensorId);
    MetricsDTO getSensorMetrics(Long sensorId);
    List<MeasurementDTO> getSensorAlerts(Long sensorId);
}
