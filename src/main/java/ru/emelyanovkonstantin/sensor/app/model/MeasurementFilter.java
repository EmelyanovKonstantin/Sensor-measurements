package ru.emelyanovkonstantin.sensor.app.model;

import lombok.Builder;
import lombok.Data;

/**
 * Simple JavaBean object that represent a MeasurementFilter
 */
@Data
@Builder
public class MeasurementFilter {

    Integer objectId;

    Integer sensorId;

    Long timestampFrom;

    Long timestampTo;
}
