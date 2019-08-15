/**
 * CREATE Script for initializing the measurement, sensor
 */

 -- Create some measurements for tests
 insert into measurement (id, measurement_time, co2, sensor_name, sensor_status) values (10000, now(), 2000, 'sensor1', 'WARN');
 insert into measurement (id, measurement_time, co2, sensor_name, sensor_status) values (10001, now(), 2100, 'sensor1', 'WARN');
 insert into measurement (id, measurement_time, co2, sensor_name, sensor_status) values (10003, now(), 2500, 'sensor1', 'ALERT');
 insert into measurement (id, measurement_time, co2, sensor_name, sensor_status) values (10004, now(), 50, 'sensor3', 'OK');
 insert into measurement (id, measurement_time, co2, sensor_name, sensor_status) values (10005, now(), 2100, 'sensor5', 'WARN');
 insert into measurement (id, measurement_time, co2, sensor_name, sensor_status) values (10006, now(), 100, 'sensor2', 'OK');
 insert into measurement (id, measurement_time, co2, sensor_name, sensor_status) values (10007, now(), 600, 'sensor5', 'OK');
 insert into measurement (id, measurement_time, co2, sensor_name, sensor_status) values (10008, now(), 700, 'sensor5', 'OK');
 insert into measurement (id, measurement_time, co2, sensor_name, sensor_status) values (10009, now(), 700, 'sensor4', 'OK');
