package com.example.course_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.course_service.entity.Course;
import com.example.course_service.repository.CourseRepository;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository repository;

    public CourseController(CourseRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return repository.save(course);
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Course not found"));
    }
}