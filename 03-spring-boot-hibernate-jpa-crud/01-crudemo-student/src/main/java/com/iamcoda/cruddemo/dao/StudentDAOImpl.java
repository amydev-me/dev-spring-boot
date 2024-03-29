package com.iamcoda.cruddemo.dao;

import com.iamcoda.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{
    // define field for entity manager
    private EntityManager entityManager;
    // inject entity manager using constructor injection

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //implement save method

    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> getAll() {
        TypedQuery<Student> studentTypedQuery =  entityManager.createQuery("FROM Student ORDER BY lastName ASC", Student.class);

        return studentTypedQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        TypedQuery<Student> studentTypedQuery = entityManager.createQuery(
                                        "FROM Student WHERE lastName=:theData", Student.class);

        // set query parameters
        studentTypedQuery.setParameter("theData", theLastName);

        return studentTypedQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
//        int numRowsUpdated = entityManager.createQuery(
//                        "UPDATE Student SET lastName = 'Tester'"
//                                ).executeUpdate();

        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
//        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student where lastName='Smith'").executeUpdate();
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numRowsDeleted;
    }
}