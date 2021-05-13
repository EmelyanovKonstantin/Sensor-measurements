package ru.emelyanovkonstantin.sensor.api.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

/**
 * DTO for {@link ru.emelyanovkonstantin.sensor.app.model.AverageObjectValue}
 */
@Value
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AverageObjectValueDto {

    int objectId;

    double averageValue;
}