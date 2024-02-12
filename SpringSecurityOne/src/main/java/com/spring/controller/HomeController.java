package com.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entity.Employee;
import com.spring.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	EmployeeServiceImpl employeeServiceImpl;
	
	@GetMapping("/users")
	public String getAllUsers() {
		return "Users";
	}
	
	@PostMapping("create")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeServiceImpl.createEmployee(employee);
	}
	
	@GetMapping("allEmp")
	public List<Employee> findAllEmployees(){
		return employeeServiceImpl.findAllEmployees();
	}
	
	@GetMapping("/emp/{id}")
	public Optional<Employee> findEmpById(@PathVariable("id") Long id){
		return employeeServiceImpl.findEmployeeById(id);
	}
}
