package com.imse.shopin.model;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_items")
public class CartItems {
	
	@Id
    private String id;
	
	@ManyToOne
	private Item item; 
	
	@ManyToOne
	private Cart cart; 
	
	@Column(name = "quantity")
	private int quantity;
	
	
	public CartItems() {}
	
	public CartItems(Item item, Cart cart) {
		this.id = UUID.randomUUID().toString();
		this.item = item; 
		this.cart = cart; 
		this.quantity = 1; 
	}
	
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Cart getCart() {
		return cart;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getId() {
		return id;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        CartItems that = (CartItems) o;
        return Objects.equals(cart, that.cart) 
        		&& Objects.equals(item, that.item);	
    }

	public void increaseQuantity() {
		quantity++;		
	}
	
	public void decreaseQuantity() {
		quantity--;		
	}

}
