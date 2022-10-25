package com.example.midtest.controller;

import com.example.midtest.entity.Course;
import com.example.midtest.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CourseController {
    @Autowired
    private CourseService courseService;

    //Xem danh sách tất cả khóa học (GET /api/v1/courses)
    @GetMapping("/courses")
    public List<Course> getCourses(){
        return courseService.getCourses();
    }

    //Xem danh sách khóa học online (GET /api/v1/courses/online)
    @GetMapping("/courses/online")
        public List<Course> getOnlineCourses(){
        return courseService.getOnlineCourses();
    }

    //Xem danh sách khóa học onlab (GET /api/v1/courses/onlab)
    @GetMapping("/courses/onlab")
    public List<Course> getOnlabCourses(){
        return courseService.getOnlabCourses();
    }

    //Xem thông tin của 1 khóa học cụ thể (thông tin bao gồm thông tin khóa học và nhân viên tư vấn) (GET /api/v1/courses/{id})
    @GetMapping("/courses/{id}")
    public ResponseEntity<?> getCoursesById(@PathVariable Integer id){
        return ResponseEntity.ok(courseService.getCoursesById(id));
    }

    //Lọc khóa học (tiêu đề, chủ đề)
    @GetMapping("/courses/filter/search")
    public ResponseEntity<?> getCourseBy(@RequestParam(name = "name",required = false) String name,@RequestParam(name = "topic",required = false) String topic) {
        if(name==null || topic==null)
            return ResponseEntity.ok(courseService.getCourseByName(name,topic));
        return ResponseEntity.ok(courseService.getCourseBy(name,topic));
    }
}
