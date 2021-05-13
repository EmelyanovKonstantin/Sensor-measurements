package ru.emelyanovkonstantin.sensor.app.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Simple JavaBean object that represent a Measurement
 */
@Entity
@Table(name = "measurements", indexes = {
        @Index(name = "idx__measurements__oid_sid_time", columnList = "object_id,sensor_id,time")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Measurement {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "object_id", nullable = false, updatable = false)
    int objectId;

    @Column(name = "sensor_id", nullable = false, updatable = false)
    int sensorId;

    @Column(name = "time", nullable = false, updatable = false)
    long time;

    @Column(name = "value", nullable = false, updatable = false)
    float value;
}