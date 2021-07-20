package com.imse.shopin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.imse.shopin.model.Category;
import com.imse.shopin.model.Employee;
import com.imse.shopin.model.Item;
import com.imse.shopin.model.Login;
import com.imse.shopin.model.Order;
import com.imse.shopin.model.OrderItems;
import com.imse.shopin.model.User;
import com.imse.shopin.service.CategoryService;
import com.imse.shopin.service.EmployeeService;
import com.imse.shopin.service.ItemService;
import com.imse.shopin.service.LoginService;
import com.imse.shopin.service.OrderItemsService;
import com.imse.shopin.service.OrderService;
import com.imse.shopin.service.UserService;

@SpringBootApplication
public class ShopInApplication implements CommandLineRunner{

	@Autowired
	LoginService loginService; 
	
	@Autowired
	OrderService orderService; 
	
	@Autowired
	OrderItemsService oiService; 
	
	@Autowired
	UserService userService; 
	
	@Autowired
	ItemService itemService; 
	
	@Autowired
	EmployeeService employeeService; 
	
	@Autowired
	CategoryService categoryService; 
	
    public static void main(String[] args) {
    	
        SpringApplication.run(ShopInApplication.class, args);
    }

//    @Bean
//    public FilterRegistrationBean<CORSFilter> corsFilterRegistration() {
//        FilterRegistrationBean<CORSFilter> registrationBean =
//                new FilterRegistrationBean<CORSFilter>(new CORSFilter());
//        registrationBean.setName("CORS Filter");
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setOrder(1);
//        return registrationBean;
//    }
  
    @Override
    public void run(String... args) throws ParseException {
    	
    	// USER TEST DATA GENERATION
    	User u1 = new User("Velja Radun", "AT896577118411290002", "Währinger Gürtel 1", false);
    	User u2 = new User("Hasan Gutti", "AT735277151951290001", "Währinger Gürtel 2", false);
    	User u3 = new User("Max Musterman", "AT720752010757774208", "Währinger Gürtel 3", false);
    	userService.saveUsers(Arrays.asList(u1, u2, u3));
    	
    	// EMPLOYEE TEST DATA GENERATION
    	Employee e3 = new Employee("Gandalf", "Der Graue");
    	Employee e1 = new Employee("Nemanja", "Sradanovic");
    	e1.setManager(e3);
    	
    	Employee e2 = new Employee("Momir", "Petrov");
    	e2.setManager(e3);
    	
    	employeeService.saveAllEmployees(Arrays.asList(e1, e2, e3));
    	
    	// LOG TEST DATA GENERATION 
    	Login l2 = new Login(u2, "mayo@gmail.com", "123456"); 
    	Login l1 = new Login(u1, "veljo@gmail.com", "Bubica123");
    	Login l3 = new Login(u3, "ketchup@gmail.com", "123456");
    	
    	Login le1 = new Login(e1, "srdjan@gmail.com", "admin");
    	Login le2 = new Login(e2, "mome@gmail.com", "admin");
    	Login le3 = new Login(e3, "gandalf@gmail.com", "admin");
    	
    	loginService.saveLogins(Arrays.asList(l2, l1, l3, le1, le2, le3));
    	
    	// ITEM TEST DATA GENERATION
    	
    	Item i1 = new Item(399.00, "Laptop HP G3", "Intel Core i7 8GB");
    	Item i2 = new Item(1258.90, "DELL - XPS 13", "i7 16GB SSD Win10");
    	Item i3 = new Item(800.00, "Acer Swift 3", "11 Generation Intel® Core");
    	Item i4 = new Item(1828.93, "Microsoft Surface Book 3", "Intel® Core™ i7");
    	Item i5 = new Item(120.00, "Samsung S24F356F", "PLS Panel (23,5 Zoll) Monitor, Dark");
    	Item i6 = new Item(1258.90, "AOC G2790PX Monitor", "68,6 cm (27 Zoll) 1920 x 1080, 144 Hz");
    	Item i7 = new Item(89.75, "Lenovo D22-20 Monitor", "54,6 cm (21,5 Zoll, 1920x1080, Full HD, 75Hz)");
    	Item i8 = new Item(822.98, "Samsung Curved Monitor", "110 cm (43 Zoll) 3.840 x 1.200 Pixel, 120Hz");
    	Item i9 = new Item(4.63, "Logitech B100", "Optical Mouse For Business Black");
    	Item i10 = new Item(10.58, "Logitech M185", "Mouse, 2.4 GHz with nano usb");
    	Item i11 = new Item(25.67 , "Logitech M500s", "USB-Plug&Play - Grey");
    	Item i12 = new Item(9.99, "Jelly Comb Mouse", "2.4G USB Nano, Black and Gold");
    	Item i13 = new Item(750.00, "Lenovo Thinkpad E480", "i5-8250U 16GB 256GB SSD");
    	Item i14 = new Item(577.00, "Lenovo Notebook", "15,6 Zoll 8GB DDR4, 512 GB SSD");
    	Item i15 = new Item(17.88, "Laptop Sleeve 15", "Spill-Resistant Handbag Black");
    	Item i16 = new Item(48.10, "Razer Cynosa", "Gaming Keyboard Spill-Resistant");
    	
    	// CATEGORY TEST DATA GENERATION
    	Category c1 = new Category("Laptops", "Laptops", Set.of(i1, i2, i3, i4, i13, i14));
    	Category c2 = new Category("Monitors", "Pc monitors", Set.of(i8, i5, i6, i7));
    	Category c3 = new Category("Mouse", "PC/Laptop Mouse", Set.of(i9, i10, i11, i12));
    	Category c4 = new Category("Sale", "Products on sale", Set.of(i1, i10, i8));
    	Category c5 = new Category("Accessories", "PC Accessories", Set.of(i15, i16));
 
    	categoryService.saveCategories(Arrays.asList(c1, c2, c3, c4, c5));
    	
    	
    	// ORDER TEST DATA GENERATION
    	Order o1 = new Order(u1, new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-09"));
    	Order o2 = new Order(u1, new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-10"));
    	
    	Order o3 = new Order(u2, new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-10"));
    	Order o4 = new Order(u2, new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-01"));
    	Order o5 = new Order(u2, new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-05"));
    	
    	
    	orderService.saveOrders(Arrays.asList(o1, o2, o3, o4, o5));
    	
    	oiService.saveOrderItems(Arrays.asList(new OrderItems(i1, o1, 2), new OrderItems(i4, o1, 1), new OrderItems(i3, o2, 5), 
    			new OrderItems(i5, o4, 8), new OrderItems(i10, o3, 6), new OrderItems(i1, o5, 4)));
    	
    }
}

