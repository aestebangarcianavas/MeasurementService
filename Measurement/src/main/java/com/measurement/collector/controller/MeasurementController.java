package com.measurement.collector.controller;

import com.measurement.collector.data.transfer.MeasurementDTO;
import com.measurement.collector.exception.ConstraintsViolationException;
import com.measurement.collector.exception.EntityNotFoundException;
import com.measurement.collector.mapper.MeasurementMapper;
import com.measurement.collector.persistence.entities.MeasurementDO;
import com.measurement.collector.service.MeasurementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.List;

import javax.validation.Valid;

/**
 * REST controller to get the information of the measurements.
 *
 * @author  Ana Esteban Garcia-Navas(<a href="mailto:ramoni.esteban@gmail.com">ramoni</a>)
 */
@RestController
@RequestMapping("v1/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(final MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping("/createMeasurement")
    @ResponseStatus(HttpStatus.CREATED)
    public MeasurementDTO createMeasurement(@Valid
            @RequestBody
            MeasurementDTO measurementDTO) throws ConstraintsViolationException {
        MeasurementDO measurementDO = MeasurementMapper.createMeasurementDOfromMeasurementDTO(measurementDTO);
        return MeasurementMapper.createMeasurementDTOfromMeasurementDO(measurementService.createMeasurement(
                    measurementDO));
    }

    @GetMapping("/measurement")
    public MeasurementDTO getMeasurement(@PathVariable
            String sensor, @PathVariable
            ZonedDateTime measurementDate) throws EntityNotFoundException {
        MeasurementDO measurementDO = measurementService.getMeasurement(sensor, measurementDate);
        return MeasurementMapper.createMeasurementDTOfromMeasurementDO(measurementDO);
    }

    @GetMapping("/measurements")
    public List<MeasurementDTO> getMeasurements(@PathVariable
            String sensor, @PathVariable
            ZonedDateTime date) throws EntityNotFoundException {
        List<MeasurementDO> measurements = measurementService.findMeasurementsBySensorFromDate(sensor, date);
        return MeasurementMapper.createMeasurementDTOListfromMeasurementDOList(measurements);
    }
}
