package com.edu.nefu.labreserve.controller;

import com.edu.nefu.labreserve.dox.Course;
import com.edu.nefu.labreserve.dto.CourseRequest;
import com.edu.nefu.labreserve.dto.CourseResponse;
import com.edu.nefu.labreserve.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    @PostMapping("/add")
    public ResponseEntity<String> addCourse(@RequestBody CourseRequest courseRequest) {
        try {
            // 调用 service 层方法，添加课程
            courseService.addCourse(courseRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("课程添加成功");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("输入数据无效");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Long id, @RequestBody CourseRequest courseRequest) {
        // 调用 service 层进行课程更新
        boolean isUpdated = courseService.updateCourse(id, courseRequest);

        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body("课程更新成功");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("课程不存在");

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        boolean isDeleted = courseService.deleteCourse(id);
        if (isDeleted) {
            return ResponseEntity.ok("删除成功");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("课程不存在");
    }
    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<CourseResponse>> getCoursesByTeacher(@PathVariable Long teacherId) {
        return ResponseEntity.ok(courseService.getCoursesByTeacher(teacherId));
    }

}
