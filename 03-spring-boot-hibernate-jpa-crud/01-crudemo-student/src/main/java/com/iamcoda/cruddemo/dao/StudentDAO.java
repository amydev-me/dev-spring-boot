package com.iamcoda.cruddemo.dao;

import com.iamcoda.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student theStudent);
    Student findById(Integer id);

    List<Student> getAll();

    List<Student> findByLastName(String theLastName);
}
