package com.ecom.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.ecom.dao.OrderProcessorRepository;
import com.ecom.dao.OrderProcessorRepositoryImpl;
import com.ecom.entity.Customer;
import com.ecom.entity.Product;
import com.ecom.exception.CustomerNotFoundException;

public class ecomController {
	Scanner scanner = new Scanner(System.in);
	OrderProcessorRepository opr = new OrderProcessorRepositoryImpl();
	
	public void createProduct() {
		//Creating product
    	System.out.println("Enter product id: ");
    	int productId = scanner.nextInt();
    	scanner.nextLine();
    	System.out.println("Enter product name: ");
    	String productName = scanner.next();
    	System.out.println("Enter product price: ");
    	double productPrice = scanner.nextDouble();
    	scanner.nextLine();
    	System.out.println("Enter product description: ");
    	String description = scanner.nextLine();
    	System.out.println("Enter product stockQuantity: ");
    	int stockQuantity = scanner.nextInt();
    	scanner.nextLine();
    	Product product = new Product(productId, productName, productPrice, description, stockQuantity);
    	boolean isDone = opr.createProduct(product);
    	if(isDone == false)
    		System.out.println("Can't create product sorry!");
    	else
    		System.out.println("Product sucessfully created");
	}
	
	public void deleteProduct() {
		System.out.println("Enter the product id to be deleted: ");
    	int delProductId = scanner.nextInt();
    	scanner.nextLine();
    	boolean isDel = opr.deleteProduct(delProductId);
    	if(isDel == false)
    		System.out.println("Can't delete product sorry!");
    	else
    		System.out.println("Product sucessfully deleted");
	}
	public void viewCustomerOrders() {
		System.out.println("Enter the customer id to view the customer's orders: ");
    	int viewCustomerId = scanner.nextInt();
    	scanner.nextLine();
    	List<Map<Product, Integer>> ordersList = opr.getOrdersByCustomer(viewCustomerId);
    	if (ordersList.isEmpty()) {
    	    System.out.println("No orders found for the customer with ID: " + viewCustomerId);
    	} else {
    	    System.out.println("Orders for customer with ID " + viewCustomerId + ":");

    	    for (Map<Product, Integer> orderDetails : ordersList) {
    	        System.out.println("Order Details:");
    	        for (Map.Entry<Product, Integer> entry : orderDetails.entrySet()) {
    	            Product product1 = entry.getKey();
    	            int quantity = entry.getValue();

    	            System.out.println("Product: " + product1.getName());
    	            System.out.println("Quantity: " + quantity);
    	            // Print other product details as needed
    	        }
    	        System.out.println("-------------");
    	    }
    	}
	}
	public void regCustomer() {
		System.out.println("Enter customer id: ");
  		int customerId = scanner.nextInt();
  		scanner.nextLine();
  		System.out.println("Enter customer name: ");
  		String cname = scanner.next();
  		System.out.println("Enter customer emailID: ");
  		String cemail = scanner.next();
  		System.out.println("Enter customer password: ");
  		String cpwd = scanner.next();
  		Customer customer = new Customer();
  		customer.setCustomer_id(customerId);
  		customer.setName(cname);
  		customer.setEmail(cemail);
  		customer.setPassword(cpwd);
  		if(opr.createCustomer(customer))
  			System.out.println("Customer created successfully!");
  		else
  			System.out.println("Something went wrong!");
	}
	public void addToCart() {
		try {
		System.out.println("Enter customer emailID: ");
  		String cemail1 = scanner.next();
  		System.out.println("Enter customer password: ");
  		String cpwd1 = scanner.next();
  		Customer c = new Customer();
  		c.setEmail(cemail1);
  		c.setPassword(cpwd1);
  		if(opr.isCustomerValid(c)) {
  			System.out.println("Enter customer id: ");
      		int cartCustId = scanner.nextInt();
      		scanner.nextLine();
      		System.out.println("Enter product id: ");
        	int productId = scanner.nextInt();
        	scanner.nextLine();
        	System.out.println("Enter quantity: ");
        	int quantity = scanner.nextInt();
        	scanner.nextLine();
        	c.setCustomer_id(cartCustId);
        	Product p = new Product();
        	p.setProductId(productId);
        	if(opr.isProductQuantityAvailable(p, quantity));
        	{
        		if(opr.addToCart(c, p, quantity)) {
        			System.out.println("Added to cart!");
        		}
        		else {
        			System.out.println("Stock not available");
        		}
        	} 
  		} else {
  			throw new CustomerNotFoundException();
  		}
		} catch (CustomerNotFoundException cnfe)
		{
			System.out.println("Customer not found!");
		}
	}
	public void viewCart() {
		System.out.println("Enter customer emailID: ");
  		String vemail1 = scanner.next();
  		System.out.println("Enter customer password: ");
  		String vpwd1 = scanner.next();
  		Customer c1 = new Customer();
  		c1.setEmail(vemail1);
  		c1.setPassword(vpwd1);
  		if(opr.isCustomerValid(c1))
  		{
  			System.out.println("Enter customer id: ");
      		int cartCustId = scanner.nextInt();
      		scanner.nextLine();
      		c1.setCustomer_id(cartCustId);
      		List<Product> cartProducts = opr.getAllFromCart(c1);
      		if (cartProducts.isEmpty()) {
    	        System.out.println("No products available in cart!");
    	    } else {
    	        System.out.println("All Cart Products:");
    	        for (Product productDetails : cartProducts) {
    	            System.out.println(productDetails);
    	        }
    	    }
      		// Place order from the cart
            System.out.println("Do you want to place the order? (yes/no): ");
            String placeOrderChoice = scanner.next();

            if ("yes".equalsIgnoreCase(placeOrderChoice)) {
                System.out.println("Enter shipping address: ");
                String shippingAddress = scanner.next();

                // Place the order
                boolean orderPlaced = opr.placeOrder(c1, getProductsAndQuantities(cartProducts), shippingAddress);

                if (orderPlaced) {
                    System.out.println("Order placed successfully!");
                } else {
                    System.out.println("Failed to place the order. Please try again.");
                }
            } else {
                System.out.println("Order placement canceled.");
            }
  		} else {
  			System.out.println("Customer not found!");
  		}
		
	}
	
	private static List<Map<Product, Integer>> getProductsAndQuantities(List<Product> cartProducts) {
		List<Map<Product, Integer>> productsAndQuantities = new ArrayList<>();

        for (Product product : cartProducts) {
            Map<Product, Integer> productQuantityMap = new HashMap<>();
            productQuantityMap.put(product, 1); // Assuming quantity is 1 for each product in the cart
            productsAndQuantities.add(productQuantityMap);
        }

        return productsAndQuantities;
	}
}
