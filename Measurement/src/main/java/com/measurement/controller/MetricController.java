package com.measurement.controller;

import com.measurement.data.transfer.MetricsDTO;
import com.measurement.exception.EntityNotFoundException;
import com.measurement.service.MetricService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller to get the metrics of the sensors.
 *
 * @author  Ana Esteban Garcia-Navas(<a href="mailto:ramoni.esteban@gmail.com">ramoni</a>)
 */
@RestController
@RequestMapping("/v1/sensors")
public class MetricController {

    @Autowired
    MetricService metricService;

    @GetMapping("/metrics")
    public MetricsDTO getSensorMetrics(@RequestParam(required = true) String sensorName)
        throws EntityNotFoundException {
        return metricService.getSensorMetrics(sensorName);
    }
}
