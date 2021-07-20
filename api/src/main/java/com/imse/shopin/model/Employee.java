package com.imse.shopin.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@Column(name="employee_id")
	private String employeeId;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;

	@JsonBackReference
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="manager_id", nullable = true)
	private Employee manager;

	@JsonManagedReference
	@OneToMany(mappedBy="manager")
	private Set<Employee> subordinates = new HashSet<Employee>();
	
	@JsonBackReference
	@OneToOne(mappedBy = "employee")
	private Login login; 

	public Employee() {}

	public Employee(String firstname, String lastname) {
		this.employeeId = UUID.randomUUID().toString();
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	
	// Getter and Setter methods

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Set<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Set<Employee> subordinates) {
		this.subordinates = subordinates;
	}

	public String getEmployeeId() {
		return employeeId;
	}
		
	
}