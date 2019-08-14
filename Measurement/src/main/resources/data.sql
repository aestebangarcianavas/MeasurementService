/**
 * CREATE Script for initializing the measurement, sensor
 */

 -- Create some measurements for tests
 insert into measurement (id, measurement_time, co2, sensor_name) values (10000, now(), 2000, 'sensor1');
 insert into measurement (id, measurement_time, co2, sensor_name) values (10001, now(), 1000, 'sensor2');
 insert into measurement (id, measurement_time, co2, sensor_name) values (10003, now(), 500, 'sensor2');
 insert into measurement (id, measurement_time, co2, sensor_name) values (10004, now(), 50, 'sensor3');
 insert into measurement (id, measurement_time, co2, sensor_name) values (10005, now(), 2100, 'sensor5');
 insert into measurement (id, measurement_time, co2, sensor_name) values (10006, now(), 100, 'sensor2');
 insert into measurement (id, measurement_time, co2, sensor_name) values (10007, now(), 600, 'sensor5');
 insert into measurement (id, measurement_time, co2, sensor_name) values (10008, now(), 700, 'sensor5');
 insert into measurement (id, measurement_time, co2, sensor_name) values (10009, now(), 700, 'sensor4');


  -- Create some sensors for tests
  insert into sensor (id, sensor_name, sensor_status, last_modification_date) values (10000, 'sensor1', 'WARN', now());
  insert into sensor (id, sensor_name, sensor_status, last_modification_date) values (10001, 'sensor2', 'OK', now());
  insert into sensor (id, sensor_name, sensor_status, last_modification_date) values (10003, 'sensor3', 'OK', now());
  insert into sensor (id, sensor_name, sensor_status, last_modification_date) values (10004, 'sensor4', 'OK', now());
  insert into sensor (id, sensor_name, sensor_status, last_modification_date) values (10005, 'sensor5', 'ALERT', now());

