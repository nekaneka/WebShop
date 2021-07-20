package com.imse.shopin.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "cart")
public class Cart {
	
	@Id
	@Column(name = "cart_id")
	private String cartId; 
		
	@Column(name = "total_price")
	private Double totalPrice; 
	
	@JsonBackReference
	@OneToOne(mappedBy ="cart")
	private User user; 
	
	// CartItems
	
	public Cart() {}

	public Cart(User user) {
		this.user = user; 
		this.cartId = UUID.randomUUID().toString();
	}

	public String getCartId() {
		return cartId;
	}
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        Cart that = (Cart) o;
        return Objects.equals(cartId, that.cartId);	
    }

	
}
