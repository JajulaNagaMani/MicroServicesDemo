package com.example.student_service.controller;

import com.example.student_service.client.Course;
import com.example.student_service.entity.Student;

public class StudentResponse {

    private Long id;

    private String name;

    private String email;

    private Long courseId;

    private String courseName;

    private String trainer;

    public StudentResponse(
            Student student,
            Course course) {

        this.id = student.getId();
        this.name = student.getName();
        this.email = student.getEmail();
        this.courseId = course.getId();
        this.courseName = course.getName();
        this.trainer = course.getTrainer();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTrainer() {
        return trainer;
    }
}