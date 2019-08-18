package com.measurement.mapper;

import com.measurement.data.transfer.MeasurementDTO;
import com.measurement.persistence.entities.MeasurementDO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to map the data transfer object into the data object.
 *
 * @author  Ana Esteban Garcia-Navas(<a href="mailto:ramoni.esteban@gmail.com">ramoni</a>)
 */
public class MeasurementMapper {

    public static MeasurementDO createMeasurementDOfromMeasurementDTO(MeasurementDTO measurementDTO) {
        MeasurementDO measurementDO = new MeasurementDO();
        measurementDO.setCo2(measurementDTO.getCo2());
        measurementDO.setSensorName(measurementDTO.getSensorName());
        measurementDO.setSensorStatus(measurementDTO.getSensorStatus());
        measurementDO.setMeasurementTime(measurementDTO.getMeasurementTime().withNano(0));
        return measurementDO;
    }

    public static MeasurementDTO createMeasurementDTOfromMeasurementDO(MeasurementDO measurementDO) {
        MeasurementDTO measurementDTO = new MeasurementDTO();
        measurementDTO.setId(measurementDO.getId());
        measurementDTO.setCo2(measurementDO.getCo2());
        measurementDTO.setSensorName(measurementDO.getSensorName());
        measurementDTO.setMeasurementTime(measurementDO.getMeasurementTime().withNano(0));
        measurementDTO.setSensorStatus(measurementDO.getSensorStatus());
        return measurementDTO;
    }

    public static List<MeasurementDTO> createMeasurementDTOListfromMeasurementDOList(
            Collection<MeasurementDO> measurements) {
        return measurements.stream()
            .map(MeasurementMapper::createMeasurementDTOfromMeasurementDO)
            .collect(Collectors.toList());
    }
}
