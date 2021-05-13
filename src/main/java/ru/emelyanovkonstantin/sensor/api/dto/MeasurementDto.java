package ru.emelyanovkonstantin.sensor.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


/**
 *  Simple data transfer object that represent a Measurement {@link ru.emelyanovkonstantin.sensor.app.model.Measurement}
 *  * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDto {

    @NotNull
    private Integer objectId;

    @NotNull
    private Integer sensorId;

    @NotNull
    private Long time;

    @NotNull
    private Float value;
}
