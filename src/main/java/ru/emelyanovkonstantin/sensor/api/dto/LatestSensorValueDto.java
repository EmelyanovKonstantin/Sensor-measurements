package ru.emelyanovkonstantin.sensor.api.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

/**
 * DTO for {@link ru.emelyanovkonstantin.sensor.app.model.LatestSensorValue}
 */
@Value
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LatestSensorValueDto {

    int sensorId;

    long time;

    double value;
}
