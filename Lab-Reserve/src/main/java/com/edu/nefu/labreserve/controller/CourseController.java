package com.edu.nefu.labreserve.controller;

import com.edu.nefu.labreserve.dto.CourseDTO;
import com.edu.nefu.labreserve.repository.CourseRepository;
import com.edu.nefu.labreserve.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course/")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseRepository courseRepository;

    /**
     * 添加课程
     * @param courseDTO 添加的课程信息
     * @return 添加结果
     */
    @PostMapping("add")
    @Operation(summary = "添加课程", description = "添加一门新的课程")
    public ResponseEntity<String> addCourse(@RequestBody CourseDTO courseDTO) {
        courseService.addCourse(courseDTO);
        return ResponseEntity.ok().body("添加成功");
    }

    /**
     * 更新课程信息
     * @param id 要更新的课程id
     * @param courseDTO 要更新的课程信息
     * @return 更新结果
     */
    @PutMapping("{id}")
    @Operation(summary = "更新课程", description = "根据课程ID更新课程信息")
    public ResponseEntity<String> updateCourse(@PathVariable("id") Long id, @RequestBody CourseDTO courseDTO) {
        if (!courseRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("课程不存在");
        }
        courseService.updateCourse(id, courseDTO);
        return ResponseEntity.ok().body("更新成功");
    }

    /**
     * 删除对应课程
     * @param id 要删除的课程id
     * @return 删除结果
     */
    @DeleteMapping("{id}")
    @Operation(summary = "删除课程", description = "根据课程ID删除指定的课程")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        boolean isDeleted = courseService.deleteCourse(id);
        if (isDeleted) {
            return ResponseEntity.ok().body("删除成功");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("课程不存在");
    }

    /**
     * 列出所有课程
     * @return 所有课程列表
     */
    @GetMapping("list")
    @Operation(summary = "获取全部课程", description = "返回一个包含所有课程的列表")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok().body(courseService.getAllCourses());
    }

    /**
     * 获取指定教师的所有课程
     * @param teacherId 指定教师的id
     * @return 该教师的课程列表
     */
    @GetMapping("teacher/{teacherId}")
    @Operation(summary = "获取教师课程", description = "返回指定教师的所有课程")
    public ResponseEntity<List<CourseDTO>> getCoursesByTeacher(@PathVariable Long teacherId) {
        return ResponseEntity.ok().body(courseService.getCoursesByTeacher(teacherId));
    }

}