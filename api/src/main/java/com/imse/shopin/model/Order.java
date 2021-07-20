package com.imse.shopin.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "user_order")
public class Order {

	@Id
	@Column(name = "order_id")
	private String orderId; 
	
	@Column(name = "oder_date")
	@Temporal(TemporalType.DATE)
	private Date orderDate; 
	
	@JsonManagedReference
	@ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="user_id", nullable=false)
    private User user;
	
	public Order() {}
	
	public Order(User user, Date orderDate) {
		this.user = user; 
		this.orderId = UUID.randomUUID().toString();
		this.orderDate = orderDate; 
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public User getUser() {
		return user;
	}
	
	
	
}
