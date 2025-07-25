package com.infosys.med_store.dao;

import com.infosys.med_store.entity.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class CustomerDao {
	EntityManager em = Persistence.createEntityManagerFactory("Myjpa").createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public Customer saveCustomer(Customer cus) {
		try {
			et.begin();
			em.persist(cus);
			et.commit();
			return cus;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Customer loginCustomer(Customer cus) {
		Query sql = em.createQuery("SELECT c from Customer c where c.email=:email");
		sql.setParameter("email", cus.getEmail());
		Customer c1 = (Customer) sql.getSingleResult();
		if(c1.getPass().equals(c1.getPass())) {
			return c1;
		}else {
			return null;
		}
	}
	public Customer getCustomer(int id) {
		return em.find(Customer.class, id);
	}
	
}
