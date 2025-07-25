package com.infosys.med_store.dao;

import java.util.List;

import com.infosys.med_store.entity.Customer;
import com.infosys.med_store.entity.Order;
import com.infosys.med_store.entity.Order.Status;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class OrderDao {
	EntityManager em = Persistence.createEntityManagerFactory("Myjpa").createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public Order saveOrder(Order order) {
		try {
			et.begin();
			em.persist(order);
			et.commit();
			return order;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Order getOrderByID(int id) {
		Order od = em.find(Order.class, id);
		return od;
	}
	@SuppressWarnings("unchecked")
	public List<Order> getOrderbyCustomer(int id){
		Customer cus = new CustomerDao().getCustomer(id);
		if(cus!=null) {
			Query sql = em.createQuery("Select O from Order O where O.customer= :cus");
			sql.setParameter("cus", cus);
			List<Order> orders = sql.getResultList();
			return orders;
			
		}else {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Order> getAllOrder(){
		
		Query sql = em.createQuery("Select O from Order O");
		List<Order> orders = sql.getResultList();
		return orders;
			
	}
	public Order setOrderStatus(int id, Status stat) {
		Order od = getOrderByID(id);
		if(od!=null) {
			
			od.setStatus(stat);
			et.begin();
			em.merge(od);
			et.commit();
			return od;
			
		}else return null;
	}
	
}
