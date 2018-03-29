package fi.haagahelia.EmployeeDatabase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fi.haagahelia.EmployeeDatabase.domain.DepartmentRepository;
import fi.haagahelia.EmployeeDatabase.domain.Employee;
import fi.haagahelia.EmployeeDatabase.domain.EmployeeRepository;
import fi.haagahelia.EmployeeDatabase.domain.OfficeRepository;

@Controller
public class EmpController {
	
	@Autowired
	EmployeeRepository repository;
	
	@Autowired
	DepartmentRepository drepository;
	
	@Autowired
	OfficeRepository orepository;
	
	//List of all employees in the database
	@RequestMapping("/emplist")
	public String employees(Model model) {
		model.addAttribute("employees", repository.findAll());
		return "emplist";
	}
	
	//Delete an employee from database
	@RequestMapping("/delete/{id}")
	public String deleteEmp(@PathVariable("id") Long empId, Model model) {
		repository.deleteById(empId);
		return "redirect:../emplist";
	}
	
	//Edit an existing employee
	@RequestMapping("/edit/{id}")
	public String editEmp(@PathVariable("id") Long empId, Model model) {
		model.addAttribute("employee", repository.findById(empId));
		model.addAttribute("departments", drepository.findAll());
		model.addAttribute("offices", orepository.findAll());
		return "empedit";
	}
	
	//Save employee info
	@RequestMapping("/saveemp")
	public String saveEmp(Employee employee) {
		repository.save(employee);
		return "redirect:emplist";
	}
	

}
