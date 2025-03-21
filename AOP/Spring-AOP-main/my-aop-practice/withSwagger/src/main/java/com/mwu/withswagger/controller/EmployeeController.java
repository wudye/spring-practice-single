package com.mwu.withswagger.controller;

import java.util.List;
import java.util.Optional;

import com.mwu.withswagger.exception.InvalidInputException;
import com.mwu.withswagger.model.Employee;
import com.mwu.withswagger.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/emp")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/addEmp")
	public Employee addEmployee(@RequestBody Employee emp) {
		return employeeService.addEmployee(emp);	
	}

	@GetMapping("/getAllEmp")
	public List<Employee> getAllEmployees() {
		try {
			return employeeService.getAllEmployee();
		}
		catch(InvalidInputException e) {
			throw new InvalidInputException(e.getMessage());
		}
		
	}
	
	@PutMapping("/updateEmp/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable(value = "id") int id,@RequestBody Employee newEmployee) {
		try {
			return employeeService.updateEmployee(id, newEmployee);
		}
		catch(InvalidInputException e) {
			throw  new InvalidInputException(e.getMessage());
		}
			
	}
	
	@DeleteMapping("/deleteEmp/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable(value = "id") int id){
		try {
			return employeeService.deleteEmployee(id);
		}
		catch(InvalidInputException e) {
			throw  new InvalidInputException(e.getMessage());
		}
		
	}
	
	@GetMapping("/getEmpById/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable(value = "id") int id){
		try {
			return employeeService.getEmployeeById(id);		
		}
		catch(InvalidInputException e) {
			throw  new InvalidInputException(e.getMessage());
		}
	}


}
