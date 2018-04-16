package fi.haagahelia.EmployeeDatabase.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message = "This is a required field, please enter first name")
	private String fName;
	
	@NotEmpty(message = "This is a required field, please enter last name")
	private String lName; 
	
	@NotEmpty(message = "This is a required field, please enter a valid email address")
	@Email(message = "This is a required field, please enter a valid email address")
	private String email; 
	
	@NotEmpty(message = "This is a required field, please enter address")
	private String address;
	
	@Size(min = 6, max = 15, message = "This is a required field, please enter a phone number that is between {min} and {max} characters long")
	private String phone;
	
	@NotEmpty(message = "This is a required field, please enter date of birth")
	private String dob;
	
	public Employee () {}

	public Employee(String fName, String lName, String email, String address, String phone, String dob, Department department, Office office) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.dob = dob;
		this.department = department;
		this.office = office;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="departmentId")
	private Department department;
	
	public Department getDepartment() {
		return department;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="officeId")
	private Office office;
	
	public Office getOffice() {
		return office;
	}
	
	public void setOffice(Office office) {
		this.office = office;
	}
}
