package com.imse.shopin.mongoService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imse.shopin.model.Category;
import com.imse.shopin.model.CategoryReportDTO;
import com.imse.shopin.model.Item;
import com.imse.shopin.model.Order;
import com.imse.shopin.model.OrderItems;
import com.imse.shopin.model.SalesReportDTO;
import com.imse.shopin.model.User;
import com.imse.shopin.mongo.MongoCategory;
import com.imse.shopin.mongo.MongoOrder;
import com.imse.shopin.mongo.MongoUser;

@Service
public class MongoAdminService {
	
	@Autowired
	MongoUserService mongoUseService;
	
	@Autowired
	MongoItemService itemService; 
	
	@Autowired
	MongoCategoryService mongoCategoryService;
	
	@Autowired
	MongoItemService mongoItemService; 
	
	@Autowired
	MongoOrderService mongoOrderService;

	public List<SalesReportDTO> createSalesReportForMongo(String from, String to) {
		List<SalesReportDTO> report = new ArrayList<SalesReportDTO>();
		Date dateFrom;
		Date dateTo;
	
	
		try {
			dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(from);
			dateTo = new SimpleDateFormat("yyyy-MM-dd").parse(to);
			
			List<MongoUser> userList = mongoUseService.findAllUser();
			
			for(MongoUser user: userList) {	
				Set<MongoOrder> userOrder = user.getOrders();
				if(!userOrder.isEmpty()) {	
					Map<String, Integer> repMap = new HashMap<String, Integer>();
					
					for(MongoOrder order: userOrder) {
						if(order.getOrderDate().after(dateFrom) && order.getOrderDate().before(dateTo))
							for(String itemId: order.getOrderItems().keySet()) {
								if(repMap.containsKey(itemId)) repMap.put(itemId, order.getOrderItems().get(itemId) + repMap.get(itemId));
								else repMap.put(itemId, order.getOrderItems().get(itemId));
							}
					}
					
					if(repMap.keySet().size() != 0) {
						String item = Collections.max(repMap.entrySet(), Map.Entry.comparingByValue()).getKey();
						SalesReportDTO srDto = new SalesReportDTO(user.getName(), itemService.findById(item).getName(), repMap.get(item));
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

	
	
	public List<CategoryReportDTO> createCategoryReportForMongo(String from, String to) {
		List<CategoryReportDTO> report = new ArrayList<CategoryReportDTO>();;
		Date dateFrom;
		Date dateTo;
		
		
		try {
			dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(from);
			dateTo = new SimpleDateFormat("yyyy-MM-dd").parse(to);
			
			List<MongoOrder> orders = mongoOrderService.getOrdersBetween(dateFrom, dateTo);
			
			System.out.println("################################ ORDERS DIZE: " +orders.size());
			List<MongoCategory> categoryList = mongoCategoryService.getAllMongoCategories();
			
			for(MongoCategory category: categoryList) {				
					Map<String, Integer> repMap = new HashMap<String, Integer>();
						
					for(MongoOrder order: orders) {
						for(String itemId: order.getOrderItems().keySet()) {
							if(repMap.containsKey(itemId))
								repMap.put(itemId, order.getOrderItems().get(itemId) + repMap.get(itemId));
							else if(category.getItems().contains(mongoItemService.findById(itemId))) {
								System.out.println("IMAAAAA");
								repMap.put(itemId, order.getOrderItems().get(itemId));
							}
						}
					}
					
					if(repMap.keySet().size() != 0) {
						String itemId = Collections.max(repMap.entrySet(), Map.Entry.comparingByValue()).getKey();
						CategoryReportDTO srDto = new CategoryReportDTO(category.getName(), itemService.findById(itemId).getName(), repMap.get(itemId));
						report.add(srDto);
					}
					else {
						CategoryReportDTO srDto = new CategoryReportDTO(category.getName(), "no Items", 0);
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
