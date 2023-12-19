package com.ecom.entity;

import java.util.List;

public class Customer {
	private int customer_id;
	private String name;
    private String email;
    private String password;
    private List<Order> orders;
    private Cart cart;
    
    public Customer() {
    	
    }
	public Customer(int customer_id, String name, String email, String password, List<Order> orders, Cart cart) {
		super();
		this.customer_id = customer_id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.orders = orders;
		this.cart = cart;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", orders=" + orders + ", cart=" + cart + "]";
	}
    
    
}
