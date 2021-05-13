package ru.emelyanovkonstantin.sensor.api;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.emelyanovkonstantin.sensor.api.dto.AverageObjectValueDto;
import ru.emelyanovkonstantin.sensor.api.dto.LatestSensorValueDto;
import ru.emelyanovkonstantin.sensor.api.dto.MeasurementDto;
import ru.emelyanovkonstantin.sensor.api.dto.MeasurementFilterDto;
import ru.emelyanovkonstantin.sensor.app.model.AverageObjectValue;
import ru.emelyanovkonstantin.sensor.app.model.LatestSensorValue;
import ru.emelyanovkonstantin.sensor.app.model.Measurement;
import ru.emelyanovkonstantin.sensor.app.model.MeasurementFilter;
import ru.emelyanovkonstantin.sensor.app.service.MeasurementService;

import javax.validation.Valid;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Controller for {@link ru.emelyanovkonstantin.sensor.app.model.Measurement}
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api")
public class MeasurementApi {

    private final MeasurementService measurementService;

    @Valid
    @PostMapping(path = "/save")
    public void save(@RequestBody List<MeasurementDto> measurements) {
        measurementService.saveAll(fromDto(measurements));
    }

    @GetMapping(path = "/history")
    public List<Double> getObjectAndSensorMeasurements(MeasurementFilterDto filter) {
        return measurementService.getObjectAndSensorMeasurements(fromDto(filter));
    }

    @GetMapping(path = "/latest")
    public List<LatestSensorValueDto> getLatestSensorValues(@RequestParam int objectId) {
        return toDto(measurementService.getLatestSensorValues(objectId), MeasurementApi::toDto);
    }

    @GetMapping(path = "/avg")
    public List<AverageObjectValueDto> getAverageObjectValues() {
        return toDto(measurementService.getAverageObjectValues(), MeasurementApi::toDto);
    }

    private static <S, T> List<T> toDto(List<S> list, Function<S, T> mapper) {
        return list.stream().map(mapper).collect(Collectors.toList());
    }

    private static LatestSensorValueDto toDto(LatestSensorValue latestSensorValue) {
        return LatestSensorValueDto.builder()
                .time(latestSensorValue.getTime())
                .sensorId(latestSensorValue.getSensorId())
                .value(latestSensorValue.getValue())
                .build();
    }

    private static AverageObjectValueDto toDto(AverageObjectValue averageObjectValue) {
        return AverageObjectValueDto.builder()
                .objectId(averageObjectValue.getObjectId())
                .averageValue(averageObjectValue.getAverageValue())
                .build();
    }

    private static List<Measurement> fromDto(List<MeasurementDto> measurements) {
        return measurements.stream().map(MeasurementApi::fromDto).collect(Collectors.toList());
    }

    private static Measurement fromDto(MeasurementDto dto) {
        return Measurement.builder()
                .sensorId(dto.getSensorId())
                .objectId(dto.getObjectId())
                .time(dto.getTime())
                .value(dto.getValue())
                .build();
    }

    private static MeasurementFilter fromDto(MeasurementFilterDto dto) {
        return MeasurementFilter.builder()
                .timestampFrom(dto.getFrom())
                .timestampTo(dto.getTo())
                .objectId(dto.getObjectId())
                .sensorId(dto.getSensorId())
                .build();
    }
}