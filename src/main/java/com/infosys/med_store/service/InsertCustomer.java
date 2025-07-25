package com.infosys.med_store.service;

import java.util.Scanner;

import com.infosys.med_store.dao.CustomerDao;
import com.infosys.med_store.entity.Customer;

public class InsertCustomer {
	public Customer insertCustomer(Scanner sc, CustomerDao cd) {
		
		Customer cus = new Customer();
		
		System.out.println("Enter The Name: ");
		String name=sc.nextLine();
		cus.setName(name);
		
		System.out.println("Enter The Email: ");
		String email=sc.nextLine();
		cus.setEmail(email);
		
		System.out.println("Enter the Password");
		String pass=sc.nextLine();
		cus.setPass(pass);
		
		System.out.println("Enter the Address");
		String add=sc.nextLine();
		cus.setAddress(add);
		
		return(cd.saveCustomer(cus));
		
	}	
}
