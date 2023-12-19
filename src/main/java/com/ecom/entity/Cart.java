package com.ecom.entity;

import java.util.List;

public class Cart {
	private int cartId;
    private Customer customer;
    private List<Product> products;
	
	public Cart() {
		
	}

	public Cart(int cartId, Customer customer, List<Product> products) {
		super();
		this.cartId = cartId;
		this.customer = customer;
		this.products = products;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", customer=" + customer + ", products=" + products + "]";
	}
	
	
}
