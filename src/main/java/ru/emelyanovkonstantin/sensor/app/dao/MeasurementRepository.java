package ru.emelyanovkonstantin.sensor.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.emelyanovkonstantin.sensor.app.model.AverageObjectValue;
import ru.emelyanovkonstantin.sensor.app.model.LatestSensorValue;
import ru.emelyanovkonstantin.sensor.app.model.Measurement;
import ru.emelyanovkonstantin.sensor.app.model.MeasurementFilter;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    @Query(nativeQuery = true, value = "select\n" +
            "   m.value as value\n" +
            "from\n" +
            "   measurements m\n" +
            "where\n" +
            "   m.sensor_id = ?#{#filter.sensorId}\n" +
            "   and m.object_id = ?#{#filter.objectId}\n" +
            "   and m.time between ?#{#filter.timestampFrom} and ?#{#filter.timestampTo}")
    List<Double> getObjectAndSensorMeasurements(MeasurementFilter filter);

    @Query(nativeQuery = true, value = "select\n" +
            "    m.sensor_id as sensorId,\n" +
            "    m.time as time,\n" +
            "    m.value as value\n" +
            "from\n" +
            "    measurements m\n" +
            "    inner join (\n" +
            "        select\n" +
            "            sensor_id, max(time) as last_time\n" +
            "        from\n" +
            "            measurements\n" +
            "        where\n" +
            "            object_id = :objectId\n" +
            "        group by\n" +
            "            sensor_id\n" +
            "    ) last_measurements on m.sensor_id = last_measurements.sensor_id and m.time = last_measurements.last_time\n" +
            "where\n" +
            "    object_id = :objectId")
    List<LatestSensorValue> getLatestSensorValues(int objectId);

    @Query(nativeQuery = true, value = "select\n" +
            "    m.object_id as objectId,\n" +
            "    avg(m.value) as averageValue\n" +
            "from\n" +
            "    measurements m\n" +
            "    inner join (\n" +
            "        select\n" +
            "            object_id, sensor_id, max(time) as time\n" +
            "        from\n" +
            "            measurements\n" +
            "        group by\n" +
            "            object_id, sensor_id\n" +
            "    ) last_measurements\n" +
            "    on m.object_id = last_measurements.object_id and m.sensor_id = last_measurements.sensor_id\n" +
            "where\n" +
            "    m.time = last_measurements.time\n" +
            "group by\n" +
            "    m.object_id")
    List<AverageObjectValue> getAverageObjectValues();
}