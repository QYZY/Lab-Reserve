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

    @Test
    void testInitCourse() {
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
}