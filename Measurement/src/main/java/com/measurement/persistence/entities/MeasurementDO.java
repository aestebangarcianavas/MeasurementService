package com.measurement.persistence.entities;

import com.measurement.datamodel.SensorStatus;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;

/**
 * Entity object that contains all information related to the measurement.
 *
 * @author  Ana Esteban Garcia-Navas(<a href="mailto:ramoni.esteban@gmail.com">ramoni</a>)
 */
@Entity
@Table(name = "measurement")
@Data
public class MeasurementDO {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "C02 can not be null!")
    private Long co2;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime measurementTime = LocalDateTime.now().withNano(0);

    @Column(nullable = false)
    @NotNull(message = "The sensor can not be null!")
    private String sensorName;

    @NotNull(message = "sensor status can not be null!")
    @Enumerated(EnumType.STRING)
    private SensorStatus sensorStatus;
}
