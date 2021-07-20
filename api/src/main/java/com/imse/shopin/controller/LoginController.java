package com.imse.shopin.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imse.shopin.model.Cart;
import com.imse.shopin.model.Employee;
import com.imse.shopin.model.Login;
import com.imse.shopin.model.User;
import com.imse.shopin.mongo.MongoEmployee;
import com.imse.shopin.mongo.MongoUser;
import com.imse.shopin.mongoService.MongoEmployeeService;
import com.imse.shopin.mongoService.MongoUserService;
import com.imse.shopin.service.CartService;
import com.imse.shopin.service.EmployeeService;
import com.imse.shopin.service.ItemService;
import com.imse.shopin.service.LoginService;
import com.imse.shopin.service.UserService;

@RestController
public class LoginController {

	@Autowired
	UserService service; 
	
	@Autowired
	CartService cartService;
	
	@Autowired
	LoginService loginService; 
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	Migrator migrator; 
	
	@Autowired
	MongoUserService mongoUserService; 
	
	@Autowired
	MongoEmployeeService mongoEmployeeService; 
	
	@CrossOrigin
	@PostMapping("/register/{email}/{password}/{name}")
	public void register(@PathVariable String email, @PathVariable String password, @PathVariable String name){
		
		if(!migrator.isMigrated()) {
			if(loginService.getLoginByEmail(email) == null) {
			
				User newUser = new User(name, false);
				Login newLogin = new Login(newUser, email, password); 
				Cart newCart = new Cart(newUser);
				loginService.saveLogin(newLogin);
				cartService.saveCart(newCart);
			}
		}else {
			if(mongoUserService.getMongoUserByMail(email) == null) {
				MongoUser newMongoUser = new MongoUser(email, password, name);
				mongoUserService.insertMongoUser(newMongoUser);
			}
		}
		
	}
	
	
	
	@CrossOrigin
	@Transactional
	@GetMapping("/login/{email}/{password}")
	public String login(@PathVariable  String email,@PathVariable String password){		
		if(!migrator.isMigrated())	{
			Login login = loginService.getLoginByEmail(email);
			if(login != null) {
				if(!login.getPassword().equals(password)) 
					return "Password doesn't match Email!"; 
		
				if(!login.isEmployee()) {
					if(service.getCurrentLogin() == null) {
						service.updateUser(loginService.getLoginByEmail(email).getUser().getUserId(), true);
						service.setCurrentLogin(login.getUser());
						return "User successfully LogedIn!";
					}
					else return "User Already LogedIn!";
				}
				else if(login.isEmployee()) {
					if(employeeService.getCurrentLogin() == null) {
						employeeService.setCurrentEmployee(login.getEmployee());
						return "Employee successfully LogedIn!";
					}else return "Employee already LogedIn";
				}
			}
		}else {
			if(mongoUserService.getMongoUserByMail(email) != null) {
				MongoUser mongoUser = mongoUserService.getMongoUserByMail(email);
				if(mongoUser.getEmail().equals(email) && mongoUser.getPassword().equals(password)) {
					mongoUser.setLogedIn(true);
					mongoUserService.setCurrentMongoUser(mongoUser);
					mongoUserService.updateMongoUser(mongoUser);
					
					return "User successfully LogedIn!";
				}else 
					return "Password doesn't match Email!";
			}else if(mongoEmployeeService.getCurrentEmployee() == null && mongoEmployeeService.findByEmail(email) != null) {
				mongoEmployeeService.setCurrentEmployee(mongoEmployeeService.findByEmail(email));
				return "Mongo Admin logged In!";
			}
		}
		
		return "User/Employee not registered!";
	}
	
	@CrossOrigin
	@GetMapping("/isUserLogedIn")
	public boolean isUserLoggedIn() {
		return service.getCurrentLogin() != null || mongoUserService.getCurrentMongoUser() != null;
	}
	
	@CrossOrigin
	@PostMapping("/logout")
	public String logout() {
		if(migrator.isMigrated()) {
			MongoUser logedIn = mongoUserService.getCurrentMongoUser();
			
			if(logedIn != null) {
				logedIn.setLogedIn(false);
				mongoUserService.setCurrentMongoUser(null);
				mongoUserService.updateMongoUser(logedIn);
			}
			
			MongoEmployee emp = mongoEmployeeService.getCurrentEmployee(); 
			
			if(emp != null) {
				mongoEmployeeService.setCurrentEmployee(null);
			}
			
			return "Employee Logged out";
		}
		
		User currUser = service.getCurrentLogin();
		Employee currEmployee = employeeService.getCurrentLogin();
		
		if(currUser == null && currEmployee == null) return "no user Logged in!";

		if(currUser != null) {
			service.updateUser(currUser.getUserId(), false);
			service.setCurrentLogin(null);
		}
		
		if(currEmployee != null) employeeService.setCurrentEmployee(null);
		
		return "Logged out succesfully";		
	}
	
	
}



