package com.measurement.data.transfer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

/**
 * Data object that contains the information related to the metrics collected from a sensor.
 *
 * @author  Ana Esteban Garcia-Navas(<a href="mailto:ramoni.esteban@gmail.com">ramoni</a>)
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MetricsDTO implements Serializable {

    private static final long serialVersionUID = -1206344338093337670L;
    @NotNull(message = "The sensor name can not be null!")
    private String sensorName;
    @NotNull(message = "The maximal value collected within a period of time!")
    private Long maxValue;
    @NotNull(message = "The average value collected within a period of time!")
    private Double averageValue;
    @NotNull(message = "The start time can not be null!")
    private LocalDateTime startTime;
    @NotNull(message = "The end time can not be null!")
    private LocalDateTime endTime;
}
