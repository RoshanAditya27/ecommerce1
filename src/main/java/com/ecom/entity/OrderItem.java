package com.ecom.entity;

public class OrderItem {
	private int orderItemId;
    private Order order;
    private Product product;
    private int quantity;
    
    public OrderItem() {
    	
    }
	public OrderItem(int orderItemId, Order order, Product product, int quantity) {
		super();
		this.orderItemId = orderItemId;
		this.order = order;
		this.product = product;
		this.quantity = quantity;
	}
	public int getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", order=" + order + ", product=" + product + ", quantity="
				+ quantity + "]";
	}
    
    
}
