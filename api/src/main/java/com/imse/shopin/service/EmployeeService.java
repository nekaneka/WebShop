package com.imse.shopin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imse.shopin.model.Employee;
import com.imse.shopin.repository.IEmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	IEmployeeRepository repository; 

	private Employee currentEmployee = null; 
	
	public Employee getCurrentLogin() {
		return currentEmployee;
	}
	
	public void saveAllEmployees(List<Employee> employeeList) {
		repository.saveAll(employeeList);
	}
	
	public void setCurrentEmployee(Employee currentEmployee) {
		this.currentEmployee = currentEmployee;
	}
	
	public List<Employee> findAll(){
		return repository.findAll();
	}
	
	

}
