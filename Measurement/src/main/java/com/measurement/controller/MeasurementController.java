package com.measurement.controller;

import com.measurement.data.transfer.MeasurementDTO;
import com.measurement.exception.ConstraintsViolationException;
import com.measurement.exception.EntityNotFoundException;
import com.measurement.mapper.MeasurementMapper;
import com.measurement.persistence.entities.MeasurementDO;
import com.measurement.service.MeasurementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

/**
 * REST controller to get the information of the measurements.
 *
 * @author  Ana Esteban Garcia-Navas(<a href="mailto:ramoni.esteban@gmail.com">ramoni</a>)
 */
@RestController
@RequestMapping("/v1/sensors")
public class MeasurementController {

    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(final MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping("/measurement/create")
    @ResponseStatus(HttpStatus.CREATED)
    public MeasurementDTO createMeasurement(@Valid
            @RequestBody
            MeasurementDTO measurementDTO) throws ConstraintsViolationException {
        MeasurementDO measurementDO = MeasurementMapper.createMeasurementDOfromMeasurementDTO(measurementDTO);
        return MeasurementMapper.createMeasurementDTOfromMeasurementDO(measurementService.createMeasurement(
                    measurementDO));
    }

    @GetMapping("/measurement")
    public MeasurementDTO getMeasurement(@RequestParam(required = true) String sensor,
            @RequestParam("measurementDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime measurementDate) throws EntityNotFoundException {
        MeasurementDO measurementDO = measurementService.getMeasurement(sensor, measurementDate);
        return MeasurementMapper.createMeasurementDTOfromMeasurementDO(measurementDO);
    }

    @GetMapping("/measurements")
    public List<MeasurementDTO> getMeasurements(@RequestParam(required = true) String sensor)
        throws EntityNotFoundException {
        List<MeasurementDO> measurements = measurementService.findMeasurementsBySensorName(sensor);
        return MeasurementMapper.createMeasurementDTOListfromMeasurementDOList(measurements);
    }

    @GetMapping("/status")
    public MeasurementDTO getSensorStatus(@RequestParam(required = true) String sensorName)
        throws EntityNotFoundException {
        MeasurementDO measurementDO = measurementService.getCurrentSensorStatus(sensorName);
        if (measurementDO == null) {
            throw new EntityNotFoundException("No status found");
        }
        return MeasurementMapper.createMeasurementDTOfromMeasurementDO(measurementDO);
    }
}
