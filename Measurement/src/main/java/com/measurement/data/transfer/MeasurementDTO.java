package com.measurement.data.transfer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.measurement.datamodel.SensorStatus;

import lombok.Data;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

/**
 * Data object that contains all information related to the measurement.
 *
 * @author  Ana Esteban Garcia-Navas(<a href="mailto:ramoni.esteban@gmail.com">ramoni</a>)
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MeasurementDTO extends Base {

    @NotNull(message = "The id can not be null!")
    private Long id;
    @NotNull(message = "The co2 can not be null!")
    private Long co2;
    @NotNull(message = "The id can not be null!")
    private LocalDateTime measurementTime;
    @NotNull(message = "The name of the sensor can not be null!")
    private String sensorName;
    @NotNull(message = "The status of the sensor can not be null!")
    private SensorStatus sensorStatus;
}
