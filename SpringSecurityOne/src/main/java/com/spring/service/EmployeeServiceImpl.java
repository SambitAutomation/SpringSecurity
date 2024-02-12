package com.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.Employee;
import com.spring.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl {

	
	@Autowired
	EmployeeRepo employeeRepo;
	
	public Employee createEmployee(Employee e) {
		Employee emp = new Employee();
		
		emp.setName(e.getName());
		emp.setPost(e.getPost());
		
		return employeeRepo.save(emp);
	}
	
	public List<Employee> findAllEmployees(){
		return employeeRepo.findAll();
	}
	
	public Optional<Employee> findEmployeeById(Long id) {
		Optional<Employee> emp =  employeeRepo.findById(id);
		return emp;
	}
}
