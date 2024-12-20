package com.edu.nefu.labreserve.service;

import com.edu.nefu.labreserve.dto.ReservationDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class ReservationServiceTest {
    @Autowired
    private ReservationService reservationService;

    @Test
    void testAddReservation() {
        ReservationDTO reservationDTO = ReservationDTO.builder()
                .userId(2L)
                .courseId(3L)
                .labId(1L)
                .periodId(1)
                .weekNumber(1)
                .weekDay(5)
                .build();

        reservationService.addReservation(reservationDTO);
    }

    @Test
    void testCancelReservation() {
        reservationService.cancelReservation(8L);
    }


    @Test
    void getAllReservations() {
        List<ReservationDTO> reservations = reservationService.getAllReservations();
        for (ReservationDTO reservation : reservations) {
            log.info("{}", reservation);
        }
    }
}