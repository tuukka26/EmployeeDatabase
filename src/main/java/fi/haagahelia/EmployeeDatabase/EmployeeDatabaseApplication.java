package fi.haagahelia.EmployeeDatabase;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.EmployeeDatabase.domain.Department;
import fi.haagahelia.EmployeeDatabase.domain.DepartmentRepository;
import fi.haagahelia.EmployeeDatabase.domain.Employee;
import fi.haagahelia.EmployeeDatabase.domain.EmployeeRepository;
import fi.haagahelia.EmployeeDatabase.domain.Office;
import fi.haagahelia.EmployeeDatabase.domain.OfficeRepository;
import fi.haagahelia.EmployeeDatabase.domain.User;
import fi.haagahelia.EmployeeDatabase.domain.UserRepository;

@SpringBootApplication
public class EmployeeDatabaseApplication {
	private static final Logger log = LoggerFactory.getLogger(EmployeeDatabaseApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EmployeeDatabaseApplication.class, args);
	}
	
	//For demonstration data purposes
	@Bean
	public CommandLineRunner demodata(EmployeeRepository repository, DepartmentRepository drepository, OfficeRepository orepository, UserRepository urepository) {
		return (args) -> {
			
			log.info("Save a few departments, employees and offices");
			Department d1 = new Department("Sales and Purchasing", "SnP");
			Department d2 = new Department("Information Technology", "IT");
			Department d3 = new Department("Finance", "Fin");
			Department d4 = new Department("Manufacturing", "MfG");
			
			drepository.save(d1);
			drepository.save(d2);
			drepository.save(d3);
			drepository.save(d4);
			
			Office o1 = new Office("Helsinki", "Finland");
			Office o2 = new Office("San Fransisco", "United States of America");
			Office o3 = new Office("London", "United Kingdom");
			
			orepository.save(o1);
			orepository.save(o2);
			orepository.save(o3);
			
			
			Employee emp1 = new Employee("Mark", "Gonzalez", "mark.gonzalez@thegonz.com", "Mannerheimintie 25 B 2, Helsinki", "+15675678899", "07/12/1970", d1, o1);
			Employee emp2 = new Employee("Brian", "Lotti", "brian.lotti@planetearth.com", "Lapinlahdenkatu 20 A 1, Helsinki", "+358400123123", "30/09/1982", d2, o1);
			Employee emp3 = new Employee("Lavar", "McBride", "lavar.mcbride@trilogy.com", "Pier 7, San Fransisco", "+19876987654", "22/03/1985", d3, o2);
			Employee emp4 = new Employee("Tom", "Penny", "tom.penny@flip.com", "Kensington High Street 5, London", "+44123412345", "05/05/1975", d4, o3);
			
			repository.save(emp1);
			repository.save(emp2);
			repository.save(emp3);
			repository.save(emp4);
			
			// Creating users: admin/admin, user/user
			User user1 = new User("user", "$2a$04$lmpTqGqAZ3I2T9lO5aHW.ejGxIoSOUlgwQvoal2xhWtn3BSLr82E.", "USER");
			User user2 = new User("admin", "$2a$04$jjqsv31kBksv0rnU84HtEONzbIEnGUQAss50vC01.a59NhN8jTm.i", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("Fetch all employees");
			for (Employee employee : repository.findAll()) {
				log.info(employee.toString());
			}
		};
	}
	
}
