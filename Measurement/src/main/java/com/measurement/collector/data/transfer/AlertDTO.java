package com.measurement.collector.data.transfer;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * Data object transfer to provide the information related to the alerts.
 *
 * @author  Ana Esteban Garcia-Navas(<a href="mailto:ramoni.esteban@gmail.com">ramoni</a>)
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlertDTO extends Base {

    @NotNull(message = "The start of the period can not be null!")
    private ZonedDateTime startPeriod;

    @NotNull(message = "The end of the period can not be null!")
    private ZonedDateTime endPeriod;

    private List<MeasurementDTO> alertMeasurements;
}
