/**
 * CREATE Script for initializing the measurement, sensor
 */

 -- Create some measurements for tests
 insert into measurement (id, measurement_time, co2, sensor_name, sensor_status) values (20000, now(), 2000, 'sensor1', 'WARN');
 insert into measurement (id, measurement_time, co2, sensor_name, sensor_status) values (20001, now(), 2100, 'sensor1', 'WARN');
 insert into measurement (id, measurement_time, co2, sensor_name, sensor_status) values (20003, now(), 2500, 'sensor1', 'ALERT');
 insert into measurement (id, measurement_time, co2, sensor_name, sensor_status) values (20004, now(), 50, 'sensor3', 'OK');
 insert into measurement (id, measurement_time, co2, sensor_name, sensor_status) values (20005, now(), 2100, 'sensor5', 'WARN');
 insert into measurement (id, measurement_time, co2, sensor_name, sensor_status) values (20006, now(), 100, 'sensor2', 'OK');
 insert into measurement (id, measurement_time, co2, sensor_name, sensor_status) values (20007, now(), 600, 'sensor5', 'OK');
 insert into measurement (id, measurement_time, co2, sensor_name, sensor_status) values (20008, now(), 700, 'sensor5', 'OK');
 insert into measurement (id, measurement_time, co2, sensor_name, sensor_status) values (20009, now(), 700, 'sensor4', 'OK');
