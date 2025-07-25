package com.infosys.med_store.service;

import java.util.Scanner;

import com.infosys.med_store.dao.CustomerDao;
import com.infosys.med_store.entity.Customer;


public class LoginCustomer {
	public Customer loginCustomer (Scanner sc, CustomerDao cd) {
		Customer cus = new Customer();
		
		
		System.out.println("Enter the registered email id");
		String email = sc.nextLine();
		
		System.out.println("Enter your Password");
		String pass = sc.nextLine();
		
		cus.setEmail(email);
		cus.setPass(pass);
		
		return (cd.loginCustomer(cus));
		
		
// 		Displaying Order history
//		List<Order> orders = od.getOrderbyCustomer(1);
//		for(Order o: orders) {
//			System.out.println(o.toString());
//		}
		
	}
}
