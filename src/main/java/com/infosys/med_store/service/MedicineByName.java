package com.infosys.med_store.service;

import java.util.Scanner;

import com.infosys.med_store.dao.MedicineDao;
import com.infosys.med_store.entity.Medicine;

public class MedicineByName {
	public Medicine getByName(Scanner sc, MedicineDao md) {
		
		System.out.println("Enter the name of Medicine");
		String med = sc.nextLine().trim().toLowerCase();
		med=Character.toUpperCase(med.charAt(0))+med.substring(1);
		
		return md.getMedByName(med);
	}
}
