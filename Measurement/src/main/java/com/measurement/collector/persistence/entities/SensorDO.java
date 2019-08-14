package com.measurement.collector.persistence.entities;

import com.measurement.collector.datamodel.SensorStatus;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

/**
 * Entity object that contains all information related to the sensor.
 *
 * @author  Ana Esteban Garcia-Navas(<a href="mailto:ramoni.esteban@gmail.com">ramoni</a>)
 */
@Entity
@Table(name = "sensor")
@Data
public class SensorDO {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    @NotNull(message = "sensor name can not be null!")
    private String sensorName;
    @Column(nullable = false)
    @NotNull(message = "sensor status can not be null!")
    @Enumerated(EnumType.STRING)
    private SensorStatus sensorStatus;
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime lastModificationDate = ZonedDateTime.now();

}
