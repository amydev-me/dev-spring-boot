package iamcoda.example.cruddemo.dao;

import iamcoda.example.cruddemo.entity.Instructor;
import iamcoda.example.cruddemo.entity.InstructorDetail;

public interface AppDAO {

    void save(Instructor instructor);

    Instructor findInstructorById(int Id);

    void deleteInstructorById(int Id);

    InstructorDetail detailById(int Id);

    void deleteInstructorDetailById(int Id);
}
