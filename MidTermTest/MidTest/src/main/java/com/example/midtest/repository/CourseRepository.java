package com.example.midtest.repository;

import com.example.midtest.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("Select u from Course u where u.type=?1")
    List<Course> getCourseByOn(String type);
    @Query("Select u from Course u join u.topics t where u.name = ?1 and t.name=?2 ")
    List<Course> getCourseBy(String courseName, String topicName);

    @Query("Select u from Course u join u.topics t where u.name = ?1 or t.name=?2 ")
    List<Course> getCourseByName(String courseName, String topicName);
}