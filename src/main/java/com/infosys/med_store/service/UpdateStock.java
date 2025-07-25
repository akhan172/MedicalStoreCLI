package com.infosys.med_store.service;

import java.util.Scanner;

import com.infosys.med_store.dao.MedicineDao;
import com.infosys.med_store.entity.Medicine;

public class UpdateStock {
	public boolean updateStock(Scanner sc, MedicineDao md) {
		Medicine med= new Medicine();
		
		
		System.out.println("Enter the Id of Medicine");
		int id = sc.nextInt(); sc.nextLine();
		System.out.println("Enter the new Quantity");
		int Quant=sc.nextInt(); sc.nextLine();
		
		med.setId(id);
		med.setQuantity(Quant);
		return (md.updateMedStock(med)!=null)?true:false;
	}
	
	
}
