package com.measurement.service;

import com.measurement.data.transfer.AlertDTO;
import com.measurement.datamodel.SensorStatus;
import com.measurement.exception.EntityNotFoundException;
import com.measurement.mapper.MeasurementMapper;
import com.measurement.persistence.entities.MeasurementDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class AlertsServiceImpl implements AlertService{

    @Autowired
    MeasurementService measurementService;

    @Override
    public AlertDTO getSensorAlerts(LocalDateTime periodStart, LocalDateTime periodEnd) throws EntityNotFoundException {
        List<MeasurementDO> measurementDOList = measurementService.findMeasurementsBySensorStatusWithinPeriod(
                SensorStatus.ALERT, periodStart, periodEnd);
        AlertDTO alertDTO = new AlertDTO();
        alertDTO.setStartPeriod(periodStart);
        alertDTO.setEndPeriod(periodEnd);
        alertDTO.setAlertMeasurements(MeasurementMapper.createMeasurementDTOListfromMeasurementDOList(
                measurementDOList));
        return alertDTO;    }
}
