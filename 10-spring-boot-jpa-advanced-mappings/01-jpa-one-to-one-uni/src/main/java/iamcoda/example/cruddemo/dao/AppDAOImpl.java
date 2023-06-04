package iamcoda.example.cruddemo.dao;

import iamcoda.example.cruddemo.entity.Instructor;
import iamcoda.example.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AppDAOImpl implements AppDAO{
    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);

    }

    @Override
    public Instructor findInstructorById(int theId) {
         return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int Id) {
        Instructor tempInstructor = entityManager.find(Instructor.class, Id);

        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail detailById(int Id) {
        return entityManager.find(InstructorDetail.class, Id);
     }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int Id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, Id);

        // remove the associated object reference
        // break bi-directional link

        instructorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(instructorDetail);
    }
}
