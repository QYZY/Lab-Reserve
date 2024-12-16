package com.edu.nefu.labreserve.dox;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 课程ID
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer stuNumber;
    @Column(nullable = false)
    private Integer studyHour;
    @Column(nullable = false, name = "class")
    private String className;
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

}
