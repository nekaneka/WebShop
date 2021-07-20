package com.imse.shopin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "LOGIN")
public class Login {
	
	@Id
	private String email; 
	private String password;
	
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true, nullable = true)
	private User user; 
	
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", unique = true, nullable = true)
	private Employee employee; 
	
	@Column(nullable = true)
	private boolean isEmployee;
	
	public Login() {}
	
	public Login(User user, String email, String password) {
		this.isEmployee = false;
		this.user = user;
		this.email = email; 
		this.password = password; 
	}
	
	public Login(Employee employee, String email, String password) {
		this.isEmployee = true;
		this.employee = employee;
		this.email = email; 
		this.password = password; 
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	public User getUser() {
		return this.user; 
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public boolean isEmployee() {
		return isEmployee;
	}

}
