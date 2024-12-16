package com.edu.nefu.labreserve.repository;

import com.edu.nefu.labreserve.dox.Course;
import com.edu.nefu.labreserve.dox.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseRepository courseRepository;


    @Test
    void testAddCourse() {
        Optional<User> user = userRepository.findByUsername("qyzy");

        Course course = Course.builder()
                .name("web开发")
                .stuNumber(20)
                .studyHour(10)
                .className("软件工程4班")
                .teacher(user.get())
                .build();
        courseRepository.save(course);
    }
}