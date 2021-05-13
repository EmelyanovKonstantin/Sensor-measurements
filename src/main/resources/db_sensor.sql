CREATE TABLE measurements(
    id BIGSERIAL NOT NULL PRIMARY KEY ,
    object_id INT NOT NULL,
    sensor_id INT NOT NULL,
    time BIGINT NOT NULL,
    value DOUBLE PRECISION NOT NULL
);

CREATE INDEX index_measurement
    ON measurements (object_id, sensor_id, time);
