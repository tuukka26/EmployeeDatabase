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

@SpringBootApplication
public class EmployeeDatabaseApplication {
	private static final Logger log = LoggerFactory.getLogger(EmployeeDatabaseApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EmployeeDatabaseApplication.class, args);
	}
	
	//For demonstration data purposes
	@Bean
	public CommandLineRunner demodata(EmployeeRepository repository, DepartmentRepository drepository) {
		return (args) -> {
			
			log.info("Save a few categories and books");
			Department d1 = new Department("Sales");
			Department d2 = new Department("IT");
			Department d3 = new Department("Financial");
			Department d4 = new Department("Purchasing");
			
			drepository.save(d1);
			drepository.save(d2);
			drepository.save(d3);
			drepository.save(d4);
			
			Employee emp1 = new Employee("Mark", "Gonzalez", "mark.gonzalez@thegonz.com", "Mannerheimintie 25 B 2, Helsinki", "+15675678899", "07/12/1970", d1);
			Employee emp2 = new Employee("Brian", "Lotti", "brian.lotti@planetearth.com", "Lapinlahdenkatu 20 A 1, Helsinki", "+358400123123", "30/09/1982", d2);
			Employee emp3 = new Employee("Lavar", "McBride", "lavar.mcbride@trilogy.com", "Pier 7, San Fransisco", "+19876987654", "22/03/1985", d3);
			Employee emp4 = new Employee("Tom", "Penny", "tom.penny@flip.com", "Kensington High Street 5, London", "+44123412345", "05/05/1975", d4);
			
			repository.save(emp1);
			repository.save(emp2);
			repository.save(emp3);
			repository.save(emp4);
			
			log.info("Fetch all employees");
			for (Employee employee : repository.findAll()) {
				log.info(employee.toString());
			}
		};
	}
	
}
