package com.iamcoda.springboot.cruddemo.service;

import com.iamcoda.springboot.cruddemo.dao.EmployeeDAO;
import com.iamcoda.springboot.cruddemo.dao.EmployeeDAOJpaImpl;
import com.iamcoda.springboot.cruddemo.entity.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO){
        employeeDAO = theEmployeeDAO;
    }
    @Override
    public List<Employee> findAll() {
        List<Employee> employees = employeeDAO.findAll();
        return employees;
    }

    @Override
    public Employee findById(int employeeId) {
        Employee employee = employeeDAO.findById(employeeId);
        return employee;
    }

    @Transactional
    @Override
    public Employee save(Employee theEmployee) {
        Employee employee =  employeeDAO.save(theEmployee);
        return employee;
    }

    @Transactional
    @Override
    public void deleteById(int employeeId) {
        employeeDAO.deleteById(employeeId);
    }
}
