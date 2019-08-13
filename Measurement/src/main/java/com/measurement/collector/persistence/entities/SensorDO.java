package com.measurement.collector.persistence.entities;

import com.measurement.collector.datamodel.SensorStatus;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "sensor status can not be null!")
    @Enumerated(EnumType.STRING)
    private SensorStatus sensorStatus;
}
