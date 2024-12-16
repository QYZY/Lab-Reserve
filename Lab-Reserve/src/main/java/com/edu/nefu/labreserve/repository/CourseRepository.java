package com.edu.nefu.labreserve.repository;

import com.edu.nefu.labreserve.dox.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTeacherId_Id(Long teacherId);

}
