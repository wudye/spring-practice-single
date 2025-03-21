package com.mwu.withswagger.service;
import java.util.List;
import java.util.Optional;

import com.mwu.withswagger.model.Employee;
import org.springframework.http.ResponseEntity;


public interface EmployeeService {
	
	public Employee addEmployee(Employee emp);

	public List<Employee> getAllEmployee();
	
	public ResponseEntity<?> updateEmployee(int id, Employee newEmployee);
	
	public ResponseEntity<String> deleteEmployee(int id);
	
	public ResponseEntity<Employee> getEmployeeById(int id);

}
