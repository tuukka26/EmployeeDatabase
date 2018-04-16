package fi.haagahelia.EmployeeDatabase.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fi.haagahelia.EmployeeDatabase.domain.Department;
import fi.haagahelia.EmployeeDatabase.domain.DepartmentRepository;
import fi.haagahelia.EmployeeDatabase.domain.Employee;
import fi.haagahelia.EmployeeDatabase.domain.EmployeeRepository;
import fi.haagahelia.EmployeeDatabase.domain.Office;
import fi.haagahelia.EmployeeDatabase.domain.OfficeRepository;

@Controller
public class EmpController implements WebMvcConfigurer {
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/emplist").setViewName("emplist");
    }
	
	@Autowired
	EmployeeRepository repository;
	
	@Autowired
	DepartmentRepository drepository;
	
	@Autowired
	OfficeRepository orepository;
	
	//Login page
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	//Default to login
	@RequestMapping("/")
	public String start() {
		return "login";
	}
	
	//Index page
	@RequestMapping("/index")
	public String home() {
		return "index";
	}
	
	//List of employees
	@RequestMapping("/emplist")
	public String employees(Model model) {
		model.addAttribute("employees", repository.findAll());
		return "emplist";
	}
	
	//List of departments
	@RequestMapping("deptlist")
	public String departments(Model model) {
		model.addAttribute("departments", drepository.findAll());
		return "deptlist";
	}
	
	//List of offices
	@RequestMapping("officelist")
	public String offices(Model model) {
		model.addAttribute("offices", orepository.findAll());
		return "officelist";
	}
	
	//Delete an employee from database
	@RequestMapping("/delete/{id}")
	public String deleteEmp(@PathVariable("id") Long empId, Model model) {
		repository.deleteById(empId);
		return "redirect:../emplist";
	}
	
	//Delete a department
	@RequestMapping("/deletedept/{departmentId}")
	public String deleteDept(@PathVariable("departmentId") Long deptId, Model model) {
		drepository.deleteById(deptId);
		return "redirect:../deptlist";
	}
	
	//Delete an office
	@RequestMapping("/deleteoffice/{officeId}")
	public String deleteOffice(@PathVariable("officeId") Long offId, Model model) {
		orepository.deleteById(offId);
		return "redirect:../officelist";
	}
	
	//Edit existing employee
	@RequestMapping("/edit/{id}")
	public String editEmp(@PathVariable("id") Long empId, Model model) {
		model.addAttribute("employee", repository.findById(empId));
		model.addAttribute("departments", drepository.findAll());
		model.addAttribute("offices", orepository.findAll());
		return "empedit";
	}
	
	//Edit existing department
	@RequestMapping("/editdept/{departmentId}")
	public String editDept(@PathVariable("departmentId") Long deptId, Model model) {
		model.addAttribute("department", drepository.findById(deptId));
		return "deptedit";
		
	}
	
	//Edit existing office
	@RequestMapping("/editoffice/{officeId}")
	public String editOffice(@PathVariable("officeId") Long offId, Model model) {
		model.addAttribute("office", orepository.findById(offId));
		return "officeedit";
	}
	
	//Save employee info
	@RequestMapping("/saveemp")
	public String saveEmp(@Valid Employee employee, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("departments", drepository.findAll());
			model.addAttribute("offices", orepository.findAll());
			return "addemp";
		}
		
		repository.save(employee);
		return "redirect:emplist";
	}
	
	//Save department info
	@RequestMapping("/savedept")
	public String saveDept(Department department) {
		drepository.save(department);
		return "redirect:deptlist";
	}
	
	//Save office info
	@RequestMapping("/saveoffice")
	public String saveOffice(Office office) {
		orepository.save(office);
		return "redirect:officelist";
	}
	
	//Add new employee
	@RequestMapping("/addemp")
	public String addEmp(Model model) {
		model.addAttribute("employee", new Employee());
		model.addAttribute("departments", drepository.findAll());
		model.addAttribute("offices", orepository.findAll());
		return "addemp";
	}
	
	//Add new department
	@RequestMapping("/adddept")
	public String addDept(Model model) {
		model.addAttribute("department", new Department());
		return "adddept";
	}
	
	//Add new office
	@RequestMapping("/addoffice")
	public String addOffice(Model model) {
		model.addAttribute("office", new Office());
		return "addoffice";
	}
	
	//RESTful service to get all employees
	@RequestMapping(value="/employees", method=RequestMethod.GET)
	public @ResponseBody List<Employee> employeeListRest() {
		return (List<Employee>) repository.findAll();
	}

	//RESTful service to get employee by id
	@RequestMapping(value="/employee/{id}", method=RequestMethod.GET)
	public @ResponseBody Optional<Employee> findEmployeeRest(@PathVariable("id") Long empId) {
		return repository.findById(empId);
	}
	
	//RESTful service to get all departments
	@RequestMapping(value="/departments", method=RequestMethod.GET)
	public @ResponseBody List<Department> departmentListRest() {
		return (List<Department>) drepository.findAll();
	}
	
	//RESTful service to get all offices
		@RequestMapping(value="/offices", method=RequestMethod.GET)
		public @ResponseBody List<Office> officeListRest() {
			return (List<Office>) orepository.findAll();
	}
}


