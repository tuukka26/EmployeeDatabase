package fi.haagahelia.EmployeeDatabase.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	List<Employee> findByFName(@Param("fName") String fName);
	List<Employee> findByLName(@Param("lName") String lName);
}
