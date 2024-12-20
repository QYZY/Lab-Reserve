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
    @Autowired
    private CourseRepository courseRepository;


    @Test
    public void testAddReservation() {
        User user = userRepository.findById(2L).get();
        Lab lab = labRepository.findById(1L).get();
        Course course = courseRepository.findById(3L).get();
        Period period = periodRepository.findById(1).get();

        Reservation reservation = Reservation.builder()
                .weekNumber(1)
                .weekDay(1)
                .status(ReservationStatus.PENDING)
                .lab(lab)
                .course(course)
                .period(period)
                .build();
        reservationRepository.save(reservation);


    }

    @Test
    public void testInitReservation() {
        Reservation[] reservations = new Reservation[]{
                Reservation.builder()
                        .weekNumber(1)
                        .weekDay(1)
                        .status(ReservationStatus.PENDING)
                        .lab(labRepository.findById(1L).get())
                        .course(courseRepository.findById(1L).get())
                        .period(periodRepository.findById(1).get())
                        .build(),
                Reservation.builder()
                        .weekNumber(1)
                        .weekDay(1)
                        .status(ReservationStatus.PENDING)
                        .lab(labRepository.findById(1L).get())
                        .course(courseRepository.findById(2L).get())
                        .period(periodRepository.findById(2).get())
                        .build(),
                Reservation.builder()
                        .weekNumber(1)
                        .weekDay(1)
                        .status(ReservationStatus.PENDING)
                        .lab(labRepository.findById(1L).get())
                        .course(courseRepository.findById(2L).get())
                        .period(periodRepository.findById(4).get())
                        .build(),
                Reservation.builder()
                        .weekNumber(1)
                        .weekDay(2)
                        .status(ReservationStatus.PENDING)
                        .lab(labRepository.findById(1L).get())
                        .course(courseRepository.findById(2L).get())
                        .period(periodRepository.findById(1).get())
                        .build(),
                Reservation.builder()
                        .weekNumber(1)
                        .weekDay(2)
                        .status(ReservationStatus.PENDING)
                        .lab(labRepository.findById(1L).get())
                        .course(courseRepository.findById(3L).get())
                        .period(periodRepository.findById(3).get())
                        .build(),
                Reservation.builder()
                        .weekNumber(1)
                        .weekDay(2)
                        .status(ReservationStatus.PENDING)
                        .lab(labRepository.findById(1L).get())
                        .course(courseRepository.findById(2L).get())
                        .period(periodRepository.findById(4).get())
                        .build(),
        };
        for (Reservation reservation : reservations) {
            reservationRepository.save(reservation);
        }
    }

}