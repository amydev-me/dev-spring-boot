package com.iamcoda.springboot.cruddemo.service;

import com.iamcoda.springboot.cruddemo.dao.EmployeeRepository;
import com.iamcoda.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository){
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    @Override
    public Employee findById(int employeeId) {
        Optional<Employee> result = employeeRepository.findById(employeeId);
        Employee theEmployee = null;
        if (result.isPresent()){
            theEmployee = result.get();
        }else{
            throw new RuntimeException("Did not find employee id - " + employeeId);
        }
        return theEmployee;
    }


    @Override
    public Employee save(Employee theEmployee) {
        Employee employee =  employeeRepository.save(theEmployee);
        return employee;
    }

    @Override
    public void deleteById(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
