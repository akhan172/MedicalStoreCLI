package com.infosys.med_store.controller;

import java.util.List;
import java.util.Scanner;

import com.infosys.med_store.dao.AdminDao;
import com.infosys.med_store.dao.CustomerDao;
import com.infosys.med_store.dao.MedicineDao;
import com.infosys.med_store.dao.OrderDao;
import com.infosys.med_store.entity.Customer;
import com.infosys.med_store.entity.Medicine;
import com.infosys.med_store.entity.Order;
import com.infosys.med_store.service.InsertCustomer;
import com.infosys.med_store.service.LoginAdmin;
import com.infosys.med_store.service.LoginCustomer;
import com.infosys.med_store.service.MedicineByName;
import com.infosys.med_store.service.MedicineInsert;
import com.infosys.med_store.service.PlaceOrder;
import com.infosys.med_store.service.UpdateOrderStatus;
import com.infosys.med_store.service.UpdateStock;

public class MainController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//DAO Classes
		CustomerDao cd = new CustomerDao();
		OrderDao od = new OrderDao();
		MedicineDao md = new MedicineDao();
		AdminDao ad = new AdminDao();
		
		//scanner class
		Scanner sc = new Scanner(System.in);
		
		LoginAdmin la = new LoginAdmin();
		UpdateOrderStatus uos = new UpdateOrderStatus();
		boolean flag = true;
		while(flag) {
			System.out.println("==== Welcome to Khan Medical Store ====");
			System.out.println("1. Admin Login");
			System.out.println("2. Customer Login");
			System.out.println("3. Register New Customer");
			System.out.println("4. Exit");
			int ch1=sc.nextInt();sc.nextLine();
			
			switch(ch1) {
			case 1:
				if(la.loginadmin(ad, sc)) {
					int ch2 =0;
					System.out.println("\n=== Admin Loggen In ====");
					
					while(ch2 != 7) {
						System.out.println("\n1. Add Medicine");
						System.out.println("2. View All Medicine");
						System.out.println("3. Update Stock");
						System.out.println("4. Delete Medicine");
						System.out.println("5. View All Orders");
						System.out.println("6. Update Order Status");
						System.out.println("7. LogOut");
						System.out.println("Enter Your Choice");
						if(sc.hasNextInt()) {
							ch2=sc.nextInt();sc.nextLine();
						}
						else {
							System.out.println("Enter the valid Input");
							sc.nextLine();
							continue;
						}
						switch(ch2) {
						case 1:
							MedicineInsert mi = new MedicineInsert();
							if(mi.addmed(sc,md)) {
								System.out.println("Medicine Added Successfully");
							}else {
								System.out.println("Medicine Not Added");
							}
							break;
						case 2:
							md.getAllMed();
							break;
						case 3:
							UpdateStock up = new UpdateStock();
							if(up.updateStock(sc,md)) {
								System.out.println("Quantity Updated Successfully");
							}else {
								System.out.println("No Such Medicine Exist In Stock");
							}
							break;
						case 4:
							System.out.println("Enter the Id Of Medicine to be deleted");
							int id=sc.nextInt(); sc.nextLine();
							if(md.deleteMed(id)) {
								System.out.println("Medicine Deleted Successfully");
							}
							else {
								System.out.println("Invalid Medicine Id or System Error");
							}
							break;
						case 5:
							List<Order> orders=od.getAllOrder();
							try {
								if(!orders.isEmpty()) {
									for(Order odr:orders) {
										System.out.println(odr);
									}
								}else {
									System.out.println("No Records of Orders");
								}
							}catch(Exception e) {
								System.out.println("No records found");
							}
							break;
						case 6:
							if(uos.updateStatus(sc,od)) {
								System.out.println("Order Status Updates Successfully");
							}else {
								System.out.println("No Such Order or Invalid Input");
							}
							break;
						default:
							System.out.println("Invalid Input");
						}
					}
				}else {
					System.out.println("Incorrect credentials");
				}
				break;
				
			case 2:
				LoginCustomer lc = new LoginCustomer();
				Customer LoggedinCustomer = lc.loginCustomer(sc,cd);
				if(LoggedinCustomer!=null) {
					
					System.out.println("==== User Logged In ====");
					int ch3 = 0;
					
					while(ch3!=5) {
						System.out.println("\n1. View Medicines");
						System.out.println("2. Search Medicine by Name");
						System.out.println("3. Place new Order");
						System.out.println("4. View Order History");
						System.out.println("5. LogOut");
						System.out.println("Enter the valid Input");
						if(sc.hasNextInt()) {
							ch3=sc.nextInt();sc.nextLine();
						}else {
							System.err.println("\nInvalid Input");
							sc.nextLine();
							continue;
						}
						switch(ch3) {
						case 1:
							md.getAllMedCus();
							break;
						case 2:
							Medicine medi=new MedicineByName().getByName(sc,md);
							if(medi != null) {
								System.out.println("\nName: "+medi.getName()+"\tPrice: "+medi.getPrice());
							}else {
								System.out.println("\nNo such Medicine Found");
							}
							break;
						case 3:
							Order CurrOrder=new PlaceOrder().placeOd(cd, md, LoggedinCustomer, sc);
							if(CurrOrder!=null) {
								System.out.println(CurrOrder);
							}else {
								System.out.println("Order Not Placed");
							}
							break;
						case 4:
							List<Order> orders = LoggedinCustomer.getOrders();
							if(!orders.isEmpty()) {
								for(Order cusOrder:orders) {
									System.out.println(cusOrder);
								}
							}else {
								System.out.println("NO Orders Placed");
							}
							break;
						case 5:
							System.out.println("Logging Out. . . ");
							try {
								Thread.sleep(2000);
							}catch(InterruptedException e) {
								e.printStackTrace();
							}
							break;
						default:
							System.err.println("\nInvalid Input");
						
						}
					}
				}
				else {
					System.out.println("Incorrect Email or Password");
				}
				break;
			case 3:
				Customer customer=new InsertCustomer().insertCustomer(sc, cd);
				if(customer!=null) {
					System.out.println("User Added Successfully");
				}else {
					System.out.println("Something Went Wrong;");
				}
				break;
			case 4:
				flag=false;
				break;
			default:
				System.err.println("Incorrect Input");
			}
		}
	}

}
