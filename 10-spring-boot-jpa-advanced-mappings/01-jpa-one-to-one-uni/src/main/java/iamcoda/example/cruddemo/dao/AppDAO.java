package iamcoda.example.cruddemo.dao;

import iamcoda.example.cruddemo.entity.Instructor;

public interface AppDAO {

    void save(Instructor instructor);

    Instructor findInstructorById(int Id);

    void deleteInstructorById(int Id);
}
