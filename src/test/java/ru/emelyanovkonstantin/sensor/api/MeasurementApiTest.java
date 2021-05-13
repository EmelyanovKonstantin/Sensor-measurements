package ru.emelyanovkonstantin.sensor.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import ru.emelyanovkonstantin.sensor.api.dto.*;
import ru.emelyanovkonstantin.sensor.app.dao.MeasurementRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts="/test.sql")
public class MeasurementApiTest {

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    private MeasurementApi api;

    @Autowired
    private MeasurementRepository repository;

    private MockMvc mvc;

    @BeforeEach
    public void onBeforeEach() {
        mvc = MockMvcBuilders.standaloneSetup(api).build();
    }

    @DisplayName("Test saveAll")
    @Test
    public void saveAll() throws Exception {
        String request = jsonMapper.writeValueAsString(Collections.singletonList(MeasurementDto.builder()
                .sensorId(1)
                .objectId(2)
                .time(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()))
                .value(14.0f)
                .build()));

        mvc.perform(MockMvcRequestBuilders.post("/api/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk());

        assertThat(repository.findAll(), is(not(empty())));
    }

    @DisplayName("Test history")
    @Test
    public void history() throws Exception {
        String expectedJson = jsonMapper.writeValueAsString(Arrays.asList(
                3d,
                30d));

        mvc.perform(MockMvcRequestBuilders.get("/api/history")
                .param("sensorId","3")
                .param("objectId","1")
                .param("from","0")
                .param("to","21"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @DisplayName("Test latest")
    @Test
    public void latest() throws Exception {
        String expectedJson = jsonMapper.writeValueAsString(Arrays.asList(
                LatestSensorValueDto.builder()
                        .sensorId(1)
                        .time(10)
                        .value(10).build(),
                LatestSensorValueDto.builder()
                        .sensorId(2)
                        .time(10)
                        .value(20).build(),
                LatestSensorValueDto.builder()
                        .sensorId(3)
                        .time(10)
                        .value(30).build()));

        mvc.perform(MockMvcRequestBuilders.get("/api/latest")
                .param("objectId","1"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @DisplayName("Test avg")
    @Test
    public void avg() throws Exception {
        String expectedJson = jsonMapper.writeValueAsString(Arrays.asList(
                AverageObjectValueDto.builder()
                        .averageValue(20)
                        .objectId(1)
                        .build(),
                AverageObjectValueDto.builder()
                        .averageValue(50)
                        .objectId(2)
                        .build()));

        mvc.perform(MockMvcRequestBuilders.get("/api/avg"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
}