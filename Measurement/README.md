# Measurement Collector
The measurement collector application provides three different REST API to store, handle, calculate metrics and identify alerts of the measurements got from different sensors.

## Core Stack / Project Setup
* java 11
* [spring 2.1](https://spring.io/)
* h2 InMemory DB
* Maven
* Jalopy format

## REST API provided

#Measurement Controller
 * Create
http://localhost:8080/v1/sensors/measurement/create
* GEt Measure
http://localhost:8080/v1/sensors/measurement?measurementDate=2019-08-18T18%3A10%3A23&sensor=sensor1
* Get measurements
http://localhost:8080/v1/sensors/measurements?sensor=sensor1
* Get status of sensor
http://localhost:8080/v1/sensors/status?sensorName=sensor1


#Alert-Controller

* Get the alerts of a sensor

http://localhost:8080/v1/sensors/alerts

#Metrics
 
* Get sensor metrics

http://localhost:8080/v1/sensors/metrics?sensorName=sensor1