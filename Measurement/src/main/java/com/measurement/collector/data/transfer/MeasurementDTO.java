package com.measurement.collector.data.transfer;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;

/**
 * Data object that contains all information related to the measurement.
 *
 * @author  Ana Esteban Garcia-Navas(<a href="mailto:ramoni.esteban@gmail.com">ramoni</a>)
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeasurementDTO extends Base {

    @NotNull(message = "The id can not be null!")
    private Long id;
    @NotNull(message = "The co2 can not be null!")
    private Long co2;
    @NotNull(message = "The id can not be null!")
    private ZonedDateTime measurementTime;
    @NotNull(message = "The name of the sensor can not be null!")
    private Long sensorId;
}
