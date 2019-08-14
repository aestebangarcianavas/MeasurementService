package com.measurement.collector.data.transfer;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

/**
 * Data object that contains the information related to the metrics collected from a sensor.
 *
 * @author  Ana Esteban Garcia-Navas(<a href="mailto:ramoni.esteban@gmail.com">ramoni</a>)
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetricsDTO {

    @NotNull(message = "The sensor name can not be null!")
    private String sensorName;
    @NotNull(message = "The maximal value collected within a period of time!")
    private Long maxValue;
    @NotNull(message = "The average value collected within a period of time!")
    private Double averageValue;
    @NotNull(message = "The start time can not be null!")
    private ZonedDateTime startTime;
    @NotNull(message = "The end time can not be null!")
    private ZonedDateTime endTime;
}
