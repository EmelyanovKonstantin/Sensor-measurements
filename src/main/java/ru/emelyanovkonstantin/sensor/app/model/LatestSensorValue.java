package ru.emelyanovkonstantin.sensor.app.model;

/**
 * Simple JavaBean object that represent a LatestSensorValue
 */
public interface LatestSensorValue {

    int getSensorId();

    long getTime();

    double getValue();
}