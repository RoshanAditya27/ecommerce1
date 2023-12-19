package com.ecom.entity;

import java.sql.Date;
import java.util.List;

public class Order {
	private int orderId;
    private Customer customer;
    private Date orderDate;
    private double totalPrice;
    private String shippingAddress;
    private List<OrderItem> orderItems;
    
    public Order() {
    	
    }

	public Order(int orderId, Customer customer, Date orderDate, double totalPrice, String shippingAddress,
			List<OrderItem> orderItems) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.shippingAddress = shippingAddress;
		this.orderItems = orderItems;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customer=" + customer + ", orderDate=" + orderDate + ", totalPrice="
				+ totalPrice + ", shippingAddress=" + shippingAddress + ", orderItems=" + orderItems + "]";
	}

    
}
