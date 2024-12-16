package com.edu.nefu.labreserve.dto;

import com.edu.nefu.labreserve.dox.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {
    private Long id;
    private String name;
    private Integer stuNumber;
    private Integer studyHour;
    private String className;
    private String teacherName;

}
