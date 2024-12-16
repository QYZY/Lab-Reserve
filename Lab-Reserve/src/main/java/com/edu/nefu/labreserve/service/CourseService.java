package com.edu.nefu.labreserve.service;

import com.edu.nefu.labreserve.dox.Course;
import com.edu.nefu.labreserve.dox.User;
import com.edu.nefu.labreserve.dto.CourseRequest;
import com.edu.nefu.labreserve.dto.CourseResponse;
import com.edu.nefu.labreserve.repository.CourseRepository;
import com.edu.nefu.labreserve.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    // 添加课程
    public boolean addCourse(CourseRequest courseRequest) {

        // 创建课程对象
        Course course = convertToCourse(courseRequest);
        // 保存课程
        courseRepository.save(course);

        return true;
    }

    // 更新课程
    public boolean updateCourse(Long courseId, CourseRequest courseRequest) {
        // 获取教师对象
        User teacher = userRepository.findByUsername(courseRequest.getTeacherName())
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found"));
        // 获取课程对象
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        // 更新课程信息
        existingCourse.setName(courseRequest.getName());
        existingCourse.setStuNumber(courseRequest.getStuNumber());
        existingCourse.setStudyHour(courseRequest.getStudyHour());
        existingCourse.setTeacher(teacher);
        existingCourse.setClassName(courseRequest.getClassName());
        // 保存课程
        courseRepository.save(existingCourse);

        return true;
    }

    // 删除课程
    public boolean deleteCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            return false;
        }
        courseRepository.deleteById(courseId); // 删除课程
        return true;

    }

    // 获取全部课程
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList()); // 获取所有课程
    }

    // 获取指定老师的课程
    public List<CourseResponse> getCoursesByTeacher(Long teacherId) {
        return courseRepository.findByTeacherId_Id(teacherId)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList()); // 调用 Repository 方法

    }

    public CourseResponse convertToResponse(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .stuNumber(course.getStuNumber())
                .studyHour(course.getStudyHour())
                .className(course.getClassName())
                .teacherName(course.getTeacher().getUsername())
                .build();

    }

    public Course convertToCourse(CourseRequest courseRequest) {
        User teacher = userRepository.findByUsername(courseRequest.getTeacherName())
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found"));
        return Course.builder()
                .name(courseRequest.getName())
                .stuNumber(courseRequest.getStuNumber())
                .studyHour(courseRequest.getStudyHour())
                .className(courseRequest.getClassName())
                .teacher(teacher)
                .build();

    }


}
