package com.edu.nefu.labreserve.service;

import com.edu.nefu.labreserve.dox.Course;
import com.edu.nefu.labreserve.dox.User;
import com.edu.nefu.labreserve.dto.CourseDTO;
import com.edu.nefu.labreserve.exception.XException;
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
    public boolean addCourse(CourseDTO dto) {

        // 验证teacher是否存在

        User teacher = userRepository.findById(dto.getTeacherId()).orElse(null);
        if (teacher == null) {
            throw new XException("Teacher not found");
        }
        // 创建课程对象
        Course course = Course.builder()
                .name(dto.getName())
                .stuNumber(dto.getStuNumber())
                .studyHour(dto.getStudyHour())
                .className(dto.getClassName())
                .teacher(teacher)
                .build();

        // 保存课程T
        courseRepository.save(course);
        return true;
    }

    // 更新课程
    public boolean updateCourse(Long courseId, CourseDTO dto) {
        // 获取教师对象
        User teacher = userRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new XException("Teacher not found"));
        // 获取课程对象
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new XException("Course not found"));
        // 更新课程信息
        existingCourse.setName(dto.getName());
        existingCourse.setStuNumber(dto.getStuNumber());
        existingCourse.setStudyHour(dto.getStudyHour());
        existingCourse.setTeacher(teacher);
        existingCourse.setClassName(dto.getClassName());
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
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::convert)
                .collect(Collectors.toList()); // 获取所有课程
    }

    // 获取指定老师的课程
    public List<CourseDTO> getCoursesByTeacher(Long teacherId) {
        return courseRepository.findByTeacherId_Id(teacherId)
                .stream()
                .map(this::convert)
                .collect(Collectors.toList()); // 调用 Repository 方法

    }

    public CourseDTO convert(Course course) {
        return CourseDTO.builder()
                .id(course.getId())
                .name(course.getName())
                .stuNumber(course.getStuNumber())
                .studyHour(course.getStudyHour())
                .className(course.getClassName())
                .teacherId(course.getTeacher().getId())
                .build();

    }



}
