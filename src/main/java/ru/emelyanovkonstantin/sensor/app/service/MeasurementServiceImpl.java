package ru.emelyanovkonstantin.sensor.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.emelyanovkonstantin.sensor.app.dao.MeasurementRepository;
import ru.emelyanovkonstantin.sensor.app.model.AverageObjectValue;
import ru.emelyanovkonstantin.sensor.app.model.LatestSensorValue;
import ru.emelyanovkonstantin.sensor.app.model.Measurement;
import ru.emelyanovkonstantin.sensor.app.model.MeasurementFilter;

import java.util.List;

/**
 * Implementation of {@link MeasurementService} interface
 *
 * @version 1.0
 */
@RequiredArgsConstructor
@Service
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository repository;

    public void saveAll(List<Measurement> list) {
        repository.saveAll(list);
    }

    @Override
    public List<Double> getObjectAndSensorMeasurements(MeasurementFilter filter) {
        return repository.getObjectAndSensorMeasurements(filter);
    }

    @Override
    public List<LatestSensorValue> getLatestSensorValues(int objectId) {
        return repository.getLatestSensorValues(objectId);
    }

    @Override
    public List<AverageObjectValue> getAverageObjectValues() {
        return repository.getAverageObjectValues();
    }
}