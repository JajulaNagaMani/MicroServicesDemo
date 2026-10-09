package com.example.student_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.student_service.client.Course;
import com.example.student_service.client.CourseClient;
import com.example.student_service.entity.Student;
import com.example.student_service.repository.StudentRepository;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository repository;

    private final CourseClient courseClient;

    public StudentController(
            StudentRepository repository,
            CourseClient courseClient) {

        this.repository = repository;
        this.courseClient = courseClient;
    }

    @PostMapping
    public Student createStudent(
            @RequestBody Student student) {

        return repository.save(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {

        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudent(
            @PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Student not found"));
    }

    @GetMapping("/{id}/details")
    public StudentResponse getStudentDetails(
            @PathVariable Long id) {

        Student student =
                repository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Student not found"));

        Course course =
                courseClient.getCourse(
                        student.getCourseId());

        return new StudentResponse(
                student,
                course);
    }
}