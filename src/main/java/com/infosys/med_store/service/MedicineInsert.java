package com.infosys.med_store.service;

import java.time.LocalDate;
import java.util.Scanner;

import com.infosys.med_store.dao.MedicineDao;
import com.infosys.med_store.entity.Medicine;

public class MedicineInsert {
	public boolean addmed (Scanner sc, MedicineDao md) {
		Medicine med=new Medicine();
		
		System.out.println("Enter the name of Medicine");
		String name = sc.nextLine();
		
		System.out.println("Enter the Price of one unit");
		int price = sc.nextInt();sc.nextLine();
		
		System.out.println("Enter the Quantity Of medicine");
		int quant = sc.nextInt(); sc.nextLine();
		
		System.out.println("Enter the Manufacture Name");
		String manfn = sc.nextLine();
		
		System.out.println("Enter the expiry date in YYYY-MM-DD format");
		String Locald = sc.nextLine();
		med.setExpD(LocalDate.parse(Locald));
		med.setManf(manfn);
		med.setName(name);
		med.setPrice(price);
		med.setQuantity(quant);

		return (md.saveMed(med)!=null)?true:false;
	}
}
