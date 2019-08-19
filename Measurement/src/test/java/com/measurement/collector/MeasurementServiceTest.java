package com.measurement.collector;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.measurement.datamodel.SensorStatus;
import com.measurement.exception.EntityNotFoundException;
import com.measurement.persistence.entities.MeasurementDO;
import com.measurement.persistence.repository.MeasurementRepository;
import com.measurement.service.MeasurementService;
import com.measurement.service.MeasurementServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import({ MeasurementServiceImpl.class })
public class MeasurementServiceTest {

    private static final MeasurementDO measurement1 = new MeasurementDO();
    private static final MeasurementDO measurement2 = new MeasurementDO();
    private static final MeasurementDO measurement3 = new MeasurementDO();
    private static final MeasurementDO measurement4 = new MeasurementDO();
    private static final MeasurementDO measurement5 = new MeasurementDO();

    public static final String SENSOR_NAME = "sensor1";

    static {
        measurement1.setId(190000000L);
        measurement1.setMeasurementTime(LocalDateTime.now().minusMinutes(40));
        measurement1.setCo2(1000L);
        measurement1.setSensorName(SENSOR_NAME);
        measurement1.setSensorStatus(SensorStatus.OK);

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

        measurement4.setId(190000003L);
        measurement4.setMeasurementTime(LocalDateTime.now().withNano(0).minusMinutes(1));
        measurement4.setCo2(2100L);
        measurement4.setSensorName(SENSOR_NAME);
        measurement4.setSensorStatus(SensorStatus.WARN);

        measurement5.setId(190000004L);
        measurement5.setMeasurementTime(LocalDateTime.now().withNano(0));
        measurement5.setCo2(2200L);
        measurement5.setSensorName(SENSOR_NAME);
        measurement5.setSensorStatus(SensorStatus.ALERT);
    }

    @MockBean
    MeasurementRepository measurementRepository;

    @Autowired
    private MeasurementService measurementServiceToTest;

    @Test
    public void shouldCreateFirstMeasurement() {
        when(measurementRepository.findAllBySensorNameAndMeasurementTimeAfterOrderByMeasurementTimeDesc(
                    Mockito.anyString(), Mockito.any()))
            .thenReturn(new ArrayList<>());
        when(measurementRepository.save(Mockito.any())).thenReturn(measurement1);
        MeasurementDO measurementDO = measurementServiceToTest.createMeasurement(measurement1);
        assertThat(measurementDO).isNotNull();
        assertThat(measurementDO.getSensorStatus()).isEqualByComparingTo(SensorStatus.OK);
        verify(measurementRepository).save(measurement1);
    }

    @Test
    public void shouldCreateMeasurementWithWarn() {
        List<MeasurementDO> measurementDOList = new ArrayList<>();
        measurementDOList.add(measurement1);
        when(measurementRepository.findAllBySensorNameAndMeasurementTimeAfterOrderByMeasurementTimeDesc(
                    Mockito.anyString(), Mockito.any()))
            .thenReturn(measurementDOList);
        when(measurementRepository.save(Mockito.any())).thenReturn(measurement2);
        MeasurementDO measurementDO = measurementServiceToTest.createMeasurement(measurement2);
        assertThat(measurementDO).isNotNull();
        assertThat(measurementDO.getSensorStatus()).isEqualByComparingTo(SensorStatus.WARN);
        verify(measurementRepository).save(measurement2);
    }

    @Test
    public void shouldCreateMeasurementWithAlert() {
        List<MeasurementDO> measurementDOList = new ArrayList<>();
        measurementDOList.add(measurement4);
        measurementDOList.add(measurement2);
        measurementDOList.add(measurement3);
        when(measurementRepository.findAllBySensorNameAndMeasurementTimeAfterOrderByMeasurementTimeDesc(
                    Mockito.anyString(), Mockito.any()))
            .thenReturn(measurementDOList);
        when(measurementRepository.save(Mockito.any())).thenReturn(measurement5);
        MeasurementDO measurementDO = measurementServiceToTest.createMeasurement(measurement5);
        assertThat(measurementDO).isNotNull();
        assertThat(measurementDO.getSensorStatus()).isEqualByComparingTo(SensorStatus.ALERT);
        verify(measurementRepository).save(measurement5);
    }

    @Test
    public void shoulGetMeasurement() throws EntityNotFoundException {
        LocalDateTime time = LocalDateTime.now().withNano(0);
        when(measurementRepository.findBySensorNameAndMeasurementTime(Mockito.anyString(), Mockito.any())).thenReturn(
            measurement1);
        MeasurementDO measurementDO = measurementServiceToTest.getMeasurement("sensor1", time);
        assertThat(measurementDO).isNotNull();
        verify(measurementRepository).findBySensorNameAndMeasurementTime(Mockito.anyString(), Mockito.any());
    }

    @Test
    public void shoulFindMeasurementsBySensorFromDate() throws EntityNotFoundException {
        LocalDateTime time = LocalDateTime.now().withNano(0);
        List<MeasurementDO> returnedList = new ArrayList<>();
        returnedList.add(measurement1);
        when(measurementRepository.findBySensorName(Mockito.anyString()))
            .thenReturn(returnedList);
        List<MeasurementDO> measurementDOList = measurementServiceToTest.findMeasurementsBySensorName("sensor1");
        assertThat(measurementDOList).isNotNull();
        assertThat(measurementDOList.size()).isNotNull();
        verify(measurementRepository).findBySensorName(Mockito.anyString());
    }

    @Test
    public void shoulGetCurrentSensorStatus() throws EntityNotFoundException {
        List<MeasurementDO> returnedList = new ArrayList<>();
        returnedList.add(measurement1);
        when(measurementRepository.findBySensorNameOrderByMeasurementTimeDesc(Mockito.anyString())).thenReturn(
            returnedList);
        MeasurementDO measurementDO = measurementServiceToTest.getCurrentSensorStatus("sensor1");
        assertThat(measurementDO).isNotNull();
        verify(measurementRepository).findBySensorNameOrderByMeasurementTimeDesc(Mockito.anyString());
    }

    @Test
    public void shoulFindMeasurementBySensorWithinPeriod() throws EntityNotFoundException {
        LocalDateTime start = LocalDateTime.now().withNano(0).minusDays(30);
        LocalDateTime end = LocalDateTime.now().withNano(0);
        List<MeasurementDO> returnedList = new ArrayList<>();
        returnedList.add(measurement1);
        when(measurementRepository.findAllBySensorNameAndMeasurementTimeBetween(Mockito.anyString(), Mockito.any(),
                    Mockito.any())).thenReturn(returnedList);
        List<MeasurementDO> measurementDOList = measurementServiceToTest.findMeasurementBySensorNameWithinPeriod(
                "sensor1", start, end);
        assertThat(measurementDOList).isNotNull();
        assertThat(measurementDOList.size()).isNotNull();
        verify(measurementRepository).findAllBySensorNameAndMeasurementTimeBetween(Mockito.anyString(), Mockito.any(),
            Mockito.any());
    }

    @Test
    public void shoulFindMeasurementsBySensorStatusWithinPeriod() throws EntityNotFoundException {
        LocalDateTime start = LocalDateTime.now().withNano(0).minusDays(30);
        LocalDateTime end = LocalDateTime.now().withNano(0);
        List<MeasurementDO> returnedList = new ArrayList<>();
        returnedList.add(measurement1);
        when(measurementRepository.findAllBySensorStatusAndMeasurementTimeBetween(Mockito.any(), Mockito.any(),
                    Mockito.any())).thenReturn(returnedList);
        List<MeasurementDO> measurementDOList = measurementServiceToTest.findMeasurementsBySensorStatusWithinPeriod(
                SensorStatus.OK, start, end);
        assertThat(measurementDOList).isNotNull();
        assertThat(measurementDOList.size()).isNotNull();
        verify(measurementRepository).findAllBySensorStatusAndMeasurementTimeBetween(Mockito.any(), Mockito.any(),
            Mockito.any());
    }
}
