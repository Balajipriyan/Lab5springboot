package com.greatlearning.empmagement.GreatlearningEmpmanagement.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greatlearning.empmagement.GreatlearningEmpmanagement.model.Employee;
import com.greatlearning.empmagement.GreatlearningEmpmanagement.service.EmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	EmployeeService empservice;
	private boolean update =false;
	private int index;

	public EmployeeController(EmployeeService empservice) {
		super();
		this.empservice = empservice;
	}

	@GetMapping("/list")
	public String showEmpList(Model model) {
		model.addAttribute("emps", empservice.getAllEmployees());
		return "employee-list";
	}

	@GetMapping("/create-emp")
	public String createEmp(Model model) {
		model.addAttribute("employee", new Employee());
		return "employee";
	}

	@PostMapping("/saveEmp")
	public String saveEmp(Model model, @ModelAttribute("employee") Employee employee) {
		empservice.saveEmployee(employee);
		if(update)
			empservice.deleteEmployee(index);
		return "redirect:/employee/list";
	}

	@GetMapping("/delete/{id}")
	public String deleteEmp(Model model, @PathVariable int id) {
		empservice.deleteEmployee(id);
		return "redirect:/employee/list";
	}

	@GetMapping("/update/{id}")
	public String updateEmp(@PathVariable int id, Model model) {
		Employee employee = empservice.getEmployee(id);
		empservice.updateEmployee(employee);
		model.addAttribute("employee", employee);
		update = true;
		index =id;
		return "employee";
	}

}
