package iamcoda.example.cruddemo;

import iamcoda.example.cruddemo.dao.AppDAO;
import iamcoda.example.cruddemo.entity.Instructor;
import iamcoda.example.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {
//			createInstructor(appDAO);
//			findInstructor(appDAO);
//			deleteInstructor(appDAO);
//			instructorDetailById(appDAO);
			deleteInstructorDetail(appDAO);
		};
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 4;
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Successfully deleted");
	}

	private void instructorDetailById(AppDAO appDAO){
		int theId = 2;
		InstructorDetail instructorDetail = appDAO.detailById(theId);

		System.out.println("Instructor : " + instructorDetail.getInstructor().toString());
	}

	private void createInstructor(AppDAO appDAO){
		Instructor theInstructor =
				new Instructor("Amy", "Naing", "iamcoda@gmail.com");
		InstructorDetail instructorDetail =
				new InstructorDetail(
						"http://www.iamcoda.com/youtube",
						"I'm coda!!!!"
				);
		theInstructor.setInstructorDetail(instructorDetail);
		/**
		 * Note : this will also save the details object
		 * because of CascadeType.All
		 */
		System.out.println("Saving Instructor: " + theInstructor);
		appDAO.save(theInstructor);
		System.out.println("Done");
	}

	private void findInstructor(AppDAO appDAO){
		int theId = 1;

		Instructor instructor = appDAO.findInstructorById(theId);

		System.out.println("Instructor Detail :" + instructor.getInstructorDetail().toString());
	}

	private void deleteInstructor(AppDAO appDAO){
		int thId = 1;

		appDAO.deleteInstructorById(thId);

		System.out.println("Successfully deleted");

	}
}
