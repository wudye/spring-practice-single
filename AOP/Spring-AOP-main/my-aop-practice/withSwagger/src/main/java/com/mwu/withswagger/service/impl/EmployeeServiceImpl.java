package com.mwu.withswagger.service.impl;
import java.util.List;
import java.util.Optional;

import com.mwu.withswagger.model.Employee;
import com.mwu.withswagger.repo.EmployeeRepo;
import com.mwu.withswagger.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mwu.withswagger.exception.InvalidInputException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public Employee addEmployee(Employee emp) {
		Employee addEmp = employeeRepo.save(emp);
		return addEmp;
	}

	@Override
	public List<Employee> getAllEmployee() {
		System.out.println("Get all employee method...");
		try {
			List<Employee> getAllEmps = employeeRepo.findAll();
			return getAllEmps;
		}
		catch(Exception e) {
			throw new InvalidInputException("No employees found ");
		}
		
	}

	@Override
	public ResponseEntity<?> updateEmployee(int id, Employee newEmployee) {
		
			Optional<Employee> getEmpById = employeeRepo.findById(id);
			if(getEmpById.isEmpty()) {
				throw new InvalidInputException("Employee not found");
			}
//			if(getEmpById.isPresent()) {
				Employee employee2 = getEmpById.get();
				employee2.setName(newEmployee.getName());
				employee2.setAddress(newEmployee.getAddress());
				employee2.setDept(newEmployee.getDept());
				employee2.setSalary(newEmployee.getSalary());
				Employee save = employeeRepo.save(employee2);
				return ResponseEntity.ok().body(save);
				
//			}
		
//		return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
	}
	
	@Override
	public ResponseEntity<String> deleteEmployee(int id) {
		Optional<Employee> deleteEmpById = employeeRepo.findById(id);
		if(deleteEmpById.isEmpty())
		{
			throw new InvalidInputException("Employee not found");
		}
//		if(deleteEmpById.isPresent()) {
			employeeRepo.deleteById(id);
			return new ResponseEntity<>("Employee deleted successful", HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
	}
	
	@Override
	public ResponseEntity<Employee> getEmployeeById(int id) {
		Optional<Employee> getEmpById = employeeRepo.findById(id);
		if(getEmpById.isEmpty())
		{
			throw new InvalidInputException("Employee not found");
		}
//		if(getEmpById.isPresent()) {
			return ResponseEntity.ok().body(getEmpById.get());
//		}
//		return ResponseEntity.notFound().build();
	}
	

}
