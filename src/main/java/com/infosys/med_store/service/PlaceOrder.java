package com.infosys.med_store.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.infosys.med_store.ListPair;
import com.infosys.med_store.dao.CustomerDao;
import com.infosys.med_store.dao.MedicineDao;
import com.infosys.med_store.dao.OrderDao;
import com.infosys.med_store.entity.Customer;
import com.infosys.med_store.entity.Medicine;
import com.infosys.med_store.entity.Order;
import com.infosys.med_store.entity.Order.Status;
import com.infosys.med_store.entity.OrderItem;

public class PlaceOrder {
	public Order placeOd(CustomerDao cd, MedicineDao md, Customer customer,Scanner sc) {
		//getting customer
		Customer cus = cd.getCustomer(customer.getId());
		
		//user input of Med ID with quantity
		Map<Integer, Integer> mp = new HashMap<>();
		System.out.println("Enter the number of Items you want to Order");
		int num=sc.nextInt();sc.nextLine();

		for(int i=0; i<num; i++) {
			Medicine medi=new MedicineByName().getByName(sc,md);
			
			if(medi != null) {
				
				System.out.println("Enter the quantity");
				int quant = sc.nextInt();sc.nextLine();
				mp.put(medi.getId(), quant);
				
			}else {
				
				System.out.println("\nNo such Medicine Found");
			}
		}
		
		//Checking all medicines are in stock and storing them in a list
		ListPair<Medicine, Medicine> medpair=md.checkStock(mp);
		List<Medicine> meds=medpair.getList1();
		List<Medicine> notMeds=medpair.getList2();
		
		if(!meds.isEmpty()) {
			
			if(!notMeds.isEmpty()){
				System.out.println("Few Medicines are Out of stock");
				for(Medicine medicine:notMeds){
					System.out.println("Name: "+medicine.getName()+ "\tAvailable quantity: "+medicine.getQuantity());
				}

				System.out.println("Enter 1 to continue");
				int check = sc.nextInt();sc.nextLine();
				if(check==1) {
					List<OrderItem> Oitems = new ArrayList<>();
					Order order = new Order();
					int amt = 0;
					for (Medicine med : meds) {
						System.out.println(med.toString());
						OrderItem oi = new OrderItem();
						oi.setMed(med);
						oi.setQuantity(mp.get(med.getId()));
						oi.setSubTotal(med.getPrice() * mp.get(med.getId()));
						oi.setOrder(order);
						Oitems.add(oi);
						amt += oi.getSubTotal();
					}
					order.setAmount(amt);
					order.setItems(Oitems);
					order.setStatus(Status.PLACED);
					order.setCustomer(cus);
					order.setOrderDate(LocalDate.now());
					OrderDao od = new OrderDao();
					Order o1 = od.saveOrder(order);
					if (o1 != null) {
						for (Medicine m : meds) {
							m.setQuantity(m.getQuantity() - mp.get(m.getId()));
						}
						if (md.updateQuantity(meds)) {
							return order;
						} else {
							return null;
						}
					}

				}
			}
		}else {
			System.out.println("selected medicine is out of Stock");
		}
		return null;
	}
}
