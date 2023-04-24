package com.iamcoda.cruddemo;

import com.iamcoda.cruddemo.dao.StudentDAO;
import com.iamcoda.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
//			createStudent(studentDAO);
//			createMultipleStudent(studentDAO);
//			readStudent(studentDAO);
//			queryForStudents(studentDAO);
			queryForFindByLastName(studentDAO);
		};
	}

	private void queryForFindByLastName(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findByLastName("Naing");
		for (Student tempStudent : students){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		List<Student> students = studentDAO.getAll();

		for (Student tempStudent : students){
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating new student object....");
		Student tempStudent1 = new Student("Om", "Ron", "omron@iamcoda.com");

		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent1);
		Integer theId = tempStudent1.getId();
		System.out.println("Saved student. Generated id : "  + theId);

		Student myStudent = studentDAO.findById(theId);
		System.out.println("Found the student "  + myStudent);

	}

	private void createMultipleStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating new student object....");
		Student tempStudent1 = new Student("Lai", "Mon", "laimon@iamcoda.com");
		Student tempStudent2 = new Student("Amy", "Pyae", "amy.pyae@iamcoda.com");
		Student tempStudent3 = new Student("Amy", "Naing", "amy.naing@iamcoda.com");

		//save the student object
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);


		// display id of the saved student
		System.out.println("Saved student. Generated id : "  + tempStudent1.getId());
		System.out.println("Saved student. Generated id : "  + tempStudent2.getId());
		System.out.println("Saved student. Generated id : "  + tempStudent3.getId());
	}

	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating new student object....");
		Student tempStudent = new Student("Paul", "Doe", "paul@iamcoda.com");

		//save the student object
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		System.out.println("Saved student. Generated id : "  + tempStudent.getId());

	}
}
