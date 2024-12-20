package com.edu.nefu.labreserve;

import com.edu.nefu.labreserve.dox.*;
import com.edu.nefu.labreserve.repository.*;
import com.edu.nefu.labreserve.service.LabService;
import com.edu.nefu.labreserve.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

@SpringBootTest
public class Init {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LabRepository labRepository;
    @Autowired
    private PeriodRepository periodRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ReservationRepository reservationRepository;


    void initUser() {

        User[] users = new User[]{
                User.builder()
                        .username("admin")
                        .password("admin123")
                        .role(UserRole.LAB_ADMIN)
                        .build(),
                User.builder()
                        .username("test")
                        .password("test123")
                        .role(UserRole.TEACHER)
                        .build(),
                User.builder()
                        .username("qyzy")
                        .password("mima1234")
                        .role(UserRole.SUPER_ADMIN)
                        .build()
        };
        for (User user : users) {
            userService.addUser(user.getUsername(), user.getPassword());
        }
    }

    void initLab() {
        Lab[] labs = new Lab[]{
                Lab.builder()
                        .name("903")
                        .admin(userRepository.findById(1L).get())
                        .description("ACM实验室")
                        .build(),
                Lab.builder()
                        .name("910")
                        .admin(userRepository.findById(2L).get())
                        .description("大数据实验室")
                        .build(),
                Lab.builder()
                        .name("901")
                        .admin(userRepository.findById(1L).get())
                        .description("机房")
                        .build(),
                Lab.builder()
                        .name("905")
                        .admin(userRepository.findById(1L).get())
                        .description("蓝桥杯机房")
                        .build(),
        };
        for (Lab lab : labs) {
            labRepository.save(lab);
        }
    }

    void initPeriod() {
        Period[] periods = new Period[]{
                Period.builder()
                        .name("一二节")
                        .startTime(LocalTime.of(8, 0))
                        .endTime(LocalTime.of(9, 35))
                        .build(),
                Period.builder()
                        .name("三四节")
                        .startTime(LocalTime.of(10, 5))
                        .endTime(LocalTime.of(11, 40))
                        .build(),
                Period.builder()
                        .name("五六节")
                        .startTime(LocalTime.of(13, 40))
                        .endTime(LocalTime.of(15, 15))
                        .build(),
                Period.builder()
                        .name("七八节")
                        .startTime(LocalTime.of(15, 35))
                        .endTime(LocalTime.of(17, 10))
                        .build()
        };
        for (Period period : periods) {
            periodRepository.save(period);
        }
    }

    void initCourse() {
        Course[] courses = new Course[]{
                Course.builder()
                        .name("web开发")
                        .stuNumber(15)
                        .studyHour(8)
                        .className("软件工程 2022-4")
                        .teacher(userRepository.findById(1L).get())
                        .build(),
                Course.builder()
                        .name("数据库原理")
                        .stuNumber(20)
                        .studyHour(10)
                        .className("计算机科学与技术 2022-1")
                        .teacher(userRepository.findById(1L).get())
                        .build(),
                Course.builder()
                        .name("软件工程")
                        .stuNumber(40)
                        .studyHour(3)
                        .className("数据科学与大数据 2023-1")
                        .teacher(userRepository.findById(3L).get())
                        .build(),
                Course.builder()
                        .name("数据结构")
                        .stuNumber(20)
                        .studyHour(10)
                        .className("软件工程 2022-4")
                        .teacher(userRepository.findById(2L).get())
                        .build(),
        };
        for (Course course : courses) {
            courseRepository.save(course);
        }
    }

    void initReservation() {
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


    @Test
    void init(){
        initUser();
        initLab();
        initPeriod();
        initCourse();
        initReservation();
    }
}

