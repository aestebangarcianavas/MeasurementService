package com.measurement.data.transfer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * Data object transfer to provide the information related to the alerts.
 *
 * @author  Ana Esteban Garcia-Navas(<a href="mailto:ramoni.esteban@gmail.com">ramoni</a>)
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AlertDTO extends Base {

    @NotNull(message = "The start of the period can not be null!")
    private LocalDateTime startPeriod;

    @NotNull(message = "The end of the period can not be null!")
    private LocalDateTime endPeriod;

    private List<MeasurementDTO> alertMeasurements;
}
