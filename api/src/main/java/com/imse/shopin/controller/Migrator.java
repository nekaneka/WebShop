package com.imse.shopin.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.imse.shopin.model.Category;
import com.imse.shopin.model.Employee;
import com.imse.shopin.model.Item;
import com.imse.shopin.model.Login;
import com.imse.shopin.model.Order;
import com.imse.shopin.model.OrderItems;
import com.imse.shopin.model.User;
import com.imse.shopin.mongo.MongoCategory;
import com.imse.shopin.mongo.MongoEmployee;
import com.imse.shopin.mongo.MongoItem;
import com.imse.shopin.mongo.MongoOrder;
import com.imse.shopin.mongo.MongoUser;
import com.imse.shopin.mongoService.MongoCategoryService;
import com.imse.shopin.mongoService.MongoEmployeeService;
import com.imse.shopin.mongoService.MongoItemService;
import com.imse.shopin.mongoService.MongoOrderService;
import com.imse.shopin.mongoService.MongoUserService;
import com.imse.shopin.service.CategoryService;
import com.imse.shopin.service.EmployeeService;
import com.imse.shopin.service.ItemService;
import com.imse.shopin.service.LoginService;
import com.imse.shopin.service.OrderItemsService;
import com.imse.shopin.service.OrderService;
import com.imse.shopin.service.UserService;

@Controller
public class Migrator {

	@Autowired
	private ItemService itemServiceSql;
	
	@Autowired
	private MongoItemService itemServiceMongo; 
	
	@Autowired
	private CategoryService categoryService; 
	
	@Autowired
	private MongoCategoryService categoryServiceMongo; 
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private MongoUserService mongoUserService; 
	
	@Autowired
	private OrderService orderService; 
	
	@Autowired
	private OrderItemsService orderItemService; 
	
	@Autowired
	private MongoOrderService orderMongoService; 
	
	@Autowired
	LoginService loginService; 
	
	@Autowired
	EmployeeService employeeService; 
	
	@Autowired
	MongoEmployeeService mongoEmployeeService;
	
	private boolean isMigrated; 
	
	public Migrator() {
		this.isMigrated = false; 
	}
	
	public void migrateItems() {
				
		// ITEM MIGRATION
		
		List<Item> itemsSql = itemServiceSql.findAllItems();
		
		System.out.println("#########################   ITEM SIZE: " + itemsSql.size());
		
		for(Item item: itemsSql) {
			itemServiceMongo.insertItem(new MongoItem(item.getId(), item.getPrice(), item.getName(), item.getDescription()));
			//itemServiceSql.removeItem(item.getId());
		}
		
		// CATEGORY MIGRATION
		
		List<Category> categories = categoryService.findAllCategories();
				
		for(Category cat: categories) {
			Set<MongoItem> categoryItems = new HashSet<MongoItem>();
				
			for(Item item: cat.getItems())
				categoryItems.add(itemServiceMongo.findById(item.getId()));
				
			categoryServiceMongo.insertMongoCategory(new MongoCategory(Integer.toString(cat.getCategoryId()), cat.getCategoryName(), cat.getDescription(), categoryItems));
		}
		
		
		// USER MIGRATION
		migrateUser();
		
		// EMPLOYEE MIGRATION
		migrateEmployee();
		
		
	}
	
	private void migrateEmployee() {
		
		for(Employee sqlEmployee: employeeService.findAll()) {
			mongoEmployeeService.insertNewEmployee(
					new MongoEmployee(
						sqlEmployee.getEmployeeId(), 
						sqlEmployee.getFirstname(),
						sqlEmployee.getLastname(),
						loginService.getLoginByEmp(sqlEmployee).getEmail(),
						loginService.getLoginByEmp(sqlEmployee).getPassword()
					));
		}
		
	}

	private void migrateUser() {
		
		List<User> sqlUsers = userService.findAllUser();
		
		for(User user: sqlUsers) {
			Login userLogin = loginService.getLoginByUser(user);
			
			MongoUser mongoUser = new MongoUser(
						user.getUserId(), 
						userLogin.getEmail(),
						userLogin.getPassword(), 
						user.getName(), 
						user.getIBAN(),
						user.getAddress(),
						user.isLogedIn()
					);
			
			Set<MongoOrder> orders= new  HashSet<MongoOrder>();
			
			List<Order> userOrders = orderService.findOrderbyuser(user);
			
			for(Order order: userOrders) {
				double total = 0.;
				List<OrderItems> orderItems = orderItemService.findOrderItemByOrder(order);
				
				HashMap<String, Integer> mongoOrderItems = new HashMap<>();
				
				for(OrderItems orderItem: orderItems) {
					mongoOrderItems.put(itemServiceMongo.findById(orderItem.getItem().getId()).getId(), orderItem.getQuantity());
					total += orderItem.getItem().getPrice() * orderItem.getQuantity();
				}
				
				MongoOrder mongoOrder = new MongoOrder(order.getOrderId(), order.getOrderDate(), mongoOrderItems, total);
				orders.add(mongoOrder);
				orderMongoService.insertOrder(mongoOrder);
				mongoUser.update(mongoOrder);
			}
			
			mongoUserService.insertMongoUser(mongoUser);
			
		}
		
	}

	public void changeMigrate() {
		isMigrated = true; 
	}

	public boolean isMigrated() {
		return isMigrated;
	}
	
	
	
}
