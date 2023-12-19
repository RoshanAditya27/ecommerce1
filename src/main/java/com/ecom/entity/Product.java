package com.ecom.entity;

public class Product {
	private int productId;
    private String name;
    private double price;
    private String description;
    private int stockQuantity;
    
    public Product() {
    	
    }

	public Product(int productId, String name, double price, String description, int stockQuantity) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.description = description;
		this.stockQuantity = stockQuantity;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", price=" + price + ", description="
				+ description + ", stockQuantity=" + stockQuantity + "]";
	}
    
    
}
