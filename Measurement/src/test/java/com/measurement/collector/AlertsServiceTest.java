package com.measurement.collector;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.measurement.data.transfer.AlertDTO;
import com.measurement.datamodel.SensorStatus;
import com.measurement.exception.EntityNotFoundException;
import com.measurement.persistence.entities.MeasurementDO;
import com.measurement.service.AlertService;
import com.measurement.service.AlertsServiceImpl;
import com.measurement.service.MeasurementService;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@Import({ AlertsServiceImpl.class })
public class AlertsServiceTest {

    private static final MeasurementDO measurement1 = new MeasurementDO();
    private static final MeasurementDO measurement2 = new MeasurementDO();
    private static final MeasurementDO measurement3 = new MeasurementDO();
    private static final MeasurementDO measurement4 = new MeasurementDO();

    public static final String SENSOR_NAME = "sensor1";

    static {
        measurement1.setId(190000000L);
        measurement1.setMeasurementTime(LocalDateTime.now().withNano(0).minusMinutes(1));
        measurement1.setCo2(2200L);
        measurement1.setSensorName(SENSOR_NAME);
        measurement1.setSensorStatus(SensorStatus.ALERT);

        measurement2.setId(190000001L);
        measurement2.setMeasurementTime(LocalDateTime.now().withNano(0).minusMinutes(2));
        measurement2.setCo2(2000L);
        measurement2.setSensorName(SENSOR_NAME);
        measurement2.setSensorStatus(SensorStatus.ALERT);

        measurement3.setId(190000002L);
        measurement3.setMeasurementTime(LocalDateTime.now().withNano(0).minusMinutes(3));
        measurement3.setCo2(2100L);
        measurement3.setSensorName(SENSOR_NAME);
        measurement3.setSensorStatus(SensorStatus.ALERT);

        measurement4.setId(190000002L);
        measurement4.setMeasurementTime(LocalDateTime.now().withNano(0).minusMinutes(4));
        measurement4.setCo2(2100L);
        measurement4.setSensorName(SENSOR_NAME);
        measurement4.setSensorStatus(SensorStatus.ALERT);
    }

    @MockBean
    MeasurementService measurementService;

    @Inject
    AlertService alertService;

    @Test
    public void shouldGetSensorAlerts() throws EntityNotFoundException {
        LocalDateTime start = LocalDateTime.now().withNano(0).minusMinutes(1);
        LocalDateTime end = LocalDateTime.now().withNano(0).minusMinutes(5);
        List<MeasurementDO> measurementDOList = new ArrayList<>();
        measurementDOList.add(measurement1);
        measurementDOList.add(measurement2);
        measurementDOList.add(measurement3);
        measurementDOList.add(measurement4);
        when(measurementService.findMeasurementsBySensorStatusWithinPeriod(SensorStatus.ALERT, start, end)).thenReturn(
            measurementDOList);
        AlertDTO alertDTO = alertService.getSensorAlerts(start, end);
        assertThat(alertDTO).isNotNull();
        assertThat(alertDTO.getAlertMeasurements().size()).isEqualTo(4);
        assertThat(alertDTO.getStartPeriod()).isEqualTo(start);
        assertThat(alertDTO.getEndPeriod()).isEqualTo(end);
        verify(measurementService).findMeasurementsBySensorStatusWithinPeriod(SensorStatus.ALERT, start, end);
    }

    @Test
    public void shouldGetSensorAlertsThrowException() {
        LocalDateTime start = LocalDateTime.now().withNano(0).minusMinutes(1);
        LocalDateTime end = LocalDateTime.now().withNano(0).minusMinutes(5);
        AlertDTO alertDTO = null;
        try {
            when(measurementService.findMeasurementsBySensorStatusWithinPeriod(SensorStatus.ALERT, start, end))
                .thenThrow(new EntityNotFoundException("No measurement found!"));
            alertDTO = alertService.getSensorAlerts(start, end);
        } catch (EntityNotFoundException exception) {
            assertThat(exception).isNotNull();
        }
        assertThat(alertDTO).isNull();
    }
}
