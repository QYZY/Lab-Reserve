package com.edu.nefu.labreserve.service;

import com.edu.nefu.labreserve.dox.Period;
import com.edu.nefu.labreserve.repository.PeriodRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PeriodServiceTest {
    @Autowired
    private PeriodService periodService;
    @Autowired
    private PeriodRepository periodRepository;


    @Test
    void testAddPeriod() {
        Period period = Period.builder()
                .name("七八节")
                .startTime(LocalTime.of(15, 35))
                .endTime(LocalTime.of(17,10 ))
                .build();
        periodService.addPeriod(period);

    }

    @Test
    void testUpdatePeriod() {
        Period period = Period.builder()
                .name("七八节")
                .startTime(LocalTime.of(13, 40))
                .endTime(LocalTime.of(15, 15))
                .build();
        periodService.updatePeriod(3, period);

    }

    @Test
    void testDeletePeriod() {
    }

    @Test
    void testGetAllPeriods() {
        List<Period> periods = periodService.getAllPeriods();
        for (Period period : periods) {
            System.out.println(period);
        }
    }
}