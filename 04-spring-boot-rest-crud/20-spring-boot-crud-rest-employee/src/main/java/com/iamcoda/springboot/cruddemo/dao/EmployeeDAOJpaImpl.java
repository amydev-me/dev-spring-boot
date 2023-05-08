package com.iamcoda.springboot.cruddemo.dao;

import com.iamcoda.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{


    // define field for entitiymanager
    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntitiyManager){
        entityManager = theEntitiyManager;
    }
    // set up constructor injection
    @Override
    public List<Employee> findAll() {
        // create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee ", Employee.class);

        //execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        //return the reults
        return employees;
    }

    @Override
    public Employee findById(int employeeId) {
        Employee theEmployee = entityManager.find(Employee.class, employeeId);
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        Employee dbEmployee = entityManager.merge(theEmployee);


        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        Employee theEmployee = entityManager.find(Employee.class, theId);
        entityManager.remove(theEmployee);
    }
}
