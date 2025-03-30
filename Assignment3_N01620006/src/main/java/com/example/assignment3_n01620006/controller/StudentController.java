package com.example.assignment3_n01620006.controller;

import com.example.assignment3_n01620006.model.Student;
import com.example.assignment3_n01620006.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class); // Add Logger

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        logger.info("Attempting to register student: {}", student.getEmail()); // Log registration attempt
        Student savedStudent = studentService.saveStudent(student);
        logger.info("Student registered successfully: {}", savedStudent.getId()); // Log success
        return ResponseEntity.ok(savedStudent);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        logger.info("Fetching all students"); // Log fetching event
        return ResponseEntity.ok(studentService.getAllStudents());
    }
}


