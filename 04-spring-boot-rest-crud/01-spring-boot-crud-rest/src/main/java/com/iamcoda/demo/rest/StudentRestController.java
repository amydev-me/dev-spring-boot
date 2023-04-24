package com.iamcoda.demo.rest;


import com.iamcoda.demo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> students;
    public StudentRestController(){
        loadData();
    }
    private void loadData(){
        students = new ArrayList<>();

        students.add(new Student("Amy", "Pyae"));
        students.add(new Student("Lai", "Mon"));
        students.add(new Student("Bae", "Mon"));
    }
    @GetMapping("/students")
    public List<Student> getStudents(){

        return students;
    }

    @GetMapping("/student/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        return students.get(studentId);
    }
}
