package fi.haagahelia.EmployeeDatabase.domain;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
