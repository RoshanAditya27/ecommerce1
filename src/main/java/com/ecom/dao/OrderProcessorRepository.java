package com.ecom.dao;

import java.util.List;
import java.util.Map;

import com.ecom.entity.Customer;
import com.ecom.entity.Product;

public interface OrderProcessorRepository {
	boolean createProduct(Product product);
    boolean createCustomer(Customer customer);
    boolean deleteProduct(int productId);
    boolean deleteCustomer(int customerId);
    boolean addToCart(Customer customer, Product product, int quantity);
    boolean removeFromCart(Customer customer, Product product);
    List<Product> getAllFromCart(Customer customer);
    boolean placeOrder(Customer customer, List<Map<Product, Integer>> productsAndQuantities, String shippingAddress);
    List<Map<Product, Integer>> getOrdersByCustomer(int customerId);
	boolean isCustomerValid(Customer c);
	boolean isProductQuantityAvailable(Product p, int quantity);
}
