package com.edu.nefu.labreserve.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CourseRequest implements Serializable {

    private String name; // 课程名称
    private Integer stuNumber; // 学生人数
    private Integer studyHour; // 学时
    private String className; // 班级名称
    private String teacherName; // 教师名称

}
