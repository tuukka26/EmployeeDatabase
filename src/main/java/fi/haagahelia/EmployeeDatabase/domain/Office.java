package fi.haagahelia.EmployeeDatabase.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Office {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long officeId;
	
	private String city, country;
	
	public Office() {}

	public Office(String city, String country) {
		super();
		this.city = city;
		this.country = country;
	}

	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "office")
	private List<Employee> employees;
	
	public List<Employee> getEmployees() {
		return employees;
	}
	
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}
