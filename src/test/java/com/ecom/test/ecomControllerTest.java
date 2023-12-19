package com.ecom.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ecom.dao.OrderProcessorRepository;
import com.ecom.dao.OrderProcessorRepositoryImpl;
import com.ecom.entity.Customer;
import com.ecom.entity.Product;

class ecomControllerTest {

	OrderProcessorRepository opr = new OrderProcessorRepositoryImpl();
	Random random = new Random();
	int id = random.nextInt(1000);
	
    @BeforeEach
    public void removeData() {
        opr.deleteProduct(id);
    }
    
    @Test
    public void createProductTest() {
    	Product product = new Product(id, "book", 12.3, "A book", 10);
        boolean isDone = opr.createProduct(product);
        assertTrue(isDone);

    }
    
    @Test
    public void addProductToCartTest() {
        Customer customer = new Customer();
        customer.setCustomer_id(1);
        Product product = new Product(id, "book", 12.3, "A book", 10);

        boolean isDone_pro = opr.createProduct(product);

        List<Product> before_list = opr.getAllFromCart(customer);

        boolean isDone = opr.addToCart(customer, product, 13);

        List<Product> after_list = opr.getAllFromCart(customer);

        assertFalse(isDone);
        assertEquals(before_list.size()+ 1, after_list.size());
    }
    
    @Test
    public void productOrderTest() {
        Customer customer = new Customer();
        customer.setCustomer_id(1);
        Product product = new Product();
        product.setProductId(id);

        boolean isDone_pro = opr.createProduct(product);

        List<Map<Product, Integer>> productsAndQuantities = new ArrayList<Map<Product, Integer>>();
        HashMap<Product, Integer> hs = new HashMap<Product, Integer>();
        hs.put(product, 3);

        productsAndQuantities.add(hs);

        String shippingAddress = "abc";

        boolean isDone = opr.placeOrder(customer, productsAndQuantities, shippingAddress);

        assertTrue(isDone);
    }

}
