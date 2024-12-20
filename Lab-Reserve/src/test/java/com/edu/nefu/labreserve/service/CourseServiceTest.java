package com.edu.nefu.labreserve.service;

import com.edu.nefu.labreserve.dox.User;
import com.edu.nefu.labreserve.dto.CourseDTO;
import com.edu.nefu.labreserve.repository.CourseRepository;
import com.edu.nefu.labreserve.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;



@SpringBootTest
@Slf4j
class CourseServiceTest {
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void testAddCourse() {
        Optional<User> user = userRepository.findByUsername("qyzy");
        CourseDTO dto = CourseDTO.builder()
                .name("计算机组成原理")
                .className("软件工程 2021-01")
                .stuNumber(20)
                .studyHour(10)
                .teacherId(user.get().getId())
                .build();
        courseService.addCourse(dto);
    }

    @Test
    void testUpdateCourse() {
        Optional<User> user = userRepository.findByUsername("test");
        CourseDTO dto = CourseDTO.builder()
                .name("web前端")
                .stuNumber(20)
                .studyHour(15)
                .className("人工智能 2022-1")
                .teacherId(user.get().getId())
                .build();

        courseService.updateCourse(5L, dto);


    }

    @Test
    void testDeleteCourse() {
        Long courseId = courseRepository.findById(14L).get().getId();
        courseService.deleteCourse(courseId);

    }

    @Test
    void testGetAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        for (CourseDTO course : courses) {
            log.debug(course.toString());
        }
    }

    @Test
    void testGetCoursesByTeacher() {
        List<CourseDTO> courses = courseService.getCoursesByTeacher(1L);
        for (CourseDTO course : courses) {
            log.debug(course.toString());
        }

    }
}