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

    @Test
    void testInitPeriod() {
        Period[] periods = new Period[]{
                Period.builder()
                        .name("一二节")
                        .startTime(LocalTime.of(8,0))
                        .endTime(LocalTime.of(9,35))
                        .build(),
                Period.builder()
                        .name("三四节")
                        .startTime(LocalTime.of(10,5))
                        .endTime(LocalTime.of(11,40))
                        .build(),
                Period.builder()
                        .name("五六节")
                        .startTime(LocalTime.of(13,40))
                        .endTime(LocalTime.of(15,15))
                        .build(),
                Period.builder()
                        .name("七八节")
                        .startTime(LocalTime.of(15,35))
                        .endTime(LocalTime.of(17,10))
                        .build()
        };
        for (Period period : periods) {
            periodRepository.save(period);
        }
    }
}