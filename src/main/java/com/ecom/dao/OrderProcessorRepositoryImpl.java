package com.ecom.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.ecom.entity.Customer;
import com.ecom.entity.Product;
import com.ecom.util.DBConn;

public class OrderProcessorRepositoryImpl implements OrderProcessorRepository{
	Scanner scanner = new Scanner(System.in);
	Connection connection;
	Statement statement;
	PreparedStatement ps, getps, ps1;
	ResultSet rs;
	@Override
	public boolean createProduct(Product product) {
		boolean isDone = false;
		try {
			connection = DBConn.getDBConn();
			ps = connection.prepareStatement("insert into products values (?, ?, ?, ?, ?)");
			ps.setInt(1, product.getProductId());
			ps.setString(2, product.getName());
			ps.setDouble(3, product.getPrice());
			ps.setString(4, product.getDescription());
			ps.setInt(5, product.getStockQuantity());
			
			int noofrows = ps.executeUpdate();
			System.out.println(noofrows + " inserted Successfully");
			isDone = true;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return isDone;
	}

	@Override
	public boolean createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		boolean isDone = false;
		try {
			connection = DBConn.getDBConn();
			ps = connection.prepareStatement("insert into customers values (?, ?, ?, ?)");
			ps.setInt(1, customer.getCustomer_id());
			ps.setString(2, customer.getName());
			ps.setString(3, customer.getEmail());
			ps.setString(4, customer.getPassword());
			int noofrows = ps.executeUpdate();
			isDone = true;
			System.out.println(noofrows + " inserted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDone;
	}

	@Override
	public boolean deleteProduct(int productId) {
		boolean isDone = false;
		try {
			connection = DBConn.getDBConn();
			ps = connection.prepareStatement("delete from table where product_id = ?");
			ps.setInt(1, productId);
			int noofrows = ps.executeUpdate();
			System.out.println(noofrows + " deleted Successfully");
			isDone = true;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return isDone;
	}

	@Override
	public boolean deleteCustomer(int customerId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addToCart(Customer customer, Product product, int quantity) {
		boolean isDone = false;
		try {
			Random random = new Random();
			int cartId = random.nextInt(1000);
			connection = DBConn.getDBConn();
			ps = connection.prepareStatement("insert into cart values (?, ?, ?, ?)");
			ps.setInt(1, cartId);
			ps.setInt(2, customer.getCustomer_id());
			ps.setInt(3, product.getProductId());
			ps.setInt(4, quantity);
			
			int noofrows = ps.executeUpdate();
			System.out.println(noofrows + " inserted successfully");
			isDone = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDone;
	}

	@Override
	public boolean removeFromCart(Customer customer, Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Product> getAllFromCart(Customer customer) {
		List<Product> cartProducts = new ArrayList<>();
		try {
			connection = DBConn.getDBConn();
			ps = connection.prepareStatement("select p.* from cart c join products p "
					+ "on c.product_id = p.product_id where c.customer_id = ?");
			ps.setInt(1, customer.getCustomer_id());
			rs = ps.executeQuery();
			while(rs.next())
			{
				Product p = new Product();
				p.setProductId(rs.getInt("product_id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				p.setDescription(rs.getString("description"));
				p.setStockQuantity(rs.getInt("stockQuantity"));
				
				cartProducts.add(p);
			}
		} catch (SQLException e) {
			
		}
		return cartProducts;
	}


	@Override
	public boolean placeOrder(Customer customer, List<Map<Product, Integer>> productsAndQuantities, String shippingAddress) {
		try {
			Random random = new Random();
	        int orderId = random.nextInt(1000);
	        long millis = System.currentTimeMillis();
	        Date date = new Date(millis);
			connection = DBConn.getDBConn();
			ps = connection.prepareStatement("insert into orders values (?, ?, ?, ?, ?)");
			ps.setInt(1, orderId);
			ps.setInt(2, customer.getCustomer_id());
			ps.setDate(3, date);
			double totalPrice = calculateTotalPrice(productsAndQuantities);
			ps.setDouble(4, totalPrice);
			ps.setString(5, shippingAddress);
			int noofrows = ps.executeUpdate();
			getps = connection.prepareStatement("select * from orders where order_id = ?");
			getps.setInt(1, orderId);
			rs = getps.executeQuery();
			while(rs.next())
			{
				int orderid1 = rs.getInt("order_id");
		        int orderItemsId = random.nextInt(1000);
				ps1 = connection.prepareStatement("insert into order_items values (?, ?, ?, ?)");
				ps1.setInt(1, orderItemsId);
				ps1.setInt(2, orderid1);
				for (Map<Product, Integer> productQuantityMap : productsAndQuantities) {
                    for (Map.Entry<Product, Integer> entry : productQuantityMap.entrySet()) {
                        Product product = entry.getKey();
                        int quantity = entry.getValue();
                        ps1.setInt(3, product.getProductId());
                        ps1.setInt(4, quantity);
                    }
                }
				int batchResult = ps1.executeUpdate();
				System.out.println(batchResult + " inserted successfully");
				
				return true;
			}
        } catch (SQLException e) {
        	e.printStackTrace();
        }
		return false;
	}



	private double calculateTotalPrice(List<Map<Product, Integer>> productsAndQuantities) {
		double totalPrice = 0.0;

	    for (Map<Product, Integer> productQuantityMap : productsAndQuantities) {
	        for (Map.Entry<Product, Integer> entry : productQuantityMap.entrySet()) {
	            Product product = entry.getKey();
	            int quantity = entry.getValue();

	            totalPrice += product.getPrice() * quantity;
	        }
	    }

	    return totalPrice;
	}

	@Override
	public List<Map<Product, Integer>> getOrdersByCustomer(int customerId) {
		List<Map<Product, Integer>> ordersList = new ArrayList<>();
		try {
			connection = DBConn.getDBConn();
			ps = connection.prepareStatement("select o.order_id, o.total_price, "
					+ "o.shipping_address, oi.product_id, oi.quantity from orders o "
					+ "join order_items oi on o.order_id = oi.order_id where o.customer_id = ?");
			ps.setInt(1, customerId);
			rs = ps.executeQuery();
			while(rs.next())
			{
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                
                Product product = getProductById(productId);
                Map<Product, Integer> orderDetails = new HashMap<>();
                orderDetails.put(product, quantity);

                ordersList.add(orderDetails);
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return ordersList;
	}

	private Product getProductById(int productId) {
		// TODO Auto-generated method stub
		Product p = new Product();
		try {
			connection = DBConn.getDBConn();
			ps = connection.prepareStatement("select * from products where product_id = ?");
			ps.setInt(1, productId);
			
			rs = ps.executeQuery();
			while(rs.next())
			{
				p.setProductId(productId);
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				p.setDescription(rs.getString("description"));
				p.setStockQuantity(rs.getInt("stockQuantity"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public boolean isCustomerValid(Customer c) {
		boolean isAvail = false;
		try {
			connection = DBConn.getDBConn();
			ps = connection.prepareStatement("select * from customers where email = ? and password = ?");
			ps.setString(1, c.getEmail());
			ps.setString(2, c.getPassword());
			
			rs = ps.executeQuery();
			while(rs.next())
				isAvail = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isAvail;
	}
	
	public boolean isProductQuantityAvailable(Product p, int quantity) {
		boolean isAvail = false;
		try {
			connection = DBConn.getDBConn();
			ps = connection.prepareStatement("select * from products where product_id = ?");
			ps.setInt(1, p.getProductId());
			rs = ps.executeQuery();
			while(rs.next())
			{
				int stockQuantity = rs.getInt("stockQuantity");
				if(stockQuantity >= quantity)
					isAvail = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isAvail;
	}

}
