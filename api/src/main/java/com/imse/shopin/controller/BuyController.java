package com.imse.shopin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imse.shopin.model.Cart;
import com.imse.shopin.model.CartDTO;
import com.imse.shopin.model.CartItems;
import com.imse.shopin.model.Item;
import com.imse.shopin.model.Order;
import com.imse.shopin.model.OrderItems;
import com.imse.shopin.model.OrderItemsOfUserDTO;
import com.imse.shopin.mongo.MongoOrder;
import com.imse.shopin.mongo.MongoUser;
import com.imse.shopin.mongoService.MongoItemService;
import com.imse.shopin.mongoService.MongoOrderService;
import com.imse.shopin.mongoService.MongoUserService;
import com.imse.shopin.service.CartItemService;
import com.imse.shopin.service.ItemService;
import com.imse.shopin.service.OrderItemsService;
import com.imse.shopin.service.OrderService;
import com.imse.shopin.service.UserService;

@CrossOrigin
@RestController
public class BuyController {

	@Autowired
	private UserService userService; 
	
	@Autowired
	private ItemService itemService; 
	
	@Autowired
	private CartItemService cartItemService; 
	
	@Autowired
	private OrderService orderService; 
	
	@Autowired
	private OrderItemsService oiService; 
	
	
	////////////////// MONGO
	@Autowired
	private Migrator migrator; 
	@Autowired
	MongoUserService mongoUserService; 
	@Autowired
	MongoItemService mongoItemService;
	@Autowired
	MongoOrderService mongoOrderService; 
	
	@PostMapping("/cartItem/{id}")
	public String addItemToCart(@PathVariable String id) {
		
		if(migrator.isMigrated()) return mongoAddItem(id);
		
		if (userService.getCurrentLogin() == null) {
			return "No user logedIn!";
		}
		
		Item item = itemService.findItem(id);
		
		Cart cart = userService.getCurrentLogin().getCart();
		
		List<CartItems> cartItems = cartItemService.getCartItemsbyCart(cart);
		CartItems newItem  = null;
		if(cartItems != null) {
			for(CartItems itemInCart: cartItems) {
				if(itemInCart.getCart().equals(cart) && itemInCart.getItem().equals(item)) {
					newItem = itemInCart;
					break;
				}
			}
			
			if(newItem != null) {
				cartItemService.increaseQuantity(newItem);
			}else 
				cartItemService.addItemToCart(new CartItems(item, cart));
		}
		return "Added to cart";
	}
	
	
	
	private String mongoAddItem(String id) {

		if(mongoUserService.getCurrentMongoUser() != null) {
			MongoUser user = mongoUserService.getCurrentMongoUser();
			user.getCart().addMongoItemToCart(mongoItemService.findById(id));
			mongoUserService.updateMongoUser(mongoUserService.getCurrentMongoUser());
			return "Added";

		}
		
		return "No User Logged in";
		
	}



	/**
	 * Rest method that decreases the number of an item in the cart
	 * @param id
	 * @return
	 */
	@PostMapping("/removeCartItem/{id}")
	public List<CartDTO> removeItemFromCart(@PathVariable String id) {
		
		if(migrator.isMigrated()) return mongoRemoveItem(id);
		
		if (userService.getCurrentLogin() == null) {
			return null;
		}
		
		Item item = itemService.findItem(id);
		
		Cart cart = userService.getCurrentLogin().getCart();
		
		List<CartItems> cartItems = cartItemService.getCartItemsbyCart(cart);
		CartItems newItem = null;
		
		for(CartItems itemInCart: cartItems) 
			if(itemInCart.getCart().equals(cart) && itemInCart.getItem().equals(item)) {
				newItem = itemInCart;
				break;
			}
			
			
		cartItemService.decreaseQuantity(newItem);
		
		
		return getItems();
	}
	
	
	private List<CartDTO> mongoRemoveItem(String id) {
		
		if(mongoUserService.getCurrentMongoUser() != null) {
			mongoUserService.getCurrentMongoUser().getCart().reduceMongoItemFromCart(mongoItemService.findById(id));
			mongoUserService.updateMongoUser(mongoUserService.getCurrentMongoUser());
			
			List<CartDTO> items = new ArrayList<CartDTO>();
			
			for(String itemId: mongoUserService.getCurrentMongoUser().getCart().getCartItems().keySet()) {
				items.add(
						new CartDTO(
								itemId,
								mongoItemService.findById(itemId).getName(), 
								mongoItemService.findById(itemId).getPrice(), 
								mongoUserService.getCurrentMongoUser().getCart().getCartItems().get(itemId)
								)
						);
			}
			return items;
		}
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	@PostMapping("/order")
	public String orderItems() {
		
		if (userService.getCurrentLogin() == null && mongoUserService.getCurrentMongoUser() == null) {
			return "No user Loged IN";
		}
		
		if(migrator.isMigrated()) {
			MongoUser user = mongoUserService.getCurrentMongoUser(); 
			MongoOrder newOrder = new MongoOrder(new Date(), user.getCart().getCartItems(), user.getCart().getTotalPrice());
			mongoOrderService.insertOrder(newOrder);
			user.update(newOrder);
			user.getCart().emptyCart();
			mongoUserService.updateMongoUser(user);
			
			return "Mongo items ordered";
		}
		
		Order order = new Order(userService.getCurrentLogin(), new Date());
		orderService.saveItem(order);
		Cart cart = userService.getCurrentLogin().getCart();
		
		List<CartItems> cartItems = cartItemService.getCartItemsbyCart(cart);	
		for(CartItems itemInCart: cartItems) {
			oiService.saveOrder(new OrderItems(itemInCart.getItem(), order, itemInCart.getQuantity()));
			cartItemService.deleteCartitem(itemInCart);
		}
		
		return "Items ordered";
	}
	
	
	
	@GetMapping("/allCartItems")
	public List<CartDTO> getItemsFromCart(){
		if (userService.getCurrentLogin() == null 
				&& mongoUserService.getCurrentMongoUser() == null) 
			return null;
		
		return getItems();
	}
	
	
	
	
	private List<CartDTO> getItems(){
		List<CartDTO> items = new ArrayList<CartDTO>();
		
		if(migrator.isMigrated()) {
			for(String itemId: mongoUserService.getCurrentMongoUser().getCart().getCartItems().keySet()) {
				items.add(
						new CartDTO(
								itemId,
								mongoItemService.findById(itemId).getName(), 
								mongoItemService.findById(itemId).getPrice(), 
								mongoUserService.getCurrentMongoUser().getCart().getCartItems().get(itemId)
								)
						);
			}
		}else {
			Cart cart = userService.getCurrentLogin().getCart();
			List<CartItems> cartItems = cartItemService.getCartItemsbyCart(cart);
			for(CartItems item : cartItems) {
				items.add(new CartDTO(
						item.getItem().getId(), 
						item.getItem().getName(), 
						item.getItem().getPrice(),
						item.getQuantity()
					));
			}
		}
		
		return items; 
	}
	
	@GetMapping("/allOrderItems")
	public List<OrderItemsOfUserDTO> getItemsFromOrder(){
		
		if (userService.getCurrentLogin() == null && mongoUserService.getCurrentMongoUser() == null) {
			return null;
		}
		
		List<OrderItemsOfUserDTO> rep = new ArrayList<OrderItemsOfUserDTO>();
		
		if(migrator.isMigrated()) {
			for(MongoOrder order: mongoUserService.getCurrentMongoUser().getOrders()) {
				for(String itemId : order.getOrderItems().keySet()) {
					rep.add(new OrderItemsOfUserDTO(
							order.getOrderId(), 
							mongoItemService.findById(itemId).getName(), 
							order.getOrderItems().get(itemId),
							mongoItemService.findById(itemId).getPrice()
							));
				}
			}
			
			return rep;
		}
		for(Order order: orderService.findOrderbyuser(userService.getCurrentLogin())) {
		
			for(OrderItems oi: oiService.findOrderItemByOrder(order))
				rep.add(new OrderItemsOfUserDTO(
						oi.getOrder().getOrderId(), 
						oi.getItem().getName(), 
						oi.getQuantity(), 
						oi.getItem().getPrice()
					));
		}
		
		return rep; 
	}
	
}
