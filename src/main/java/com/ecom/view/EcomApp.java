package com.ecom.view;


import java.util.Scanner;

import com.ecom.controller.ecomController;

public class EcomApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ecomController ecc = new ecomController();
		
		int choice = 0;
		do {
        System.out.println("\n--- E-Commerce System ---");
        System.out.println("\n Logging in as Customer or Admin?");
        System.out.println("1. Admin");
        System.out.println("2. Customer");
        System.out.println("3. Exit");
                 
        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
        case 1:
        	//Admin
        	System.out.println("Enter Admin username: ");
        	String uname = scanner.next();
            System.out.println("Enter Admin password: ");
            String pwd = scanner.next();
            if((uname.equals("Rosie")) && (pwd.equals("root")))
            {
            	int choice1 = 0;
            	do {
            	System.out.println("1. Create Product");
                System.out.println("2. Delete Product");
                System.out.println("3. View Customer Order");
                System.out.println("4. Exit Admin");
                System.out.println("Enter your choice: ");
                choice1 = scanner.nextInt();
                scanner.nextLine();
                switch(choice1) {
                case 1:
                	//Creating product
                	ecc.createProduct();
                    break;
                case 2:
                	//Delete product
                	ecc.deleteProduct();
                    break;
                case 3:
                	//View Customer Order
                	ecc.viewCustomerOrders();
                    break;
                case 4:
                	//Exit Admin
                    System.out.println("Exiting admin!");
                    break;
                default:
                	System.out.println("Invalid input");
                    break;
                }
              	} while (choice1 != 4);
            } 	
            break;
        case 2:
        	//Customer
        	int choice2 = 0;
        	do {
        	System.out.println("1. Register Customer");
            System.out.println("2. Add to cart");
            System.out.println("3. View cart");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");
            choice2 = scanner.nextInt();
            scanner.nextLine();
            switch(choice2) {
              	case 1:
                    // Register customer
              		ecc.regCustomer();
              		break;
              	case 2:
              		// Add to cart
              		ecc.addToCart();
                    break;
              	case 3:
                	// View Cart
              		ecc.viewCart();
                    break;
                case 4:
                	// Exit Customer
                	System.out.println("Exiting Customer! Thank you");
                	break;
                default:
                	System.out.println("Invalid Input");
                	break;
            }
        	} while (choice2 != 4);
                	
        case 3:
        	System.out.println("Exiting the application thank you");
        	break;
        default:
        	System.out.println("Invalid Input.");
        	break;
        }
		} while (choice != 3);
        scanner.close();

	}

}
