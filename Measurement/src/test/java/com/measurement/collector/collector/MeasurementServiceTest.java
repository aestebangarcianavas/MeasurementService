package com.measurement.collector.collector;

import com.measurement.collector.datamodel.SensorStatus;
import com.measurement.collector.persistence.entities.MeasurementDO;
import com.measurement.collector.persistence.repository.MeasurementRepository;
import com.measurement.collector.service.MeasurementService;
import com.measurement.collector.service.MeasurementServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.assertThat;


import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@DataJpaTest
@Import({ MeasurementServiceImpl.class })
public class MeasurementServiceTest {

    private static final MeasurementDO measurement = new MeasurementDO();

    static {
        measurement.setId(1L);
        measurement.setMeasurementTime(ZonedDateTime.now().minusMinutes(1));
        measurement.setCo2(1000L);
        measurement.setSensorName("sensor1");
        measurement.setSensorStatus(SensorStatus.OK);
    }

    @MockBean
    MeasurementRepository measurementRepository;

    @Autowired
    private MeasurementService measurementServiceToTest;

    @Test
    public void shouldCreateMeasurement() {
        when(measurementRepository.findAllBySensorNameAndMeasurementTimeAfter(
                Mockito.anyString(), Mockito.any())).thenReturn(new ArrayList<>());
        when(measurementRepository.save(Mockito.any())).thenReturn(measurement);
        MeasurementDO measurementDO = measurementServiceToTest.createMeasurement(measurement);
        assertThat(measurementDO).isNotNull();
        verify(measurementRepository).save(measurement);
    }
}
