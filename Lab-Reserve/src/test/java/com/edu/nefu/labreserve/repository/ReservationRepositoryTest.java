package com.edu.nefu.labreserve.repository;

import com.edu.nefu.labreserve.dox.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
class ReservationRepositoryTest {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PeriodRepository periodRepository;
    @Autowired
    private LabRepository labRepository;

//    @Test
//    public void testAddReservation() {
//        Optional<Period> period = periodRepository.findById(Long.valueOf(1));
//        Optional<User> user = userRepository.findByUsername("miguo");
//        Optional<Lab> lab = labRepository.findByName("903");
//
//
//        Reservation reservation = Reservation.builder()
//                .course(course.get())
//                .period(period.get())
//                .lab(lab.get())
//                .weekNumber(1)
//                .weekDay(1)
//                .reservationDate(LocalDate.from(LocalDate.of(2024, 12, 15)))
//                .status(ReservationStatus.PENDING)
//                .build();
//        reservationRepository.save(reservation);
//
//    }
}