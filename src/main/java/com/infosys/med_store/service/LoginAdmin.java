package com.infosys.med_store.service;

import java.util.Scanner;

import com.infosys.med_store.dao.AdminDao;
import com.infosys.med_store.entity.Admin;

public class LoginAdmin {
	
	public boolean loginadmin(AdminDao ad, Scanner sc) {
		Admin admin = new Admin();
		
		System.out.println("Enter the username");
		String usern = sc.nextLine();
		
		System.out.println("Enter your password");
		String pass = sc.nextLine();
		
		admin.setUsername(usern);
		admin.setPass(pass);
		
		return (ad.loginAdmin(admin)!=null)?true:false;
	}
	
}
