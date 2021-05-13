package ru.emelyanovkonstantin.sensor.app.service;

import ru.emelyanovkonstantin.sensor.app.model.AverageObjectValue;
import ru.emelyanovkonstantin.sensor.app.model.LatestSensorValue;
import ru.emelyanovkonstantin.sensor.app.model.Measurement;
import ru.emelyanovkonstantin.sensor.app.model.MeasurementFilter;

import java.util.List;

/**
 * Service class for {@link ru.emelyanovkonstantin.sensor.app.model.Measurement}
 *
 * @version 1.0
 */
public interface MeasurementService {

    void saveAll(List<Measurement> measurements);

    List<Double> getObjectAndSensorMeasurements(MeasurementFilter filter);

    List<LatestSensorValue> getLatestSensorValues(int objectId);

    List<AverageObjectValue> getAverageObjectValues();
}
