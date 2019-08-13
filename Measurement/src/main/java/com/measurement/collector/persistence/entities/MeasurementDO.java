package com.measurement.collector.persistence.entities;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    @NotNull(message = "license plate can not be null!")
    private Long co2;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime measuredTime = ZonedDateTime.now();
}
