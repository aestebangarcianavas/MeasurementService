package com.measurement.collector.persistence.repository;

import com.measurement.collector.persistence.entities.SensorDO;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface to access to the data contained within the measurement table.
 *
 * @author  Ana Esteban Garcia-Navas(<a href="mailto:ramoni.esteban@gmail.com">ramoni</a>)
 */
public interface SensorRepository extends CrudRepository<SensorDO, Long> { }
