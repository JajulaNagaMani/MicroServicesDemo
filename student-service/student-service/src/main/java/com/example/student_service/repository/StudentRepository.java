package com.example.student_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.student_service.entity.Student;

public interface StudentRepository
		extends JpaRepository<Student, Long> {

}