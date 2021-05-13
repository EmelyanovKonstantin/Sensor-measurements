package ru.emelyanovkonstantin.sensor.api.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Filter DTO for {@link MeasurementDto}
 */
@Getter
@Setter
public class MeasurementFilterDto {

    private Integer objectId;

    private Integer sensorId;

    private Long from;

    private Long to;
}
