package com.measurement.controller;

import com.measurement.data.transfer.AlertDTO;
import com.measurement.exception.EntityNotFoundException;
import com.measurement.service.AlertService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * REST controller to get the alerts registered by the sensors.
 *
 * @author  Ana Esteban Garcia-Navas(<a href="mailto:ramoni.esteban@gmail.com">ramoni</a>)
 */
@RestController
@RequestMapping("/v1/sensors")
public class AlertController {

    @Autowired
    AlertService alertService;

    @GetMapping("/alerts")
    public AlertDTO getSensorAlerts(
            @RequestParam("periodStart")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime periodStart,
            @RequestParam("periodEnd")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime periodEnd) throws EntityNotFoundException {
        return alertService.getSensorAlerts(periodStart, periodEnd);
    }
}
