package com.imse.shopin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imse.shopin.model.Employee;
import com.imse.shopin.model.Login;
import com.imse.shopin.model.User;
import com.imse.shopin.repository.ILoginRepository;

@Service
public class LoginService {

	@Autowired
	private ILoginRepository repository;
	
	public Login saveLogin(Login login) {
		return repository.save(login);
	}
	
	public List<Login> saveLogins(List<Login> logins) {
		return repository.saveAll(logins);
	}
	
	public List<Login> getLogins(){
		return repository.findAll();
	}
	
	public Login getLoginByEmail(String email) {
		return repository.findById(email).orElse(null);
	}
	
	public Login getLoginByUser(User user) {
		return repository.findByUser(user);
	}
	
	public Login getLoginByEmp(Employee emp) {
		return repository.findByemployee(emp);
	}
	
}
