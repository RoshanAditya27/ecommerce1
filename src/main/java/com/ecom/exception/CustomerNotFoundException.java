package com.ecom.exception;

public class CustomerNotFoundException extends Exception{
	public CustomerNotFoundException() {
		System.out.println("From Customer not found exception");
	}

}
