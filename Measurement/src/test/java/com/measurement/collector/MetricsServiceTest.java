package com.measurement.collector;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.measurement.data.transfer.MetricsDTO;
import com.measurement.datamodel.SensorStatus;
import com.measurement.exception.EntityNotFoundException;
import com.measurement.persistence.entities.MeasurementDO;
import com.measurement.service.MeasurementService;
import com.measurement.service.MetricService;
import com.measurement.service.MetricServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@Import({ MetricServiceImpl.class })
public class MetricsServiceTest {

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
        measurement1.setSensorStatus(SensorStatus.WARN);

        measurement2.setId(190000001L);
        measurement2.setMeasurementTime(LocalDateTime.now().withNano(0).minusMinutes(2));
        measurement2.setCo2(2000L);
        measurement2.setSensorName(SENSOR_NAME);
        measurement2.setSensorStatus(SensorStatus.WARN);

        measurement3.setId(190000002L);
        measurement3.setMeasurementTime(LocalDateTime.now().withNano(0).minusMinutes(3));
        measurement3.setCo2(2100L);
        measurement3.setSensorName(SENSOR_NAME);
        measurement3.setSensorStatus(SensorStatus.WARN);

        measurement4.setId(190000002L);
        measurement4.setMeasurementTime(LocalDateTime.now().withNano(0).minusMinutes(4));
        measurement4.setCo2(2100L);
        measurement4.setSensorName(SENSOR_NAME);
        measurement4.setSensorStatus(SensorStatus.ALERT);
    }

    @MockBean
    MeasurementService measurementService;

    @Autowired
    MetricService metricServiceToTest;

    @Test
    public void shouldGetSensorMetrics() throws EntityNotFoundException {
        List<MeasurementDO> measurementDOList = new ArrayList<>();
        measurementDOList.add(measurement1);
        measurementDOList.add(measurement2);
        measurementDOList.add(measurement3);
        measurementDOList.add(measurement4);
        when(measurementService.findMeasurementBySensorNameWithinPeriod(Mockito.anyString(), Mockito.any(), Mockito.any()))
            .thenReturn(measurementDOList);
        MetricsDTO metricsDTO = metricServiceToTest.getSensorMetrics(SENSOR_NAME);
        assertThat(metricsDTO).isNotNull();
        assertThat(metricsDTO.getMaxValue()).isEqualTo(2200L);
        assertThat(metricsDTO.getAverageValue()).isEqualTo(2100L);
        verify(measurementService).findMeasurementBySensorNameWithinPeriod(Mockito.anyString(), Mockito.any(),
            Mockito.any());
    }

    @Test
    public void shouldGetSensorMetricsThrowException() {
        MetricsDTO metricsDTO = null;
        try {
            when(measurementService.findMeasurementBySensorNameWithinPeriod(Mockito.anyString(), Mockito.any(),
                        Mockito.any()))
                .thenThrow(new EntityNotFoundException("No measurement found!"));
            metricsDTO = metricServiceToTest.getSensorMetrics(SENSOR_NAME);
        } catch (EntityNotFoundException exception) {
            assertThat(exception).isNotNull();
        }
        assertThat(metricsDTO).isNull();
    }
}
