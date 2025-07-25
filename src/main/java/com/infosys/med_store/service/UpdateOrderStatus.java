package com.infosys.med_store.service;

import java.util.Scanner;

import com.infosys.med_store.dao.OrderDao;
import com.infosys.med_store.entity.Order.Status;

public class UpdateOrderStatus {
	public boolean updateStatus(Scanner sc, OrderDao od) {
		System.out.println("Enter the Id of Order you want yo update");
		int oid = sc.nextInt();sc.nextLine();
		System.out.println("Enter the updated Status (PLACED, SHIPPED, CANCELLED)");
		String st = sc.nextLine().trim().toUpperCase();
		
		Status status = Status.valueOf(st);
		
		return (od.setOrderStatus(oid, status)!=null)?true:false;
		
	}
}
