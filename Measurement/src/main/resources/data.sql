/**
 * CREATE Script for initializing the measurement, sensor
 */

 -- Create some measurements for tests
 insert into measurement (id, measurement_time, co2, sensor_id) values (10000, now(), 2000, 10000);
 insert into measurement (id, measurement_time, co2, sensor_id) values (10001, now(), 1000, 10001);
 insert into measurement (id, measurement_time, co2, sensor_id) values (10003, now(), 500, 10001);
 insert into measurement (id, measurement_time, co2, sensor_id) values (10004, now(), 50, 10003);
 insert into measurement (id, measurement_time, co2, sensor_id) values (10005, now(), 2100, 10005);
 insert into measurement (id, measurement_time, co2, sensor_id) values (10006, now(), 100, 10002);
 insert into measurement (id, measurement_time, co2, sensor_id) values (10007, now(), 600, 10005);
 insert into measurement (id, measurement_time, co2, sensor_id) values (10008, now(), 700, 10005);

  -- Create some sensors for tests
  insert into sensor (id, sensor_status) values (10000, 'WARN');
  insert into sensor (id, sensor_status) values (10001, 'OK');
  insert into sensor (id, sensor_status) values (10003, 'OK');
  insert into sensor (id, sensor_status) values (10004, 'OK');
  insert into sensor (id, sensor_status) values (10005, 'ALERT');

