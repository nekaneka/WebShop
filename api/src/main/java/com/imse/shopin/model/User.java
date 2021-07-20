package com.imse.shopin.model;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonMerge;

@Entity
@Table(name = "user")
public class User{
	
	@Id
	private String userId;
	
	@JsonBackReference
	@OneToOne(mappedBy = "user")
	private Login login; 
	
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id", unique = true, nullable = true)
	private Cart cart; 
	
	@JsonBackReference
	@OneToMany(mappedBy="user")
    private Set<Order> orders;
	
	private String name;
	private String IBAN; 
	@Column(name = "is_logedin")
	private boolean isLogedin;
	private String address; 
	
	public User() {
	}
	
	public User(String name, boolean logedIn) {
		this.userId = UUID.randomUUID().toString();
		this.name = name;  
		this.isLogedin = logedIn;
		this.cart = new Cart(this);
	}
	
	public User(String name, String iban, String address, boolean logedIn) {
		this.userId = UUID.randomUUID().toString();
		//this.login = login; 
		this.name = name;  
		this.IBAN = iban; 
		this.isLogedin = logedIn;
		this.address = address; 
		this.cart = new Cart(this);
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLogedIn() {
		return isLogedin;
	}

	public void setLogedIn(boolean isLogedIn) {
		this.isLogedin = isLogedIn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}
	
	public Login getLogin() {
		return this.login; 
	}
	
	public String getUserId() {
		return userId; 
	}
	
	public Cart getCart() {
		return cart;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        User that = (User) o;
        return Objects.equals(userId, that.userId);	
    }
	
}
