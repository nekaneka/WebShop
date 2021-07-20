package com.imse.shopin.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imse.shopin.model.User;
import com.imse.shopin.repository.IUserRepository;

@Service
public class UserService {

	@Autowired
	private IUserRepository repository; 
	//private LoginService loginService; 
	
	private User currentLogin = null; 
	
	public User saveUser(User user) {
		return repository.save(user); 
	}
	
	public List<User> saveUsers(List<User> users) {
		return repository.saveAll(users); 
	}
	
	public User findUserById(String uuid) {
		System.out.println("Find");
		System.out.println("Email: " + uuid);
		return repository.findById(uuid).orElse(null);
	}
	
	public List<User> findAllUser(){
		return repository.findAll(); 
	}
	
	
	public void updateUser(String uuid, boolean bool) {
		User update = repository.findById(uuid).orElse(null);
		update.setLogedIn(bool);
		repository.save(update);
		System.out.println("uuid : " + update.getName() + "  Activity :    " + update.getLogin());
		//System.out.println(repository.updateUser(uuid, bool));
	}
	
	
	
	public User getCurrentLogin() {
		return currentLogin;
	}
	
	public void setCurrentLogin(User currentLogin) {
		this.currentLogin = currentLogin;
	}
		
}
