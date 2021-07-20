package com.imse.shopin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imse.shopin.model.CategoryReportDTO;
import com.imse.shopin.model.SalesReportDTO;
import com.imse.shopin.mongoService.MongoAdminService;
import com.imse.shopin.mongoService.MongoEmployeeService;
import com.imse.shopin.service.AdministrationService;
import com.imse.shopin.service.EmployeeService;
import com.imse.shopin.service.OrderService;

@CrossOrigin
@RestController
public class AdministrationController {

	@Autowired
	AdministrationService service; 
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	EmployeeService employeerService;

	@Autowired
	Migrator migrator;
	
	@Autowired
	MongoAdminService mongoAdminService; 
	
	@Autowired
	MongoEmployeeService mongoEmployeeService; 
	
	// Example: 2020-11-28/2021-01-09
	@CrossOrigin
	@GetMapping("/productReport/{from}/{to}")
	public List<SalesReportDTO> UserItemReport(@PathVariable String from, @PathVariable String to) {
		if(!migrator.isMigrated()) {
			if(employeerService.getCurrentLogin() != null)
				return service.createSalesRep(from, to);
		}else {
			if(mongoEmployeeService.getCurrentEmployee() != null) {
				System.out.println("sale1");
				return mongoAdminService.createSalesReportForMongo(from, to);
			}
		}
		return null;
	}
	
	
	
	@CrossOrigin
	@GetMapping("/categoryReport/{from}/{to}")
	public List<CategoryReportDTO> CategoryItemReport(@PathVariable String from, @PathVariable String to) {
		if(!migrator.isMigrated()) {
			if(employeerService.getCurrentLogin() != null)
				return service.createCategoryReport(from, to);
		}else {
			if(mongoEmployeeService.getCurrentEmployee() != null)
				return mongoAdminService.createCategoryReportForMongo(from, to);
		}
		return null;
	}
	
	
	@CrossOrigin
	@GetMapping("/isEmployeeLogedIn")
	public boolean isUserLoggedIn() {
		return employeerService.getCurrentLogin() != null || mongoEmployeeService.getCurrentEmployee() != null;
	}
	
	@CrossOrigin
	@PostMapping("/migrate")
	public void migrateData() {
		if(!migrator.isMigrated()) {
			migrator.changeMigrate();
			migrator.migrateItems();
			
			System.out.println("####   DATA MIGRATED   ####");
		}
	}
	
	@CrossOrigin
	@GetMapping("/ismigrated")
	public boolean ismigrated() {
		return migrator.isMigrated();
	}
}
