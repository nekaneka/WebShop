package com.imse.shopin.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imse.shopin.model.Category;
import com.imse.shopin.model.CategoryReportDTO;
import com.imse.shopin.model.Employee;
import com.imse.shopin.model.Item;
import com.imse.shopin.model.Order;
import com.imse.shopin.model.OrderItems;
import com.imse.shopin.model.SalesReportDTO;
import com.imse.shopin.model.User;
import com.imse.shopin.repository.IEmployeeRepository;

@Service
public class AdministrationService {

	@Autowired
	IEmployeeRepository repository; 
	
	@Autowired
	OrderService orderService; 
	
	@Autowired
	OrderItemsService oiService; 
	
	@Autowired
	ItemService itemService; 
	
	@Autowired
	UserService userService;

	@Autowired
	private CategoryService categoryService;
	
	public Employee findEmployee() {
		return null;	
	}

	public List<Employee> findAllEmployees() {
		return repository.findAll();
	}
	
	public List<SalesReportDTO> createSalesRep(String from, String to) {
		List<SalesReportDTO> report = new ArrayList<SalesReportDTO>();
		Date dateFrom;
		Date dateTo;
		
		
		try {
			dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(from);
			dateTo = new SimpleDateFormat("yyyy-MM-dd").parse(to);
			
			List<User> userList = userService.findAllUser();
			
			for(User user: userList) {
				
			List<Order> userOrder = orderService.findOrderbyuser(user);
				if(userOrder.size() != 0) {	
					Map<Item, Integer> repMap = new HashMap<Item, Integer>();
					
					for(Order order: userOrder) {
						List<OrderItems> orderItems = oiService.findOrderItemByOrder(order);
						if(order.getOrderDate().after(dateFrom) && order.getOrderDate().before(dateTo))
							for(OrderItems oi: orderItems) {
								if(!repMap.containsKey(oi.getItem())) repMap.put(oi.getItem(), oi.getQuantity());
								else {
									repMap.put(oi.getItem(), repMap.get(oi.getItem()) + oi.getQuantity());
								}
							}
					}
					if(repMap.keySet().size() != 0) {
						Item item = Collections.max(repMap.entrySet(), Map.Entry.comparingByValue()).getKey();
						SalesReportDTO srDto = new SalesReportDTO(user.getName(), item.getName(), repMap.get(item));
						report.add(srDto);
					}
				}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return report; 
	}

	public List<CategoryReportDTO> createCategoryReport(String from, String to) {
		List<CategoryReportDTO> report = new ArrayList<CategoryReportDTO>();;
		Date dateFrom;
		Date dateTo;
		
		
		try {
			dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(from);
			dateTo = new SimpleDateFormat("yyyy-MM-dd").parse(to);
			
			List<Category> categoryList = categoryService.findAllCategories();
			for(Category category: categoryList) {				
				List<Order> allOrder = orderService.findAllOrders();
					Map<Item, Integer> repMap = new HashMap<Item, Integer>();
						
					for(Order order: allOrder) {
						List<OrderItems> orderItems = oiService.findOrderItemByOrder(order);
						if(order.getOrderDate().after(dateFrom) && order.getOrderDate().before(dateTo))
							for(OrderItems oi: orderItems) {
								if(!repMap.containsKey(oi.getItem()) && category.getItems().contains(oi.getItem())) 
									repMap.put(oi.getItem(), oi.getQuantity());
								else if(category.getItems().contains(oi.getItem())){
									repMap.put(oi.getItem(), repMap.get(oi.getItem()) + oi.getQuantity());
								}
							}
					}
					
					if(repMap.keySet().size() != 0) {
						Item item = Collections.max(repMap.entrySet(), Map.Entry.comparingByValue()).getKey();
						CategoryReportDTO srDto = new CategoryReportDTO(category.getCategoryName(), item.getName(), repMap.get(item));
						report.add(srDto);
					}
					else {
						CategoryReportDTO srDto = new CategoryReportDTO(category.getCategoryName(), "no Items", 0);
						report.add(srDto);
					}
					
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return report; 
	}
	
	
	
}
