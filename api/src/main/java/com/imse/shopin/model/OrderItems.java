
package com.imse.shopin.model;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItems {
	
	@Id
    private String id;
	
	@ManyToOne
	private Item item; 
	
	@ManyToOne
	private Order order; 
	
	@Column(name = "quantity")
	private int quantity;
	
	
	public OrderItems() {}
	
	public OrderItems(Item item, Order order, int quantity) {
		this.id = UUID.randomUUID().toString();
		this.item = item; 
		this.order = order; 
		this.quantity = quantity; 
	}
	
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Order getOrder() {
		return order;
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
 
        OrderItems that = (OrderItems) o;
        return Objects.equals(order, that.order) 
        		&& Objects.equals(item, that.item);	
    }

}

