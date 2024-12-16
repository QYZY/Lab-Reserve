package com.edu.nefu.labreserve.service;

import com.edu.nefu.labreserve.dox.User;
import com.edu.nefu.labreserve.dto.CourseRequest;
import com.edu.nefu.labreserve.dto.CourseResponse;
import com.edu.nefu.labreserve.repository.CourseRepository;
import com.edu.nefu.labreserve.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;



@SpringBootTest
class CourseServiceTest {
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void testAddCourse() {
        Optional<User> user = userRepository.findByUsername("admin");
        CourseRequest courseRequest = CourseRequest.builder()
                .name("数据结构")
                .className("大数据2023-01")
                .stuNumber(20)
                .studyHour(10)
                .teacherName(user.get().getUsername())
                .build();
        courseService.addCourse(courseRequest);
    }

    @Test
    void testUpdateCourse() {
        Optional<User> user = userRepository.findByUsername("admin");
        CourseRequest updatedCourseRequest = CourseRequest.builder()
                .name("web前端")
                .stuNumber(20)
                .studyHour(15)
                .className("人工智能1班")
                .teacherName(user.get().getUsername())
                .build();

        courseService.updateCourse(4L, updatedCourseRequest);


    }

    @Test
    void testDeleteCourse() {
        Long courseId = courseRepository.findById(6L).get().getId();
        courseService.deleteCourse(courseId);

    }

    @Test
    void testGetAllCourses() {
        List<CourseResponse> courses = courseService.getAllCourses();
        for (CourseResponse course : courses) {
            System.out.println(course);
        }
    }

    @Test
    void testGetCoursesByTeacher() {
        List<CourseResponse> courses = courseService.getCoursesByTeacher(9L);
        for (CourseResponse course : courses) {
            System.out.println(course);
        }

    }
}