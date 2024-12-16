package com.edu.nefu.labreserve.repository;

import com.edu.nefu.labreserve.dox.Period;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

@SpringBootTest
class PeriodRepositoryTest {

    @Autowired
    private PeriodRepository periodRepository;

    @Test
    void testCreatePeriod() {
        Period period = Period.builder()
                .name("一二节")
                .startTime(LocalTime.of(8,0))
                .endTime(LocalTime.of(9,35))
                .build();
        periodRepository.save(period);

    }
}