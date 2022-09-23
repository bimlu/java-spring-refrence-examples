package com.example.studenttask.controllers;

import com.example.studenttask.entities.Student;
import com.example.studenttask.repos.StudentRepository;
import io.swagger.v3.oas.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/students")
    @Operation(description = "List all the students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    @Operation(description = "Get student by id")
    @Parameter(name = "id", description = "ID of the student")
    public Student getStudent(@PathVariable("id") Long id) {
       return studentRepository.findById(id).get();
    }

    @PostMapping("/students")
    @Operation(description = "Create a new student")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @DeleteMapping("/students/{id}")
    @Operation(description = "Delete a student")
    @Parameter(name = "id", description = "ID of the student")
    public void deleteStudent(@PathVariable("id") Long id) {
       studentRepository.deleteById(id);
    }

    @PutMapping("/students/{id}")
    @Operation(description = "Update a student")
    @Parameters({
            @Parameter(name = "id", description = "ID of the student"),
            @Parameter(name = "hi", description = "Hello") // just for testing
    })
    public Student updateStudent(@RequestBody Student request, @PathVariable("id") Long id) {
        Student student = studentRepository.findById(id).get();
        student.setRollNumber(request.getRollNumber());
        student.setMobileNumber(request.getMobileNumber());
        student.setEmail(request.getEmail());
        student.setStudentName(request.getStudentName());
        return studentRepository.save(student);
    }

}
