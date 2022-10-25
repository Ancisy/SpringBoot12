package com.example.midtest.service;

import com.example.midtest.entity.Course;
import com.example.midtest.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequestMapping("api/v1")
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getCourses() {
        List<Course> courseList = courseRepository.findAll();
       return courseList;
    }

    public List<Course> getOnlineCourses(){
        List<Course> courseList = courseRepository.getCourseByOn("Online");
        return courseList;
    }

    public List<Course> getOnlabCourses() {
        List<Course> courseList = courseRepository.getCourseByOn("OnLab");
        return courseList;
    }

    public Optional<Course> getCoursesById(Integer id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        return courseOptional;
    }

    public List<Course> getCourseBy(String courseName,String topicName) {
        List<Course> courseList = courseRepository.getCourseBy(courseName,topicName);
        return courseList;
    }

    public List<Course> getCourseByName(String courseName,String topicName) {
        List<Course> courseList = courseRepository.getCourseByName(courseName,topicName);
        return courseList;
    }
}
